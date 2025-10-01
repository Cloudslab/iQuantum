package org.iquantum.utils;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.iquantum.tasks.QTask;
;

public class QTaskListGui {

    /**
     * Represents the list of QTasks after the simulation
     * @param list list of QTasks
     */
    public static void showQTaskListInGUI(List<QTask> list) {
        // Column names for the table
        String[] columnNames = {
                "QTask ID", "Status", "QDCenter", "QNode ID","ECL",
                "Execution Time", "Start Time", "Finish Time","Waiting_Time","Total Waiting Time"
        };

        // Prepare table data
        DecimalFormat dft = new DecimalFormat("###.##");
        String[][] data = new String[list.size()+1][columnNames.length];

        long totalWaitingTime = 0;
        for (int i = 0; i < list.size(); i++) {
            QTask task = list.get(i);
            data[i][0] = String.valueOf(task.getQTaskId());
            data[i][1] = (task.getQTaskStatus() == QTask.SUCCESS) ? "SUCCESS" : task.getQTaskStatusString();
            data[i][2] = (task.getResourceId() != 0) ? String.valueOf(task.getResourceId()) : "-";
            data[i][3] = String.valueOf(task.getQNodeId());
            data[i][4] = dft.format(task.getNumECL());
            data[i][5] = dft.format(task.getActualQPUTime());
            data[i][6] = dft.format(task.getExecStartTime());
            data[i][7] = dft.format(task.getFinishTime());
            data[i][8] = dft.format(task.getWaitingTime());
            int arrivalTime = 0;
            totalWaitingTime += task.getExecStartTime() - arrivalTime;
        }
        data[0][9] = dft.format(totalWaitingTime);

        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create the main frame
        JFrame frame = new JFrame("QTask List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 900);
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setResizable(true);
    }

}
