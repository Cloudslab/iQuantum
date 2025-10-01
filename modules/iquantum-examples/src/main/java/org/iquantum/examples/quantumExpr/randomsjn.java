package org.iquantum.examples.quantumExpr;

import org.iquantum.backends.quantum.IBMQNodeMQ;
import org.iquantum.backends.quantum.QNodeMQ;
import org.iquantum.brokers.QBrokerMQ;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.QDatacenterCharacteristicsExtended;
import org.iquantum.datacenters.QDatacenterExtended;
import org.iquantum.policies.qtasks.QTaskSchedulerFCFSMQ;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;
import org.iquantum.utils.QTaskExporter;
import org.iquantum.utils.QTaskImporter;
import org.iquantum.utils.GraphicalTopoRepr;
import org.iquantum.utils.QTaskListGui;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
// import java.text.DecimalFormat;
import java.util.*;

public class randomsjn {
    private static List<QTask> QTaskList;

    private static  List<QNodeMQ> qNodeList;

    public static void main(String[] args) throws IOException {
        System.out.println("Start the iQuantum Multi QPU Example 1...");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);
        String exampleName = "randomsjn";

        // Step 2: Create a QDatacenter and two quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterExtended qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QBrokerMQ qBroker = createQBroker();

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
        // printQTaskList(newList);
        QTaskListGui.showQTaskListInGUI(newList);
        // Formatting output for better aligned list.
        QTaskExporter.printQTaskList(QTaskList);
        QTaskExporter.extractQTaskListToCSV(newList, exampleName);

        Log.printLine("iQuantum randomsjn Example finished!");
    }

    private static List<QTask> createQTaskList(QDatacenterExtended qDatacenter, QBrokerMQ qBroker) {
        List<QTask> QTL = new ArrayList<>();
        String folderPath = "dataset/iquantum/MQT-Set04-7-127-AllOpt-IBMMapped-Extra.csv";
        Path datasetPath = Paths.get(System.getProperty("user.dir"), folderPath);
        QTaskImporter QTaskImporter = new QTaskImporter();
        try {
            List<QTask> QTasks = QTaskImporter.importQTasksFromCsv(datasetPath.toString());
            List<QNodeMQ> qNodeList = (List<QNodeMQ>) qDatacenter.getCharacteristics().getQNodeList();
            // Assign random QNode to each QTask
            Random random = new Random();

            for (QTask QTask : QTasks) {
                QTask.setBrokerId(qBroker.getId());
                QTask.setQNodeId(qNodeList.get(random.nextInt(qNodeList.size())).getId());
                QTL.add(QTask);
            }
            //  Sorting the QTasks [List] based on Least Executable Circuit Layers First
            QTL.sort(Comparator.comparingInt(QTask::getNumECL));

            /** Graphical representation of a Qtask topology */
            GraphicalTopoRepr.repr(QTasks.get(0).getQubitTopology());
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return QTL;
    }

    /**
     * Create a QBroker
     * @return QBroker
     */
    private static QBrokerMQ createQBroker() {
        QBrokerMQ qBroker;
        try {
            qBroker = new QBrokerMQ("QBroker");
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
    private static QDatacenterExtended createQDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Cairo) from the dataset
        QNodeMQ qNode1 = IBMQNodeMQ.createNode(0,"ibm_washington",new QTaskSchedulerFCFSMQ());
        QNodeMQ qNode2 = IBMQNodeMQ.createNode(1,"ibm_sherbrooke",new QTaskSchedulerFCFSMQ());
        //QubitTopology.printTopology(qNode1.getQubitTopology());

        /** Graphical representation of a Qnode topology */
        GraphicalTopoRepr.repr(qNode1.getQPUList().getQubitTopologyOfQPUById(0));
        // GraphicalTopoRepr.repr(qNode2.getQPUList().getQubitTopologyOfQPUById(0));
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1, qNode2));
        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristicsExtended characteristics = new QDatacenterCharacteristicsExtended(qNodeList, timeZone, costPerSec);
        QDatacenterExtended qDatacenter = new QDatacenterExtended(name, characteristics);
        return qDatacenter;
    }
}

