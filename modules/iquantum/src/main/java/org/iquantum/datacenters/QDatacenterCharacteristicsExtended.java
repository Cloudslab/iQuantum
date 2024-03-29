/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.datacenters;

import org.iquantum.backends.quantum.QNodeMQ;
import org.iquantum.core.iQuantum;

import java.util.List;

public class QDatacenterCharacteristicsExtended {
    /** The datacenter id -- setup when datacenter is created. */
    private int id;

    /** The hosts owned by the datacenter. */
    private List<? extends QNodeMQ> qNodeList;

    /** The time zone, defined as the difference from GMT. */
    private double timeZone;

    private double costPerSecond;
    public QDatacenterCharacteristicsExtended(List<? extends QNodeMQ> qNodeList, double timeZone, double costPerSecond) {
        setId(-1);
        setQNodeList(qNodeList);
        setTimeZone(0.0);
        setCostPerSecond(costPerSecond);

    }

    private void setCostPerSecond(double costPerSecond) {
        this.costPerSecond = costPerSecond;
    }

    public double getCostPerSecond() {
        return costPerSecond;
    }

    public String getResourceName() {
        return iQuantum.getEntityName(getId());
    }

    /////// GETTERS AND SETTERS ///////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<? extends QNodeMQ> getQNodeList() {
        return qNodeList;
    }

    public void setQNodeList(List<? extends QNodeMQ> qNodeList) {
        this.qNodeList = qNodeList;
    }

    public double getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(double timeZone) {
        this.timeZone = timeZone;
    }

    /////// END OF GETTERS AND SETTERS ///////
}
