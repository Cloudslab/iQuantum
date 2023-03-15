package org.iquantum.components;

import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.core.SimEvent;

import java.util.List;

public class QBroker extends SimEntity {
    private QDatacenter quantumDatacenter;
    private int id;

    public QBroker(int id, QDatacenter quantumDatacenter) {
        super("QBroker_" + id);
        this.id = id;
        this.quantumDatacenter = quantumDatacenter;
    }

    public QNode scheduleQulet(Qulet qulet) {
        List<QNode> qNodes = quantumDatacenter.getQNodes();
        QNode selectedQNode = null;
        double minExpectedCompletionTime = Double.MAX_VALUE;

        for (QNode qNode : qNodes) {
            if (qNode.isAvailable(qulet)) {
                double expectedCompletionTime = qNode.getExpectedCompletionTime(qulet);
                if (expectedCompletionTime < minExpectedCompletionTime) {
                    selectedQNode = qNode;
                    minExpectedCompletionTime = expectedCompletionTime;
                }
            }
        }

        if (selectedQNode == null) {
            throw new IllegalStateException("No available QNodes to schedule Qulet");
        }

        selectedQNode.addQulet(qulet);
        return selectedQNode;
    }

    public int getId() {
        return id;
    }

    @Override
    public void startEntity() {

    }

    @Override
    public void processEvent(SimEvent ev) {

    }

    @Override
    public void shutdownEntity() {

    }
}

