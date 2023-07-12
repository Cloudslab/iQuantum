/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.tasks;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.core.iQuantum;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * QTask is the class that represents a quantum task to be executed by a Quantum Computer.
 * It contains all the information about the quantum task, such as the number of qubits,
 * the number of layers, the number of shots, the gate set, the topology of the qubits,
 * the estimated completion time, the execution status, the execution start time and
 * the execution finish time.
 * <p/>
 * @author Hoa Nguyen
 * @since iQuantum 1.0
 */
public class QTask {
    /** The id of the QTask. */
    private int quletId;
    /** The id of the QC broker this QTask is submitted to */
    private int brokerId;
    /** The number of qubit in this QTask. */
    private int numQubits;
    /** The number of circuit layers in this QTask. */
    private int numLayers;
    /** The number of shots in this QTask. */
    private int numShots;
    /** List of all gate set in the QTask. */
    private List<String> gateSet;
    /** The topology of the qubits in this QTask. */
    private QubitTopology qubitTopology;
    /** The estimated completion time of this QTask. */
    private double estimatedCompletionTime;
    /** The execution status this QTask. */
    private int status;
    /** The execution start time of this QTask. */
    private double execStartTime;
    /** The execution finish time of this QTask. */
    private double execFinishTime;
    /** Indicates if transaction history records for this QTask is to be outputted. */
    private boolean record;
    /** The format of decimal numbers. */
    private DecimalFormat num;
    /** The qulet transaction history. */
    private StringBuffer history;
    /** Stores the operating system line separator */
    private String newline;
    /** The id of the QNode that is planned to execute this QTask. */
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
     * value -1 indicates the qulet has not been executed yet.
     */
    private int index;

    /** Constants attributes */
    /**
     * The QTask has been created and added to the QTaskList object.
     */
    public static final int CREATED = 0;

    /**
     * The QTask has been assigned to a CloudResource object to be executed
     * as planned.
     */
    public static final int READY = 1;

    /**
     * The QTask has moved to a Cloud node.
     */
    public static final int QUEUED = 2;

    /**
     * The QTask is in execution in a Cloud node.
     */
    public static final int RUNNING = 3;

    /**
     * The QTask has been executed successfully.
     */
    public static final int SUCCESS = 4;

    /**
     * The QTask has failed.
     */
    public static final int FAILED = 5;

    /**
     * The QTask has been canceled.
     */
    public static final int CANCELED = 6;

    /**
     * The QTask has been paused. It can be resumed by changing the status
     * into <tt>RESUMED</tt>.
     */
    public static final int PAUSED = 7;

    /**
     * The QTask has been resumed from <tt>PAUSED</tt> state.
     */
    public static final int RESUMED = 8;

    /**
     * The QTask has failed due to a resource failure.
     */
    public static final int FAILED_RESOURCE_UNAVAILABLE = 9;

    /**
     * The QTask has failed due to qubits insufficient.
     */
    public static final int FAILED_QUBITS_INSUFFICIENT = 10;

    /**
     * The QTask has failed due to gates insufficient.
     */
    public static final int FAILED_GATES_INSUFFICIENT = 11;

    /**
     * The QTask has failed due to qubit map impossible.
     */
    public static final int FAILED_QUBIT_MAP = 12;
    /**
     * Initializes a QTask (Gate-based Quantum Task) object.
     * @param quletId: the id of the QTask
     * @param numQubits: the number of qubits in the QTask
     * @param numLayers: the number of circuit layers in the QTask
     * @param numShots: the number of shots the QTask is to be executed
     * @param gateSet: the list of all gate set in the QTask
     * @param qubitTopology: the topology of the qubits in the QTask
     */
    public QTask(final int quletId, final int numQubits, final int numLayers,
                 final int numShots, final List<String> gateSet, final QubitTopology qubitTopology) {
        validateParameters(numQubits, numLayers, numShots, gateSet, qubitTopology);
        brokerId = -1;
        status = CREATED;
        this.quletId = quletId;
        this.numQubits = numQubits;
        this.numLayers = numLayers;
        this.numShots = numShots;
        this.gateSet = gateSet;
        this.qubitTopology = qubitTopology;
        this.record = iQuantum.getTraceFlag();
        execStartTime = 0.0;
        execFinishTime = -1.0;
        qNodeId = -1;
        resList = new ArrayList<Resource>(2);
        index = -1;

    }

    /**
     * Validates the parameters of the QTask.
     * @param numQubits: the number of qubits in the QTask
     * @param numLayers: the number of circuit layers in the QTask
     * @param numShots: the number of shots the QTask is to be executed
     * @param gateSet: the list of all gate set in the QTask
     * @param qubitTopology: the topology of the qubits in the QTask
     */
    private static void validateParameters(int numQubits, int numLayers, int numShots, List<String> gateSet,
                                           QubitTopology qubitTopology) {

        if (numQubits <= 0) {
            throw new IllegalArgumentException("numQubits must be greater than zero");
        }

        if (numLayers <= 0) {
            throw new IllegalArgumentException("numLayers must be greater than zero");
        }

        if (numShots <= 0) {
            throw new IllegalArgumentException("numShots must be greater than zero");
        }

        if (gateSet == null || gateSet.isEmpty()) {
            throw new IllegalArgumentException("gateSet must not be null or empty");
        }

        if (qubitTopology == null) {
            throw new IllegalArgumentException("qubitTopology must not be null");
        }
    }


    /**
     * GETTERS AND SETTERS START------------------------------------------------
     */
    public int getQTaskStatus() {
        return status;
    }

    public void setQTaskId(final int id) {
        quletId = id;
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

    public QubitTopology getQubitTopology() {
        return qubitTopology;
    }

    public void setBrokerId(final int id) {
        brokerId = id;
        if (record) {
            write("Assigns the QTask to " + iQuantum.getEntityName(id) + " (ID #" + id + ")");
        }
    }

    public int getBrokerId() {
        return brokerId;
    }

    public int getQTaskId() {
        return quletId;
    }
    public int getQNodeId() {
        return qNodeId;
    }
    public void setQNodeId(final int qNodeId) {
        this.qNodeId = qNodeId;
    }

    /**
     * GETTERS AND SETTERS END------------------------------------------------
     */

    /**
     * INTERNAL CLASSES START------------------------------------------------
     * Internal class that keeps track of qulet's movement in different
     * CloudResources. Each time a qulet is run on a given VM, the qulet's
     * execution history on each VM is registered at {@link QTask#resList}
     */
    private static class Resource {
        /**
         * QTask's submission (arrival) time to a CloudResource.
         */
        public double submissionTime = 0.0;

        /**
         * The time this QTask resides in a CloudResource (from arrival time
         * until departure time, that may include waiting time).
         */
        public double wallClockTime = 0.0;

        /**
         * The total time the QTask spent being executed in a CloudResource.
         */
        public double actualQPUTime = 0.0;

        /**
         * Cost per second a CloudResource charge to execute this QTask.
         */
        public double costPerSec = 0.0;

        /**
         * QTask's layer finished so far.
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
    /** INTERNAL CLASSES END------------------------------------------------*/

    /**
     * Sets the resource parameters for which the QTask is going to be
     * executed. From the second time this method is called, every call make the
     * qulet to be migrated to the indicated resource.<br>
     *
     * NOTE: This method <tt>should</tt> be called only by a resource entity,
     * not the user or owner of this QTask.
     *
     * @param resourceID the CloudResource ID
     * @param cost the cost running this CloudResource per second
     * @pre resourceID >= 0
     * @pre cost > 0.0
     */
    public void setResourceParameter(final int resourceID, final double cost) {
        final Resource res = new Resource();
        res.resourceId = resourceID;
        res.costPerSec = cost;
        res.resourceName = iQuantum.getEntityName(resourceID);
        resList.add(res);

        if (index == -1 && record) {
            write("Allocates this QTask to " + res.resourceName + " (ID #" + resourceID
                    + ") with cost = $" + cost + "/sec");
        } else if (record) {
            final int id = resList.get(index).resourceId;
            final String name = resList.get(index).resourceName;
            write("Moves QTask from " + name + " (ID #" + id + ") to " + res.resourceName + " (ID #"
                    + resourceID + ") with cost = $" + cost + "/sec");
        }
        index++;
    }

    /**
     * Get waiting time of the QTask.
     */
    public double getWaitingTime() {
        if (index == -1) {
            return 0;
        }
        // Use the latest resource submission time
        final double subTime = resList.get(index).submissionTime;
        return execStartTime - subTime;
    }

    /**
     * Prints the QTask's execution history.
     * @return QTask's execution history
     */
    public String getQTaskHistory() {
        String msg = null;
        if (history == null) {
            msg = "No history is recorded for QTask #" + quletId;
        } else {
            msg = history.toString();
        }

        return msg;
    }

    /**
     * Get the number of circuit layers of qulet finished so far.
     */
    public long getQTaskFinishedSoFar() {
        if (index == -1) {
            return numLayers;
        }
        final long finish = resList.get(index).finishedSoFar;
        if (finish > numLayers * numShots) {
            return numLayers * numShots;
        }
        return finish;
    }

    /**
     * Set the number of circuit layers of qulet finished so far.
     */
    public void setQTaskFinishedSoFar(final long layers) {
        if (layers < 0.0 || index < 0) {
            return;
        }
        final Resource res = resList.get(index);
        res.finishedSoFar = layers;

        if (record) {
            write("Sets the circuit layers finished so far to " + layers);
        }
    }

    /**
     * Check if the QTask has finished.
     */
    public boolean isFinished() {
        if (index == -1) {
            return false;
        }
        boolean completed = false;
        // if result is 0 or -ve then this QTask has finished
        final long finish = resList.get(index).finishedSoFar;
        final long result = numLayers - finish;
        if (result <= 0.0) {
            completed = true;
        }
        return completed;
    }

    /** Get the latest resource id that this QTask has run on. */
    public int getResourceId() {
        if (index == -1) {
            return -1;
        }
        return resList.get(index).resourceId;
    }

    /**
     * Sets the submission (arrival) time of this QTask into a CloudResource.
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
     * Gets the submission (arrival) time of this QTask from the latest
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
     * Sets the execution start time of this QTask.
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
        final QTask.Resource res = resList.get(index);
        res.wallClockTime = wallTime;
        res.actualQPUTime = actualTime;

        if (record) {
            write("Sets the wall clock time to " + num.format(wallTime) + " and the actual QPU time to "
                    + num.format(actualTime));
        }
    }

    public void setQTaskStatus(final int newStatus) throws Exception {
        // if the new status is same as current one, then ignore the rest
        if (status == newStatus) {
            return;
        }

        // throws an exception if the new status is outside the range
        if (newStatus < QTask.CREATED || newStatus > QTask.FAILED_QUBIT_MAP) {
            throw new Exception(
                    "QTask.setQTaskStatus() : Error - Invalid integer range for QTask status.");
        }

        if (newStatus == QTask.SUCCESS) {
            execFinishTime = iQuantum.clock();
        }

        if (record) {
            write("Sets QTask status from " + getQTaskStatusString() + " to "
                    + QTask.quletStatusString(newStatus));
        }

        status = newStatus;
    }

    public String getQTaskStatusString() {
        return QTask.quletStatusString(status);
    }


    public static String quletStatusString(final int status) {
        String statusString = null;
        switch (status) {
            case QTask.CREATED:
                statusString = "Created";
                break;

            case QTask.READY:
                statusString = "Ready";
                break;

            case QTask.RUNNING:
                statusString = "Running";
                break;

            case QTask.SUCCESS:
                statusString = "Success";
                break;

            case QTask.QUEUED:
                statusString = "Queued";
                break;

            case QTask.FAILED:
                statusString = "Failed";
                break;

            case QTask.CANCELED:
                statusString = "Canceled";
                break;

            case QTask.PAUSED:
                statusString = "Paused";
                break;

            case QTask.RESUMED:
                statusString = "Resumed";
                break;

            case QTask.FAILED_RESOURCE_UNAVAILABLE:
                statusString = "Failed as resource is unavailable";
                break;

            case QTask.FAILED_QUBITS_INSUFFICIENT:
                statusString = "FAILED (as the number of qubits at scheduled QNode is insufficient)";
                break;

            case QTask.FAILED_GATES_INSUFFICIENT:
                statusString = "FAILED (as the gate set of scheduled QNode is insufficient)";
                break;

            case QTask.FAILED_QUBIT_MAP:
                statusString = "FAILED (as the qubit mapping is not possible)";
                break;

            default:
                break;
        }

        return statusString;
    }
    /**
     * Gets the time of this QTask resides in the latest CloudResource (from
     * arrival time until departure time).
     *
     * @return the time of this QTask resides in a CloudResource
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
     * Gets all the CloudResource names that executed this QTask.
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
     * Gets all the CloudResource IDs that executed this QTask.
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
     * Gets the total execution time of this QTask in a given CloudResource
     * ID.
     *
     * @param resId a CloudResource entity ID
     * @return the total execution time of this QTask in a CloudResource or
     * <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getActualQPUTime(final int resId) {
        QTask.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.actualQPUTime;
        }
        return 0.0;
    }

    public double getActualQPUTime() {
        return getFinishTime() - getExecStartTime();
    }

    /**
     * Gets the length of this QTask that has been executed so far in a given
     * CloudResource ID. This method is useful when trying to move this QTask
     * into different CloudResources or to cancel it.
     *
     * @param resId a CloudResource entity ID
     * @return the length of a partially executed QTask or the full QTask
     * length if it is completed or <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public long getQTaskFinishedSoFar(final int resId) {
        QTask.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.finishedSoFar;
        }
        return 0;
    }

    /**
     * Gets the submission (arrival) time of this QTask in the given
     * CloudResource ID.
     *
     * @param resId a CloudResource entity ID
     * @return the submission time or <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getSubmissionTime(final int resId) {
        QTask.Resource resource = getResourceById(resId);
        if (resource != null) {
            return resource.submissionTime;
        }
        return 0.0;
    }

    /**
     * Gets the time of this QTask resides in a given CloudResource ID (from
     * arrival time until departure time).
     *
     * @param resId a CloudResource entity ID
     * @return the time of this QTask resides in the CloudResource or
     * <tt>0.0</tt> if not found
     * @pre resId >= 0
     * @post $result >= 0.0
     */
    public double getWallClockTime(final int resId) {
        QTask.Resource resource = getResourceById(resId);
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
        QTask.Resource resource = getResourceById(resId);
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
    public QTask.Resource getResourceById(final int resourceId) {
        for (QTask.Resource resource : resList) {
            if (resource.resourceId == resourceId) {
                return resource;
            }
        }
        return null;
    }

    /**
     * Gets the finish time of this QTask in a CloudResource.
     *
     * @return the finish or completion time of this QTask or <tt>-1</tt> if
     * not finished yet.
     * @pre $none
     * @post $result >= -1
     */
    public double getFinishTime() {
        return execFinishTime;
    }

    // //////////////////////// PROTECTED METHODS //////////////////////////////
    /**
     * Writes this particular history transaction of this QTask into a log.
     *
     * @param str a history transaction of this QTask
     * @pre str != null
     * @post $none
     */
    protected void write(final String str) {
        if (!record) {
            return;
        }

        if (num == null || history == null) { // Creates the history or
            // transactions of this QTask
            newline = System.getProperty("line.separator");
            num = new DecimalFormat("#0.00#"); // with 3 decimal spaces
            history = new StringBuffer(1000);
            history.append("Time below denotes the simulation time.");
            history.append(System.getProperty("line.separator"));
            history.append("Time (sec)       Description QTask #" + quletId);
            history.append(System.getProperty("line.separator"));
            history.append("------------------------------------------");
            history.append(System.getProperty("line.separator"));
            history.append(num.format(iQuantum.clock()));
            history.append("   Creates QTask ID #" + quletId);
            history.append(System.getProperty("line.separator"));
        }

        history.append(num.format(iQuantum.clock()));
        history.append("   " + str + newline);
    }

}
