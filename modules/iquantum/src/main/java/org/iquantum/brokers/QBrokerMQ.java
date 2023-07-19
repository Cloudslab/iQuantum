/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.brokers;

import org.iquantum.backends.quantum.QNodeMQ;
import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.datacenters.QDatacenterCharacteristicsExtended;
import org.iquantum.lists.QNodeMQList;
import org.iquantum.lists.QTaskList;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;

import java.util.*;

public class QBrokerMQ extends SimEntity {

    /** The list of QNodes submitted to be managed by the broker.. */
    protected List<? extends QNodeMQ> qNodeList;

    /** The list of QTask submitted to the broker. */
    protected List<? extends QTask> qtaskList;

    /** The list of submitted QTasks that are waiting to be executed. */
    protected List<? extends QTask> qtaskSubmittedList;

    /** The list of received QTasks. */
    protected List<? extends QTask> qtaskReceivedList;

    /** The number of submitted QTasks. */
    protected int numQTaskSubmitted;

    /** The id list of available quantum datacenters. */
    protected List<Integer> qDatacenterIdList;

    /** The Qdatacenter characteristics map where each key
     * is a datacenter id and each value is its characteristics.. */
    protected Map<Integer, QDatacenterCharacteristicsExtended> qDatacenterCharacteristicsList;
    protected List<Integer> datacenterIdsList;


    public QBrokerMQ(String name) throws Exception {
        super(name);
        numQTaskSubmitted = 0;

        setQNodeList(new ArrayList<QNodeMQ>());
        setQTaskList(new ArrayList<QTask>());
        setQTaskSubmittedList(new ArrayList<QTask>());
        setQTaskReceivedList(new ArrayList<QTask>());

        setQDatacenterIdList(new ArrayList<Integer>());
        setQDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristicsExtended>());
    }

    public void submitQTaskList(List<? extends QTask> qtaskList) {
        getQTaskList().addAll(qtaskList);
    }

    public void bindCloudletToQNode(int qtaskId, int qNodeId) {
        QTaskList.getById(getQTaskList(), qtaskId).setQNodeId(qNodeId);
    }


    /////// GETTERS AND SETTERS ///////

    protected <T extends QNodeMQ> void setQNodeList(List<T> qNodeList) {
        this.qNodeList = qNodeList;
    }

    public <T extends QNodeMQ> List<T> getQNodeList() {
        return (List<T>) qNodeList;
    }

    protected <T extends QTask> void setQTaskList(List<T> qtaskList) {
        this.qtaskList = qtaskList;
    }

    public <T extends QTask> List<T> getQTaskList() {
        return (List<T>) qtaskList;
    }

    protected <T extends QTask> void setQTaskSubmittedList(List<T> qtaskSubmittedList) {
        this.qtaskSubmittedList = qtaskSubmittedList;
    }

    public <T extends QTask> List<T> getQTaskSubmittedList() {
        return (List<T>) qtaskSubmittedList;
    }

    protected <T extends QTask> void setQTaskReceivedList(List<T> qtaskReceivedList) {
        this.qtaskReceivedList = qtaskReceivedList;
    }

    public <T extends QTask> List<T> getQTaskReceivedList() {
        return (List<T>) qtaskReceivedList;
    }

    protected void setQDatacenterIdList(List<Integer> qDatacenterIdList) {
        this.qDatacenterIdList = qDatacenterIdList;
    }

    public List<Integer> getQDatacenterIdList() {
        return qDatacenterIdList;
    }

    protected void setQDatacenterCharacteristicsList(Map<Integer, QDatacenterCharacteristicsExtended> qDatacenterCharacteristicsList) {
        this.qDatacenterCharacteristicsList = qDatacenterCharacteristicsList;
    }

    public Map<Integer, QDatacenterCharacteristicsExtended> getQDatacenterCharacteristicsList() {
        return qDatacenterCharacteristicsList;
    }

    /////// END GETTERS AND SETTERS ///////


    /////// Overriding SimEntity methods ///////

    @Override
    public void startEntity() {
        Log.printConcatLine(getName(), " is starting...");
        schedule(getId(), 0, iQuantumTags.QRESOURCE_CHARACTERISTICS_REQUEST);
    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            // Resource characteristics request
            case iQuantumTags.QRESOURCE_CHARACTERISTICS_REQUEST:
                processQResourceCharacteristicsRequest(ev);
                break;

            // Resource characteristics request, but not create VM like classical datacenter
            case iQuantumTags.QRESOURCE_CHARACTERISTICS:
                processQDatacenterCharacteristics(ev);
                break;
            // QTask submit
            case iQuantumTags.QTASK_SUBMIT_READY:
                processQTaskSubmit(ev);
                break;
            // QTask return event
            case iQuantumTags.QTASK_RETURN:
                processQTaskReturn(ev);
                break;
            // if the simulation finishes
            case iQuantumTags.END_OF_SIMULATION:
                shutdownEntity();
                break;
            // other unknown tags are processed by this method
            default:
                processOtherEvent(ev);
                break;
        }

    }

    protected void processQResourceCharacteristicsRequest(SimEvent ev) {
        setDatacenterIdsList(iQuantum.getQuantumResourceList());
        setDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristicsExtended>());

        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Quantum Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.QRESOURCE_CHARACTERISTICS, getId());
        }
    }

    protected List<Integer> getDatacenterIdsList() {
        return datacenterIdsList;
    }

    protected void setDatacenterCharacteristicsList(
            Map<Integer, QDatacenterCharacteristicsExtended> datacenterCharacteristicsList) {
        this.qDatacenterCharacteristicsList = datacenterCharacteristicsList;
    }

    protected void setDatacenterIdsList(List<Integer> datacenterIdsList) {
        this.datacenterIdsList = datacenterIdsList;
    }

    /**
     * Process QTask Submit and Scheduling
     * @param ev
     */
    private void processQTaskSubmit(SimEvent ev) {
        int[] data = (int[]) ev.getData();
        int qDatacenter = data[0];
        int qNodeId = 0;
        List<QTask> submittedQTasks = new ArrayList<QTask>();
        Log.printConcatLine(iQuantum.clock(), ": ", getName(), " : Started scheduling all QTasks to QDatacenter #", qDatacenter);

        List<? extends QNodeMQ> qNodeList = getQDatacenterCharacteristicsList().get(qDatacenter).getQNodeList();
        setQNodeList(qNodeList);

        for (QTask QTask : getQTaskList()) {
            QNodeMQ qNode;
            if (QTask.getQNodeId() == -1) {
                // Submit qtask to a the first available QNode
                // TODO: Implement a better (default) scheduling algorithm
                qNode = getQNodeList().get(qNodeId);
            } else {
                // Submit qtask to a specific QNode
                qNode = QNodeMQList.getById(getQNodeList(), QTask.getQNodeId());
                if (qNode == null) {
                    if(!Log.isDisabled()) {
                        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Postponing execution of QTask ", QTask.getQTaskId(),
                                ": QNode is not available");
                    }
                    continue;
                }
            }
            // TODO: Check the constraints
            /** QNode must have enough resources to execute the QTask
             * number of qubits of QNode >= number of qubits of QTask
             */
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Checking if QNode #", qNode.getId(), " has enough qubits/gates to execute QTask",
                        QTask.getQTaskId());
            }
            if(verifyConstraints(qNode, QTask, submittedQTasks)) {
                if (!Log.isDisabled()) {
                    Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Sending QTask ",
                            QTask.getQTaskId(), " to QNode #", qNode.getId());
                }
                sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QTASK_SUBMIT, QTask);
                numQTaskSubmitted++;
                submittedQTasks.add(QTask);
            }
        }
        getQTaskList().removeAll(submittedQTasks);
    }

    private boolean verifyConstraints(QNodeMQ qNode, QTask QTask, List<QTask> submittedQTasks){
        if(qNode.getNumQubits() < QTask.getNumQubits()) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of QTask #", QTask.getQTaskId(),
                        ": QNode #", qNode.getId(), " does not have enough qubits (", qNode.getNumQubits(), " < ", QTask.getNumQubits(), ")");
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QTASK_FAILED_QUBIT, QTask);
            numQTaskSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else if (!isSubset(QTask.getGateSet(),qNode.getGateSets())) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of QTask #", QTask.getQTaskId(),
                        ": QNode #", qNode.getId(), " does not support all gates of qtask");
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QTASK_FAILED_GATES, QTask);
            numQTaskSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else if (qNode.getQTaskScheduler().qtaskMapping(QTask, qNode) == null) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of QTask #", QTask.getQTaskId(),
                        ": Cannot map qubit topology of qtask to the QNode #", qNode.getId());
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QTASK_FAILED_QUBIT_MAP, QTask);
            numQTaskSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else {
            Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": QNode #", qNode.getId(), " has enough qubits and sufficient gates to execute QTask",
                    QTask.getQTaskId());
            Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Mapping qtask " + QTask.getQTaskId() + " to QNode #", qNode.getId(), " successfully: ", qNode.getQTaskScheduler().qtaskMapping(QTask, qNode));
            return true;
        }
    }
    public boolean isSubset(List<String> checkList, Set<String> gateSets) {
        return gateSets.containsAll(checkList);
    }
//    private static boolean isSubset(List<String> firstList, List<String> secondList) {
//        for (String element : firstList) {
//            if (!secondList.contains(element)) {
//                return false; // element is not present in secondList, so firstList is not a subset of secondList
//            }
//        }
//        return true; // all elements of firstList are present in secondList, so firstList is a subset of secondList
//    }

    private void processOtherEvent(SimEvent ev) {
        if (ev == null) {
            Log.printConcatLine(getName(), ".processOtherEvent(): ", "Error - an event is null.");
            return;
        }

        Log.printConcatLine(getName(), ".processOtherEvent(): Error - event unknown by this DatacenterBroker.");
    }

    private void processQDatacenterCharacteristics(SimEvent ev) {
        QDatacenterCharacteristicsExtended characteristics = (QDatacenterCharacteristicsExtended) ev.getData();
        getQDatacenterCharacteristicsList().put(characteristics.getId(), characteristics);
        getQDatacenterIdList().add(characteristics.getId());
        // Do not create VMs like classical DatacenterBroker

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.QTASK_SUBMIT_READY, getId());
        }
    }

    private void processQTaskReturn(SimEvent ev) {
        QTask QTask = (QTask) ev.getData();
        getQTaskReceivedList().add(QTask);
        Log.printConcatLine(iQuantum.clock(), ": ",getName(), ": QTask ", QTask.getQTaskId(), " result received");
        numQTaskSubmitted--;
        if (getQTaskList().size() == 0 && numQTaskSubmitted == 0) { // all QTasks executed
            Log.printConcatLine(iQuantum.clock(), ": ",getName(), ": All QTasks executed. Finishing...");
        }
    }

    protected void finishExecution() {
            sendNow(getId(), iQuantumTags.END_OF_SIMULATION);
    }

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }
}

