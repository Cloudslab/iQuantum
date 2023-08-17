/**
 * iQuantum Cloud Edge Example
 * This example demonstrates the use of iQuantum in a hybrid cloud-edge environment.
 * It creates a CEDatacenter, a CCDatacenter, a QEDatacenter, and a QDatacenter, and then creates all respective brokers
 * and gateways for the cloud and edge environments.
 * It also creates a CTask and a QTask to be submitted to the CCloudBroker and QCloudBroker respectively.
 * Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.hybrid;

import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.brokers.*;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.QCloudDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.datacenters.QEdgeDatacenter;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.gateways.EdgeGateway;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;
import org.iquantum.utils.QTaskExporter;
import org.iquantum.utils.QTaskImporter;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class iQuantumHybridExample4 {
    private static List<QTask> qTaskList;

    private static  List<QNode> qcNodeList;

    private static  List<QNode> qeNodeList;
    public static void main(String[] args) throws Exception {
//        Log.disable();
        // Get the current time before executing the Java code
        long startTime = System.currentTimeMillis();
        String exampleName = "iQuantumCloudEdgeExample3";
        System.out.println("Start the " + exampleName + " simulation");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create QDatacenter

        QCloudDatacenter QCDatacenter = createQCDatacenter("QCloudDatacenter");
        QEdgeDatacenter QEDatacenter = createQEDatacenter("QEdgeDatacenter");

        // Step 3: Create a CBroker and a QBroker
        CCloudBroker ccBroker = createBroker();
        CEdgeBroker ceBroker = createEBroker();

        QCloudBroker qcBroker = createQCBroker();
        QEdgeBroker qeBroker = createQEBroker();

        CloudGateway cloudGateway = new CloudGateway("CloudGateway", ccBroker, qcBroker);
        EdgeGateway edgeGateway = new EdgeGateway("EdgeGateway", ceBroker, qeBroker, cloudGateway);

        // Step 6: Create 4 QTasks
        qTaskList = createQTaskList(qeBroker);

        // Step 7: Submit all tasks to brokers
        edgeGateway.submitQTasks(qTaskList);

        // Step 8: Start the simulation
        iQuantum.startSimulation();

        // Step 9: Stop the simulation
        iQuantum.stopSimulation();

        // Step 10: Print the results when simulation is over
        Log.printLine("SIMULATION RESULTS");
        Log.printLine("==========================================================");
        List<QTask> qcTaskResults = qcBroker.getQTaskReceivedList();
        Log.printLine();
        Log.printLine("CLOUD Layer ================================");
        if(qcTaskResults.size() == 0) {
            Log.printLine("No QTask received");
        } else {
            QTaskExporter.printQTaskList(qcTaskResults);
            QTaskExporter.extractQTaskListToCSV(qcTaskResults, exampleName+"-cloud");
        }

        Log.printLine();
        List<QTask> qeTaskResults = qeBroker.getQTaskReceivedList();
        Log.printLine("EDGE Layer ================================");
        if(qeTaskResults.size() == 0) {
            Log.printLine("No QTask received");
        } else {
            QTaskExporter.printQTaskList(qeTaskResults);
            QTaskExporter.extractQTaskListToCSV(qeTaskResults, exampleName+"-edge");
        }
        Log.printLine(exampleName +" finished!");

        // ----- RESOURCE CONSUMPTION
        // Get the current time after executing the Java code
        long endTime = System.currentTimeMillis(); // or System.nanoTime();

        // Calculate the execution time
        long executionTime = endTime - startTime;

        // Print the execution time in milliseconds (for System.currentTimeMillis()) or nanoseconds (for System.nanoTime())
        System.out.println("Execution time: " + executionTime + " milliseconds");
        // Get the OperatingSystemMXBean instance
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        // Get the MemoryMXBean instance
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        // Get the number of available processors (CPU cores)
        int availableProcessors = osBean.getAvailableProcessors();
        System.out.println("Number of available processors (CPU cores): " + availableProcessors);

        // Print CPU usage information
        double cpuUsage = osBean.getSystemLoadAverage();
        System.out.println("CPU usage: " + cpuUsage + " (load average)");

        // Print RAM (memory) usage information
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed();
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax();
        System.out.println("Used memory: " + usedMemory / (1024L * 1024L) + " MB");
        System.out.println("Max memory: " + maxMemory / (1024L * 1024L) + " MB");
    }

    /**
     * Creates the broker.
     *
     * @return the datacenter broker
     */
    private static CCloudBroker createBroker() {
        CCloudBroker broker = null;
        try {
            broker = new CCloudBroker("CCBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    private static CEdgeBroker createEBroker() {
        CEdgeBroker broker = null;
        try {
            broker = new CEdgeBroker("CEBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    /**
     * QUANTUM PART
     */

    private static List<QTask> createQTaskList(QBroker qBroker) {
        List<QTask> QTaskList = new ArrayList<>();
//        String folderPath = "dataset/iquantum/MQT-Set01-298-10-27-IBMQ27-Opt3-Extra.csv";
//        String folderPath = "dataset/iquantum/MQT-Set02-10-27-Mapped-AllAlgorithmLeft-Extra.csv";
//        String folderPath = "dataset/iquantum/MQT-Set03-7-127-AllOpt-IBMMapped-Only127-Extra.csv";
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
    private static QCloudBroker createQCBroker() {
        QCloudBroker qBroker = null;
        try {
            qBroker = new QCloudBroker("QCBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    private static QEdgeBroker createQEBroker() {
        QEdgeBroker qBroker = null;
        try {
            qBroker = new QEdgeBroker("QEBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    private static QEdgeDatacenter createQEDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Cairo) from the dataset
        QNode qNode1 = IBMQNode.createNode(11,"ibm_hanoi",new QTaskSchedulerSpaceShared());
        QNode qNode2 = IBMQNode.createNode(12,"ibm_auckland",new QTaskSchedulerSpaceShared());
        QNode qNode3 = IBMQNode.createNode(13,"ibm_cairo",new QTaskSchedulerSpaceShared());
        QNode qNode4 = IBMQNode.createNode(14,"ibmq_mumbai",new QTaskSchedulerSpaceShared());
        QNode qNode5 = IBMQNode.createNode(15,"ibmq_kolkata",new QTaskSchedulerSpaceShared());
        qeNodeList = new ArrayList<>();
        qeNodeList.addAll(Arrays.asList(qNode1, qNode2, qNode3, qNode4, qNode5));
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qeNodeList, timeZone, costPerSec);
        return new QEdgeDatacenter(name, characteristics);
    }

    private static QCloudDatacenter createQCDatacenter(String name) {
        int[] nodeIds = {21, 22, 23, 24, 25, 26};
        String backendName = "ibm_washington";
        QTaskSchedulerSpaceShared taskScheduler = new QTaskSchedulerSpaceShared();
        int quantumVolume = 128;
        int clops = 850;
        List<QNode> qcNodeList = new ArrayList<>();
        for (int nodeId : nodeIds) {
            QNode qcNode = IBMQNode.createNode(nodeId, backendName, taskScheduler);
            qcNodeList.add(qcNode);
            // Modify the quantum volume and CLOPS of the remaining 3 nodes
            if (nodeId >= 24) {
                qcNode.setQuantumVolume(quantumVolume);
                qcNode.setCLOPS(clops);
            }
        }
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qcNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

}

