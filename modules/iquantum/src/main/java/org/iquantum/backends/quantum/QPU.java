package org.iquantum.backends.quantum;

import org.iquantum.backends.quantum.qubittopologies.QubitTopology;

import java.util.List;

//TODO: Implement QPU as processing element for QNode
public class QPU {
    private int id;

    private int numQubits;

    private int quantumVolume;

    private double clops;

    private List<String> gateSets;

    private QubitTopology qubitTopology;

    private int status;

    /** Denotes QPU is FREE for allocation. */
    public static final int FREE = 1;

    /** Denotes QPU is allocated and hence busy processing some QTasks. */
    public static final int BUSY = 2;
    /**
     * Denotes Pe is failed and hence it can't process any QTask at this moment. This QPU is
     * failed because it belongs to a machine which is also failed.
     */
    public static final int FAILED = 3;

    public QPU(int id, int numQubits, int quantumVolume, double clops, List<String> gateSets,
               QubitTopology qubitTopology) {
        // Validate the parameters
        validateParameters(numQubits, quantumVolume, clops, gateSets, qubitTopology);
        setId(id);
        this.numQubits = numQubits;
        this.quantumVolume = quantumVolume;
        this.clops = clops;
        this.gateSets = gateSets;
        this.qubitTopology = qubitTopology;
        status = FREE;
    }

    /**
     * Validates the parameters.
     * @param numQubits number of qubits
     * @param quantumVolume quantum volume
     * @param clops circuit layer operations per second (CLOPS)
     * @param gateSets all gate sets supported by the quantum node
     * @param qubitTopology qubit topology of the quantum node
     */
    private static void validateParameters(int numQubits, int quantumVolume, double clops, List<String> gateSets,
                                           QubitTopology qubitTopology) {

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

        if (quantumVolume > Math.pow(2, numQubits)) {
            throw new IllegalArgumentException("quantumVolume must be less than or equal to 2^numQubits");
        }
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public int getQuantumVolume() {
        return quantumVolume;
    }

    public double getClops() {
        return clops;
    }

    public List<String> getGateSets() {
        return gateSets;
    }

    public QubitTopology getQubitTopology() {
        return qubitTopology;
    }



}
