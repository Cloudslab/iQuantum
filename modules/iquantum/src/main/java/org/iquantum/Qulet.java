package org.iquantum;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.core.CloudSim;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Qulet {
    /** The id of the Qulet. */
    private int quletId;
    /** The id of the QC broker this Qulet is submitted to */
    private int brokerId;
    /** The number of qubit in this Qulet. */
    private int numQubits;
    /** The number of circuit layers in this Qulet. */
    private int numLayers;
    /** The number of shots in this Qulet. */
    private int numShots;
    /** List of all gate set in the Qulet. */
    private List<String> gateSet;
    /** The topology of the qubits in this Qulet. */
    private QubitTopology qubitTopology;
    /** The estimated completion time of this Qulet. */
    private double estimatedCompletionTime;
    /** The execution status this Qulet. */
    private int status;
    /** The execution start time of this Qulet. */
    private double execStartTime;
    /** The execution finish time of this Qulet. */
    private double execFinishTime;
    /** Indicates if transaction history records for this Qulet is to be outputted. */
    private boolean record;
    /** The format of decimal numbers. */
    private DecimalFormat num;
    /** The qulet transaction history. */
    private StringBuffer history;
    /** Stores the operating system line separator */
    private String newline;
    /** The id of the QNode that is planned to execute this Qulet. */
    private int qNodeId;
    /**
     * The list of every resource where the qulet has been executed. In case
     * it starts and finishes executing in a single cloud resource, without
     * being migrated, this list will have only one item.
     */
    private final List<Resource> resList;

    /**
     * The index of the last resource where the qulet was executed. If the
     * qulet is migrated during its execution, this index is updated. The
     * value -1 indicates the qulet has not been executed
     yet.
     */
    private int index;

    /**
     * The classType or priority of this qulet for scheduling on a resource.
     */
    private int classType;

    /** Constants attributes */
    /**
     * The Qulet has been created and added to the QuletList object.
     */
    public static final int CREATED = 0;

    /**
     * The Qulet has been assigned to a CloudResource object to be executed
     * as planned.
     */
    public static final int READY = 1;

    /**
     * The Qulet has moved to a Cloud node.
     */
    public static final int QUEUED = 2;

    /**
     * The Qulet is in execution in a Cloud node.
     */
    public static final int INEXEC = 3;

    /**
     * The Qulet has been executed successfully.
     */
    public static final int SUCCESS = 4;

    /**
     * The Qulet has failed.
     */
    public static final int FAILED = 5;

    /**
     * The Qulet has been canceled.
     */
    public static final int CANCELED = 6;

    /**
     * The Qulet has been paused. It can be resumed by changing the status
     * into <tt>RESUMED</tt>.
     */
    public static final int PAUSED = 7;

    /**
     * The Qulet has been resumed from <tt>PAUSED</tt> state.
     */
    public static final int RESUMED = 8;

    /**
     * The Qulet has failed due to a resource failure.
     */
    public static final int FAILED_RESOURCE_UNAVAILABLE = 9;

    public Qulet(final int quletId, final int numQubits, final int numLayers,
                 final int numShots, final List<String> gateSet, final QubitTopology qubitTopology) {
        this(quletId, numQubits, numLayers, numShots, gateSet, qubitTopology, false);
        qNodeId = -1;
    }

    public Qulet(final int quletId, final int numQubits, final int numLayers,
                 final int numShots, final List<String> gateSet, final QubitTopology qubitTopology, final boolean record) {
        brokerId = -1;
        status = CREATED;
        this.quletId = quletId;
        this.numQubits = numQubits;
        this.numLayers = numLayers;
        this.numShots = numShots;
        this.gateSet = gateSet;
        this.qubitTopology = qubitTopology;
        this.record = record;
        execStartTime = 0.0;
        execFinishTime = -1.0;
        qNodeId = -1;
        resList = new ArrayList<Resource>(2);
        index = -1;

    }

    public int getQuletStatus() {
        return status;
    }

    public void setQuletId(final int id) {
        quletId = id;
    }

    public void setResourceParameter(final int resourceID, final double cost) {
        final Resource res = new Resource();
        res.resourceId = resourceID;
        res.costPerSec = cost;
        res.resourceName = CloudSim.getEntityName(resourceID);

        // add into a list if moving to a new grid resource
        resList.add(res);

        if (index == -1 && record) {
            write("Allocates this Qulet to " + res.resourceName + " (ID #" + resourceID
                    + ") with cost = $" + cost + "/sec");
        } else if (record) {
            final int id = resList.get(index).resourceId;
            final String name = resList.get(index).resourceName;
            write("Moves Qulet from " + name + " (ID #" + id + ") to " + res.resourceName + " (ID #"
                    + resourceID + ") with cost = $" + cost + "/sec");
        }

        index++;  // initially, index = -1
    }

    // ////////////////////// INTERNAL CLASS ///////////////////////////////////
    /**
     * Internal class that keeps track of qulet's movement in different
     * CloudResources. Each time a qulet is run on a given VM, the qulet's
     * execution history on each VM is registered at {@link Qulet#resList}
     */
    private static class Resource {

        /**
         * Qulet's submission (arrival) time to a CloudResource.
         */
        public double submissionTime = 0.0;

        /**
         * The time this Qulet resides in a CloudResource (from arrival time
         * until departure time, that may include waiting time).
         */
        public double wallClockTime = 0.0;

        /**
         * The total time the Qulet spent being executed in a CloudResource.
         */
        public double actualQPUTime = 0.0;

        /**
         * Cost per second a CloudResource charge to execute this Qulet.
         */
        public double costPerSec = 0.0;

        /**
         * Qulet's layer finished so far.
         */
        public long finishedSoFar = 0;

        /**
         * a CloudResource id.
         */
        public int resourceId = -1;

        /**
         * a CloudResource name.
         */
        public String resourceName = null;
    }
    //////////////////////// END OF INTERNAL CLASS ////////////////////////////
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

    public QubitTopology getQubitTopology() {
        return qubitTopology;
    }

    public void setBrokerId(final int id) {
        brokerId = id;
        if (record) {
            write("Assigns the Qulet to " + CloudSim.getEntityName(id) + " (ID #" + id + ")");
        }
    }

    public int getBrokerId() {
        return brokerId;
    }

    public int getQuletId() {
        return quletId;
    }
    public int getQNodeId() {
        return qNodeId;
    }
    public void setQNodeId(final int qNodeId) {
        this.qNodeId = qNodeId;
    }

    public double getWaitingTime() {
        if (index == -1) {
            return 0;
        }

        // use the latest resource submission time
        final double subTime = resList.get(index).submissionTime;
        return execStartTime - subTime;
    }

    public double getEstimatedCompletionTime() {
        // TEMPORARY assume the estimated completion time is the number of shots (MUST BE CHANGED)
        estimatedCompletionTime = 1.0 * numShots;
        return estimatedCompletionTime;
    }

    public String getQuletHistory() {
        String msg = null;
        if (history == null) {
            msg = "No history is recorded for Qulet #" + quletId;
        } else {
            msg = history.toString();
        }

        return msg;
    }
    public long getQuletFinishedSoFar() {
        if (index == -1) {
            return numLayers;
        }

        final long finish = resList.get(index).finishedSoFar;
        if (finish > numLayers) {
            return numLayers;
        }

        return finish;
    }
    public boolean isFinished() {
        if (index == -1) {
            return false;
        }

        boolean completed = false;

        // if result is 0 or -ve then this Qulet has finished
        final long finish = resList.get(index).finishedSoFar;
        final long result = numLayers - finish;
        if (result <= 0.0) {
            completed = true;
        }
        return completed;
    }

    public void setQuletFinishedSoFar(final long layers) {
        // if layers is -ve then ignore
        if (layers < 0.0 || index < 0) {
            return;
        }

        final Resource res = resList.get(index);
        res.finishedSoFar = layers;

        if (record) {
            write("Sets the layers's finished so far to " + layers);
        }
    }

    /** Get the latest resource id that this Qulet has run on. */
    public int getResourceId() {
        if (index == -1) {
            return -1;
        }
        return resList.get(index).resourceId;
    }
    /**
     * Sets the submission (arrival) time of this Qulet into a CloudResource.
     *
     * @param clockTime the submission time
     * @pre clockTime >= 0.0
     * @post $none
     */
    public void setSubmissionTime(final double clockTime) {
        if (clockTime < 0.0 || index < 0) {
            return;
        }

        final Resource res = resList.get(index);
        res.submissionTime = clockTime;

        if (record) {
            write("Sets the submission time to " + num.format(clockTime));
        }
    }
    /**
     * Gets the submission (arrival) time of this Qulet from the latest
     * CloudResource.
     *
     * @return the submission time or <tt>0.0</tt> if none
     * @pre $none
     * @post $result >= 0.0
     */
    public double getSubmissionTime() {
        if (index == -1) {
            return 0.0;
        }
        return resList.get(index).submissionTime;
    }
    /**
     * Sets the execution start time of this Qulet.
     *
     * @param clockTime the execution start time
     * @pre clockTime >= 0.0
     * @post $none
     */
    public void setExecStartTime(final double clockTime) {
        execStartTime = clockTime;
        if (record) {
            write("Sets the execution start time to " + num.format(clockTime));
        }
    }
    /**
     * Gets the latest execution start time.
     *
     * @return the latest execution start time
     * @pre $none
     * @post $result >= 0.0
     */
    public double getExecStartTime() {
        return execStartTime;
    }

    public void setExecParam(final double wallTime, final double actualTime) {
        if (wallTime < 0.0 || actualTime < 0.0 || index < 0) {
            return;
        }

        final Qulet.Resource res = resList.get(index);
        res.wallClockTime = wallTime;
        res.actualQPUTime = actualTime;

        if (record) {
            write("Sets the wall clock time to " + num.format(wallTime) + " and the actual QPU time to "
                    + num.format(actualTime));
        }
    }

    public void setQuletStatus(final int newStatus) throws Exception {
        // if the new status is same as current one, then ignore the rest
        if (status == newStatus) {
            return;
        }

        // throws an exception if the new status is outside the range
        if (newStatus < Qulet.CREATED || newStatus > Qulet.FAILED_RESOURCE_UNAVAILABLE) {
            throw new Exception(
                    "Qulet.setQuletStatus() : Error - Invalid integer range for Qulet status.");
        }

        if (newStatus == Qulet.SUCCESS) {
            execFinishTime = CloudSim.clock();
        }

        if (record) {
            write("Sets Qulet status from " + getQuletStatusString() + " to "
                    + Qulet.QuletString(newStatus));
        }

        status = newStatus;
    }

    public String getQuletStatusString() {
        return Qulet.QuletString(status);
    }

    /**
     * Get the status of the Cloudlet.
     *
     * @return status of the Cloudlet
     * @pre $none
     * @post $none
     *
     */
    public int Qulet() {
        return status;
    }

    /**
     * Gets the ID of this Cloudlet.
     *
     * @return Cloudlet Id
     * @pre $none
     * @post $none
     */

    public static String QuletString(final int status) {
        String statusString = null;
        switch (status) {
            case Qulet.CREATED:
                statusString = "Created";
                break;

            case Qulet.READY:
                statusString = "Ready";
                break;

            case Qulet.INEXEC:
                statusString = "InExec";
                break;

            case Qulet.SUCCESS:
                statusString = "Success";
                break;

            case Qulet.QUEUED:
                statusString = "Queued";
                break;

            case Qulet.FAILED:
                statusString = "Failed";
                break;

            case Qulet.CANCELED:
                statusString = "Canceled";
                break;

            case Qulet.PAUSED:
                statusString = "Paused";
                break;

            case Qulet.RESUMED:
                statusString = "Resumed";
                break;

            case Qulet.FAILED_RESOURCE_UNAVAILABLE:
                statusString = "Failed_resource_unavailable";
                break;

            default:
                break;
        }

        return statusString;
    }
    /**
     * Gets the time of this Qulet resides in the latest CloudResource (from
     * arrival time until departure time).
     *
     * @return the time of this Qulet resides in a CloudResource
     * @pre $none
     * @post $result >= 0.0
     */
    public double getWallClockTime() {
        if (index == -1) {
            return 0.0;
        }
        return resList.get(index).wallClockTime;
    }

    /**
     * Gets all the CloudResource names that executed this Qulet.
     *
     * @return an array of CloudResource names or <tt>null</tt> if it has none
     * @pre $none
     * @post $none
     */
    public String[] getAllResourceName() {
        final int size = resList.size();
        String[] data = null;

        if (size > 0) {
            data = new String[size];
            for (int i = 0; i < size; i++) {
                data[i] = resList.get(i).resourceName;
            }
        }

        return data;
    }
    /**
     * Gets all the CloudResource IDs that executed this Qulet.
     *
     * @return an array of CloudResource IDs or <tt>null</tt> if it has none
     * @pre $none
     * @post $none
     */
    public int[] getAllResourceId() {
        final int size = resList.size();
        int[] data = null;

        if (size > 0) {
            data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = resList.get(i).resourceId;
            }
        }

        return data;
    }

    /**
     * Gets the total execution time of this Qulet in a given CloudResource
     * ID.
     *
     * @param resId a CloudResource entity ID
     * @return the total execution time of this Qulet in a CloudResource or
     * <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getActualQPUTime(final int resId) {
        Qulet.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.actualQPUTime;
        }
        return 0.0;
    }

    public double getActualQPUTime() {
        return getFinishTime() - getExecStartTime();
    }

    /**
     * Gets the length of this Qulet that has been executed so far in a given
     * CloudResource ID. This method is useful when trying to move this Qulet
     * into different CloudResources or to cancel it.
     *
     * @param resId a CloudResource entity ID
     * @return the length of a partially executed Qulet or the full Qulet
     * length if it is completed or <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public long getQuletFinishedSoFar(final int resId) {
        Qulet.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.finishedSoFar;
        }
        return 0;
    }

    /**
     * Gets the submission (arrival) time of this Qulet in the given
     * CloudResource ID.
     *
     * @param resId a CloudResource entity ID
     * @return the submission time or <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getSubmissionTime(final int resId) {
        Qulet.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.submissionTime;
        }
        return 0.0;
    }

    /**
     * Gets the time of this Qulet resides in a given CloudResource ID (from
     * arrival time until departure time).
     *
     * @param resId a CloudResource entity ID
     * @return the time of this Qulet resides in the CloudResource or
     * <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getWallClockTime(final int resId) {
        Qulet.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.wallClockTime;
        }
        return 0.0;
    }

    /**
     * Gets the CloudResource name based on its ID.
     *
     * @param resId a CloudResource entity ID
     * @return the CloudResource name or <tt>null</tt> if not found
     * @pre resId >= 0
     * @post $none
     */
    public String getResourceName(final int resId) {
        Qulet.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.resourceName;
        }
        return null;
    }

    /**
     * Gets the resource by id.
     *
     * @param resourceId the resource id
     * @return the resource by id
     */
    public Qulet.Resource getResourceById(final int resourceId) {
        for (Qulet.Resource resource : resList) {
            if (resource.resourceId == resourceId) {
                return resource;
            }
        }
        return null;
    }

    /**
     * Gets the finish time of this Qulet in a CloudResource.
     *
     * @return the finish or completion time of this Qulet or <tt>-1</tt> if
     * not finished yet.
     * @pre $none
     * @post $result >= -1
     */
    public double getFinishTime() {
        return execFinishTime;
    }

    // //////////////////////// PROTECTED METHODS //////////////////////////////
    /**
     * Writes this particular history transaction of this Qulet into a log.
     *
     * @param str a history transaction of this Qulet
     * @pre str != null
     * @post $none
     */
    protected void write(final String str) {
        if (!record) {
            return;
        }

        if (num == null || history == null) { // Creates the history or
            // transactions of this Qulet
            newline = System.getProperty("line.separator");
            num = new DecimalFormat("#0.00#"); // with 3 decimal spaces
            history = new StringBuffer(1000);
            history.append("Time below denotes the simulation time.");
            history.append(System.getProperty("line.separator"));
            history.append("Time (sec)       Description Qulet #" + quletId);
            history.append(System.getProperty("line.separator"));
            history.append("------------------------------------------");
            history.append(System.getProperty("line.separator"));
            history.append(num.format(CloudSim.clock()));
            history.append("   Creates Qulet ID #" + quletId);
            history.append(System.getProperty("line.separator"));
        }

        history.append(num.format(CloudSim.clock()));
        history.append("   " + str + newline);
    }

}
