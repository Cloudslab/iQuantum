package org.iquantum;

import org.cloudbus.cloudsim.core.CloudSim;

import java.text.DecimalFormat;
import java.util.List;

public class Qulet {
    private int quletId;
    private List<String> gateSet;
    private int numQubits;
    private int numLayers;
    private int numShots;
    private List<List<Integer>> qubitTopology;

    private double estimatedCompletionTime;

    private int brokerId;


    /**
     * Indicates if transaction history records for this Qulet is to be
     * outputted.
     */
    private boolean record;
    /**
     * The format of decimal numbers.
     */
    private DecimalFormat num;

    /**
     * The cloudlet transaction history.
     */
    private StringBuffer history;
    /**
     * Stores the operating system line separator.
     */
    private String newline;

    public Qulet(List<String> gateSet, int numQubits, int numLayers,
                 int numShots, List<List<Integer>> qubitTopology) {
        this.gateSet = gateSet;
        this.numQubits = numQubits;
        this.numLayers = numLayers;
        this.numShots = numShots;
        this.qubitTopology = qubitTopology;
    }


    public List<String> getGateSet() {
        return gateSet;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public int getNumLayers() {
        return numLayers;
    }

    public int getNumShots() {
        return numShots;
    }

    public List<List<Integer>> getQubitTopology() {
        return qubitTopology;
    }

    public void setBrokerId(final int id) {
        brokerId = id;
        if (record) {
            write("Assigns the Cloudlet to " + CloudSim.getEntityName(id) + " (ID #" + id + ")");
        }
    }

    public int getBrokerId() {
        return brokerId;
    }

    public double getEstimatedCompletionTime() {
        // TEMPORARY assume the estimated completion time is the number of shots (MUST BE CHANGED)
        estimatedCompletionTime = 1.0 * numShots;
        return estimatedCompletionTime;
    }

    /**
     * Writes this particular history transaction of this Cloudlet into a log.
     *
     * @param str a history transaction of this Cloudlet
     * @pre str != null
     * @post $none
     */
    protected void write(final String str) {
        if (!record) {
            return;
        }

        if (num == null || history == null) { // Creates the history or
            // transactions of this Cloudlet
            newline = System.getProperty("line.separator");
            num = new DecimalFormat("#0.00#"); // with 3 decimal spaces
            history = new StringBuffer(1000);
            history.append("Time below denotes the simulation time.");
            history.append(System.getProperty("line.separator"));
            history.append("Time (sec)       Description Cloudlet #" + quletId);
            history.append(System.getProperty("line.separator"));
            history.append("------------------------------------------");
            history.append(System.getProperty("line.separator"));
            history.append(num.format(CloudSim.clock()));
            history.append("   Creates Cloudlet ID #" + quletId);
            history.append(System.getProperty("line.separator"));
        }

        history.append(num.format(CloudSim.clock()));
        history.append("   " + str + newline);
    }
}
