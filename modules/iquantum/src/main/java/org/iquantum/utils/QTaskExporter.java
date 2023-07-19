package org.iquantum.utils;

import org.iquantum.tasks.QTask;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QTaskExporter {

    public static void printQTaskList(List<QTask> list) {
        int size = list.size();
        QTask QTask;

        String indent = "   ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("QTask ID" + indent + "Status" + indent
                + "QDCenter" + indent + "QNode ID" + indent + "Execution Time" + indent
                + "Start Time" + indent + "Finish Time" + indent + "No.Qubits" + indent + "No.Layers" + indent + "No.Shots" + indent + "Cost ($)" + indent + "Application");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            QTask = list.get(i);
            Log.print(indent + QTask.getQTaskId() + indent + indent);
            if (QTask.getQTaskStatus() == QTask.SUCCESS) {
                Log.print("SUCCESS");
                Log.printLine(indent + indent + QTask.getResourceId()
                        + indent + indent + indent + QTask.getQNodeId()
                        + indent + indent + indent + dft.format(QTask.getActualQPUTime())
                        + indent + indent + indent + indent + dft.format(QTask.getExecStartTime())
                        + indent + indent + indent + dft.format(QTask.getFinishTime())
                        + indent + indent + indent + QTask.getNumQubits()
                        + indent + indent + indent + QTask.getNumLayers()
                        + indent + indent + indent + QTask.getNumShots()
                        + indent + indent + indent + dft.format(QTask.getCost())
                        + indent + indent + indent + QTask.getApplicationName()
                );

            }
            else {
                Log.print("FAILED");
                Log.printLine(indent + indent + QTask.getResourceId()
                        + indent + indent + indent + QTask.getQNodeId()
                        + indent + indent + indent + QTask.getQTaskStatusString());
            }
        }
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
                String header = "QTask_ID,Status,QDCenter,QNode_ID,Execution_Time,Start_Time,Finish_Time,No_Qubits,No_Layers,Shots,Cost,Application";
                writer.write(header);
                writer.write(System.lineSeparator());

                DecimalFormat dft = new DecimalFormat("###.##");
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
                            .append(QTask.getNumQubits()).append(",")
                            .append(QTask.getNumLayers()).append(",")
                            .append(QTask.getNumShots()).append(",")
                            .append(dft.format(QTask.getCost())).append(",")
                            .append(QTask.getApplicationName());
                    writer.write(lineBuilder.toString());
                    writer.write(System.lineSeparator());
                }

                writer.flush();
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
