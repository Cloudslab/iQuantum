package org.iquantum.lists;

import org.iquantum.backends.quantum.QPU;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;

import java.util.*;

public class QPUList {
    private Map<Integer, QPU> qpuMap;

    public QPUList() {
        qpuMap = new HashMap<>();
    }

    public void add(QPU qpu) {
        qpuMap.put(qpu.getId(), qpu);
    }
    public QPU getById(int id) {
        return qpuMap.get(id);
    }

    public List<QPU> getQPUList() {
        return (List<QPU>) qpuMap.values();
    }

    public int getNumberOfQPU() {
        return qpuMap.size();
    }

    public int getNumQubitsOfQPUById(int id) {
        return qpuMap.get(id).getNumQubits();
    }

    public int getTotalNumQubits() {
        int numQubits = 0;
        for (QPU qpu : qpuMap.values()) {
            numQubits += qpu.getNumQubits();
        }
        return numQubits;
    }


    public double getClopsOfQPUById(int id) {
        return qpuMap.get(id).getClops();
    }

    public double getTotalClops() {
        double clops = 0.0;
        for (QPU qpu : qpuMap.values()) {
            clops += qpu.getClops();
        }
        return clops;
    }

    public double getMaxClops() {
        double maxClops = 0.0;
        for (QPU qpu : qpuMap.values()) {
            if (qpu.getClops() > maxClops) {
                maxClops = qpu.getClops();
            }
        }
        return maxClops;
    }

    public Set<String> getAllGateSets() {
        Set<String> allGateSets = new HashSet<>();
        for (QPU qpu : qpuMap.values()) {
            allGateSets.addAll(qpu.getGateSets());
        }
        return allGateSets;
    }

    public List<String> getGateSetsOfQPUById(int id) {
        return qpuMap.get(id).getGateSets();
    }

    public QubitTopology getQubitTopologyOfQPUById(int id) {
        return qpuMap.get(id).getQubitTopology();
    }

    public List<QubitTopology> getAllQubitTopologies() {
        List<QubitTopology> allQubitTopologies = new ArrayList<>();
        for (QPU qpu : qpuMap.values()) {
            allQubitTopologies.add(qpu.getQubitTopology());
        }
        return allQubitTopologies;
    }
}
