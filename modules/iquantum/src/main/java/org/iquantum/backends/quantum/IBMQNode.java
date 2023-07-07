package org.iquantum.backends.quantum;

import org.iquantum.tasks.qubittopologies.IBMQTopology;
import org.iquantum.tasks.qubittopologies.QubitTopologyExtended;
import org.iquantum.policies.qctasks.QuletScheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class IBMQNode {

    public static QNode createNode(int id, String node, QuletScheduler quletScheduler) {
        int numQubits = 0;
        int quantumVolume = 0;
        int clops = 0;
        ArrayList<String> basicGateset;
        // Predefined common basic gate sets: rz, sx, x, measure, cx
        ArrayList<String> basicGateset_1 = new ArrayList<>(Arrays.asList("cx", "id", "rz", "sx", "x", "measure"));
        ArrayList<String> basicGateset_2 = new ArrayList<>(Arrays.asList("ECR", "ID", "RZ", "SX", "X"));
        switch (node) {
            case "ibm_washington":
                numQubits = 127;
                quantumVolume = 64;
                clops = 850;
                basicGateset = basicGateset_1;
                break;
            case "ibm_sherbrooke":
                numQubits = 127;
                quantumVolume = 32;
                clops = 904;
                basicGateset = basicGateset_2;
                break;
            case "ibmq_kolkata":
                numQubits = 27;
                quantumVolume = 128;
                clops = 2000;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_mumbai":
                numQubits = 27;
                quantumVolume = 128;
                clops = 1800;
                basicGateset = basicGateset_1;
                break;
            case "ibm_cairo":
                numQubits = 27;
                quantumVolume = 64;
                clops = 2400;
                basicGateset = basicGateset_1;
                break;
            case "ibm_auckland":
                numQubits = 27;
                quantumVolume = 64;
                clops = 2400;
                basicGateset = basicGateset_1;
                break;
            case "ibm_hanoi":
                numQubits = 27;
                quantumVolume = 64;
                clops = 2300;
                basicGateset = basicGateset_1;
                break;
            case "ibm_geneva":
                numQubits = 27;
                quantumVolume = 32;
                clops = 1900;
                basicGateset = basicGateset_1;
                break;
            case "ibm_guadalupe":
                numQubits = 16;
                quantumVolume = 32;
                clops = 2400;
                basicGateset = basicGateset_1;
                break;
            case "ibm_perth":
                numQubits = 7;
                quantumVolume = 32;
                clops = 2900;
                basicGateset = basicGateset_1;
                break;
            case "ibm_lagos":
                numQubits = 7;
                quantumVolume = 32;
                clops = 2700;
                basicGateset = basicGateset_1;
                break;
            case "ibm_nairobi":
                numQubits = 7;
                quantumVolume = 32;
                clops = 2600;
                basicGateset = basicGateset_1;
                break;
            case "ibm_oslo":
                numQubits = 7;
                quantumVolume = 32;
                clops = 2600;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_jakarta":
                numQubits = 7;
                quantumVolume = 16;
                clops = 2400;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_manila":
                numQubits = 5;
                quantumVolume = 32;
                clops = 2800;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_quito":
                numQubits = 5;
                quantumVolume = 16;
                clops = 2500;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_belem":
                numQubits = 5;
                quantumVolume = 16;
                clops = 2500;
                basicGateset = basicGateset_1;
                break;
            case "ibmq_lima":
                numQubits = 5;
                quantumVolume = 8;
                clops = 2700;
                basicGateset = basicGateset_1;
                break;
            default:
                throw new IllegalArgumentException("Invalid IBMQ Node name");
        }
        QubitTopologyExtended qubitTopology = IBMQTopology.getQubitTopology(node);
        return new QNode(id, numQubits, quantumVolume, clops, basicGateset, qubitTopology, quletScheduler);
    }
}
