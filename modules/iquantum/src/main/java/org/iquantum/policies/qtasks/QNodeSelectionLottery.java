package org.iquantum.policies.qtasks;

import org.iquantum.backends.quantum.QNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QNodeSelectionLottery {

    double weightQuantumVolume = 0.5;  // Weight for quantumVolume
    double weightClops = 0.5;          // Weight for clops
    double minQuantumVolume = Double.MAX_VALUE;
    double maxQuantumVolume = Double.MIN_VALUE;
    double minClops = Double.MAX_VALUE;
    double maxClops = Double.MIN_VALUE;

    public QNodeSelectionLottery(double weightQuantumVolume, double weightClops) {
        this.weightQuantumVolume = weightQuantumVolume;
        this.weightClops = weightClops;
    }

    /**
     * Selects a QNode from a list of QNodes using a lottery algorithm.
     * @param qNodeList list of QNodes
     * @return selected QNode
     */
    public QNode selectQNode(List<? extends QNode> qNodeList) {
        int totalTickets = calculateTotalTickets(qNodeList); // Step 1: Calculate total number of tickets
        int randomTicket = generateRandomTicket(totalTickets); // Step 3: Generate random ticket
        List<QNode> potentialWinners = new ArrayList<>();
        int cumulativeTickets = 0;
        for (QNode qNode : qNodeList) {
            cumulativeTickets += calculateTicketsForQNode(qNode, qNodeList); // Step 4: Calculate cumulative number of tickets

            if (cumulativeTickets >= randomTicket) {
                potentialWinners.add(qNode); // Store potential winners
                break; // Stop iteration when cumulativeTickets exceeds randomTicket
            }
        }

        if (!potentialWinners.isEmpty()) {
            // Select a random winner from potentialWinners
            Random random = new Random();
            return potentialWinners.get(random.nextInt(potentialWinners.size()));
        }
        return null; // No QNode selected
    }

    private int calculateTotalTickets(List<? extends QNode> qNodeList) {
        int totalTickets = 0;
        for (QNode qNode : qNodeList) {
            totalTickets += calculateTicketsForQNode(qNode, qNodeList);
        }
        return totalTickets;
    }

    private int calculateTicketsForQNode(QNode qNode, List<? extends QNode> qNodeList) {
        int numIdenticalNodes = 0;
        if (qNodeList.size() == 1) {
            // If there is only one QNode, return a default or fallback number of tickets
            return 1;
        }
        for (QNode node : qNodeList) {
            double quantumVolume = node.getQuantumVolume();
            double clops = node.getCLOPS();

            minQuantumVolume = Math.min(minQuantumVolume, quantumVolume);
            maxQuantumVolume = Math.max(maxQuantumVolume, quantumVolume);
            minClops = Math.min(minClops, clops);
            maxClops = Math.max(maxClops, clops);

            if (quantumVolume == qNode.getQuantumVolume() && clops == qNode.getCLOPS()) {
                numIdenticalNodes++;
            }
        }

        if (numIdenticalNodes > 1) {
            // If there are multiple identical nodes, return a fixed number of tickets for all of them
            return numIdenticalNodes;
        }

        // Normalize the quantumVolume and clops values to a range between 0 and 1
        double normalizedQuantumVolume = normalizeValue(qNode.getQuantumVolume(), minQuantumVolume, maxQuantumVolume);
        double normalizedClops = normalizeValue(qNode.getCLOPS(), minClops, maxClops);

        // Calculate the weighted sum of normalized values
        double weightedSum = (weightQuantumVolume * normalizedQuantumVolume)
                + (weightClops * normalizedClops);

        // Scale the weighted sum to the desired range of tickets (e.g., 1 to 100)
        int minTickets = 1;       // Minimum number of tickets
        int maxTickets = 100;     // Maximum number of tickets

        int numTickets = (int) Math.round((weightedSum * (maxTickets - minTickets)) + minTickets);
        return numTickets;
    }

    private double normalizeValue(double value, double minValue, double maxValue) {
        // Normalize the value to a range between 0 and 1
        // Normalize value between 0 and 1 using linear scaling
        return (value - minValue) / (maxValue - minValue);
    }


    private int generateRandomTicket(int totalTickets) {
        Random random = new Random();
        if (totalTickets == 1) {
            return 1; // If there is only one ticket, return 1
        } else if (totalTickets <= 0) {
            return 0; // If there are no tickets, return 0
        }
        return random.nextInt(totalTickets) + 1; // Generate a random ticket between 1 and totalTickets
    }
}
