package org.iquantum.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QASMReader {

    private int numQubits;
    private Map<String, Integer> singleQubitGates;
    private Map<String, Integer> multiQubitGates;
    private Set<String> qubitConnectivity;

    public QASMReader() {
        singleQubitGates = new HashMap<>();
        multiQubitGates = new HashMap<>();
        qubitConnectivity = new HashSet<>();

        String[] gates = {"id", "x", "y", "z", "h", "sx", "sdg", "t", "tdg", "rx", "ry", "rz", "u1", "u2", "u3"};
        for (String gate : gates) {
            singleQubitGates.put(gate, 0);
        }
    }

    public void readQASMFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("qreg")) {
                    Pattern p = Pattern.compile("q\\[(\\d+)\\];");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        numQubits = Integer.parseInt(m.group(1));
                    }
                } else if (line.startsWith("cx")) {
                    Pattern p = Pattern.compile("q\\[(\\d+)\\],q\\[(\\d+)\\];");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        int qubit1 = Integer.parseInt(m.group(1));
                        int qubit2 = Integer.parseInt(m.group(2));
                        qubitConnectivity.add(String.format("(%d,%d)", qubit1, qubit2));
                    }
                    multiQubitGates.put("CX", multiQubitGates.getOrDefault("CX", 0) + 1);
                } else {
                    for (String gate : singleQubitGates.keySet()) {
                        if (line.startsWith(gate)) {
                            singleQubitGates.put(gate, singleQubitGates.get(gate) + 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    public int getNumQubits() {
        return numQubits;
    }

    public Map<String, Integer> getSingleQubitGates() {
        return singleQubitGates;
    }

    public Map<String, Integer> getMultiQubitGates() {
        return multiQubitGates;
    }

    public Set<String> getQubitConnectivity() {
        return qubitConnectivity;
    }
}

