package org.iquantum.utils;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;

public class GraphicalTopoRepr {
    public QubitTopology qubitTopology;

    // public GraphicalTopoRepr() {
    //     // qubitTopology = qbt;
    //     // repr(qubitTopology);
    // }

    public static void repr(QubitTopology qbt) {

        int nodesSize = qbt.getNumQubits();
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Graph");
        List<String> edgeIds = new ArrayList<>();
        List<QubitTopology.Node> qubits = qbt.getQubits();
        for (QubitTopology.Node node : qubits) {
            graph.addNode(String.valueOf(node.getQubitIndex()));
        }
        for (int i = 0; i < nodesSize; i++) {
            String bNodeIndex = String.valueOf(qbt.getQubits().get(i).getQubitIndex());
            int neighborsSize = qbt.getQubits().get(i).getNeighbors().size();
            // graph.addNode(bNodeIndex);
            for (int j = 0; j < neighborsSize; j++) {
                String nNodeIndex = String.valueOf(qbt.getQubits().get(i).getNeighbors().get(j).getQubitIndex());
                String s1 = bNodeIndex.concat(nNodeIndex);
                String s2 = nNodeIndex.concat(bNodeIndex);
                if (!edgeIds.contains(s1)) {
                    graph.addEdge(s1, bNodeIndex, nNodeIndex);
                    edgeIds.add(s1);
                    edgeIds.add(s2);
                }
            }
        }

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
        graph.setAttribute("ui.stylesheet", "node {text-alignment: above; text-size: 15px; text-color: Black; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        graph.display();
    }
    
}
