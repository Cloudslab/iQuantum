package org.iquantum.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.iquantum.tasks.QTask;

public class QTaskExporter {

    public static void printQTaskList(List<QTask> list) {
        int size = list.size();
        QTask QTask;

        String indent = "  ";
        Log.printLine("\n============= OUTPUT =============\n");
        Log.printLine("ID" + indent+ indent + "Status" + indent + indent
                + "DCenID" + indent + "NodeID" + indent + "Exec Time" + indent
                + "Start Time" + indent + "Finish Time" + indent + "No.Qubits" + indent + "No.Layers" + indent
                + "No.Shots" + indent + "Cost ($)" + indent + "Application");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            QTask = list.get(i);
            Log.print(formatter(QTask.getQTaskId(),6));
            if (QTask.getQTaskStatus() == QTask.SUCCESS) {
                Log.print("SUCCESS   ");
                Log.printLine(formatter(QTask.getResourceId(),8)
                        + formatter(QTask.getQNodeId(),8)
                        + formatter( dft.format(QTask.getActualQPUTime()),11)
                        + formatter(dft.format(QTask.getExecStartTime()),12)
                        + formatter(dft.format(QTask.getFinishTime()),13)
                        + formatter(QTask.getNumQubits(),11)
                        + formatter(QTask.getNumLayers(),11)
                        + formatter(QTask.getNumShots(),10)
                        + formatter(dft.format(QTask.getCost()),10)
                        + formatter(QTask.getApplicationName(),10));

            } else {
                Log.print("FAILED");
                Log.printLine(formatter(QTask.getResourceId(),10)
                        + formatter( QTask.getQNodeId(),10)
                        + formatter(QTask.getQTaskStatusString(),10));
            }
        }
    }

    public static String formatter(String input,int size) {
        return String.format("%-" + size + "s", input);
    }

    public static String formatter(int input, int size) {
        return String.format("%-" + size + "s", String.valueOf(input));
    }

    public static void extractQTaskListToCSV(List<QTask> list, String fileName) {
        try {
            Path outputFolderPath = Paths.get("output");
            if (!Files.exists(outputFolderPath)) {
                Files.createDirectories(outputFolderPath);
            }

            String outputFilePath = getOutputFilePath(outputFolderPath, fileName);
            try (FileWriter writer = new FileWriter(outputFilePath)) {

                int size = list.size();
                QTask QTask;

                String indent = "   ";
                String header = "QTask_ID,Status,QDCenter,QNode_ID,Execution_Time,Start_Time,Finish_Time,Waiting_Time,No_Qubits,No_Layers,Shots,ECL,Cost,Application,Total_waiting_time";
                writer.write(header);
                writer.write(System.lineSeparator());

                DecimalFormat dft = new DecimalFormat("###.##");
                double totalWaitingTime = 0;
                for (int i = 0; i < size; i++) {
                    QTask = list.get(i);
                    StringBuilder lineBuilder = new StringBuilder();
                    lineBuilder.append(QTask.getQTaskId()).append(",")
                            .append(QTask.getQTaskStatus() == QTask.SUCCESS ? "SUCCESS" : "FAILED").append(",")
                            .append(QTask.getResourceId()).append(",")
                            .append(QTask.getQNodeId()).append(",")
                            .append(dft.format(QTask.getActualQPUTime())).append(",")
                            .append(dft.format(QTask.getExecStartTime())).append(",")
                            .append(dft.format(QTask.getFinishTime())).append(",")
                            .append(dft.format(QTask.getWaitingTime())).append(",")
                            .append(QTask.getNumQubits()).append(",")
                            .append(QTask.getNumLayers()).append(",")
                            .append(QTask.getNumShots()).append(",")
                            .append(QTask.getNumECL()).append(",")
                            .append(dft.format(QTask.getCost())).append(",")
                            .append(QTask.getApplicationName());
                    writer.write(lineBuilder.toString());
                    writer.write(System.lineSeparator());
                    // Calculating Total_Waiting_Time
                    totalWaitingTime += QTask.getWaitingTime();
                }
                // writer.append(",,,,,,,,,,,,,,"+String.valueOf(totalWaitingTime));
                // writer.write(System.lineSeparator());
                writer.flush();
                // Reading file to add Post_Simulation data
                List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
                String firstline = lines.get(1);
                lines.set(1, firstline + "," + dft.format(totalWaitingTime));
                Files.write(Paths.get(outputFilePath), lines);



            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getOutputFilePath(Path outputFolderPath, String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timestamp = formatter.format(new Date());
        String modifiedFileName = fileName.replace(" ", "_");
        String outputFileName = modifiedFileName + "-" + timestamp + ".csv";
        return outputFolderPath.resolve(outputFileName).toString();
    }
}
