package org.iquantum.policies.qubitMapping;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class QubitMappingBackTracking {
    /**
     * Find a mapping from the circuit qubits to the system qubits, if possible and return index mapping
     * @param qnodeTopology
     * @param quletTopology
     */
    public static Map<Integer, Integer> findMappingIndex(QubitTopology qnodeTopology, QubitTopology quletTopology) {
        Map<Integer, Integer> mapping = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        if (findMappingHelper(qnodeTopology, quletTopology, mapping, visited)) {
            return mapping;
        } else {
            return null;
        }
    }

    /**
     * Find a mapping from the circuit qubits to the system qubits, if possible and return name mapping, e.g. n0 -> c1
     * where n0 is the name of the node qubit and c1 is the name of the circuit qubit
     * @param qnodeTopology
     * @param quletTopology
     * @return
     */
    public static Map<String, String> findMapping(QubitTopology qnodeTopology, QubitTopology quletTopology) {
        Map<Integer, Integer> mapping = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        if (findMappingHelper(qnodeTopology, quletTopology, mapping, visited)) {
            Map<String, String> formattedMapping = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : mapping.entrySet()) {
                formattedMapping.put("QTask q" + entry.getKey(), "QNode q" + entry.getValue());
            }
            return formattedMapping;
        } else {
            return null;
        }
    }

    /**
     * Helper function for findMapping, using backtracking to find a mapping
     * @param qnodeTopology
     * @param quletTopology
     * @param mapping
     * @param visited
     * @return
     */
    private static boolean findMappingHelper(QubitTopology qnodeTopology, QubitTopology quletTopology, Map<Integer, Integer> mapping, HashSet<Integer> visited) {
        if (mapping.size() == quletTopology.getNumQubits()) {
            return true;
        }
        for (QubitTopology.Node circuitQubit : quletTopology.getQubits()) {
            if (!mapping.containsKey(circuitQubit.getQubitIndex())) {
                for (QubitTopology.Node systemQubit : qnodeTopology.getQubits()) {
                    if (!visited.contains(systemQubit.getQubitIndex()) && isConnected(mapping, circuitQubit, systemQubit, quletTopology, qnodeTopology)) {
                        mapping.put(circuitQubit.getQubitIndex(), systemQubit.getQubitIndex());
                        visited.add(systemQubit.getQubitIndex());
                        if (findMappingHelper(qnodeTopology, quletTopology, mapping, visited)) {
                            return true;
                        }
                        mapping.remove(circuitQubit.getQubitIndex());
                        visited.remove(systemQubit.getQubitIndex());
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isConnected(Map<Integer, Integer> mapping, QubitTopology.Node quletQubit, QubitTopology.Node qnodeQubit, QubitTopology quletTopology, QubitTopology qnodeTopology) {
        for (QubitTopology.Node connectedCircuitQubit : quletQubit.getNeighbors()) {
            Integer mappedSystemQubitIndex = mapping.get(connectedCircuitQubit.getQubitIndex());
            if (mappedSystemQubitIndex != null) {
                QubitTopology.Node mappedSystemQubit = qnodeTopology.getQubits().get(mappedSystemQubitIndex);
                if (!qnodeQubit.getNeighbors().contains(mappedSystemQubit)) {
                    return false;
                }
            }
        }
        return true;
    }
}