/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.tasks;

import org.iquantum.core.iQuantum;

/**
 * iQuantum ResQTask represents a QTask submitted to CloudResource for processing. This class
 * keeps track the time for all activities in the CloudResource for a specific QTask. Before a
 * QTask exits the CloudResource, it is RECOMMENDED to call this method
 * {@link #finalizeQTask()}.
 * <p/>
 * It contains a QTask object along with its arrival time and the ID of the machine and the Pe
 * (Processing Element) allocated to it. It acts as a placeholder for maintaining the amount of
 * resource share allocated at various times for simulating any scheduling using internal events.
 * <p/>
 * Note: This class is inspired from iQuantum's ResCloudlet class.
 * @author Hoa Nguyen
 * @since iQuantum Toolkit 1.0
 */
public class ResQTask {

	/** The QTask object. */
	private final QTask QTask;

	/** The QTask arrival time for the first time. */
	private double arrivalTime;

	/** The estimation of QTask finished time. */
	private double finishedTime;

	/** The length of QTask finished so far. */
	private long qtaskFinishedSoFar;

	/**
	 * QTask execution start time. This attribute will only hold the latest time since a QTask
	 * can be canceled, paused or resumed.
	 */
	private double startExecTime;

	/** The total time to complete this QTask. */
	private double totalCompletionTime;

	// The below attributes are only to be used by the SpaceShared policy.

	/** The machine id this QTask is assigned to. */
	private int machineId;

	/** The Pe id this QTask is assigned to. */
	private int peId;

	/** The an array of machine IDs. */
	private int[] machineArrayId = null;

	/** The an array of Pe IDs. */
	private int[] peArrayId = null;

	/** The index of machine and Pe arrays. */
	private int index;

	// NOTE: Below attributes are related to AR stuff

	/** The Constant NOT_FOUND. */
	private static final int NOT_FOUND = -1;

	/** The reservation start time. */
	private final long startTime;

	/** The reservation duration time. */
	private final int duration;

	/** The reservation id. */
	private final int reservId;

	/** The num Pe needed to execute this QTask. */
	private int pesNumber;

	/**
	 * Allocates a new ResQTask object upon the arrival of a QTask object.
	 * 
	 * @param QTask a QTask object
	 * @see iQuantum#clock()
	 * @pre QTask != null
	 * @post $none
	 */
	public ResQTask(QTask QTask) {
		// when a new ResQTask is created, then it will automatically set
		// the submission time and other properties, such as remaining length
		this.QTask = QTask;
		startTime = 0;
		reservId = NOT_FOUND;
		duration = 0;

		init();
	}

	/**
	 * Allocates a new ResQTask object upon the arrival of a QTask object. Use this
	 * constructor to store reserved QTasks, i.e. QTasks that done reservation before. The
	 * arriving time is determined by {@link iQuantum#clock()}.
	 * 
	 * @param QTask a QTask object
	 * @param startTime a reservation start time. Can also be interpreted as starting time to
	 *            execute this QTask.
	 * @param duration a reservation duration time. Can also be interpreted as how long to execute
	 *            this QTask.
	 * @param reservID a reservation ID that owns this QTask
	 * @see iQuantum#clock()
	 * @pre QTask != null
	 * @pre startTime > 0
	 * @pre duration > 0
	 * @pre reservID > 0
	 * @post $none
	 */
	public ResQTask(QTask QTask, long startTime, int duration, int reservID) {
		this.QTask = QTask;
		this.startTime = startTime;
		reservId = reservID;
		this.duration = duration;

		init();
	}

	/**
	 * Gets the QTask or reservation start time.
	 * 
	 * @return QTask's starting time
	 * @pre $none
	 * @post $none
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Gets the reservation duration time.
	 * 
	 * @return reservation duration time
	 * @pre $none
	 * @post $none
	 */
	public int getDurationTime() {
		return duration;
	}

	/**
	 * Gets the number of PEs required to execute this QTask.
	 * 
	 * @return number of Pe
	 * @pre $none
	 * @post $none
	 */
	public int getNumberOfPes() {
		return pesNumber;
	}

	/**
	 * Gets the reservation ID that owns this QTask.
	 * 
	 * @return a reservation ID
	 * @pre $none
	 * @post $none
	 */
	public int getReservationID() {
		return reservId;
	}

	/**
	 * Checks whether this QTask is submitted by reserving or not.
	 * 
	 * @return <tt>true</tt> if this QTask has reserved before, <tt>false</tt> otherwise
	 * @pre $none
	 * @post $none
	 */
	public boolean hasReserved() {
		if (reservId == NOT_FOUND) {
			return false;
		}

		return true;
	}

	/**
	 * Initialises all local attributes.
	 * 
	 * @pre $none
	 * @post $none
	 */
	private void init() {

		arrivalTime = iQuantum.clock();
		QTask.setSubmissionTime(arrivalTime);

		// default values
		finishedTime = NOT_FOUND;  // Cannot finish in this hourly slot.
		machineId = NOT_FOUND;
		peId = NOT_FOUND;
		index = 0;
		totalCompletionTime = 0.0;
		startExecTime = 0.0;

		// In case a QTask has been executed partially by some other grid
		// hostList.
		qtaskFinishedSoFar = QTask.getQTaskFinishedSoFar();
	}

	/**
	 * Gets this QTask entity Id.
	 * 
	 * @return the QTask entity Id
	 * @pre $none
	 * @post $none
	 */
	public int getQTaskId() {
		return QTask.getQTaskId();
	}

	/**
	 * Gets the user or owner of this QTask.
	 * 
	 * @return the QTask's user Id
	 * @pre $none
	 * @post $none
	 */
	public int getBrokerId() {
		return QTask.getBrokerId();
	}

	public int getNumShots() {
		return QTask.getNumShots();
	}

	/**
	 * Gets the QTask's length.
	 * 
	 * @return QTask's length
	 * @pre $none
	 * @post $none
	 */
	public long getQTaskLayer() {
		return QTask.getNumLayers();
	}

	public long getQTaskLength() {
		return QTask.getNumLayers() * QTask.getNumShots();
	}

	/**
	 * Sets the QTask status.
	 * 
	 * @param status the QTask status
	 * @return <tt>true</tt> if the new status has been set, <tt>false</tt> otherwise
	 * @pre status >= 0
	 * @post $none
	 */
	public boolean setQTaskStatus(int status) {
		// gets QTask's previous status
		int prevStatus = QTask.getQTaskStatus();

		// if the status of a QTask is the same as last time, then ignore
		if (prevStatus == status) {
			return false;
		}

		boolean success = true;
		try {
			double clock = iQuantum.clock();   // gets the current clock

			// sets QTask's current status
			QTask.setQTaskStatus(status);

			// if a previous QTask status is INEXEC
			if (prevStatus == QTask.RUNNING) {
				// and current status is either CANCELED, PAUSED or SUCCESS
				if (status == QTask.CANCELED || status == QTask.PAUSED || status == QTask.SUCCESS) {
					// then update the QTask completion time
					totalCompletionTime += (clock - startExecTime);
					index = 0;
					return true;
				}
			}

			if (prevStatus == QTask.RESUMED && status == QTask.SUCCESS) {
				// then update the QTask completion time
				totalCompletionTime += (clock - startExecTime);
				return true;
			}

			// if a QTask is now in execution
			if (status == QTask.RUNNING || (prevStatus == QTask.PAUSED && status == QTask.RESUMED)) {
				startExecTime = clock;
				QTask.setExecStartTime(startExecTime);
			}

		} catch (Exception e) {
			success = false;
		}

		return success;
	}

	/**
	 * Gets the QTask's execution start time.
	 * 
	 * @return QTask's execution start time
	 * @pre $none
	 * @post $none
	 */
	public double getExecStartTime() {
		return QTask.getExecStartTime();
	}

	/**
	 * Sets this QTask's execution parameters. These parameters are set by the CloudResource
	 * before departure or sending back to the original QTask's owner.
	 * 
	 * @param wallClockTime the time of this QTask resides in a CloudResource (from arrival time
	 *            until departure time).
	 * @param actualQPUTime the total execution time of this QTask in a CloudResource.
	 * @pre wallClockTime >= 0.0
	 * @pre actualCPUTime >= 0.0
	 * @post $none
	 */
	public void setExecParam(double wallClockTime, double actualQPUTime) {
		QTask.setExecParam(wallClockTime, actualQPUTime);
	}

	public long getRemainingQTaskLength() {
		// Remaining QTask length.
		long rLength = this.getQTaskLength() - qtaskFinishedSoFar;

		// Remaining QTask length can't be negative number.
		if (rLength < 0) {
			return 0;
		}

		return rLength;
	}

	/**
	 * Finalizes all relevant information before <tt>exiting</tt> the CloudResource entity. This
	 * method sets the final data of:
	 * <ul>
	 * <li>wall clock time, i.e. the time of this QTask resides in a CloudResource (from arrival
	 * time until departure time).
	 * <li>actual CPU time, i.e. the total execution time of this QTask in a CloudResource.
	 * <li>QTask's finished time so far
	 * </ul>
	 * 
	 * @pre $none
	 * @post $none
	 */
	public void finalizeQTask() {
		// Sets the wall clock time and actual QPU time
		double wallClockTime = iQuantum.clock() - arrivalTime;
		QTask.setExecParam(wallClockTime, totalCompletionTime);

		long finished = 0;
		if (QTask.getQTaskStatus()== QTask.SUCCESS) {
			finished = QTask.getNumLayers() * QTask.getNumShots();
		} else {
			finished = qtaskFinishedSoFar;
		}

		QTask.setQTaskFinishedSoFar(finished);
	}

	/**
	 * Updates the length of QTask that has already been completed.
	 * 
	 * @param layers QTask length that has been completed so far
	 * @pre layers >= 0.0
	 * @post $none
	 */
	public void updateQTaskFinishedSoFar(long layers) {
		qtaskFinishedSoFar += layers;
	}

	/**
	 * Gets arrival time of a QTask.
	 * 
	 * @return arrival time
	 * @pre $none
	 * @post $result >= 0.0
         * 
         * @todo It is being used different words for the same term.
         * Here it is used arrival time while at Resource inner classe of the QTask class
         * it is being used submissionTime. It needs to be checked if they are 
         * the same term or different ones in fact.
	 */
	public double getQTaskArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the finish time for this QTask. If time is negative, then it is being ignored.
	 * 
	 * @param time finish time
	 * @pre time >= 0.0
	 * @post $none
	 */
	public void setFinishTime(double time) {
		if (time < 0.0) {
			return;
		}

		finishedTime = time;
	}

	/**
	 * Gets the QTask's finish time.
	 * 
	 * @return finish time of a QTask or <tt>-1.0</tt> if it cannot finish in this hourly slot
	 * @pre $none
	 * @post $result >= -1.0
	 */
	public double getQTaskFinishTime() {
		return finishedTime;
	}

	/**
	 * Gets the related QTask object.
	 * 
	 * @return QTask object
	 * @pre $none
	 * @post $result != null
	 */
	public QTask getQTask() {
		return QTask;
	}

	/**
	 * Gets the QTask status.
	 * 
	 * @return QTask status
	 * @pre $none
	 * @post $none
	 */
	public int getQTaskStatus() {
		return QTask.getQTaskStatus();
	}

	/**
	 * Get am Unique Identifier (UID) of the QTask.
	 * 
	 * @return The UID
	 */
	public String getUid() {
		return getBrokerId() + "-" + getQTaskId();
	}

}
