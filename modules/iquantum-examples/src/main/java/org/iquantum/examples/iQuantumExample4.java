/**
 * iQuantum Example 4
 * This example shows how to create a QDatacenter with two 27-qubit quantum nodes following the topology of
 * IBM Hanoi and IBM Geneva automatically from the datasheet. Then, it creates a QBroker and four Qulets to
 * be submitted to the QBroker. Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples;
import org.iquantum.qbrokers.QBroker;
import org.iquantum.qdatacenters.QDatacenter;
import org.iquantum.qdatacenters.QDatacenterCharacteristics;
import org.iquantum.qnodes.IBMQNode;
import org.iquantum.qnodes.QNode;
import org.iquantum.core.iQuantum;
import org.iquantum.qubitTopologies.QubitTopology;
import org.iquantum.qulets.Qulet;
import org.iquantum.schedulers.QuletSchedulerSpaceShared;
import org.iquantum.utils.Log;
import java.text.DecimalFormat;
import java.util.*;

public class iQuantumExample4 {
    private static List<Qulet> quletList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) {
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

        // Step 5: Submit qulet to the QBroker
        qBroker.submitQuletList(quletList);

        // Step 6: Start the simulation
        iQuantum.startSimulation();

        // Step 7: Stop the simulation
        iQuantum.stopSimulation();

        // Step 8: Print the results when simulation is over
        List<Qulet> newList = qBroker.getQuletReceivedList();
        printQuletList(newList);

        Log.printLine("iQuantum Example 4 finished!");
    }

    private static List<Qulet> createQuletList(QDatacenter qDatacenter, QBroker qBroker) {
        List<Qulet> quletList = new ArrayList<>();
        // Create Qulet 1
        List<int[]> ql1Edges = new ArrayList<>();
        ql1Edges.add(new int[]{0, 1});
        ql1Edges.add(new int[]{1, 0});
        ql1Edges.add(new int[]{1, 2});
        ql1Edges.add(new int[]{1, 3});
        ql1Edges.add(new int[]{2, 1});
        ql1Edges.add(new int[]{3, 1});
        ql1Edges.add(new int[]{3, 4});
        ql1Edges.add(new int[]{4, 3});
        ql1Edges.add(new int[]{4, 5});
        ql1Edges.add(new int[]{5, 4});
        QubitTopology ql1Topology = new QubitTopology(6, ql1Edges);
        ArrayList<String> ql1Gates = new ArrayList<>(Arrays.asList("CX", "RZ", "X"));
        Qulet qulet1 = new Qulet(0,4, 26, 3000, ql1Gates, ql1Topology);
        // Set QBroker for Qulet
        qulet1.setBrokerId(qBroker.getId());
        // Set QNode for Qulet
        qulet1.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Create Qulet 2
        List<int[]> ql2Edges = new ArrayList<>();
        ql2Edges.add(new int[]{0, 1});
        ql2Edges.add(new int[]{1, 0});
        ql2Edges.add(new int[]{1, 2});
        ql2Edges.add(new int[]{2, 1});
        QubitTopology ql2Topology = new QubitTopology(3, ql2Edges);
        ArrayList<String> ql2Gates = new ArrayList<>(Arrays.asList("CX", "RZ", "X", "H"));
        Qulet qulet2 = new Qulet(1,3, 29, 1000, ql2Gates, ql2Topology);
        // Set QBroker for Qulet 2
        qulet2.setBrokerId(qBroker.getId());
        // Set QNode for Qulet 2
        qulet2.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(1).getId());

        // Create Qulet 3
        Qulet qulet3 = new Qulet(2,3, 58, 4000, ql1Gates, ql2Topology);
        // Set QBroker for Qulet 3
        qulet3.setBrokerId(qBroker.getId());
        // Set QNode for Qulet 3
        qulet3.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(1).getId());

        // Create Qulet 4
        Qulet qulet4 = new Qulet(3,8, 78, 2000, ql1Gates, ql2Topology);
        // Set QBroker for Qulet 4
        qulet4.setBrokerId(qBroker.getId());
        // Set QNode for Qulet 4
        qulet4.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Add all Qulets to the list
        quletList.add(qulet1);
        quletList.add(qulet2);
        quletList.add(qulet3);
        quletList.add(qulet4);

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
        // Automatically create two quantum nodes (IBM Hanoi and IBM Geneva) from the dataset
        QNode qNode1 = IBMQNode.createNode(0,"ibm_hanoi",new QuletSchedulerSpaceShared());
        QNode qNode2 = IBMQNode.createNode(1,"ibm_geneva",new QuletSchedulerSpaceShared());
        QubitTopology.printTopology(qNode1.getQubitTopology());
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

