/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum;

import org.iquantum.schedulers.QuletScheduler;
import java.util.List;
import java.util.Map;


public class QNode {
    private int id;
    private int numQubits;
    private int quantumVolume;
    private double clops;
    private List<String> gateSets;
    private QubitTopology qubitTopology;
    private Map<String, Double> errorRates;

    private QuletScheduler quletScheduler;

    private QDatacenter qDatacenter;

    /**
     * Creates a new QNode object.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     * @param errorRates error rates of the quantum node
     * @param quletScheduler qulet scheduler of the quantum node
     */
    public QNode(int id, int numQubits, int quantumVolume, double clops, List<String> gateSets,
                 QubitTopology qubitTopology, Map<String, Double> errorRates, QuletScheduler quletScheduler) {
        this.id = id;
        this.numQubits = numQubits;
        this.quantumVolume = quantumVolume;
        this.clops = clops;
        this.gateSets = gateSets;
        this.qubitTopology = qubitTopology;
        this.errorRates = errorRates;
        this.quletScheduler = quletScheduler;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public int getQuantumVolume() {
        return quantumVolume;
    }

    public double getCLOPS() {
        return clops;
    }

    public List<String> getGateSets() {
        return gateSets;
    }

    public QubitTopology getQubitTopology() {
        return qubitTopology;
    }

    public Map<String, Double> getErrorRates() {
        return errorRates;
    }

    public QuletScheduler getQuletScheduler() {
        return quletScheduler;
    }

    public void setQuletScheduler(QuletScheduler quletScheduler) {
        this.quletScheduler = quletScheduler;
    }

    public int getId() {
        return id;
    }

    public void setQDatacenter(QDatacenter qDatacenter) {
        this.qDatacenter = qDatacenter;
    }

    public QDatacenter getQDatacenter() {
        return qDatacenter;
    }

    public double updateQuletProcessing(double currentTime) {
        double smallerTime = Double.MAX_VALUE;
        double time = getQuletScheduler().updateQNodeProcessing(currentTime, getCLOPS());
        if (time > 0.0 && time < smallerTime) {
            smallerTime = time;
        }
        return smallerTime;
    }
}

