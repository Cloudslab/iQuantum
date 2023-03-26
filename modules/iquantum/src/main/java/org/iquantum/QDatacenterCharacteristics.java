package org.iquantum;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.List;

public class QDatacenterCharacteristics {
    /** The datacenter id -- setup when datacenter is created. */
    private int id;

    /** The hosts owned by the datacenter. */
    private List<? extends QNode> qNodeList;

    /** The time zone, defined as the difference from GMT. */
    private double timeZone;

    private double costPerSecond;
    public QDatacenterCharacteristics(List<? extends QNode> qNodeList, double timeZone, double costPerSecond) {
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
        return CloudSim.getEntityName(getId());
    }

    /////// GETTERS AND SETTERS ///////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<? extends QNode> getQNodeList() {
        return qNodeList;
    }

    public void setQNodeList(List<? extends QNode> qNodeList) {
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
