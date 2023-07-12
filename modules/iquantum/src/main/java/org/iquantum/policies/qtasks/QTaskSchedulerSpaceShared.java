/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.policies.qtasks;
import org.iquantum.tasks.ResQTask;
import org.iquantum.core.iQuantum;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.policies.qubitMapping.QubitMappingBackTracking;
import org.iquantum.tasks.QTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.iquantum.utils.DataFormat.roundDouble;

/**
 * QTaskSchedulerSpaceShared implements a policy of scheduling performed by a quantum node.
 * @author Hoa Nguyen
 * @since iQuantum 1.0
 */
public class QTaskSchedulerSpaceShared extends QTaskScheduler {
	protected int currentQPUs;

	public QTaskSchedulerSpaceShared() {
		super();
		// Initialize the number of QPUs (single QPU by default)
		currentQPUs = 1;
	}

	@Override
	public Map<String, String> qtaskMapping(QTask QTask, QNode qNode) {
		return QubitMappingBackTracking.findMapping(qNode.getQubitTopology(), QTask.getQubitTopology());
	}

	@Override
	public double updateQNodeProcessing(double currentTime, double clops) {
		setCurrentClops(clops);
		// Get the time passed since the last time this method was called
		double timeSpam = currentTime - getPreviousTime();

		// Update the remaining length of qtasks (qtask length = circuit layers * shots)
		for (ResQTask rql : getQTaskExecList()) {
			rql.updateQTaskFinishedSoFar((long) (getCurrentClops() * timeSpam));
		}

		// If there is no qtask in the executing list, return 0.0
		if (getQTaskExecList().size() == 0) {
			setPreviousTime(currentTime);
			return 0.0;
		}

		// Check finished qtasks
		int finished = 0;
		List<ResQTask> toRemove = new ArrayList<ResQTask>();
		for (ResQTask rql : getQTaskExecList()) {
			long remainingLength = rql.getRemainingQTaskLength();
			if (remainingLength == 0) { // finished: remove from the list
				toRemove.add(rql);
				qtaskFinish(rql);
				finished++;
			}
		}
		getQTaskExecList().removeAll(toRemove);


		// Check waiting qtasks
		if (!getQTaskWaitingList().isEmpty()) {
			for (int i = 0; i < finished; i++) {
				toRemove.clear();
				for (ResQTask rql : getQTaskWaitingList()) {
					rql.setQTaskStatus(QTask.RUNNING);
					getQTaskExecList().add(rql);
					toRemove.add(rql);
					break;
				}
				getQTaskWaitingList().removeAll(toRemove);
			}
		}

		// Estimate finish time of qtasks
		double nextEvent = Double.MAX_VALUE;
		for (ResQTask rql : getQTaskExecList()) {
			double estimatedFinishTime = currentTime + (rql.getRemainingQTaskLength() / getCurrentClops());
			estimatedFinishTime = roundDouble(estimatedFinishTime,2); // round up to 2 decimal places
			if (estimatedFinishTime - currentTime < iQuantum.getMinTimeBetweenEvents()) {
				estimatedFinishTime = currentTime + iQuantum.getMinTimeBetweenEvents();
			}
			if (estimatedFinishTime < nextEvent) {
				nextEvent = estimatedFinishTime;
			}
		}
		setPreviousTime(currentTime);
		return nextEvent;
	}

	protected double getCapacity(double clops) {
		double capacity = clops;

		// If there is any qtask in the executing list, return 0.0
		if(!getQTaskExecList().isEmpty()) {
			return 0.0;
		}
		return capacity;
	}

	@Override
	public QTask qtaskCancel(int qtaskId) {
		boolean found = false;
		int position = 0;
		// First, looks in the finished queue
		found = false;
		for (ResQTask rql : getQTaskFinishedList()) {
			if (rql.getQTaskId() == qtaskId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			return getQTaskFinishedList().remove(position).getQTask();
		}

		// Then searches in the exec list
		position=0;
		for (ResQTask rql : getQTaskExecList()) {
			if (rql.getQTaskId() == qtaskId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResQTask rql = getQTaskExecList().remove(position);
			if (rql.getRemainingQTaskLength() == 0) {
				qtaskFinish(rql);
			} else {
				rql.setQTaskStatus(QTask.CANCELED);
			}
			return rql.getQTask();
		}

		// Now, looks in the paused queue
		found = false;
		position=0;
		for (ResQTask rql : getQTaskPausedList()) {
			if (rql.getQTaskId() == qtaskId) {
				found = true;
				rql.setQTaskStatus(QTask.CANCELED);
				break;
			}
			position++;
		}

		if (found) {
			return getQTaskPausedList().remove(position).getQTask();
		}
		return null;
	}

	@Override
	public void qtaskFinish(ResQTask rql) {
		rql.setQTaskStatus(QTask.SUCCESS);
		rql.finalizeQTask();
		getQTaskFinishedList().add(rql);
	}

	@Override
	public double qtaskSubmit(QTask QTask, double fileTransferTime) {
		if(getQTaskExecList().size() < 1) {
			ResQTask rql = new ResQTask(QTask);
			rql.setQTaskStatus(QTask.RUNNING);
			getQTaskExecList().add(rql);
			double estimatedCompletionTime = (QTask.getNumLayers() / getCurrentClops()) * QTask.getNumShots();
			return estimatedCompletionTime;
		} else {
			ResQTask rql = new ResQTask(QTask);
			rql.setQTaskStatus(QTask.QUEUED);
			getQTaskWaitingList().add(rql);
			return 0.0;
		}
	}

	@Override
	public double qtaskSubmit(QTask QTask) {
		return qtaskSubmit(QTask, 0.0);
	}

	@Override
	public int getQTaskStatus(int qtaskId) {
		for (ResQTask rcl : getQTaskExecList()) {
			if (rcl.getQTaskId() == qtaskId) {
				return rcl.getQTaskStatus();
			}
		}
		for (ResQTask rcl : getQTaskPausedList()) {
			if (rcl.getQTaskId() == qtaskId) {
				return rcl.getQTaskStatus();
			}
		}
		return -1;
	}

	@Override
	public boolean isFinishedQTasks() {
		return getQTaskFinishedList().size() > 0;
	}

	@Override
	public QTask getNextFinishedQTask() {
		if (getQTaskFinishedList().size() > 0) {
			return getQTaskFinishedList().remove(0).getQTask();
		}
		return null;
	}

	@Override
	public int runningQTasks() {
		return getQTaskExecList().size();
	}

	@Override
	public QTask migrateQTask() {
		// TODO: QTask migration is not supported as of now
		ResQTask rgl = getQTaskExecList().remove(0);
		rgl.finalizeQTask();
		return rgl.getQTask();
	}
	@Override
	public boolean qtaskPause(int qtaskId) {
		// TODO: QTask pause is not supported as of now
		return false;
	}

	@Override
	public double qtaskResume(int qtaskId) {
		// TODO: QTask resume is not supported as of now
		return 0.0;
	}
}
