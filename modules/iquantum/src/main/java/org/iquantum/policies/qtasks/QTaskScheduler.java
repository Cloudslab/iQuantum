/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.policies.qtasks;

import org.iquantum.tasks.ResQTask;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.tasks.QTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * QTaskScheduler is an abstract class that represents the policy of scheduling performed by a
 * quantum node to run its {@link QTask QTasks}.
 * So, classes extending this must execute QTasks. Also, the interface for
 * QTask management is also implemented in this class.
 * Each QNode has to have its own instance of a QTaskScheduler.
 * 
 * @author Hoa Nguyen
 * @since iQuantum 1.0
 */
public abstract class QTaskScheduler {

	/** The previous time. */
	private double previousTime;

	/** The list of current CLOPS share available for the QNode using the scheduler. */
	private double currentClops;

	/** The list of QTask waiting to be executed on the QNode. */
	protected List<? extends ResQTask> qtaskWaitingList;

	/** The list of QTasks being executed on the QNode. */
	protected List<? extends ResQTask> qtaskExecList;

	/** The list of paused QTasks. */
	protected List<? extends ResQTask> qtaskPausedList;

	/** The list of finished QTasks. */
	protected List<? extends ResQTask> qtaskFinishedList;

	/** The list of failed QTasks. */
	protected List<? extends ResQTask> qtaskFailedList;

	/**
	 * Creates a new QTaskScheduler object.
         * A QTaskScheduler must be created before starting the actual simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	public QTaskScheduler() {
		setPreviousTime(0.0);
		qtaskWaitingList = new LinkedList<ResQTask>();
		qtaskExecList = new LinkedList<ResQTask>();
		qtaskPausedList = new LinkedList<ResQTask>();
		qtaskFinishedList = new LinkedList<ResQTask>();
		qtaskFailedList = new LinkedList<ResQTask>();
	}

	public abstract Map<String, String> qtaskMapping(QTask QTask, QNode qNode);

	/**
	 * Updates the processing of QTasks running under management of this scheduler.
	 * 
	 * @param currentTime current simulation time
	 * @param clopsShare list with CLOPS share of each Pe available to the scheduler
	 * @return the predicted completion time of the earliest finishing QTask, 
         * or 0 if there is no next events
	 * @pre currentTime >= 0
	 * @post $none
	 */
	public abstract double updateQNodeProcessing(double currentTime, double clopsShare);

	/**
	 * Receives an QTask to be executed in the QNode managed by this scheduler.
	 * 
	 * @param ql the submited QTask 
	 * @param fileTransferTime time required to move the required files from the SAN to the QNode
	 * @return expected finish time of this QTask, or 0 if it is in a waiting queue
	 * @pre gl != null
	 * @post $none
	 */
	public abstract double qtaskSubmit(QTask ql, double fileTransferTime);

	/**
	 * Receives an QTask to be executed in the QNode managed by this scheduler.
	 * 
	 * @param ql the submited QTask
	 * @return expected finish time of this QTask, or 0 if it is in a waiting queue
	 * @pre gl != null
	 * @post $none
	 */
	public abstract double qtaskSubmit(QTask ql);

	/**
	 * Cancels execution of a QTask.
	 * 
	 * @param qlId ID of the QTask being canceled
	 * @return the canceled QTask, $null if not found
	 * @pre $none
	 * @post $none
	 */
	public abstract QTask qtaskCancel(int qlId);

	/**
	 * Pauses execution of a QTask.
	 * 
	 * @param qlId ID of the QTask being paused
	 * @return $true if QTask paused, $false otherwise
	 * @pre $none
	 * @post $none
	 */
	public abstract boolean qtaskPause(int qlId);

	/**
	 * Resumes execution of a paused QTask.
	 * 
	 * @param qlId ID of the QTask being resumed
	 * @return expected finish time of the QTask, 0.0 if queued
	 * @pre $none
	 * @post $none
	 */
	public abstract double qtaskResume(int qlId);

	/**
	 * Processes a finished QTask.
	 * 
	 * @param rql finished QTask
	 * @pre rgl != $null
	 * @post $none
	 */
	public abstract void qtaskFinish(ResQTask rql);

	/**
	 * Gets the status of a QTask.
	 * 
	 * @param qlId ID of the QTask
	 * @return status of the QTask, -1 if QTask not found
	 * @pre $none
	 * @post $none
         * 
         * @todo QTask status should be an enum
	 */
	public abstract int getQTaskStatus(int qlId);

	/**
	 * Informs if there is any QTask that finished to execute in the QNode managed by this scheduler.
	 * 
	 * @return $true if there is at least one finished QTask; $false otherwise
	 * @pre $none
	 * @post $none
         * @todo the method name would be isThereFinishedQTasks to be clearer
	 */
	public abstract boolean isFinishedQTasks();

	/**
	 * Returns the next QTask in the finished list.
	 * 
	 * @return a finished QTask or $null if the respective list is empty
	 * @pre $none
	 * @post $none
	 */
	public abstract QTask getNextFinishedQTask();

	/**
	 * Returns the number of QTasks running in the virtual machine.
	 * 
	 * @return number of QTasks running
	 * @pre $none
	 * @post $none
	 */
	public abstract int runningQTasks();

	/**
	 * Returns one QTask to migrate to another QNode.
	 * 
	 * @return one running QTask
	 * @pre $none
	 * @post $none
	 */
	public abstract QTask migrateQTask();


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
	 * Sets the current CLOPS share.
	 * 
	 * @param currentClops the new current CLOPS share
	 */
	protected void setCurrentClops(double currentClops) {
		this.currentClops = currentClops;
	}

	/**
	 * Gets the current CLOPS share.
	 * 
	 * @return the current CLOPS share
	 */
	public double getCurrentClops() {
		return currentClops;
	}

	/**
	 * Gets the QTask waiting list.
	 * 
	 * @param <T> the generic type
	 * @return the QTask waiting list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQTask> List<T> getQTaskWaitingList() {
		return (List<T>) qtaskWaitingList;
	}

	/**
	 * QTask waiting list.
	 * 
	 * @param <T> the generic type
	 * @param qtaskWaitingList the QTask waiting list
	 */
	protected <T extends ResQTask> void setQTaskWaitingList(List<T> qtaskWaitingList) {
		this.qtaskWaitingList = qtaskWaitingList;
	}

	/**
	 * Gets the QTask exec list.
	 * 
	 * @param <T> the generic type
	 * @return the QTask exec list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQTask> List<T> getQTaskExecList() {
		return (List<T>) qtaskExecList;
	}

	/**
	 * Sets the QTask exec list.
	 * 
	 * @param <T> the generic type
	 * @param qtaskExecList the new QTask exec list
	 */
	protected <T extends ResQTask> void setQTaskExecList(List<T> qtaskExecList) {
		this.qtaskExecList = qtaskExecList;
	}

	/**
	 * Gets the QTask paused list.
	 * 
	 * @param <T> the generic type
	 * @return the QTask paused list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQTask> List<T> getQTaskPausedList() {
		return (List<T>) qtaskPausedList;
	}

	/**
	 * Sets the QTask paused list.
	 * 
	 * @param <T> the generic type
	 * @param qtaskPausedList the new QTask paused list
	 */
	protected <T extends ResQTask> void setQTaskPausedList(List<T> qtaskPausedList) {
		this.qtaskPausedList = qtaskPausedList;
	}

	/**
	 * Gets the QTask finished list.
	 * 
	 * @param <T> the generic type
	 * @return the QTask finished list
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQTask> List<T> getQTaskFinishedList() {
		return (List<T>) qtaskFinishedList;
	}

	/**
	 * Sets the QTask finished list.
	 * 
	 * @param <T> the generic type
	 * @param qtaskFinishedList the new QTask finished list
	 */
	protected <T extends ResQTask> void setQTaskFinishedList(List<T> qtaskFinishedList) {
		this.qtaskFinishedList = qtaskFinishedList;
	}

	/**
	 * Gets the QTask failed list.
	 * 
	 * @param <T> the generic type
	 * @return the QTask failed list.
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResQTask> List<T> getQTaskFailedList() {
		return (List<T>) qtaskFailedList;
	}

	/**
	 * Sets the QTask failed list.
	 * 
	 * @param <T> the generic type
	 * @param qtaskFailedList the new QTask failed list.
	 */
	protected <T extends ResQTask> void setQTaskFailedList(List<T> qtaskFailedList) {
		this.qtaskFailedList = qtaskFailedList;
	}

}
