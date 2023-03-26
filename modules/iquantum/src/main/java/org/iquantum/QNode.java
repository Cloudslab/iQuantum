package org.iquantum;

import org.cloudbus.cloudsim.Vm;

import java.util.List;
import java.util.Map;


public class QNode {
    private int id;
    private int qubits;
    private int quantumVolume;
    private double clops;
    private List<String> gateSets;
    private QubitTopology qubitTopology;
    private Map<String, Double> errorRates;

    private QuletScheduler quletScheduler;

    private QDatacenter qDatacenter;

    public QNode(int qubits, int quantumVolume, double clops, List<String> gateSets,
                 QubitTopology qubitTopology, Map<String, Double> errorRates, QuletScheduler quletScheduler) {
        this.qubits = qubits;
        this.quantumVolume = quantumVolume;
        this.clops = clops;
        this.gateSets = gateSets;
        this.qubitTopology = qubitTopology;
        this.errorRates = errorRates;
        this.quletScheduler = quletScheduler;
    }

    public int getQubits() {
        return qubits;
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

    // Other methods
    public boolean isAvailable(Qulet qulet) {
        // Check if this QNode can execute the given Qulet
        // based on its attributes and the QNode's current workload
        return true; // Replace with actual implementation
    }

    public double getExpectedCompletionTime(Qulet qulet) {
        // Calculate the expected completion time for the given Qulet
        // on this QNode, based on its attributes and the QNode's current workload
        return 0.0; // Replace with actual implementation
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

