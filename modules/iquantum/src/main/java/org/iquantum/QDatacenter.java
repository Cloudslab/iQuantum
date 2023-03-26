package org.iquantum;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.core.CloudSimTags;

import java.util.List;

public class QDatacenter extends SimEntity {

    /** The characteristics. */
    private QDatacenterCharacteristics characteristics;

    /** The last time qulets was processed in the Quantum Datacenter. */
    private double lastProcessTime;

    private String regionalCISName;


    public QDatacenter(String name, QDatacenterCharacteristics characteristics) {
        super(name);
        setLastProcessTime(0.0);
        setCharacteristics(characteristics);

        for (QNode qNode : getCharacteristics().getQNodeList()) {
            qNode.setQDatacenter(this);
        }

        /** Store id of the Quantum Datacenter in the characteristics. */
        getCharacteristics().setId(super.getId());


    }

    protected void setCharacteristics(QDatacenterCharacteristics characteristics) {
        this.characteristics = characteristics;
    }

    public QDatacenterCharacteristics getCharacteristics() {
        return characteristics;
    }


    public void setLastProcessTime(double lastProcessTime) {
        this.lastProcessTime = lastProcessTime;
    }

    public double getLastProcessTime() {
        return lastProcessTime;
    }

    public void setRegionalCISName(String regionalCISName) {
        this.regionalCISName = regionalCISName;
    }

    public String getRegionalCISName() {
        return regionalCISName;
    }


    @Override
    public void startEntity() {
        Log.printConcatLine(getName(), " is starting...");
        // This resource should register to regional CIS
        // If it is not specified, then it will be registered to the default CIS entity
        int cisId = CloudSim.getEntityId(regionalCISName);
        if (cisId == -1) {
            cisId = CloudSim.getCloudInfoServiceEntityId();
        }

        // Send the registration request to the CIS to register the QDatacenter
        sendNow(cisId, CloudSimTags.REGISTER_RESOURCE, getId());
        registerOtherEntity();
    }

    private void registerOtherEntity() {
    }

    @Override
    public void processEvent(SimEvent ev) {
        int srcId = -1;

        switch (ev.getTag()) {
            case CloudSimTags.RESOURCE_CHARACTERISTICS:
                srcId = ((Integer) ev.getData()).intValue();
                sendNow(srcId, ev.getTag(), getCharacteristics());
                break;

            case CloudSimTags.QULET_SUBMIT_READY:
                int[] data = new int[2];
                data[0] = getId();
                srcId = ((Integer) ev.getData()).intValue();
                send(srcId, CloudSim.getMinTimeBetweenEvents(), ev.getTag(), data);
                break;

            case CloudSimTags.QULET_SUBMIT:
                processQuletSubmit(ev,false);
                break;

            case CloudSimTags.UPDATE_QULET_PROCESSING:
                updateQuletProcessing();
                checkQuletCompletion();
                break;

            // other unknown tags are processed by this method
            default:
                processOtherEvent(ev);
                break;
        }

    }

    private void checkQuletCompletion() {
        List<? extends QNode> qNodeList = getCharacteristics().getQNodeList();
        for (QNode qNode : qNodeList) {
            QuletScheduler scheduler = qNode.getQuletScheduler();
            while (scheduler.isFinishedQulets()) {
                Qulet qulet = scheduler.getNextFinishedQulet();
                if (qulet != null) {
                    sendNow(qulet.getBrokerId(), CloudSimTags.QULET_RETURN, qulet);
                }
            }
        }
    }

    private void processOtherEvent(SimEvent ev) {
        if (ev == null) {
            Log.printConcatLine(getName(), ".processOtherEvent(): Error - an event is null.");
        }
    }

    protected void processQuletSubmit(SimEvent ev, boolean ack) {
        updateQuletProcessing();
        try {
            Qulet qulet = (Qulet) ev.getData();
            int qBrokerId = qulet.getBrokerId();

            // checks whether this Cloudlet has finished or not
            if (qulet.isFinished()) {
                String name = CloudSim.getEntityName(qulet.getBrokerId());
                Log.printConcatLine(getName(), ": Warning - Cloudlet #", qulet.getQuletId(), " owned by ", name,
                        " is already completed/finished.");
                Log.printLine("Therefore, it is not being executed again");
                Log.printLine();

                // NOTE: If a Cloudlet has finished, then it won't be processed.
                // So, if ack is required, this method sends back a result.
                // If ack is not required, this method don't send back a result.
                // Hence, this might cause CloudSim to be hanged since waiting
                // for this Cloudlet back.
                sendNow(qulet.getBrokerId(), CloudSimTags.QULET_RETURN, qulet);
                return;
            }

            qulet.setResourceParameter(getId(),getCharacteristics().getCostPerSecond());

            // Process the Qulet
            int qNodeId = qulet.getQNodeId();


            // Get the QNode
            QNode qNode = getCharacteristics().getQNodeList().get(qNodeId);
            // Temporary ignore the transfer time (will be considered in the future)
            double transferTime = 0.0;

            QuletScheduler scheduler = qNode.getQuletScheduler();
            double estimatedCompletionTime = scheduler.quletSubmit(qulet, transferTime);

            if(estimatedCompletionTime > 0.0 && !Double.isInfinite(estimatedCompletionTime)) {
                estimatedCompletionTime += transferTime;
                send(getId(), estimatedCompletionTime, CloudSimTags.UPDATE_QULET_PROCESSING);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        checkQuletCompletion();

    }

    private void updateQuletProcessing() {
        // if some time passed since last processing
        // R: for term is to allow loop at simulation start. Otherwise, one initial
        // simulation step is skipped and schedulers are not properly initialized
        if (CloudSim.clock() < 0.111 || CloudSim.clock() >= getLastProcessTime() + CloudSim.getMinTimeBetweenEvents()) {
            List<? extends QNode> list = getCharacteristics().getQNodeList();
            double smallerTime = Double.MAX_VALUE;
            // for each host...
            for (int i = 0; i < list.size(); i++) {
                QNode qNode = list.get(i);
                // inform VMs to update processing
                double time = qNode.updateQuletProcessing(CloudSim.clock());
                // what time do we expect that the next cloudlet will finish?
                if (time < smallerTime) {
                    smallerTime = time;
                }
            }
            // gurantees a minimal interval before scheduling the event
            if (smallerTime < CloudSim.clock() + CloudSim.getMinTimeBetweenEvents() + 0.01) {
                smallerTime = CloudSim.clock() + CloudSim.getMinTimeBetweenEvents() + 0.01;
            }
            if (smallerTime != Double.MAX_VALUE) {
                schedule(getId(), (smallerTime - CloudSim.clock()), CloudSimTags.UPDATE_QULET_PROCESSING);
            }
            setLastProcessTime(CloudSim.clock());
        }
    }

    @Override
    public void shutdownEntity() {

    }

}

