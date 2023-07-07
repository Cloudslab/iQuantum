/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.tasks.qubittopologies;

import java.util.ArrayList;
import java.util.List;

public class QubitTopology {

    private List<Node> qubits;
    private int numQubits;

    public QubitTopology(int numQubits, List<int[]> edges) {
        this.numQubits = numQubits;
        qubits = new ArrayList<>();
        for (int i = 0; i < numQubits; i++) {
            qubits.add(new Node(i));
        }
        for (int[] edge : edges) {
            qubits.get(edge[0]).addEdge(qubits.get(edge[1]));
            qubits.get(edge[1]).addEdge(qubits.get(edge[0]));
        }
    }

    public List<Node> getQubits() {
        return qubits;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public static class Node {
        protected int qubitIndex;
        protected List<Node> neighbors;

        public Node(int qubitIndex) {
            this.qubitIndex = qubitIndex;
            this.neighbors = new ArrayList<>();
        }

        public void addEdge(Node neighbor) {
            if (!neighbors.contains(neighbor)) {
                neighbors.add(neighbor);
                neighbor.addEdge(this);
            }
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }

        public int getQubitIndex() {
            return qubitIndex;
        }
    }

    public static void printTopology(QubitTopology topology) {
        System.out.println("Qubit Topology:");
        List<QubitTopology.Node> qubits = topology.getQubits();
        for (QubitTopology.Node qubit : qubits) {
            System.out.print("Qubit " + qubit.getQubitIndex() + " is connected to qubits: ");
            List<QubitTopology.Node> neighbors = qubit.getNeighbors();
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i).getQubitIndex());
                if (i < neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public static void printTopologyShort(QubitTopology topology) {
        System.out.println("Qubit Topology:");
        List<QubitTopology.Node> qubits = topology.getQubits();
        for (QubitTopology.Node qubit : qubits) {
            StringBuilder sb = new StringBuilder();
            sb.append(qubit.getQubitIndex() + " - ");
            List<QubitTopology.Node> neighbors = qubit.getNeighbors();
            for (int i = 0; i < neighbors.size(); i++) {
                sb.append(neighbors.get(i).getQubitIndex());
                if (i < neighbors.size() - 1) {
                    sb.append(" - ");
                }
            }
            System.out.println(sb.toString());
        }
    }

}
