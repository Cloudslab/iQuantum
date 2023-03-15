package org.iquantum;

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

