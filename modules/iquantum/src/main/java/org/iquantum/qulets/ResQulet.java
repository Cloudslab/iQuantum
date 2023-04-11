/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.qulets;

import org.iquantum.core.iQuantum;

/**
 * iQuantum ResQulet represents a Qulet submitted to CloudResource for processing. This class
 * keeps track the time for all activities in the CloudResource for a specific Qulet. Before a
 * Qulet exits the CloudResource, it is RECOMMENDED to call this method
 * {@link #finalizeQulet()}.
 * <p/>
 * It contains a Qulet object along with its arrival time and the ID of the machine and the Pe
 * (Processing Element) allocated to it. It acts as a placeholder for maintaining the amount of
 * resource share allocated at various times for simulating any scheduling using internal events.
 * <p/>
 * Note: This class is inspired from CloudSim's ResCloudlet class.
 * @author Hoa Nguyen
 * @since iQuantum Toolkit 1.0
 */
public class ResQulet {

	/** The Qulet object. */
	private final Qulet qulet;

	/** The Qulet arrival time for the first time. */
	private double arrivalTime;

	/** The estimation of Qulet finished time. */
	private double finishedTime;

	/** The length of Qulet finished so far. */
	private long quletFinishedSoFar;

	/**
	 * Qulet execution start time. This attribute will only hold the latest time since a Qulet
	 * can be canceled, paused or resumed.
	 */
	private double startExecTime;

	/** The total time to complete this Qulet. */
	private double totalCompletionTime;

	// The below attributes are only to be used by the SpaceShared policy.

	/** The machine id this Qulet is assigned to. */
	private int machineId;

	/** The Pe id this Qulet is assigned to. */
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

	/** The num Pe needed to execute this Qulet. */
	private int pesNumber;

	/**
	 * Allocates a new ResQulet object upon the arrival of a Qulet object.
	 * 
	 * @param Qulet a Qulet object
	 * @see iQuantum#clock()
	 * @pre Qulet != null
	 * @post $none
	 */
	public ResQulet(Qulet qulet) {
		// when a new ResQulet is created, then it will automatically set
		// the submission time and other properties, such as remaining length
		this.qulet = qulet;
		startTime = 0;
		reservId = NOT_FOUND;
		duration = 0;

		init();
	}

	/**
	 * Allocates a new ResQulet object upon the arrival of a Qulet object. Use this
	 * constructor to store reserved Qulets, i.e. Qulets that done reservation before. The
	 * arriving time is determined by {@link iQuantum#clock()}.
	 * 
	 * @param Qulet a Qulet object
	 * @param startTime a reservation start time. Can also be interpreted as starting time to
	 *            execute this Qulet.
	 * @param duration a reservation duration time. Can also be interpreted as how long to execute
	 *            this Qulet.
	 * @param reservID a reservation ID that owns this Qulet
	 * @see iQuantum#clock()
	 * @pre Qulet != null
	 * @pre startTime > 0
	 * @pre duration > 0
	 * @pre reservID > 0
	 * @post $none
	 */
	public ResQulet(Qulet qulet, long startTime, int duration, int reservID) {
		this.qulet = qulet;
		this.startTime = startTime;
		reservId = reservID;
		this.duration = duration;

		init();
	}

	/**
	 * Gets the Qulet or reservation start time.
	 * 
	 * @return Qulet's starting time
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
	 * Gets the number of PEs required to execute this Qulet.
	 * 
	 * @return number of Pe
	 * @pre $none
	 * @post $none
	 */
	public int getNumberOfPes() {
		return pesNumber;
	}

	/**
	 * Gets the reservation ID that owns this Qulet.
	 * 
	 * @return a reservation ID
	 * @pre $none
	 * @post $none
	 */
	public int getReservationID() {
		return reservId;
	}

	/**
	 * Checks whether this Qulet is submitted by reserving or not.
	 * 
	 * @return <tt>true</tt> if this Qulet has reserved before, <tt>false</tt> otherwise
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
		qulet.setSubmissionTime(arrivalTime);

		// default values
		finishedTime = NOT_FOUND;  // Cannot finish in this hourly slot.
		machineId = NOT_FOUND;
		peId = NOT_FOUND;
		index = 0;
		totalCompletionTime = 0.0;
		startExecTime = 0.0;

		// In case a Qulet has been executed partially by some other grid
		// hostList.
		quletFinishedSoFar = qulet.getQuletFinishedSoFar();
	}

	/**
	 * Gets this Qulet entity Id.
	 * 
	 * @return the Qulet entity Id
	 * @pre $none
	 * @post $none
	 */
	public int getQuletId() {
		return qulet.getQuletId();
	}

	/**
	 * Gets the user or owner of this Qulet.
	 * 
	 * @return the Qulet's user Id
	 * @pre $none
	 * @post $none
	 */
	public int getBrokerId() {
		return qulet.getBrokerId();
	}

	public int getNumShots() {
		return qulet.getNumShots();
	}

	/**
	 * Gets the Qulet's length.
	 * 
	 * @return Qulet's length
	 * @pre $none
	 * @post $none
	 */
	public long getQuletLayer() {
		return qulet.getNumLayers();
	}

	public long getQuletLength() {
		return qulet.getNumLayers() * qulet.getNumShots();
	}

	/**
	 * Sets the Qulet status.
	 * 
	 * @param status the Qulet status
	 * @return <tt>true</tt> if the new status has been set, <tt>false</tt> otherwise
	 * @pre status >= 0
	 * @post $none
	 */
	public boolean setQuletStatus(int status) {
		// gets Qulet's previous status
		int prevStatus = qulet.getQuletStatus();

		// if the status of a Qulet is the same as last time, then ignore
		if (prevStatus == status) {
			return false;
		}

		boolean success = true;
		try {
			double clock = iQuantum.clock();   // gets the current clock

			// sets Qulet's current status
			qulet.setQuletStatus(status);

			// if a previous Qulet status is INEXEC
			if (prevStatus == Qulet.RUNNING) {
				// and current status is either CANCELED, PAUSED or SUCCESS
				if (status == Qulet.CANCELED || status == Qulet.PAUSED || status == Qulet.SUCCESS) {
					// then update the Qulet completion time
					totalCompletionTime += (clock - startExecTime);
					index = 0;
					return true;
				}
			}

			if (prevStatus == Qulet.RESUMED && status == Qulet.SUCCESS) {
				// then update the Qulet completion time
				totalCompletionTime += (clock - startExecTime);
				return true;
			}

			// if a Qulet is now in execution
			if (status == Qulet.RUNNING || (prevStatus == Qulet.PAUSED && status == Qulet.RESUMED)) {
				startExecTime = clock;
				qulet.setExecStartTime(startExecTime);
			}

		} catch (Exception e) {
			success = false;
		}

		return success;
	}

	/**
	 * Gets the Qulet's execution start time.
	 * 
	 * @return Qulet's execution start time
	 * @pre $none
	 * @post $none
	 */
	public double getExecStartTime() {
		return qulet.getExecStartTime();
	}

	/**
	 * Sets this Qulet's execution parameters. These parameters are set by the CloudResource
	 * before departure or sending back to the original Qulet's owner.
	 * 
	 * @param wallClockTime the time of this Qulet resides in a CloudResource (from arrival time
	 *            until departure time).
	 * @param actualCPUTime the total execution time of this Qulet in a CloudResource.
	 * @pre wallClockTime >= 0.0
	 * @pre actualCPUTime >= 0.0
	 * @post $none
	 */
	public void setExecParam(double wallClockTime, double actualQPUTime) {
		qulet.setExecParam(wallClockTime, actualQPUTime);
	}

	public long getRemainingQuletLength() {
		// Remaining Qulet length.
		long rLength = this.getQuletLength() - quletFinishedSoFar;

		// Remaining Qulet length can't be negative number.
		if (rLength < 0) {
			return 0;
		}

		return rLength;
	}

	/**
	 * Finalizes all relevant information before <tt>exiting</tt> the CloudResource entity. This
	 * method sets the final data of:
	 * <ul>
	 * <li>wall clock time, i.e. the time of this Qulet resides in a CloudResource (from arrival
	 * time until departure time).
	 * <li>actual CPU time, i.e. the total execution time of this Qulet in a CloudResource.
	 * <li>Qulet's finished time so far
	 * </ul>
	 * 
	 * @pre $none
	 * @post $none
	 */
	public void finalizeQulet() {
		// Sets the wall clock time and actual QPU time
		double wallClockTime = iQuantum.clock() - arrivalTime;
		qulet.setExecParam(wallClockTime, totalCompletionTime);

		long finished = 0;
		if (qulet.getQuletStatus()==Qulet.SUCCESS) {
			finished = qulet.getNumLayers() * qulet.getNumShots();
		} else {
			finished = quletFinishedSoFar;
		}

		qulet.setQuletFinishedSoFar(finished);
	}

	/**
	 * Updates the length of Qulet that has already been completed.
	 * 
	 * @param miLength Qulet length in Instructions (I)
	 * @pre miLength >= 0.0
	 * @post $none
	 */
	public void updateQuletFinishedSoFar(long layers) {
		quletFinishedSoFar += layers;
	}

	/**
	 * Gets arrival time of a Qulet.
	 * 
	 * @return arrival time
	 * @pre $none
	 * @post $result >= 0.0
         * 
         * @todo It is being used different words for the same term.
         * Here it is used arrival time while at Resource inner classe of the Qulet class
         * it is being used submissionTime. It needs to be checked if they are 
         * the same term or different ones in fact.
	 */
	public double getQuletArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the finish time for this Qulet. If time is negative, then it is being ignored.
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
	 * Gets the Qulet's finish time.
	 * 
	 * @return finish time of a Qulet or <tt>-1.0</tt> if it cannot finish in this hourly slot
	 * @pre $none
	 * @post $result >= -1.0
	 */
	public double getQuletFinishTime() {
		return finishedTime;
	}

	/**
	 * Gets the related Qulet object.
	 * 
	 * @return Qulet object
	 * @pre $none
	 * @post $result != null
	 */
	public Qulet getQulet() {
		return qulet;
	}

	/**
	 * Gets the Qulet status.
	 * 
	 * @return Qulet status
	 * @pre $none
	 * @post $none
	 */
	public int getQuletStatus() {
		return qulet.getQuletStatus();
	}

	/**
	 * Get am Unique Identifier (UID) of the Qulet.
	 * 
	 * @return The UID
	 */
	public String getUid() {
		return getBrokerId() + "-" + getQuletId();
	}

}
