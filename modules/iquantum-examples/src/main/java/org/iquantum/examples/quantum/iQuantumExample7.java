/**
 * iQuantum Example 5
 * This example shows how to create a QDatacenter with a 27-qubit and a 127-qubit quantum nodes following the topology of
 * IBM Hanoi and IBM Geneva automatically from the datasheet. Then, it creates a QBroker and four QTasks to
 * be submitted to the QBroker. Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.quantum;

import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.brokers.QBroker;
import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.QCloudDatacenter;
import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;
import org.iquantum.utils.QTaskExporter;
import org.iquantum.utils.QTaskImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class iQuantumExample7 {
    private static List<QTask> QTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws IOException {
        String exampleName = "iQuantumExample7";
        System.out.println("Start the " + exampleName);

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter and two quantum nodes (IBM Hanoi and IBM Geneva)
        QCloudDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QCloudBroker qBroker = createQBroker();

        // Step 4: Create a list of QTasks
        QTaskList = createQTaskList(qBroker);

        // Step 5: Submit QTask to the QBroker
        qBroker.submitQTaskList(QTaskList);

        // Step 6: Start the simulation
        iQuantum.startSimulation();

        // Step 7: Stop the simulation
        iQuantum.stopSimulation();

        // Step 8: Print the results when simulation is over
        List<QTask> newList = qBroker.getQTaskReceivedList();
        QTaskExporter.printQTaskList(newList);
        QTaskExporter.extractQTaskListToCSV(newList, exampleName);

        Log.printLine(exampleName + " finished!");
    }

    private static List<QTask> createQTaskList(QCloudBroker qBroker) {
        List<QTask> QTaskList = new ArrayList<>();
        String folderPath = "dataset/iquantum/MQT-Set04-7-127-AllOpt-IBMMapped-Extra.csv";
        Path datasetPath = Paths.get(System.getProperty("user.dir"), folderPath);
        QTaskImporter QTaskImporter = new QTaskImporter();
        try {
            List<QTask> QTasks = QTaskImporter.importQTasksFromCsv(datasetPath.toString());
            for (QTask qtask : QTasks) {
                qtask.setBrokerId(qBroker.getId());
                QTaskList.add(qtask);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return QTaskList;
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
     * Create a QDatacenter with two quantum nodes (IBM Hanoi and IBM Geneva)
     * @param name name of the QDatacenter
     * @return QDatacenter
     */
    private static QCloudDatacenter createQDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Cairo) from the dataset
        QNode qNode1 = IBMQNode.createNode(0,"ibm_hanoi",new QTaskSchedulerSpaceShared());
        QNode qNode2 = IBMQNode.createNode(1,"ibm_auckland",new QTaskSchedulerSpaceShared());
        QNode qNode3 = IBMQNode.createNode(2,"ibm_cairo",new QTaskSchedulerSpaceShared());
        QNode qNode4 = IBMQNode.createNode(3,"ibmq_mumbai",new QTaskSchedulerSpaceShared());
        QNode qNode5 = IBMQNode.createNode(4,"ibmq_kolkata",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1, qNode2, qNode3, qNode4, qNode5));
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

}

