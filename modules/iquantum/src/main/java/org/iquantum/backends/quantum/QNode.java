/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.backends.quantum;

import org.iquantum.datacenters.QDatacenter;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.policies.qtasks.QTaskScheduler;
import java.util.List;


public class QNode {
    private int id;
    private int numQubits;
    private int quantumVolume;
    private double clops;
    private List<String> gateSets;
    private QubitTopology qubitTopology;
    private QTaskScheduler QTaskScheduler;
    private QDatacenter qDatacenter;

    /**
     * Creates a new QNode object.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     * @param QTaskScheduler qtask scheduler of the quantum node
     */
    public QNode(int id, int numQubits, int quantumVolume, double clops, List<String> gateSets,
                 QubitTopology qubitTopology, QTaskScheduler QTaskScheduler) {
        // Validate the parameters
        validateParameters(numQubits, quantumVolume, clops, gateSets, qubitTopology, QTaskScheduler);

        this.id = id;
        this.numQubits = numQubits;
        this.quantumVolume = quantumVolume;
        this.clops = clops;
        this.gateSets = gateSets;
        this.qubitTopology = qubitTopology;
        this.QTaskScheduler = QTaskScheduler;
    }


    /**
     * Validates the parameters.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     * @param QTaskScheduler qtask scheduler of the quantum node
     */
    private static void validateParameters(int numQubits, int quantumVolume, double clops, List<String> gateSets,
                                           QubitTopology qubitTopology, QTaskScheduler QTaskScheduler) {

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

        if (QTaskScheduler == null) {
            throw new IllegalArgumentException("qTaskScheduler must not be null");
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

    public QTaskScheduler getQTaskScheduler() {
        return QTaskScheduler;
    }

    public void setQTaskScheduler(QTaskScheduler QTaskScheduler) {
        this.QTaskScheduler = QTaskScheduler;
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

    public double updateQTaskProcessing(double currentTime) {
        double smallerTime = Double.MAX_VALUE;
        double time = getQTaskScheduler().updateQNodeProcessing(currentTime, getCLOPS());
        if (time > 0.0 && time < smallerTime) {
            smallerTime = time;
        }
        return smallerTime;
    }
}

