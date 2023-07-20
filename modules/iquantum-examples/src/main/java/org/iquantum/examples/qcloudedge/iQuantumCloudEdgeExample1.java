/**
 * iQuantum Cloud Edge Example
 * This example demonstrates the use of iQuantum in a hybrid cloud-edge environment.
 * It creates a CEDatacenter, a CCDatacenter, a QEDatacenter, and a QDatacenter, and then creates all respective brokers
 * and gateways for the cloud and edge environments.
 * It also creates a CTask and a QTask to be submitted to the CCloudBroker and QCloudBroker respectively.
 * Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.qcloudedge;

import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.backends.classical.Vm;
import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.brokers.*;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.*;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.gateways.EdgeGateway;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelFull;
import org.iquantum.policies.ctasks.CloudletSchedulerSpaceShared;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.policies.vm.VmAllocationPolicySimple;
import org.iquantum.policies.vm.VmSchedulerTimeShared;
import org.iquantum.provisioners.BwProvisionerSimple;
import org.iquantum.provisioners.PeProvisionerSimple;
import org.iquantum.provisioners.RamProvisionerSimple;
import org.iquantum.tasks.CTask;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;
import org.iquantum.utils.QTaskExporter;
import org.iquantum.utils.QTaskImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class iQuantumCloudEdgeExample1 {
    private static List<QTask> qTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws Exception {
        String exampleName = "iQuantumCloudEdgeExample1";
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
        EdgeGateway edgeGateway = new EdgeGateway("EdgeGateway", ceBroker, qeBroker);

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
        Log.printLine("CLOUD Layer ================================");
        if(qcTaskResults.size() == 0) {
            Log.printLine("No QTask received");
        } else {
            QTaskExporter.printQTaskList(qcTaskResults);
            QTaskExporter.extractQTaskListToCSV(qcTaskResults, exampleName+"-cloud");
        }
        List<QTask> qeTaskResults = qeBroker.getQTaskReceivedList();
        Log.printLine("EDGE Layer ================================");
        if(qeTaskResults.size() == 0) {
            Log.printLine("No QTask received");
        } else {
            QTaskExporter.printQTaskList(qeTaskResults);
            QTaskExporter.extractQTaskListToCSV(qeTaskResults, exampleName+"-edge");
        }
        Log.printLine(exampleName +" finished!");
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
//        String folderPath = "dataset/iquantum/MQT-Set1-298-10-27-IBMQ27-Opt3-Extra.csv";
        String folderPath = "dataset/iquantum/MQT-Set2-7-127-AllOpt-IBMMapped-Extra.csv";
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
        QNode qeNode1 = IBMQNode.createNode(11,"ibm_hanoi",new QTaskSchedulerSpaceShared());
        QNode qeNode2 = IBMQNode.createNode(12,"ibmq_kolkata",new QTaskSchedulerSpaceShared());
        QNode qeNode3 = IBMQNode.createNode(13,"ibm_auckland",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qeNode1, qeNode2, qeNode3));
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        return new QEdgeDatacenter(name, characteristics);
    }

    private static QCloudDatacenter createQCDatacenter(String name) {
        QNode qcNode1 = IBMQNode.createNode(21,"ibm_washington",new QTaskSchedulerSpaceShared());
        QNode qcNode2 = IBMQNode.createNode(22,"ibm_washington",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qcNode1, qcNode2));
        double timeZone = 0.0;
        double costPerSec = 1.6; // the cost of using a quantum node per second (as IBM Quantum Pricing)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

}

