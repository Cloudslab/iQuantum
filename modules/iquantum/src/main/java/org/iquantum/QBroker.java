package org.iquantum;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.*;
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
    protected List<? extends Qulet> quletList;

    /** The list of submitted Qulets that are waiting to be executed. */
    protected List<? extends Qulet> quletSubmittedList;

    /** The list of received Qulets. */
    protected List<? extends Qulet> quletReceivedList;

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
        setQuletList(new ArrayList<Qulet>());
        setQuletSubmittedList(new ArrayList<Qulet>());
        setQuletReceivedList(new ArrayList<Qulet>());

        setQDatacenterIdList(new ArrayList<Integer>());
        setQDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristics>());
    }

    public void submitQuletList(List<? extends Qulet> quletList) {
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

    protected <T extends Qulet> void setQuletList(List<T> quletList) {
        this.quletList = quletList;
    }

    public <T extends Qulet> List<T> getQuletList() {
        return (List<T>) quletList;
    }

    protected <T extends Qulet> void setQuletSubmittedList(List<T> quletSubmittedList) {
        this.quletSubmittedList = quletSubmittedList;
    }

    public <T extends Qulet> List<T> getQuletSubmittedList() {
        return (List<T>) quletSubmittedList;
    }

    protected <T extends Qulet> void setQuletReceivedList(List<T> quletReceivedList) {
        this.quletReceivedList = quletReceivedList;
    }

    public <T extends Qulet> List<T> getQuletReceivedList() {
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
        schedule(getId(), 0, CloudSimTags.RESOURCE_CHARACTERISTICS_REQUEST);
    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            // Resource characteristics request
            case CloudSimTags.RESOURCE_CHARACTERISTICS_REQUEST:
                processQResourceCharacteristicsRequest(ev);
                break;

            // Resource characteristics request, but not create VM like classical datacenter
            case CloudSimTags.RESOURCE_CHARACTERISTICS:
                processQDatacenterCharacteristics(ev);
                break;
            // Qulet submit
            case CloudSimTags.QULET_SUBMIT_READY:
                processQuletSubmit(ev);
                break;
            // Qulet return event
            case CloudSimTags.QULET_RETURN:
                processQuletReturn(ev);
                break;
            // if the simulation finishes
            case CloudSimTags.END_OF_SIMULATION:
                shutdownEntity();
                break;
            // other unknown tags are processed by this method
            default:
                processOtherEvent(ev);
                break;
        }

    }

    private void processQResourceCharacteristicsRequest(SimEvent ev) {
        setDatacenterIdsList(CloudSim.getCloudResourceList());
        setDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristics>());

        Log.printConcatLine(CloudSim.clock(), ": ", getName(), ": Cloud Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, CloudSimTags.RESOURCE_CHARACTERISTICS, getId());
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
     * Key: Process Qulet Submit and Scheduling
     * @param ev
     */
    private void processQuletSubmit(SimEvent ev) {
        int[] data = (int[]) ev.getData();
        int qDatacenter = data[0];
        int qNodeId = 0;
        List<Qulet> succesfullySubmittedQulets = new ArrayList<Qulet>();
        Log.printConcatLine(CloudSim.clock(), ": ", getName(), " : Started scheduling all Qulets to QDatacenter #", qDatacenter);

        List<? extends QNode> qNodeList = getQDatacenterCharacteristicsList().get(qDatacenter).getQNodeList();
        setQNodeList(qNodeList);

        for (Qulet qulet : getQuletList()) {
            QNode qNode;
            if (qulet.getQNodeId() == -1) {
                qNode = getQNodeList().get(qNodeId);
            } else {
                // Submit qulet to a specific QNode
                qNode = QNodeList.getById(getQNodeList(), qulet.getQNodeId());
                if (qNode == null) {
                    if(!Log.isDisabled()) {
                        Log.printConcatLine(CloudSim.clock(), ": ", getName(), ": Postponing execution of Qulet ", qulet.getQuletId(),
                                ": bount QNode not available");
                    }
                    continue;
                }
            }

            if (!Log.isDisabled()) {
                Log.printConcatLine(CloudSim.clock(), ": ", getName(), ": Sending Qulet ",
                        qulet.getQuletId(), " to QNode #", qNode.getId());
            }

            sendNow(qNode.getQDatacenter().getId(), CloudSimTags.QULET_SUBMIT, qulet);
            numQuletSubmitted++;
            succesfullySubmittedQulets.add(qulet);
        }
        getQuletList().removeAll(succesfullySubmittedQulets);
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
            sendNow(datacenterId, CloudSimTags.QULET_SUBMIT_READY, getId());
        }
    }

    private void processQuletReturn(SimEvent ev) {
        Qulet qulet = (Qulet) ev.getData();
        getQuletReceivedList().add(qulet);
        Log.printConcatLine(CloudSim.clock(), ": ",getName(), ": Qulet ", qulet.getQuletId(), " result received");
        numQuletSubmitted--;
        if (getQuletList().size() == 0 && numQuletSubmitted == 0) { // all Qulets executed
            Log.printConcatLine(CloudSim.clock(), ": ",getName(), ": All Qulets executed. Finishing...");
//            finishExecution();
        }
    }

    protected void finishExecution() {
            sendNow(getId(), CloudSimTags.END_OF_SIMULATION);
    }

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }
}

