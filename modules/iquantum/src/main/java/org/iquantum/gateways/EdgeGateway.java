package org.iquantum.gateways;

import org.iquantum.brokers.CEdgeBroker;
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
public class EdgeGateway extends SimEntity {
    protected String name;
    /** List of all CTask submitted to the cloud gateway. */
    protected List<? extends CTask> cTaskList;

    /** List of all QTask submitted to the cloud gateway. */
    protected List<? extends QTask> qTaskList;

    protected CEdgeBroker cBroker;

    protected QEdgeBroker qBroker;

    public EdgeGateway(String name, CEdgeBroker cBroker, QEdgeBroker qBroker) throws Exception {
        super(name);
        this.cBroker = cBroker;
        this.qBroker = qBroker;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
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

    private void processTaskDispatch(SimEvent ev) {
        Log.printConcatLine(iQuantum.clock(), ": ", getName(), " : Dispatching ",getCTaskList().size()," CTasks and ",getQTaskList().size()," QTasks from Edge Gateway to Brokers for processing");
        cBroker.submitCloudletList(getCTaskList());
        qBroker.submitQTaskList(getQTaskList());
    }

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }
}
