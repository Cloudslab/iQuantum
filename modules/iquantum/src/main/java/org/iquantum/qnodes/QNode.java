/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.qnodes;

import org.iquantum.qdatacenters.QDatacenter;
import org.iquantum.qubitTopologies.QubitTopology;
import org.iquantum.schedulers.QuletScheduler;
import java.util.List;


public class QNode {
    private int id;
    private int numQubits;
    private int quantumVolume;
    private double clops;
    private List<String> gateSets;
    private QubitTopology qubitTopology;
    private QuletScheduler quletScheduler;

    private QDatacenter qDatacenter;

    /**
     * Creates a new QNode object.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     * @param quletScheduler qulet scheduler of the quantum node
     */
    public QNode(int id, int numQubits, int quantumVolume, double clops, List<String> gateSets,
                 QubitTopology qubitTopology, QuletScheduler quletScheduler) {
        // Validate the parameters
        validateParameters(numQubits, quantumVolume, clops, gateSets, qubitTopology, quletScheduler);

        this.id = id;
        this.numQubits = numQubits;
        this.quantumVolume = quantumVolume;
        this.clops = clops;
        this.gateSets = gateSets;
        this.qubitTopology = qubitTopology;
        this.quletScheduler = quletScheduler;
    }

    /**
     * Validates the parameters.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     * @param errorRates error rates of the quantum node
     * @param quletScheduler qulet scheduler of the quantum node
     */
    private static void validateParameters(int numQubits, int quantumVolume, double clops, List<String> gateSets,
                                           QubitTopology qubitTopology, QuletScheduler quletScheduler) {

        if (numQubits <= 0) {
            throw new IllegalArgumentException("numQubits must be greater than zero");
        }

        if (quantumVolume <= 0) {
            throw new IllegalArgumentException("quantumVolume must be greater than zero");
        }

        if (clops <= 0) {
            throw new IllegalArgumentException("clops must be greater than zero");
        }

        if (gateSets == null || gateSets.isEmpty()) {
            throw new IllegalArgumentException("gateSets must not be null or empty");
        }

        if (qubitTopology == null) {
            throw new IllegalArgumentException("qubitTopology must not be null");
        }

        if (quletScheduler == null) {
            throw new IllegalArgumentException("quletScheduler must not be null");
        }

        if (quantumVolume > Math.pow(2, numQubits)) {
            throw new IllegalArgumentException("quantumVolume must be less than or equal to 2^numQubits");
        }
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

