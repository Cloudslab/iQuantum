/**
 * iQuantum Example 1: Getting started
 * This example shows how to create a simple datacenter with single quantum node and a QBroker.
 * Then, it creates a quantum task (qulet) to be submitted to the QBroker.
 * Finally, it starts the simulation and prints the results.
 */
package org.iquantum.examples;

import org.iquantum.core.iQuantum;
import org.iquantum.qbrokers.QBroker;
import org.iquantum.qdatacenters.QDatacenter;
import org.iquantum.qdatacenters.QDatacenterCharacteristics;
import org.iquantum.qnodes.QNode;
import org.iquantum.qubitTopologies.QubitTopology;
import org.iquantum.qulets.Qulet;
import org.iquantum.schedulers.QuletSchedulerSpaceShared;
import org.iquantum.utils.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class iQuantumExample1 {
    private static List<Qulet> quletList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) {
        System.out.println("Start the iQuantum Example 1");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter with a 7-qubit quantum nodes (IBM Oslo)
        QDatacenter qDatacenter = createQDatacenter("QDatacenter_0");

        // Step 3: Create a QBroker
        QBroker qBroker = createQBroker();

        // Step 4: Create a list of 1 qulets for testing
        quletList = createQuletList(qDatacenter, qBroker);

        // Step 5: Submit qulet to the QBroker
        qBroker.submitQuletList(quletList);

        // Step 6: Start the simulation
        iQuantum.startSimulation();

        // Step 7: Stop the simulation
        iQuantum.stopSimulation();

        // Step 8: Print the results when simulation is over
        List<Qulet> newList = qBroker.getQuletReceivedList();
        printQuletList(newList);

        Log.printLine("iQuantum Example 1 finished!");
    }

    /**
     * Create a list of 1 Qulet and set the QBroker and QNode for the Qulet
     * @param qDatacenter: QDatacenter where the QNode is located
     * @param qBroker: QBroker that will receive the Qulet
     * @return A list of 1 Qulet
     */
    private static List<Qulet> createQuletList(QDatacenter qDatacenter, QBroker qBroker) {
        List<Qulet> quletList = new ArrayList<>();
        ArrayList<String> qlGates = new ArrayList<>(Arrays.asList("CX", "RZ", "X"));
        // Create Qulet 1
        List<int[]> ql1Edges = new ArrayList<>();
        ql1Edges.add(new int[]{0, 1});
        ql1Edges.add(new int[]{1, 0});
        ql1Edges.add(new int[]{1, 2});
        ql1Edges.add(new int[]{1, 3});
        ql1Edges.add(new int[]{2, 1});
        ql1Edges.add(new int[]{3, 1});
        QubitTopology ql1Topology = new QubitTopology(4, ql1Edges);
        Qulet qulet1 = new Qulet(0,4, 25, 4096, qlGates, ql1Topology);
        // Set QBroker for Qulet 1
        qulet1.setBrokerId(qBroker.getId());
        // Set QNode for Qulet 1
        qulet1.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());
        // Add all Qulets to the list
        quletList.add(qulet1);

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
     * Create a QDatacenter with a 7-qubit quantum nodes (follow the topology of IBM Oslo)
     * @param name: name of the QDatacenter
     * @return QDatacenter
     */
    private static QDatacenter createQDatacenter(String name) {
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
                osloTopology, new QuletSchedulerSpaceShared());
        qNodeList = new ArrayList<QNode>();
        qNodeList.add(qNodeOslo);
        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with a 7-qubit quantum node (IBM Oslo)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QDatacenter qDatacenter = new QDatacenter(name, characteristics);
        return qDatacenter;
    }

    /**
     * Print the list of Qulets after the simulation
     * @param list: list of Qulets
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

