/**
 * iQuantum Example 1: Getting started
 * This example shows how to create a simple datacenter with single quantum node and a QBroker.
 * Then, it creates a quantum task (qtask) to be submitted to the QBroker.
 * Finally, it starts the simulation and prints the results.
 */
package org.iquantum.examples.quantum;

import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.iQuantum;
import org.iquantum.brokers.QBroker;
import org.iquantum.datacenters.QCloudDatacenter;
import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.tasks.QTask;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.utils.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class iQuantumExample1 {
    private static List<QTask> qTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws Exception {
        System.out.println("Start the iQuantum Example 1");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter with a 7-qubit quantum nodes (IBM Oslo)
        QCloudDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QCloudBroker qBroker = createQBroker();
        CloudGateway cloudGateway = new CloudGateway("CloudGateway_0", qBroker);

        // Step 4: Create a list of 1 qtasks for testing
        qTaskList = createQTaskList(qBroker);

        // Step 5: Submit qtask to the QBroker
        cloudGateway.submitQTasks(qTaskList);

        // Step 6: Start the simulation
        iQuantum.startSimulation();

        // Step 7: Stop the simulation
        iQuantum.stopSimulation();

        // Step 8: Print the results when simulation is over
        List<QTask> newList = qBroker.getQTaskReceivedList();
        printQTaskList(newList);

        Log.printLine("iQuantum Example 1 finished!");
    }

    /**
     * Create a list of 1 QTask and set the QBroker and QNode for the QTask
     * @param qBroker: QBroker that will receive the QTask
     * @return A list of 1 QTask
     */
    private static List<QTask> createQTaskList(QCloudBroker qBroker) {
        List<QTask> qTaskList = new ArrayList<>();
        // Create QTask 1
        List<int[]> ql1Edges = new ArrayList<>();
        ql1Edges.add(new int[]{0, 1});
        ql1Edges.add(new int[]{1, 0});
        ql1Edges.add(new int[]{1, 2});
        ql1Edges.add(new int[]{1, 3});
        ql1Edges.add(new int[]{2, 1});
        ql1Edges.add(new int[]{3, 1});
        ArrayList<String> qlGates = new ArrayList<>(Arrays.asList("CX", "RZ", "X"));
        QubitTopology ql1Topology = new QubitTopology(4, ql1Edges);
        QTask qTask1 = new QTask(0,4, 25, 4096, qlGates, ql1Topology);
        // Set QBroker for QTask 1
        qTask1.setBrokerId(qBroker.getId());
        // Add all QTasks to the list
        qTaskList.add(qTask1);

        return qTaskList;
    }

    /**
     * Create a QBroker
     * @return QBroker
     */
    private static QCloudBroker createQBroker() {
        QCloudBroker qBroker = null;
        try {
            qBroker = new QCloudBroker("QBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    /**
     * Create a QDatacenter with a 7-qubit quantum nodes (follow the topology of IBM Oslo)
     * @param name: name of the QDatacenter
     * @return QDatacenter
     */
    private static QCloudDatacenter createQDatacenter(String name) {
        // Create 7-qubit quantum node (IBM Oslo)
        // Manually create the topology of the quantum node (Automatically create the topology in Example 4)
        List<int[]> edges_oslo = new ArrayList<>();
        edges_oslo.add(new int[]{0, 1});
        edges_oslo.add(new int[]{1, 0});
        edges_oslo.add(new int[]{1, 2});
        edges_oslo.add(new int[]{1, 3});
        edges_oslo.add(new int[]{2, 1});
        edges_oslo.add(new int[]{3, 1});
        edges_oslo.add(new int[]{3, 5});
        edges_oslo.add(new int[]{4, 5});
        edges_oslo.add(new int[]{5, 3});
        edges_oslo.add(new int[]{5, 4});
        edges_oslo.add(new int[]{5, 6});
        edges_oslo.add(new int[]{6, 5});
        QubitTopology osloTopology = new QubitTopology(7, edges_oslo);
        ArrayList<String> gateSet1 = new ArrayList<>(Arrays.asList("CX", "ID", "RZ", "SX", "X"));
        QNode qNodeOslo = new QNode(0, 7,128,2600, gateSet1,
                osloTopology, new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<QNode>();
        qNodeList.add(qNodeOslo);
        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with a 7-qubit quantum node (IBM Oslo)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

    /**
     * Print the list of QTasks after the simulation
     * @param list: list of QTasks
     */
    private static void printQTaskList(List<QTask> list) {
        int size = list.size();
        QTask QTask;

        String indent = "   ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("QTask ID" + indent + "Status" + indent
                + "QDCenter" + indent + "QNode ID" + indent + "Execution Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            QTask = list.get(i);
            Log.print(indent + QTask.getQTaskId() + indent + indent);
            if (QTask.getQTaskStatus() == QTask.SUCCESS) {
                Log.print("SUCCESS");
                Log.printLine(indent + indent + QTask.getResourceId()
                        + indent + indent + indent + QTask.getQNodeId()
                        + indent + indent + indent + dft.format(QTask.getActualQPUTime())
                        + indent + indent + indent + indent + dft.format(QTask.getExecStartTime())
                        + indent + indent + indent + dft.format(QTask.getFinishTime()));
            }
            else {
                Log.printLine(QTask.getQTaskStatusString());
            }
        }
    }

}

