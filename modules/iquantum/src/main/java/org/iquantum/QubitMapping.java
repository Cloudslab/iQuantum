package org.iquantum;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class QubitMapping {
    /**
     * Find a mapping from the circuit qubits to the system qubits, if possible and return index mapping
     * @param systemTopology
     * @param circuitTopology
     */
    public static Map<Integer, Integer> findMappingIndex(QubitTopology systemTopology, QubitTopology circuitTopology) {
        Map<Integer, Integer> mapping = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        if (findMappingHelper(systemTopology, circuitTopology, mapping, visited)) {
            return mapping;
        } else {
            return null;
        }
    }

    /**
     * Find a mapping from the circuit qubits to the system qubits, if possible and return name mapping, e.g. n0 -> c1
     * where n0 is the name of the node qubit and c1 is the name of the circuit qubit
     * @param systemTopology
     * @param circuitTopology
     * @return
     */
    public static Map<String, String> findMapping(QubitTopology systemTopology, QubitTopology circuitTopology) {
        Map<Integer, Integer> mapping = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        if (findMappingHelper(systemTopology, circuitTopology, mapping, visited)) {
            Map<String, String> formattedMapping = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : mapping.entrySet()) {
                formattedMapping.put("n" + entry.getKey(), "c" + entry.getValue());
            }
            return formattedMapping;
        } else {
            return null;
        }
    }

    /**
     * Helper function for findMapping, using backtracking to find a mapping
     * @param systemTopology
     * @param circuitTopology
     * @param mapping
     * @param visited
     * @return
     */
    private static boolean findMappingHelper(QubitTopology systemTopology, QubitTopology circuitTopology, Map<Integer, Integer> mapping, HashSet<Integer> visited) {
        if (mapping.size() == circuitTopology.getNumQubits()) {
            return true;
        }

        for (QubitTopology.Node circuitQubit : circuitTopology.getQubits()) {
            if (!mapping.containsKey(circuitQubit.qubitIndex)) {
                for (QubitTopology.Node systemQubit : systemTopology.getQubits()) {
                    if (!visited.contains(systemQubit.qubitIndex) && isConnected(mapping, circuitQubit, systemQubit, circuitTopology, systemTopology)) {
                        mapping.put(circuitQubit.qubitIndex, systemQubit.qubitIndex);
                        visited.add(systemQubit.qubitIndex);

                        if (findMappingHelper(systemTopology, circuitTopology, mapping, visited)) {
                            return true;
                        }

                        mapping.remove(circuitQubit.qubitIndex);
                        visited.remove(systemQubit.qubitIndex);
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isConnected(Map<Integer, Integer> mapping, QubitTopology.Node circuitQubit, QubitTopology.Node systemQubit, QubitTopology circuitTopology, QubitTopology systemTopology) {
        for (QubitTopology.Node connectedCircuitQubit : circuitQubit.neighbors) {
            Integer mappedSystemQubitIndex = mapping.get(connectedCircuitQubit.qubitIndex);
            if (mappedSystemQubitIndex != null) {
                QubitTopology.Node mappedSystemQubit = systemTopology.getQubits().get(mappedSystemQubitIndex);
                if (!systemQubit.neighbors.contains(mappedSystemQubit)) {
                    return false;
                }
            }
        }
        return true;
    }
}