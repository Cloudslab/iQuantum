package org.iquantum.backends.quantum.qubittopologies;

import java.util.List;
import java.util.Map;

public class QubitTopologyExtended extends QubitTopology {
    private int numQubits;
    private List<int[]> edges;
    private Map<Integer, QubitProperties> qubitProperties;
    private Map<Integer, Map<Integer, QubitEdge>> edgeProperties;

    /**
     * Constructor for QubitTopologyExtended
     * @param numQubits number of qubits in the topology
     * @param edges list of edges (qubit connectivity) in the topology
     * @param qubitProperties map of single QubitProperties (indexed by qubit index)
     * @param edgeProperties map of qubit edge properties (indexed by qubit index) - CNOT error and Gate time
     */
    public QubitTopologyExtended(int numQubits, List<int[]> edges, Map<Integer, QubitProperties> qubitProperties,
                                 Map<Integer, Map<Integer, QubitEdge>> edgeProperties) {
        super(numQubits, edges);
        this.numQubits = numQubits;
        this.edges = edges;
        this.qubitProperties = qubitProperties;
        this.edgeProperties = edgeProperties;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public List<int[]> getEdges() {
        return edges;
    }

    public QubitProperties getQubitProperties(int qubitIndex) {
        return qubitProperties.get(qubitIndex);
    }

    public Map<Integer, QubitEdge> getEdgeProperties(int qubitIndex) {
        return edgeProperties.get(qubitIndex);
    }

    public static class QubitProperties {
        private double t1Us;
        private double t2Us;
        private double frequencyGHz;
        private double anharmonicityGHz;
        private double readoutAssignmentError;
        private double probMeas0Prep1;
        private double probMeas1Prep0;
        private double readoutLengthNs;
        private double idError;
        private double sxError;
        private double pauliXError;

        public QubitProperties(double t1Us, double t2Us, double frequencyGHz, double anharmonicityGHz,
                               double readoutAssignmentError, double probMeas0Prep1, double probMeas1Prep0,
                               double readoutLengthNs, double idError, double sxError, double pauliXError) {
            this.t1Us = t1Us;
            this.t2Us = t2Us;
            this.frequencyGHz = frequencyGHz;
            this.anharmonicityGHz = anharmonicityGHz;
            this.readoutAssignmentError = readoutAssignmentError;
            this.probMeas0Prep1 = probMeas0Prep1;
            this.probMeas1Prep0 = probMeas1Prep0;
            this.readoutLengthNs = readoutLengthNs;
            this.idError = idError;
            this.sxError = sxError;
            this.pauliXError = pauliXError;
        }

        public double getT1Us() {
            return t1Us;
        }

        public double getT2Us() {
            return t2Us;
        }

        public double getFrequencyGHz() {
            return frequencyGHz;
        }

        public double getAnharmonicityGHz() {
            return anharmonicityGHz;
        }

        public double getReadoutAssignmentError() {
            return readoutAssignmentError;
        }

        public double getProbMeas0Prep1() {
            return probMeas0Prep1;
        }

        public double getProbMeas1Prep0() {
            return probMeas1Prep0;
        }

        public double getReadoutLengthNs() {
            return readoutLengthNs;
        }

        public double getIdError() {
            return idError;
        }

        public double getSxError() {
            return sxError;
        }

        public double getPauliXError() {
            return pauliXError;
        }
    }
    public static class QubitEdge {
        private int qubitIndex;
        private double cnotErrorRate;
        private double gateTime;

        public QubitEdge(int qubitIndex, double cnotErrorRate, double gateTime) {
            this.qubitIndex = qubitIndex;
            this.cnotErrorRate = cnotErrorRate;
            this.gateTime = gateTime;
        }

        public int getQubitIndex() {
            return qubitIndex;
        }

        public double getCnotErrorRate() {
            return cnotErrorRate;
        }

        public double getGateTime() {
            return gateTime;
        }

        public void setGateTime(double gateTime) {
            this.gateTime = gateTime;
        }

        public void setCnotErrorRate(double cnotErrorRate) {
            this.cnotErrorRate = cnotErrorRate;
        }
    }

    public void printAllQubitMetrics() {
        for (int i = 0; i < numQubits; i++) {
            QubitProperties properties = qubitProperties.get(i);
            System.out.println("Qubit " + i + ":");
            System.out.println("+ T1 (us) = " + properties.getT1Us());
            System.out.println("+ T2 (us) = " + properties.getT2Us());
            System.out.println("+ Frequency (GHz) = " + properties.getFrequencyGHz());
            System.out.println("+ Anharmonicity (GHz) = " + properties.getAnharmonicityGHz());
            System.out.println("+ Readout assignment error = " + properties.getReadoutAssignmentError());
            System.out.println("+ Prob meas0 prep1 = " + properties.getProbMeas0Prep1());
            System.out.println("+ Prob meas1 prep0 = " + properties.getProbMeas1Prep0());
            System.out.println("+ Readout length (ns) = " + properties.getReadoutLengthNs());
            System.out.println("+ ID error = " + properties.getIdError());
            System.out.println("+ √x (sx) error = " + properties.getSxError());
            System.out.println("+ Pauli-X error = " + properties.getPauliXError());
        }
    }

    public void printAllQubitConnectivity() {
        System.out.println("All qubit connectivity:");
        for (int[] edge : edges) {
            int qubitIndex = edge[0];
            int neighborIndex = edge[1];
            String edgeString = "q" + qubitIndex + "_q" + neighborIndex + ":";
            Map<Integer, QubitEdge> edgeMap = edgeProperties.get(qubitIndex);
            QubitEdge qubitEdge = edgeMap.get(neighborIndex);
            edgeString += "\n+ CNOT error rate = " + qubitEdge.getCnotErrorRate();
            edgeString += "\n+ Gate time (ns) = " + qubitEdge.getGateTime();
            System.out.println(edgeString);
        }
    }

    public void printQubitMetrics(int qubitIndex) {
        if (qubitIndex < 0 || qubitIndex >= numQubits) {
            System.err.println("Error: Qubit index out of scope");
            return;
        }
        QubitProperties properties = qubitProperties.get(qubitIndex);
        System.out.println("All metrics of Qubit " + qubitIndex + ":");
        System.out.printf("+ T1 (us) = %.2f\n", properties.getT1Us());
        System.out.printf("+ T2 (us) = %.2f\n", properties.getT2Us());
        System.out.printf("+ Frequency (GHz) = %.2f\n", properties.getFrequencyGHz());
        System.out.printf("+ Anharmonicity (GHz) = %.2f\n", properties.getAnharmonicityGHz());
        System.out.printf("+ Readout assignment error = %.2f\n", properties.getReadoutAssignmentError());
        System.out.printf("+ Prob meas0 prep1 = %.2f\n", properties.getProbMeas0Prep1());
        System.out.printf("+ Prob meas1 prep0 = %.2f\n", properties.getProbMeas1Prep0());
        System.out.printf("+ Readout length (ns) = %.2f\n", properties.getReadoutLengthNs());
        System.out.printf("+ ID error = %.2e\n", properties.getIdError());
        System.out.printf("+ √x (sx) error = %.2e\n", properties.getSxError());
        System.out.printf("+ Pauli-X error = %.2e\n", properties.getPauliXError());
    }

    public void printQubitConnectivityMetrics(int qubitIndex, int neighborIndex) {
        Map<Integer, QubitEdge> edgeMap = edgeProperties.get(qubitIndex);
        if (edgeMap == null) {
            System.out.println("Error: No connectivity information available for qubit " + qubitIndex);
            return;
        }
        QubitEdge qubitEdge = edgeMap.get(neighborIndex);
        if (qubitEdge == null) {
            System.out.println("Error: No connectivity information available between qubits " + qubitIndex + " and " + neighborIndex);
            return;
        }
        System.out.println("Connectivity between q" + qubitIndex + "_q" + neighborIndex + ":");
        System.out.printf("+ CNOT error rate = %.2e\n", qubitEdge.getCnotErrorRate());
        System.out.printf("+ Gate time (ns) = %.2f\n", qubitEdge.getGateTime());
    }
}
