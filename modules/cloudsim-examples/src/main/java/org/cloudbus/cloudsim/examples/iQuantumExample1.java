package org.cloudbus.cloudsim.examples;

import org.iquantum.components.QNode;

import java.util.ArrayList;

public class iQuantumExample1 {

    public static void main(String[] args) {
        System.out.println("Hello iQuantum");
        QNode qNode1 = new QNode(2,10,1000,new ArrayList<>(),new ArrayList<>(),null);
        System.out.println(qNode1.getQubits());
    }
}
