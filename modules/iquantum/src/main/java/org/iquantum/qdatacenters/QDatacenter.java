/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.qdatacenters;

import org.iquantum.qulets.Qulet;
import org.iquantum.qnodes.QNode;
import org.iquantum.utils.Log;
import org.iquantum.core.iQuantum;
import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.core.SimEvent;
import org.iquantum.core.iQuantumTags;
import org.iquantum.schedulers.QuletScheduler;

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
        int cisId = iQuantum.getEntityId(regionalCISName);
        if (cisId == -1) {
            cisId = iQuantum.getCloudInfoServiceEntityId();
        }

        // Send the registration request to the CIS to register the QDatacenter
        sendNow(cisId, iQuantumTags.REGISTER_RESOURCE, getId());
        registerOtherEntity();
    }

    private void registerOtherEntity() {
    }

    @Override
    public void processEvent(SimEvent ev) {
        int srcId = -1;

        switch (ev.getTag()) {
            case iQuantumTags.RESOURCE_CHARACTERISTICS:
                srcId = ((Integer) ev.getData()).intValue();
                sendNow(srcId, ev.getTag(), getCharacteristics());
                break;

            case iQuantumTags.QULET_SUBMIT_READY:
                int[] data = new int[2];
                data[0] = getId();
                srcId = ((Integer) ev.getData()).intValue();
                send(srcId, iQuantum.getMinTimeBetweenEvents(), ev.getTag(), data);
                break;

            case iQuantumTags.QULET_SUBMIT:
                processQuletSubmit(ev,false);
                break;

            case iQuantumTags.UPDATE_QULET_PROCESSING:
                updateQuletProcessing();
                checkQuletCompletion();
                break;

            case iQuantumTags.QULET_FAILED_QUBIT:
                try {
                    processQulet(ev, iQuantumTags.QULET_FAILED_QUBIT);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case iQuantumTags.QULET_FAILED_GATES:
                try {
                    processQulet(ev, iQuantumTags.QULET_FAILED_GATES);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case iQuantumTags.QULET_FAILED_QUBIT_MAP:
                try {
                    processQulet(ev, iQuantumTags.QULET_FAILED_QUBIT_MAP);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            // other unknown tags are processed by this method
            default:
                processOtherEvent(ev);
                break;
        }

    }

    private void processQulet(SimEvent ev, int type) throws Exception {
        Qulet qulet = (Qulet) ev.getData();
        switch (type) {
            case iQuantumTags.QULET_FAILED_QUBIT:
                qulet.setQuletStatus(Qulet.FAILED_QUBITS_INSUFFICIENT);
                sendNow(qulet.getBrokerId(), iQuantumTags.QULET_RETURN, qulet);
                break;

            case iQuantumTags.QULET_FAILED_GATES:
                qulet.setQuletStatus(Qulet.FAILED_GATES_INSUFFICIENT);
                sendNow(qulet.getBrokerId(), iQuantumTags.QULET_RETURN, qulet);
                break;

            case iQuantumTags.QULET_FAILED_QUBIT_MAP:
                qulet.setQuletStatus(Qulet.FAILED_QUBIT_MAP);
                sendNow(qulet.getBrokerId(), iQuantumTags.QULET_RETURN, qulet);
                break;

            default:
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
                    sendNow(qulet.getBrokerId(), iQuantumTags.QULET_RETURN, qulet);
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

            // checks whether this qulet has finished or not
            if (qulet.isFinished()) {
                String name = iQuantum.getEntityName(qulet.getBrokerId());
                Log.printConcatLine(getName(), ": Warning - Qulet #", qulet.getQuletId(), " owned by ", name,
                        " is already completed/finished.");
                Log.printLine("Therefore, it is not being executed again");
                Log.printLine();

                // NOTE: If a Qulet has finished, then it won't be processed.
                // So, if ack is required, this method sends back a result.
                // If ack is not required, this method don't send back a result.
                // Hence, this might cause iQuantum to be hanged since waiting
                // for this Qulet back.
                sendNow(qBrokerId, iQuantumTags.QULET_RETURN, qulet);
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
                send(getId(), estimatedCompletionTime, iQuantumTags.UPDATE_QULET_PROCESSING);
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
        if (iQuantum.clock() < 0.111 || iQuantum.clock() >= getLastProcessTime() + iQuantum.getMinTimeBetweenEvents()) {
            List<? extends QNode> list = getCharacteristics().getQNodeList();
            double smallerTime = Double.MAX_VALUE;
            // for each host...
            for (int i = 0; i < list.size(); i++) {
                QNode qNode = list.get(i);
                double time = qNode.updateQuletProcessing(iQuantum.clock());
                // what time do we expect that the next qulet will finish?
                if (time < smallerTime) {
                    smallerTime = time;
                }
            }
            if (smallerTime != Double.MAX_VALUE) {
                schedule(getId(), (smallerTime - iQuantum.clock()), iQuantumTags.UPDATE_QULET_PROCESSING);
            }
            setLastProcessTime(iQuantum.clock());
        }
    }

    @Override
    public void shutdownEntity() {

    }

}

