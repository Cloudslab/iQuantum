package org.iquantum;

import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.core.CloudSimTags;

import java.util.List;

public class QDatacenter extends SimEntity {

    private String name;
    private List<QNode> qNodeList;

    /** The last time qulets was processed in the Quantum Datacenter. */
    private double lastProcessTime;

    public QDatacenter(String name, List<QNode> qNodeList) {
        super(name);
        setLastProcessTime(0.0);
        setqNodeList(qNodeList);
    }

    public String getName() {
        return name;
    }

    @Override
    public void startEntity() {

    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            case CloudSimTags.QULET_SUBMIT:
                processQuletSubmit(ev,false);
                break;
        }

    }

    protected void processQuletSubmit(SimEvent ev, boolean ack) {
        try {
            Qulet qulet = (Qulet) ev.getData();
            // Process the Qulet
            int brokerId = qulet.getBrokerId();

            // Temporary ignore the transfer time (will be considered in the future)
            double transferTime = 0.0;
            double estimatedCompletionTime = qulet.getEstimatedCompletionTime();
            if(estimatedCompletionTime > 0.0 && !Double.isInfinite(estimatedCompletionTime)) {
                estimatedCompletionTime += transferTime;
                send(getId(), estimatedCompletionTime, CloudSimTags.QULET_SUBMIT);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void shutdownEntity() {

    }

    public void setLastProcessTime(double lastProcessTime) {
        this.lastProcessTime = lastProcessTime;
    }

    public void setqNodeList(List<QNode> qNodeList) {
        this.qNodeList = qNodeList;
    }
}

