package org.iquantum.qubitTopologies;

import java.nio.file.Path;
import java.nio.file.Paths;

public class IBMQTopologies {

    public static QubitTopologyExtended IBMQ7Oslo() {
        String filePath = "modules/iquantum/src/main/java/org/iquantum/qubitTopologies/ibmqData/ibm_oslo.csv";
        Path path = Paths.get(System.getProperty("user.dir"), filePath);
        QubitTopologyExtended topology = IBMQTopologyCSVImport.importFromCsv(path.toString());
        topology.printAllQubitConnectivity();
        return topology;
    }

    public static QubitTopologyExtended IBMQ5Manila() {
        String filePath = "modules/iquantum/src/main/java/org/iquantum/qubitTopologies/ibmqData/ibmq_manila.csv";
        Path path = Paths.get(System.getProperty("user.dir"), filePath);
        QubitTopologyExtended topology = IBMQTopologyCSVImport.importFromCsv(path.toString());
        topology.printAllQubitConnectivity();
        return topology;
    }
}
