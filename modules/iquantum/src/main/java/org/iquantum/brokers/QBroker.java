/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.brokers;

import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;
import org.iquantum.core.*;
import org.iquantum.core.iQuantumTags;
import org.iquantum.lists.QNodeList;
import org.iquantum.lists.QuletList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QBroker extends SimEntity {

    /** The list of QNodes submitted to be managed by the broker.. */
    protected List<? extends QNode> qNodeList;

    /** The list of Qulet submitted to the broker. */
    protected List<? extends QTask> quletList;

    /** The list of submitted Qulets that are waiting to be executed. */
    protected List<? extends QTask> quletSubmittedList;

    /** The list of received Qulets. */
    protected List<? extends QTask> quletReceivedList;

    /** The number of submitted Qulets. */
    protected int numQuletSubmitted;

    /** The id list of available quantum datacenters. */
    protected List<Integer> qDatacenterIdList;

    /** The Qdatacenter characteristics map where each key
     * is a datacenter id and each value is its characteristics.. */
    protected Map<Integer, QDatacenterCharacteristics> qDatacenterCharacteristicsList;
    protected List<Integer> datacenterIdsList;


    public QBroker(String name) throws Exception {
        super(name);
        numQuletSubmitted = 0;

        setQNodeList(new ArrayList<QNode>());
        setQuletList(new ArrayList<QTask>());
        setQuletSubmittedList(new ArrayList<QTask>());
        setQuletReceivedList(new ArrayList<QTask>());

        setQDatacenterIdList(new ArrayList<Integer>());
        setQDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristics>());
    }

    public void submitQuletList(List<? extends QTask> quletList) {
        getQuletList().addAll(quletList);
    }

    public void bindCloudletToQNode(int quletId, int qNodeId) {
        QuletList.getById(getQuletList(), quletId).setQNodeId(qNodeId);
    }


    /////// GETTERS AND SETTERS ///////

    protected <T extends QNode> void setQNodeList(List<T> qNodeList) {
        this.qNodeList = qNodeList;
    }

    public <T extends QNode> List<T> getQNodeList() {
        return (List<T>) qNodeList;
    }

    protected <T extends QTask> void setQuletList(List<T> quletList) {
        this.quletList = quletList;
    }

    public <T extends QTask> List<T> getQuletList() {
        return (List<T>) quletList;
    }

    protected <T extends QTask> void setQuletSubmittedList(List<T> quletSubmittedList) {
        this.quletSubmittedList = quletSubmittedList;
    }

    public <T extends QTask> List<T> getQuletSubmittedList() {
        return (List<T>) quletSubmittedList;
    }

    protected <T extends QTask> void setQuletReceivedList(List<T> quletReceivedList) {
        this.quletReceivedList = quletReceivedList;
    }

    public <T extends QTask> List<T> getQuletReceivedList() {
        return (List<T>) quletReceivedList;
    }

    protected void setQDatacenterIdList(List<Integer> qDatacenterIdList) {
        this.qDatacenterIdList = qDatacenterIdList;
    }

    public List<Integer> getQDatacenterIdList() {
        return qDatacenterIdList;
    }

    protected void setQDatacenterCharacteristicsList(Map<Integer, QDatacenterCharacteristics> qDatacenterCharacteristicsList) {
        this.qDatacenterCharacteristicsList = qDatacenterCharacteristicsList;
    }

    public Map<Integer, QDatacenterCharacteristics> getQDatacenterCharacteristicsList() {
        return qDatacenterCharacteristicsList;
    }

    /////// END GETTERS AND SETTERS ///////


    /////// Overriding SimEntity methods ///////

    @Override
    public void startEntity() {
        Log.printConcatLine(getName(), " is starting...");
        schedule(getId(), 0, iQuantumTags.RESOURCE_CHARACTERISTICS_REQUEST);
    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            // Resource characteristics request
            case iQuantumTags.RESOURCE_CHARACTERISTICS_REQUEST:
                processQResourceCharacteristicsRequest(ev);
                break;

            // Resource characteristics request, but not create VM like classical datacenter
            case iQuantumTags.RESOURCE_CHARACTERISTICS:
                processQDatacenterCharacteristics(ev);
                break;
            // Qulet submit
            case iQuantumTags.QULET_SUBMIT_READY:
                processQuletSubmit(ev);
                break;
            // Qulet return event
            case iQuantumTags.QULET_RETURN:
                processQuletReturn(ev);
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

    private void processQResourceCharacteristicsRequest(SimEvent ev) {
        setDatacenterIdsList(iQuantum.getCloudResourceList());
        setDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristics>());

        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cloud Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.RESOURCE_CHARACTERISTICS, getId());
        }
    }

    protected List<Integer> getDatacenterIdsList() {
        return datacenterIdsList;
    }

    protected void setDatacenterCharacteristicsList(
            Map<Integer, QDatacenterCharacteristics> datacenterCharacteristicsList) {
        this.qDatacenterCharacteristicsList = datacenterCharacteristicsList;
    }

    protected void setDatacenterIdsList(List<Integer> datacenterIdsList) {
        this.datacenterIdsList = datacenterIdsList;
    }

    /**
     * Process Qulet Submit and Scheduling
     * @param ev
     */
    private void processQuletSubmit(SimEvent ev) {
        int[] data = (int[]) ev.getData();
        int qDatacenter = data[0];
        int qNodeId = 0;
        List<QTask> submittedQTasks = new ArrayList<QTask>();
        Log.printConcatLine(iQuantum.clock(), ": ", getName(), " : Started scheduling all Qulets to QDatacenter #", qDatacenter);

        List<? extends QNode> qNodeList = getQDatacenterCharacteristicsList().get(qDatacenter).getQNodeList();
        setQNodeList(qNodeList);

        for (QTask QTask : getQuletList()) {
            QNode qNode;
            if (QTask.getQNodeId() == -1) {
                // Submit qulet to a the first available QNode
                // TODO: Implement a better (default) scheduling algorithm
                qNode = getQNodeList().get(qNodeId);
            } else {
                // Submit qulet to a specific QNode
                qNode = QNodeList.getById(getQNodeList(), QTask.getQNodeId());
                if (qNode == null) {
                    if(!Log.isDisabled()) {
                        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Postponing execution of Qulet ", QTask.getQuletId(),
                                ": QNode is not available");
                    }
                    continue;
                }
            }
            // TODO: Check the constraints
            /** QNode must have enough resources to execute the Qulet
             * number of qubits of QNode >= number of qubits of Qulet
             */
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Checking if QNode #", qNode.getId(), " has enough qubits/gates to execute Qulet",
                        QTask.getQuletId());
            }
            if(verifyConstraints(qNode, QTask, submittedQTasks)) {
                if (!Log.isDisabled()) {
                    Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Sending Qulet ",
                            QTask.getQuletId(), " to QNode #", qNode.getId());
                }
                sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QULET_SUBMIT, QTask);
                numQuletSubmitted++;
                submittedQTasks.add(QTask);
            }
        }
        getQuletList().removeAll(submittedQTasks);
    }

    private boolean verifyConstraints(QNode qNode, QTask QTask, List<QTask> submittedQTasks){
        if(qNode.getNumQubits() < QTask.getNumQubits()) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of Qulet #", QTask.getQuletId(),
                        ": QNode #", qNode.getId(), " does not have enough qubits (", qNode.getNumQubits(), " < ", QTask.getNumQubits(), ")");
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QULET_FAILED_QUBIT, QTask);
            numQuletSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else if (!isSubset(QTask.getGateSet(),qNode.getGateSets())) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of Qulet #", QTask.getQuletId(),
                        ": QNode #", qNode.getId(), " does not support all gates of qulet");
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QULET_FAILED_GATES, QTask);
            numQuletSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else if (qNode.getQuletScheduler().quletMapping(QTask, qNode) == null) {
            if (!Log.isDisabled()) {
                Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cancel the execution of Qulet #", QTask.getQuletId(),
                        ": Cannot map qubit topology of qulet to the QNode #", qNode.getId());
            }
            sendNow(qNode.getQDatacenter().getId(), iQuantumTags.QULET_FAILED_QUBIT_MAP, QTask);
            numQuletSubmitted++;
            submittedQTasks.add(QTask);
            return false;
        } else {
            Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": QNode #", qNode.getId(), " has enough qubits and sufficient gates to execute Qulet",
                    QTask.getQuletId());
            Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Mapping qulet " + QTask.getQuletId() + " to QNode #", qNode.getId(), " successfully: ", qNode.getQuletScheduler().quletMapping(QTask, qNode));
            return true;
        }
    }

    private static boolean isSubset(List<String> firstList, List<String> secondList) {
        for (String element : firstList) {
            if (!secondList.contains(element)) {
                return false; // element is not present in secondList, so firstList is not a subset of secondList
            }
        }
        return true; // all elements of firstList are present in secondList, so firstList is a subset of secondList
    }

    private void processOtherEvent(SimEvent ev) {
        if (ev == null) {
            Log.printConcatLine(getName(), ".processOtherEvent(): ", "Error - an event is null.");
            return;
        }

        Log.printConcatLine(getName(), ".processOtherEvent(): Error - event unknown by this DatacenterBroker.");
    }

    private void processQDatacenterCharacteristics(SimEvent ev) {
        QDatacenterCharacteristics characteristics = (QDatacenterCharacteristics) ev.getData();
        getQDatacenterCharacteristicsList().put(characteristics.getId(), characteristics);
        getQDatacenterIdList().add(characteristics.getId());
        // Do not create VMs like classical DatacenterBroker

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.QULET_SUBMIT_READY, getId());
        }
    }

    private void processQuletReturn(SimEvent ev) {
        QTask QTask = (QTask) ev.getData();
        getQuletReceivedList().add(QTask);
        Log.printConcatLine(iQuantum.clock(), ": ",getName(), ": Qulet ", QTask.getQuletId(), " result received");
        numQuletSubmitted--;
        if (getQuletList().size() == 0 && numQuletSubmitted == 0) { // all Qulets executed
            Log.printConcatLine(iQuantum.clock(), ": ",getName(), ": All Qulets executed. Finishing...");
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

