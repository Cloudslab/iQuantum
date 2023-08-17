/**
 * iQuantum Example 3
 * This example shows how to create a QDatacenter with two 7-qubit quantum nodes (IBM Oslo and IBM Nairobi),
 * a QBroker, and a list of 4 QTasks for testing. Some QTasks require more qubits than the quantum nodes.
 * The QTasks are submitted to the QBroker and the simulation is started.
 * The results are printed when the simulation is over.
 */
package org.iquantum.examples.quantum;
import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.iQuantum;
import org.iquantum.brokers.QBroker;
import org.iquantum.datacenters.QCloudDatacenter;
import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.tasks.QTask;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.utils.Log;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class iQuantumExample3 {
    private static List<QTask> QTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) {
        System.out.println("Start the iQuantum Example 3");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter and two 7-qubit quantum nodes (IBM Oslo and IBM Nairobi)
        QCloudDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QBroker qBroker = createQBroker();

        // Step 4: Create a list of 4 QTasks for testing
        QTaskList = createQTaskList(qDatacenter, qBroker);

        // Step 5: Submit QTask to the QBroker
        qBroker.submitQTaskList(QTaskList);

        // Step 6: Start the simulation
        iQuantum.startSimulation();

        // Step 7: Stop the simulation
        iQuantum.stopSimulation();

        // Step 8: Print the results when simulation is over
        List<QTask> newList = qBroker.getQTaskReceivedList();
        printQTaskList(newList);

        Log.printLine("iQuantum Example 3 finished!");
    }

    /**
     * Create a list of 4 QTasks and set the QBroker and QNode for each QTask
     * @param qDatacenter: QDatacenter where the QNode is located
     * @param qBroker: QBroker that will receive the QTasks
     * @return A list of 4 QTasks
     */
    private static List<QTask> createQTaskList(QDatacenter qDatacenter, QBroker qBroker) {
        List<QTask> QTaskList = new ArrayList<>();
        // Create QTask 1
        List<int[]> ql1Edges = new ArrayList<>();
        ql1Edges.add(new int[]{0, 1});
        ql1Edges.add(new int[]{1, 0});
        ql1Edges.add(new int[]{1, 2});
        ql1Edges.add(new int[]{1, 3});
        ql1Edges.add(new int[]{2, 1});
        ql1Edges.add(new int[]{3, 1});
        QubitTopology ql1Topology = new QubitTopology(4, ql1Edges);
        ArrayList<String> qlGates = new ArrayList<>(Arrays.asList("CX", "RZ", "X"));
        QTask QTask1 = new QTask(0,4, 52, 3000, qlGates, ql1Topology);
        // Set QBroker for QTask 1
        QTask1.setBrokerId(qBroker.getId());
        // Set QNode for QTask 1
        QTask1.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Create QTask 2
        List<int[]> ql2Edges = new ArrayList<>();
        ql2Edges.add(new int[]{0, 1});
        ql2Edges.add(new int[]{1, 0});
        ql2Edges.add(new int[]{1, 2});
        ql2Edges.add(new int[]{2, 1});
        QubitTopology ql2Topology = new QubitTopology(3, ql2Edges);
        QTask QTask2 = new QTask(1,10, 58, 1000, qlGates, ql2Topology);
        // Set QBroker for QTask 2
        QTask2.setBrokerId(qBroker.getId());
        // Set QNode for QTask 2
        QTask2.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(1).getId());

        // Create QTask 3
        QTask QTask3 = new QTask(2,3, 58, 4000, qlGates, ql2Topology);
        // Set QBroker for QTask 3
        QTask3.setBrokerId(qBroker.getId());
        // Set QNode for QTask 3
        QTask3.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(1).getId());

        // Create QTask 4
        QTask QTask4 = new QTask(3,8, 78, 2000, qlGates, ql2Topology);
        // Set QBroker for QTask 4
        QTask4.setBrokerId(qBroker.getId());
        // Set QNode for QTask 4
        QTask4.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Add all QTasks to the list
        QTaskList.add(QTask1);
        QTaskList.add(QTask2);
        QTaskList.add(QTask3);
        QTaskList.add(QTask4);

        return QTaskList;
    }

    /**
     * Create a QBroker
     * @return QBroker
     */
    private static QBroker createQBroker() {
        QBroker qBroker = null;
        try {
            qBroker = new QCloudBroker("QBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    /**
     * Create a QDatacenter with two 7-qubit quantum nodes (follow the topology of IBM Oslo and IBM Perth)
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
        ArrayList<String> gateSet2 = new ArrayList<>(Arrays.asList("CX", "ID", "RZ", "SX", "X"));
        QNode qNodeOslo = new QNode(0, 7,128,2600, gateSet1,
                osloTopology, new QTaskSchedulerSpaceShared());
        QNode qNodePerth = new QNode(1, 7,128,2900, gateSet2,
                osloTopology, new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<QNode>();
        qNodeList.add(qNodeOslo);
        qNodeList.add(qNodePerth);

        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Oslo and IBM Perth)
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

