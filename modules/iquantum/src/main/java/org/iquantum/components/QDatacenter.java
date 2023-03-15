package org.iquantum.components;

import java.util.ArrayList;
import java.util.List;

public class QDatacenter {
    private String name;
    private List<QNode> qNodeList;
    private QBroker qBroker;

    // Follow the Datacenter class in CloudSim, create a similar class for QDatacenter

    public QDatacenter(String name, List<QNode> qNodeList, QBroker qBroker) {
        this.name = name;
        this.qNodeList = qNodeList;
        this.qBroker = qBroker;
    }

    public String getName() {
        return name;
    }

    public List<QNode> getQNodes() {
        return qNodeList;
    }

    public QBroker getQBroker() {
        return qBroker;
    }

    public void addQNode(QNode qNode) {
        qNodeList.add(qNode);
    }

    public void addQNodes(List<QNode> qNodes) {
        qNodeList.addAll(qNodes);
    }

    public void setQBroker(QBroker qBroker) {
        this.qBroker = qBroker;
    }

    public void setQNodes(List<QNode> qNodes) {
        this.qNodeList = qNodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearQNodes() {
        qNodeList.clear();
    }

    public void clearQBroker() {
        qBroker = null;
    }

    public void clear() {
        clearQNodes();
        clearQBroker();
    }

    public void removeQNode(QNode qNode) {
        qNodeList.remove(qNode);
    }

    public void removeQNodes(List<QNode> qNodes) {
        qNodeList.removeAll(qNodes);
    }

    public void removeQBroker() {
        qBroker = null;
    }

    public void remove() {
        removeQNodes(qNodeList);
        removeQBroker();
    }


}

