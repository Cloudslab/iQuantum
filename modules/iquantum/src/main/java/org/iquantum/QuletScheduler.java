/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum;

import java.util.LinkedList;
import java.util.List;


/**
 * QuletScheduler is an abstract class that represents the policy of scheduling performed by a
 * virtual machine to run its {@link Qulet Qulets}.
 * So, classes extending this must execute Qulets. Also, the interface for
 * Qulet management is also implemented in this class.
 * Each VM has to have its own instance of a QuletScheduler.
 * 
 * @author Rodrigo N. Calheiros
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 1.0
 */
public abstract class QuletScheduler {

	/** The previous time. */
	private double previousTime;

	/** The list of current mips share available for the VM using the scheduler. */
	private double currentClops;

	/** The list of Qulet waiting to be executed on the VM. */
	protected List<? extends ResQulet> quletWaitingList;

	/** The list of Qulets being executed on the VM. */
	protected List<? extends ResQulet> quletExecList;

	/** The list of paused Qulets. */
	protected List<? extends ResQulet> quletPausedList;

	/** The list of finished Qulets. */
	protected List<? extends ResQulet> quletFinishedList;

	/** The list of failed Qulets. */
	protected List<? extends ResQulet> quletFailedList;

	/**
	 * Creates a new QuletScheduler object. 
         * A QuletScheduler must be created before starting the actual simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	public QuletScheduler() {
		setPreviousTime(0.0);
		quletWaitingList = new LinkedList<ResQulet>();
		quletExecList = new LinkedList<ResQulet>();
		quletPausedList = new LinkedList<ResQulet>();
		quletFinishedList = new LinkedList<ResQulet>();
		quletFailedList = new LinkedList<ResQulet>();
	}

	/**
	 * Updates the processing of Qulets running under management of this scheduler.
	 * 
	 * @param currentTime current simulation time
	 * @param clopsShare list with MIPS share of each Pe available to the scheduler
	 * @return the predicted completion time of the earliest finishing Qulet, 
         * or 0 if there is no next events
	 * @pre currentTime >= 0
	 * @post $none
	 */
	public abstract double updateQNodeProcessing(double currentTime, double clopsShare);

	/**
	 * Receives an Qulet to be executed in the VM managed by this scheduler.
	 * 
	 * @param ql the submited Qulet 
	 * @param fileTransferTime time required to move the required files from the SAN to the VM
	 * @return expected finish time of this Qulet, or 0 if it is in a waiting queue
	 * @pre gl != null
	 * @post $none
	 */
	public abstract double quletSubmit(Qulet ql, double fileTransferTime);

	/**
	 * Receives an Qulet to be executed in the VM managed by this scheduler.
	 * 
	 * @param gl the submited Qulet
	 * @return expected finish time of this Qulet, or 0 if it is in a waiting queue
	 * @pre gl != null
	 * @post $none
	 */
	public abstract double quletSubmit(Qulet ql);

	/**
	 * Cancels execution of a Qulet.
	 * 
	 * @param qlId ID of the Qulet being canceled
	 * @return the canceled Qulet, $null if not found
	 * @pre $none
	 * @post $none
	 */
	public abstract Qulet quletCancel(int qlId);

	/**
	 * Pauses execution of a Qulet.
	 * 
	 * @param qlId ID of the Qulet being paused
	 * @return $true if Qulet paused, $false otherwise
	 * @pre $none
	 * @post $none
	 */
	public abstract boolean quletPause(int qlId);

	/**
	 * Resumes execution of a paused Qulet.
	 * 
	 * @param qlId ID of the Qulet being resumed
	 * @return expected finish time of the Qulet, 0.0 if queued
	 * @pre $none
	 * @post $none
	 */
	public abstract double quletResume(int qlId);

	/**
	 * Processes a finished Qulet.
	 * 
	 * @param rql finished Qulet
	 * @pre rgl != $null
	 * @post $none
	 */
	public abstract void quletFinish(ResQulet rql);

	/**
	 * Gets the status of a Qulet.
	 * 
	 * @param qlId ID of the Qulet
	 * @return status of the Qulet, -1 if Qulet not found
	 * @pre $none
	 * @post $none
         * 
         * @todo Qulet status should be an enum
	 */
	public abstract int getQuletStatus(int qlId);

	/**
	 * Informs if there is any Qulet that finished to execute in the VM managed by this scheduler.
	 * 
	 * @return $true if there is at least one finished Qulet; $false otherwise
	 * @pre $none
	 * @post $none
         * @todo the method name would be isThereFinishedQulets to be clearer
	 */
	public abstract boolean isFinishedQulets();

	/**
	 * Returns the next Qulet in the finished list.
	 * 
	 * @return a finished Qulet or $null if the respective list is empty
	 * @pre $none
	 * @post $none
	 */
	public abstract Qulet getNextFinishedQulet();

	/**
	 * Returns the number of Qulets running in the virtual machine.
	 * 
	 * @return number of Qulets running
	 * @pre $none
	 * @post $none
	 */
	public abstract int runningQulets();

	/**
	 * Returns one Qulet to migrate to another vm.
	 * 
	 * @return one running Qulet
	 * @pre $none
	 * @post $none
	 */
	public abstract Qulet migrateQulet();


	/**
	 * Gets the current requested mips.
	 * 
	 * @return the current mips
	 */
	public abstract List<Double> getCurrentRequestedClops();

	/**
	 * Gets the total current available mips for the Qulet.
	 * 
	 * @param rcl the rcl
	 * @param mipsShare the mips share
	 * @return the total current mips
         * @todo In fact, this method is returning different data depending 
         * of the subclass. It is expected that the way the method use to compute
         * the resulting value can be different in every subclass,
         * but is not supposed that each subclass returns a complete different 
         * result for the same method of the superclass.
         * In some class such as {@link NetworkQuletSpaceSharedScheduler},
         * the method returns the average MIPS for the available PEs,
         * in other classes such as {@link QuletSchedulerDynamicWorkload} it returns
         * the MIPS' sum of all PEs.
	 */
	public abstract double getTotalCurrentAvailableClopsForQulet(ResQulet rql, double clops);

	/**
	 * Gets the total current requested mips for a given Qulet.
	 * 
	 * @param rcl the rcl
	 * @param time the time
	 * @return the total current requested mips for the given Qulet
	 */
	public abstract double getTotalCurrentRequestedClopsForQulet(ResQulet rql, double time);

	/**
	 * Gets the total current allocated mips for Qulet.
	 * 
	 * @param rcl the rcl
	 * @param time the time
	 * @return the total current allocated mips for Qulet
	 */
	public abstract double getTotalCurrentAllocatedClopsForQulet(ResQulet rql, double time);

	/**
	 * Gets the previous time.
	 * 
	 * @return the previous time
	 */
	public double getPreviousTime() {
		return previousTime;
	}

	/**
	 * Sets the previous time.
	 * 
	 * @param previousTime the new previous time
	 */
	protected void setPreviousTime(double previousTime) {
		this.previousTime = previousTime;
	}

	/**
	 * Sets the current mips share.
	 * 
	 * @param currentClopsShare the new current mips share
	 */
	protected void setCurrentClops(double currentClops) {
		this.currentClops = currentClops;
	}

	/**
	 * Gets the current mips share.
	 * 
	 * @return the current mips share
	 */
	public double getCurrentClops() {
		return currentClops;
	}

	/**
	 * Gets the Qulet waiting list.
	 * 
	 * @param <T> the generic type
	 * @return the Qulet waiting list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQulet> List<T> getQuletWaitingList() {
		return (List<T>) quletWaitingList;
	}

	/**
	 * Qulet waiting list.
	 * 
	 * @param <T> the generic type
	 * @param quletWaitingList the Qulet waiting list
	 */
	protected <T extends ResQulet> void setQuletWaitingList(List<T> quletWaitingList) {
		this.quletWaitingList = quletWaitingList;
	}

	/**
	 * Gets the Qulet exec list.
	 * 
	 * @param <T> the generic type
	 * @return the Qulet exec list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQulet> List<T> getQuletExecList() {
		return (List<T>) quletExecList;
	}

	/**
	 * Sets the Qulet exec list.
	 * 
	 * @param <T> the generic type
	 * @param quletExecList the new Qulet exec list
	 */
	protected <T extends ResQulet> void setQuletExecList(List<T> quletExecList) {
		this.quletExecList = quletExecList;
	}

	/**
	 * Gets the Qulet paused list.
	 * 
	 * @param <T> the generic type
	 * @return the Qulet paused list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQulet> List<T> getQuletPausedList() {
		return (List<T>) quletPausedList;
	}

	/**
	 * Sets the Qulet paused list.
	 * 
	 * @param <T> the generic type
	 * @param quletPausedList the new Qulet paused list
	 */
	protected <T extends ResQulet> void setQuletPausedList(List<T> quletPausedList) {
		this.quletPausedList = quletPausedList;
	}

	/**
	 * Gets the Qulet finished list.
	 * 
	 * @param <T> the generic type
	 * @return the Qulet finished list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQulet> List<T> getQuletFinishedList() {
		return (List<T>) quletFinishedList;
	}

	/**
	 * Sets the Qulet finished list.
	 * 
	 * @param <T> the generic type
	 * @param quletFinishedList the new Qulet finished list
	 */
	protected <T extends ResQulet> void setQuletFinishedList(List<T> quletFinishedList) {
		this.quletFinishedList = quletFinishedList;
	}

	/**
	 * Gets the Qulet failed list.
	 * 
	 * @param <T> the generic type
	 * @return the Qulet failed list.
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQulet> List<T> getQuletFailedList() {
		return (List<T>) quletFailedList;
	}

	/**
	 * Sets the Qulet failed list.
	 * 
	 * @param <T> the generic type
	 * @param quletFailedList the new Qulet failed list.
	 */
	protected <T extends ResQulet> void setQuletFailedList(List<T> quletFailedList) {
		this.quletFailedList = quletFailedList;
	}

}
