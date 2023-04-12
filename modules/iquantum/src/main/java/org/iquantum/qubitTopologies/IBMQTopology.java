package org.iquantum.qubitTopologies;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IBMQTopology {
    public static QubitTopologyExtended getQubitTopology(String node) {
        String filePath = getFilePath(node);
        if (filePath != null) {
            return importFromCsv(filePath);
        }
        return null;
    }

    /**
     * Get the latest CSV file for the given node.
     * All CSV file names must start with the node name and end with ".csv", placed in ibmqData folder.
     * @param node The node name, e.g. ibm_hanoi
     * @return File path of the latest CSV file or null if no file is found
     */
    private static String getFilePath(String node) {
        String folderPath = "modules/iquantum/src/main/java/org/iquantum/qubitTopologies/ibmqData/";
        Path fullPath = Paths.get(System.getProperty("user.dir"), folderPath);
        File folder = new File(fullPath.toString());
        File[] files = folder.listFiles(pathname -> pathname.getName().startsWith(node) && pathname.getName().endsWith(".csv"));
        if (files != null && files.length > 0) {
            File latestFile = files[0];
            for (int i = 1; i < files.length; i++) {
                if (files[i].lastModified() > latestFile.lastModified()) {
                    latestFile = files[i];
                }
            }
            String filePath = latestFile.getPath();
            System.out.println("Loaded latest Datasheet CSV Datasheet for " + node + " at " + filePath);
            return filePath;
        } else {
            System.out.println("No CSV files found for " + node);
        }
        return null;
    }

    public static QubitTopologyExtended importFromCsv(String filename) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withNullString("-1"));
            List<int[]> edges = new ArrayList<>();
            Map<Integer, QubitTopologyExtended.QubitProperties> qubitProperties = new HashMap<>();
            Map<Integer, Map<Integer, QubitTopologyExtended.QubitEdge>> edgeProperties = new HashMap<>();

            for (CSVRecord csvRecord : csvParser) {
                int qubitIndex = Integer.parseInt(csvRecord.get("Qubit"));
                double t1Us = parseDoubleOrDefault(csvRecord.get("T1 (us)"), -1);
                double t2Us = parseDoubleOrDefault(csvRecord.get("T2 (us)"), -1);
                double frequencyGHz = parseDoubleOrDefault(csvRecord.get("Frequency (GHz)"), -1);
                double anharmonicityGHz = parseDoubleOrDefault(csvRecord.get("Anharmonicity (GHz)"), -1);
                double readoutAssignmentError = parseDoubleOrDefault(csvRecord.get("Readout assignment error "), -1);
                double probMeas0Prep1 = parseDoubleOrDefault(csvRecord.get("Prob meas0 prep1 "), -1);
                double probMeas1Prep0 = parseDoubleOrDefault(csvRecord.get("Prob meas1 prep0 "), -1);
                double readoutLengthNs = parseDoubleOrDefault(csvRecord.get("Readout length (ns)"), -1);
                double idError = parseDoubleOrDefault(csvRecord.get("ID error "), -1);
                double sxError = parseDoubleOrDefault(csvRecord.get("√x (sx) error "), -1);
                double pauliXError = parseDoubleOrDefault(csvRecord.get("Pauli-X error "), -1);
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

    private static double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
