package org.iquantum.gateways;

import org.iquantum.brokers.CCloudBroker;
import org.iquantum.brokers.CEdgeBroker;
import org.iquantum.brokers.QCloudBroker;
import org.iquantum.brokers.QEdgeBroker;
import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.tasks.CTask;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Edge Gateway
 */
public class EdgeGateway extends Gateway {
    protected String name;
    /** List of all CTask submitted to the cloud gateway. */
    protected List<? extends CTask> cTaskList;

    /** List of all QTask submitted to the cloud gateway. */
    protected List<? extends QTask> qTaskList;

    protected CEdgeBroker cBroker;

    protected QEdgeBroker qBroker;

    /** Linked Cloud Gateway that will be used to offload Tasks. */
    protected CloudGateway cloudGateway;

    public EdgeGateway(String name, CEdgeBroker cBroker, QEdgeBroker qBroker) throws Exception {
        super(name);
        this.cBroker = cBroker;
        this.qBroker = qBroker;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }
    public EdgeGateway(String name, CEdgeBroker cBroker) throws Exception {
        super(name);
        this.name = name;
        this.cBroker = cBroker;
        this.qBroker = null;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }

    public EdgeGateway(String name, QEdgeBroker qBroker) throws Exception {
        super(name);
        this.name = name;
        this.cBroker = null;
        this.qBroker = qBroker;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }

    public EdgeGateway(String name, CEdgeBroker cBroker, QEdgeBroker qBroker, CloudGateway cloudGateway) throws Exception {
        super(name);
        this.cBroker = cBroker;
        this.qBroker = qBroker;
        this.cloudGateway = cloudGateway;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
        qBroker.setEdgeGateway(this);
        qBroker.setCloudGateway(cloudGateway);

    }

    // GETTERS AND SETTERS
    protected <T extends CTask> void setCTaskList(List<T> ctaskList) {
        this.cTaskList = ctaskList;
    }

    public <T extends CTask> List<T> getCTaskList() {
        return (List<T>) cTaskList;
    }
    protected <T extends QTask> void setQTaskList(List<T> qtaskList) {
        this.qTaskList = qtaskList;
    }

    public <T extends QTask> List<T> getQTaskList() {
        return (List<T>) qTaskList;
    }

    public void submitTasks(List<? extends CTask> cTaskList, List<? extends QTask> qTaskList) {
        getCTaskList().addAll(cTaskList);
        getQTaskList().addAll(qTaskList);
    }

    public void submitQTasks(List<? extends QTask> qTaskList) {
        getQTaskList().addAll(qTaskList);
    }

    public void submitCTasks(List<? extends CTask> cTaskList) {
        getCTaskList().addAll(cTaskList);
    }


    @Override
    public void startEntity() {
        Log.printConcatLine(getName(), " is starting...");
        schedule(getId(), 0, iQuantumTags.CLOUD_GATEWAY_DISPATCH_TASK);
    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            case iQuantumTags.CLOUD_GATEWAY_DISPATCH_TASK:
                processTaskDispatch(ev);
                break;
            default:
                Log.printConcatLine(getName(), ": unknown event type at Edge Gateway");
        }
    }

    @Override
    protected void processTaskDispatch(SimEvent ev) {
        Log.printConcatLine(iQuantum.clock(), ": ", getName(), " : Dispatching ",getCTaskList().size()," CTasks and ",getQTaskList().size()," QTasks from Edge Gateway to Brokers for processing");
        if(cBroker != null) {
            cBroker.submitCloudletList(getCTaskList());
        }
        if(qBroker != null) {
            qBroker.submitQTaskList(getQTaskList());
        }
    }

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }
}
