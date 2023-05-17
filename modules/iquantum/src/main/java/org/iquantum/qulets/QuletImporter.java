package org.iquantum.qulets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.iquantum.qubitTopologies.QubitTopology;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuletImporter {

    public List<Qulet> importQuletsFromCsv(String csvFilePath) throws IOException {
        List<Qulet> qulets = new ArrayList<>();

        try (Reader reader = new FileReader(Paths.get(csvFilePath).toFile())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                int quletId = Integer.parseInt(record.get("index"));
                int numQubits = Integer.parseInt(record.get("n_qubits"));
                int numLayers = Integer.parseInt(record.get("qc_depth"));

                int numShots;
                if (record.isMapped("n_shots")) {
                    numShots = Integer.parseInt(record.get("n_shots"));
                } else {
                    numShots = new Random().nextInt(3073) + 1024;
                }

                List<String> gateSet = extractGateSet(record.get("single_qubit_gates"), record.get("multi_qubit_gates"));
//                System.out.println(gateSet);

                QubitTopology qubitTopology = extractQubitTopology(numQubits, record.get("qubit_topology"));

                qulets.add(new Qulet(quletId, numQubits, numLayers, numShots, gateSet, qubitTopology));
            }
        }

        return qulets;
    }

    private List<String> extractGateSet(String singleQubitGatesStr, String multiQubitGatesStr) {
        List<String> gateSet = new ArrayList<>();
        gateSet.addAll(extractGates(singleQubitGatesStr));
        gateSet.addAll(extractGates(multiQubitGatesStr));
        return gateSet;
    }

    private List<String> extractGates(String gatesStr) {
        gatesStr = gatesStr.substring(1, gatesStr.length() - 1); // remove the curly braces
        return Arrays.stream(gatesStr.split(","))
                .map(gate -> gate.trim().split(":")[0].replaceAll("'", ""))
                .collect(Collectors.toList());
    }

    private QubitTopology extractQubitTopology(int numQubits, String qubitTopologyStr) {
        if (qubitTopologyStr == null || qubitTopologyStr.length() <= 1) {
            return new QubitTopology(numQubits, new ArrayList<>());
        }

        List<int[]> edges = Arrays.stream(qubitTopologyStr.split(";"))
                .map(edgeStr -> {
                    edgeStr = edgeStr.trim().replaceAll("[()]", ""); // Remove parentheses and trim whitespace
                    int[] edge = new int[2];
                    String[] edgeNumbers = edgeStr.split(","); // Split by comma
                    edge[0] = Integer.parseInt(edgeNumbers[0].trim());
                    edge[1] = Integer.parseInt(edgeNumbers[1].trim());
                    return edge;
                })
                .collect(Collectors.toList());

        return new QubitTopology(numQubits, edges);
    }



}