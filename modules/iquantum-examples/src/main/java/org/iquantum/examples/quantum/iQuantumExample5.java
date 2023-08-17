/**
 * iQuantum Example 5
 * This example shows how to create a QDatacenter with two 27-qubit quantum nodes following the topology of
 * IBM Hanoi and IBM Geneva automatically from the datasheet. Then, it creates a QBroker and four QTasks to
 * be submitted to the QBroker. Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.quantum;

import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.iQuantum;
import org.iquantum.brokers.QBroker;
import org.iquantum.datacenters.QCloudDatacenter;
import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.QTaskExporter;
import org.iquantum.utils.QTaskImporter;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.utils.Log;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class iQuantumExample5 {
    private static List<QTask> QTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws IOException {
        String exampleName = "iQuantumExample5";
        System.out.println("Start the iQuantum Example 5");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter and two quantum nodes (IBM Hanoi and IBM Geneva)
        QCloudDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QBroker qBroker = createQBroker();

        // Step 4: Create a QTask
        QTaskList = createQTaskList(qDatacenter, qBroker);
//
//        // Step 5: Submit QTask to the QBroker
        qBroker.submitQTaskList(QTaskList);
//
//        // Step 6: Start the simulation
        iQuantum.startSimulation();
//
//        // Step 7: Stop the simulation
        iQuantum.stopSimulation();
//
//        // Step 8: Print the results when simulation is over
        List<QTask> newList = qBroker.getQTaskReceivedList();
        printQTaskList(newList);
        QTaskExporter.extractQTaskListToCSV(newList, exampleName);

        Log.printLine("iQuantum Example 5 finished!");
    }

    private static List<QTask> createQTaskList(QDatacenter qDatacenter, QBroker qBroker) {
        List<QTask> QTaskList = new ArrayList<>();
        String folderPath = "dataset/iquantum/MQT-Set01-298-10-27-IBMQ27-Opt3-Extra.csv";
        Path datasetPath = Paths.get(System.getProperty("user.dir"), folderPath);
        QTaskImporter QTaskImporter = new QTaskImporter();
        try {
            List<QTask> QTasks = QTaskImporter.importQTasksFromCsv(datasetPath.toString());
            List<QNode> qNodeList = (List<QNode>) qDatacenter.getCharacteristics().getQNodeList();
            // Assign random QNode to each QTask
            Random random = new Random();

            for (QTask QTask : QTasks) {
                QTask.setBrokerId(qBroker.getId());
                QTask.setQNodeId(qNodeList.get(random.nextInt(qNodeList.size())).getId());
                QTaskList.add(QTask);
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
     * Create a QDatacenter with two quantum nodes (IBM Hanoi and IBM Geneva)
     * @param name name of the QDatacenter
     * @return QDatacenter
     */
    private static QCloudDatacenter createQDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Cairo) from the dataset
        QNode qNode1 = IBMQNode.createNode(0,"ibm_hanoi",new QTaskSchedulerSpaceShared());
        QNode qNode2 = IBMQNode.createNode(1,"ibm_cairo",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1, qNode2));
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

    /**
     * Print the list of QTasks after the simulation
     * @param list list of QTasks
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

