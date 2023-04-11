/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */

package org.iquantum.schedulers;
import org.iquantum.core.iQuantum;
import org.iquantum.qulets.Qulet;
import org.iquantum.qulets.ResQulet;

import java.util.ArrayList;
import java.util.List;

import static org.iquantum.utils.DataFormat.roundDouble;

/**
 * QuletSchedulerSpaceShared implements a policy of scheduling performed by a quantum node.
 * @author Hoa Nguyen
 * @since iQuantum 1.0
 */
public class QuletSchedulerSpaceShared extends QuletScheduler {
	protected int currentQPUs;

	public QuletSchedulerSpaceShared() {
		super();
		// Initialize the number of QPUs (single QPU by default)
		currentQPUs = 1;
	}

	@Override
	public double updateQNodeProcessing(double currentTime, double clops) {
		setCurrentClops(clops);
		// Get the time passed since the last time this method was called
		double timeSpam = currentTime - getPreviousTime();

		// Update the remaining length of qulets (qulet length = circuit layers * shots)
		for (ResQulet rql : getQuletExecList()) {
			rql.updateQuletFinishedSoFar((long) (getCurrentClops() * timeSpam));
		}

		// If there is no qulet in the executing list, return 0.0
		if (getQuletExecList().size() == 0) {
			setPreviousTime(currentTime);
			return 0.0;
		}

		// Check finished qulets
		int finished = 0;
		List<ResQulet> toRemove = new ArrayList<ResQulet>();
		for (ResQulet rql : getQuletExecList()) {
			long remainingLength = rql.getRemainingQuletLength();
			if (remainingLength == 0) { // finished: remove from the list
				toRemove.add(rql);
				quletFinish(rql);
				finished++;
			}
		}
		getQuletExecList().removeAll(toRemove);


		// Check waiting qulets
		if (!getQuletWaitingList().isEmpty()) {
			for (int i = 0; i < finished; i++) {
				toRemove.clear();
				for (ResQulet rql : getQuletWaitingList()) {
					rql.setQuletStatus(Qulet.RUNNING);
					getQuletExecList().add(rql);
					toRemove.add(rql);
					break;
				}
				getQuletWaitingList().removeAll(toRemove);
			}
		}

		// Estimate finish time of qulets
		double nextEvent = Double.MAX_VALUE;
		for (ResQulet rql : getQuletExecList()) {
			double estimatedFinishTime = currentTime + (rql.getRemainingQuletLength() / getCurrentClops());
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

		// If there is any qulet in the executing list, return 0.0
		if(!getQuletExecList().isEmpty()) {
			return 0.0;
		}
		return capacity;
	}

	@Override
	public Qulet quletCancel(int quletId) {
		boolean found = false;
		int position = 0;
		// First, looks in the finished queue
		found = false;
		for (ResQulet rql : getQuletFinishedList()) {
			if (rql.getQuletId() == quletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			return getQuletFinishedList().remove(position).getQulet();
		}

		// Then searches in the exec list
		position=0;
		for (ResQulet rql : getQuletExecList()) {
			if (rql.getQuletId() == quletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResQulet rql = getQuletExecList().remove(position);
			if (rql.getRemainingQuletLength() == 0) {
				quletFinish(rql);
			} else {
				rql.setQuletStatus(Qulet.CANCELED);
			}
			return rql.getQulet();
		}

		// Now, looks in the paused queue
		found = false;
		position=0;
		for (ResQulet rql : getQuletPausedList()) {
			if (rql.getQuletId() == quletId) {
				found = true;
				rql.setQuletStatus(Qulet.CANCELED);
				break;
			}
			position++;
		}

		if (found) {
			return getQuletPausedList().remove(position).getQulet();
		}
		return null;
	}

	@Override
	public void quletFinish(ResQulet rql) {
		rql.setQuletStatus(Qulet.SUCCESS);
		rql.finalizeQulet();
		getQuletFinishedList().add(rql);
	}

	@Override
	public double quletSubmit(Qulet qulet, double fileTransferTime) {
		if(getQuletExecList().size() < 1) {
			ResQulet rql = new ResQulet(qulet);
			rql.setQuletStatus(Qulet.RUNNING);
			getQuletExecList().add(rql);
			double estimatedCompletionTime = (qulet.getNumLayers() / getCurrentClops()) * qulet.getNumShots();
			return estimatedCompletionTime;
		} else {
			ResQulet rql = new ResQulet(qulet);
			rql.setQuletStatus(Qulet.QUEUED);
			getQuletWaitingList().add(rql);
			return 0.0;
		}
	}

	@Override
	public double quletSubmit(Qulet qulet) {
		return quletSubmit(qulet, 0.0);
	}

	@Override
	public int getQuletStatus(int quletId) {
		for (ResQulet rcl : getQuletExecList()) {
			if (rcl.getQuletId() == quletId) {
				return rcl.getQuletStatus();
			}
		}
		for (ResQulet rcl : getQuletPausedList()) {
			if (rcl.getQuletId() == quletId) {
				return rcl.getQuletStatus();
			}
		}
		return -1;
	}

	@Override
	public boolean isFinishedQulets() {
		return getQuletFinishedList().size() > 0;
	}

	@Override
	public Qulet getNextFinishedQulet() {
		if (getQuletFinishedList().size() > 0) {
			return getQuletFinishedList().remove(0).getQulet();
		}
		return null;
	}

	@Override
	public int runningQulets() {
		return getQuletExecList().size();
	}

	@Override
	public Qulet migrateQulet() {
		// TODO: Qulet migration is not supported as of now
		ResQulet rgl = getQuletExecList().remove(0);
		rgl.finalizeQulet();
		return rgl.getQulet();
	}
	@Override
	public boolean quletPause(int quletId) {
		// TODO: Qulet pause is not supported as of now
		return false;
	}

	@Override
	public double quletResume(int quletId) {
		// TODO: Qulet resume is not supported as of now
		return 0.0;
	}
}
