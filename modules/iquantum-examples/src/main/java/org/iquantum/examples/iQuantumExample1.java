package org.iquantum.examples;

import org.cloudbus.cloudsim.core.CloudSim;
import org.iquantum.components.QBroker;
import org.iquantum.components.QDatacenter;
import org.iquantum.components.QNode;
import org.iquantum.components.Qulet;

import java.util.ArrayList;
import java.util.Calendar;

public class iQuantumExample1 {

    public static void main(String[] args) {
        System.out.println("Start the iQuantum Example 1");

        // Step 1: Initialize Cloudsim package. It should be called before creating any entities.
        int num_user = 1;   // number of cloud users
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false;  // trace events
        CloudSim.init(num_user, calendar, trace_flag);

        // Step 2: Create a QDatacenter
        QDatacenter qDatacenter = new QDatacenter("QDatacenter_1", new ArrayList<>(), null);

        // Step 3: Create 2 QNodes and add them to the QDatacenter
        QNode qNode1 = new QNode(2,10,1000,new ArrayList<>(),new ArrayList<>(),null);
        QNode qNode2 = new QNode(2,10,1000,new ArrayList<>(),new ArrayList<>(),null);
        ArrayList<QNode> qNodeList = new ArrayList<>();
        qNodeList.add(qNode1);
        qNodeList.add(qNode2);
        qDatacenter.setQNodes(qNodeList);

        // Step 4: Create a QBroker
        QBroker qBroker = new QBroker(1, qDatacenter);

        // Step 5: Create Qulet
        Qulet qulet = new Qulet(new ArrayList<>(), 2, 10, 1000, new ArrayList<>());
        qulet.setBrokerId(qBroker.getId());

        // Step 6: Submit qulet to the QBroker
        qBroker.scheduleQulet(qulet);

        // Step 7: Start the simulation
        CloudSim.startSimulation();

        // Step 8: Stop the simulation
        CloudSim.stopSimulation();

        // Step 9: Print the results when simulation is over
    }
}
