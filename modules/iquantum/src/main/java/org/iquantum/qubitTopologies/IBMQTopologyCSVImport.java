package org.iquantum.qubitTopologies;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IBMQTopologyCSVImport {
    public static QubitTopologyExtended importFromCsv(String filename) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<int[]> edges = new ArrayList<>();
            Map<Integer, QubitTopologyExtended.QubitProperties> qubitProperties = new HashMap<>();
            Map<Integer, Map<Integer, QubitTopologyExtended.QubitEdge>> edgeProperties = new HashMap<>();

            for (CSVRecord csvRecord : csvParser) {
                int qubitIndex = Integer.parseInt(csvRecord.get("Qubit"));
                double t1Us = Double.parseDouble(csvRecord.get("T1 (us)"));
                double t2Us = Double.parseDouble(csvRecord.get("T2 (us)"));
                double frequencyGHz = Double.parseDouble(csvRecord.get("Frequency (GHz)"));
                double anharmonicityGHz = Double.parseDouble(csvRecord.get("Anharmonicity (GHz)"));
                double readoutAssignmentError = Double.parseDouble(csvRecord.get("Readout assignment error "));
                double probMeas0Prep1 = Double.parseDouble(csvRecord.get("Prob meas0 prep1 "));
                double probMeas1Prep0 = Double.parseDouble(csvRecord.get("Prob meas1 prep0 "));
                double readoutLengthNs = Double.parseDouble(csvRecord.get("Readout length (ns)"));
                double idError = Double.parseDouble(csvRecord.get("ID error "));
                double sxError = Double.parseDouble(csvRecord.get("√x (sx) error "));
                double pauliXError = Double.parseDouble(csvRecord.get("Pauli-X error "));
                String cnotErrors = csvRecord.get("CNOT error ");
                String gateTimes = csvRecord.get("Gate time (ns)");


                // Add qubit properties
                qubitProperties.put(qubitIndex, new QubitTopologyExtended.QubitProperties(
                        t1Us, t2Us, frequencyGHz, anharmonicityGHz, readoutAssignmentError,
                        probMeas0Prep1, probMeas1Prep0, readoutLengthNs, idError, sxError, pauliXError
                ));

                // Parse edge properties
                Map<Integer, QubitTopologyExtended.QubitEdge> edgesMap = new HashMap<>();
                String[] cnotErrorsArray = cnotErrors.split("; ");
                String[] gateTimesArray = gateTimes.split("; ");

                for (String cnotError : cnotErrorsArray) {
                    String[] parts = cnotError.split(":");
                    String[] qubitIndices = parts[0].split("_");
                    int cqubitIndex = Integer.parseInt(qubitIndices[0]);
                    int neighborIndex = Integer.parseInt(qubitIndices[1]);
                    double errorRate = Double.parseDouble(parts[1]);
                    if (cqubitIndex == qubitIndex) {
                        edges.add(new int[]{qubitIndex, neighborIndex});
                        edgesMap.put(neighborIndex, new QubitTopologyExtended.QubitEdge(neighborIndex, errorRate, 0.0));
                    } else {
                        throw new IllegalArgumentException("Error: CNOT error rate is not for the correct qubit. Please check the CSV file.");
                    }

                }
                for (String gateTime : gateTimesArray) {
                    String[] parts = gateTime.split(":");
                    String[] qubitIndices = parts[0].split("_");
                    int cqubitIndex = Integer.parseInt(qubitIndices[0]);
                    int neighborIndex = Integer.parseInt(qubitIndices[1]);
                    double time = Double.parseDouble(parts[1]);
                    if (cqubitIndex == qubitIndex) {
                        QubitTopologyExtended.QubitEdge edge = edgesMap.get(neighborIndex);
                        if (edge != null) {
                            edge.setGateTime(time);
                        }
                    } else {
                        throw new IllegalArgumentException("Error: Gate time is not for the correct qubit. Please check the CSV file.");
                    }
                }
                edgeProperties.put(qubitIndex, edgesMap);
            }
            csvParser.close();
            reader.close();
            return new QubitTopologyExtended(qubitProperties.size(), edges, qubitProperties, edgeProperties);
        } catch (IOException e) {
            System.out.println("Error while reading CSV file: " + e);
        }
        return null;
    }
}
