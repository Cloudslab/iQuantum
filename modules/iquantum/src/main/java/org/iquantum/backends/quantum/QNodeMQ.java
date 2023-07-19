package org.iquantum.backends.quantum;

import org.iquantum.datacenters.QDatacenterExtended;
import org.iquantum.lists.QPUList;
import org.iquantum.policies.qtasks.QTaskSchedulerFCFSMQ;

import java.util.Set;

/**
 * Quantum Node with multiple QPU support. All information of QPU capabilities are modeled in the QPU class.
 * This class will replace the original QNode class in the future.
 */
public class QNodeMQ {

    private int id;
    private QPUList qpuList;

    private QTaskSchedulerFCFSMQ qTaskScheduler;

    private QDatacenterExtended qDatacenter;

    public QNodeMQ(int id, QPUList qpuList, QTaskSchedulerFCFSMQ qTaskScheduler) {
        this.id = id;
        this.qpuList = qpuList;
        this.qTaskScheduler = qTaskScheduler;
    }

    public int getId() {
        return id;
    }

    public QPUList getQPUList() {
        return qpuList;
    }

    public int getNumberOfQPU() {
        return qpuList.getNumberOfQPU();
    }

    public int getNumQubits() {
        return qpuList.getTotalNumQubits();
    }

    public double getTotalClops() {
        return qpuList.getTotalClops();
    }

    public Set<String> getGateSets() {
        return qpuList.getAllGateSets();
    }

    public QTaskSchedulerFCFSMQ getQTaskScheduler() {
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
