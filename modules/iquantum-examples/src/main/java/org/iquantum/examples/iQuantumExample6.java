/**
 * iQuantum Example 4
 * This example shows how to create a QDatacenter with two 27-qubit quantum nodes following the topology of
 * IBM Hanoi and IBM Geneva automatically from the datasheet. Then, it creates a QBroker and four Qulets to
 * be submitted to the QBroker. Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples;

import org.iquantum.core.iQuantum;
import org.iquantum.qbrokers.QBroker;
import org.iquantum.qdatacenters.QDatacenter;
import org.iquantum.qdatacenters.QDatacenterCharacteristics;
import org.iquantum.qnodes.IBMQNode;
import org.iquantum.qnodes.QNode;
import org.iquantum.qulets.Qulet;
import org.iquantum.qulets.QuletImporter;
import org.iquantum.schedulers.QuletSchedulerSpaceShared;
import org.iquantum.utils.Log;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class iQuantumExample6 {
    private static List<Qulet> quletList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws IOException {
        System.out.println("Start the iQuantum Example 4");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter and two quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QBroker qBroker = createQBroker();

        // Step 4: Create a Qulet
        quletList = createQuletList(qDatacenter, qBroker);
//
//        // Step 5: Submit qulet to the QBroker
        qBroker.submitQuletList(quletList);
//
//        // Step 6: Start the simulation
        iQuantum.startSimulation();
//
//        // Step 7: Stop the simulation
        iQuantum.stopSimulation();
//
//        // Step 8: Print the results when simulation is over
        List<Qulet> newList = qBroker.getQuletReceivedList();
        printQuletList(newList);

        Log.printLine("iQuantum Example 6 finished!");
    }

    private static List<Qulet> createQuletList(QDatacenter qDatacenter, QBroker qBroker) {
        List<Qulet> quletList = new ArrayList<>();
        String folderPath = "dataset/iquantum/MQTQulets/montrealDataset.csv";
        Path datasetPath = Paths.get(System.getProperty("user.dir"), folderPath);
        QuletImporter quletImporter = new QuletImporter();
        try {
            List<Qulet> qulets = quletImporter.importQuletsFromCsv(datasetPath.toString());
            List<QNode> qNodeList = (List<QNode>) qDatacenter.getCharacteristics().getQNodeList();
            // Assign random QNode to each Qulet
            Random random = new Random();

            for (Qulet qulet : qulets) {
                qulet.setBrokerId(qBroker.getId());
                qulet.setQNodeId(qNodeList.get(random.nextInt(qNodeList.size())).getId());
                quletList.add(qulet);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return quletList;
    }

    /**
     * Create a QBroker
     * @return QBroker
     */
    private static QBroker createQBroker() {
        QBroker qBroker = null;
        try {
            qBroker = new QBroker("QBroker");
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
    private static QDatacenter createQDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Cairo) from the dataset
        QNode qNode1 = IBMQNode.createNode(0,"ibm_cairo",new QuletSchedulerSpaceShared());
        QNode qNode2 = IBMQNode.createNode(1,"ibm_hanoi",new QuletSchedulerSpaceShared());
//        QubitTopology.printTopology(qNode1.getQubitTopology());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1, qNode2));
        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QDatacenter qDatacenter = new QDatacenter(name, characteristics);
        return qDatacenter;
    }

    /**
     * Print the list of Qulets after the simulation
     * @param list list of Qulets
     */
    private static void printQuletList(List<Qulet> list) {
        int size = list.size();
        Qulet qulet;

        String indent = "   ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Qulet ID" + indent + "Status" + indent
                + "QDCenter" + indent + "QNode ID" + indent + "Execution Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            qulet = list.get(i);
            Log.print(indent + qulet.getQuletId() + indent + indent);
            if (qulet.getQuletStatus() == Qulet.SUCCESS) {
                Log.print("SUCCESS");
                Log.printLine(indent + indent + qulet.getResourceId()
                        + indent + indent + indent + qulet.getQNodeId()
                        + indent + indent + indent + dft.format(qulet.getActualQPUTime())
                        + indent + indent + indent + indent + dft.format(qulet.getExecStartTime())
                        + indent + indent + indent + dft.format(qulet.getFinishTime()));
            }
            else {
                Log.printLine(qulet.getQuletStatusString());
            }
        }
    }

}

