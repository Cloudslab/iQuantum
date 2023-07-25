package org.iquantum.gateways;

import org.iquantum.brokers.CBroker;
import org.iquantum.brokers.CCloudBroker;
import org.iquantum.brokers.QBroker;
import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.tasks.CTask;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class Gateway extends SimEntity {
    /** The name of the gateway. */
    protected String name;
    /** List of all CTask submitted to the gateway. */
    protected List<? extends CTask> cTaskList;

    /** List of all QTask submitted to the gateway. */
    protected List<? extends QTask> qTaskList;

    protected CBroker cBroker;

    protected QBroker qBroker;

    public Gateway(String name, CBroker cBroker, QBroker qBroker) throws Exception {
        super(name);
        this.name = name;
        this.cBroker = cBroker;
        this.qBroker = qBroker;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }

    public Gateway(String name, CBroker cBroker) throws Exception {
        super(name);
        this.name = name;
        this.cBroker = cBroker;
        this.qBroker = null;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }

    public Gateway(String name, QBroker qBroker) throws Exception {
        super(name);
        this.name = name;
        this.cBroker = null;
        this.qBroker = null;
        setCTaskList(new ArrayList<>());
        setQTaskList(new ArrayList<>());
    }

    public Gateway(String name) {
        super(name);
        this.name = name;
    }

    // GETTERS AND SETTERS
    protected <T extends CTask> void setCTaskList(List<T> cTaskList) {
        this.cTaskList = cTaskList;
    }

    public <T extends CTask> List<T> getCTaskList() {
        return (List<T>) cTaskList;
    }

    protected <T extends QTask> void setQTaskList(List<T> qTaskList) {
        this.qTaskList = qTaskList;
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
                Log.printConcatLine(getName(), ": unknown event type at Gateway");
        }
    }

    protected abstract void processTaskDispatch(SimEvent ev);

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }
}
