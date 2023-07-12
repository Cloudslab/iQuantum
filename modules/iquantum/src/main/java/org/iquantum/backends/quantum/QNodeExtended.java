package org.iquantum.backends.quantum;

import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterExtended;
import org.iquantum.policies.qtasks.QTaskScheduler;
import org.iquantum.policies.qtasks.QTaskSchedulerFCFSMultiQPU;

import java.util.List;

public class QNodeExtended {

    private int id;
    private List<? extends QPU> qpuList;

    private QTaskSchedulerFCFSMultiQPU qTaskScheduler;

    private QDatacenterExtended qDatacenter;

    public QNodeExtended(int id, List<? extends QPU> qpuList, QTaskSchedulerFCFSMultiQPU qTaskScheduler) {
        this.id = id;
        this.qpuList = qpuList;
        this.qTaskScheduler = qTaskScheduler;
    }

    public int getId() {
        return id;
    }

    public List<? extends QPU> getQPUList() {
        return qpuList;
    }

    public int getNumberOfQPU() {
        return qpuList.size();
    }

    public int getNumQubits() {
        return getQPUList().get(0).getNumQubits();
    }

    public int getTotalClops() {
        int clops = 0;
        for (QPU qpu : qpuList) {
            clops += qpu.getClops();
        }
        return clops;
    }

    public List<String> getGateSets() {
        return getQPUList().get(0).getGateSets();
    }

    public QTaskSchedulerFCFSMultiQPU getQTaskScheduler() {
        return qTaskScheduler;
    }

    public void setQDatacenter(QDatacenterExtended qDatacenter) {
        this.qDatacenter = qDatacenter;
    }

    public QDatacenterExtended getQDatacenter() {
        return qDatacenter;
    }

    public double updateQTaskProcessing(double currentTime) {
        double smallerTime = Double.MAX_VALUE;
        double time = getQTaskScheduler().updateQNodeProcessing(currentTime, getTotalClops());
        if (time > 0.0 && time < smallerTime) {
            smallerTime = time;
        }
        return smallerTime;
    }
}
