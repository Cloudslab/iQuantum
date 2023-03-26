/*
 * Title: CloudSim Toolkit Description: CloudSim (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * 
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.ResCloudlet;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.ArrayList;
import java.util.List;

/**
 * QuletSchedulerTimeShared implements a policy of scheduling performed by a virtual machine
 * to run its {@link Qulet Qulets}.
 * Qulets execute in time-shared manner in VM.
 * Each VM has to have its own instance of a QuletScheduler.
 * 
 * @author Rodrigo N. Calheiros
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 1.0
 */
public class QuletSchedulerSpaceShared extends QuletScheduler {
	/** The number of PEs currently available for the VM using the scheduler,
         * according to the mips share provided to it by
         * {@link #updateQNodeProcessing(double, List)} method. */
	protected int currentQPUs;

	/**
	 * Creates a new QuletSchedulerTimeShared object. This method must be invoked before starting
	 * the actual simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	public QuletSchedulerSpaceShared() {
		super();
		currentQPUs = 0;
	}

	@Override
	public double updateQNodeProcessing(double currentTime, double clops) {
		setCurrentClops(clops);
		double timeSpam = currentTime - getPreviousTime();

		for (ResQulet rql : getQuletExecList()) {
			rql.updateQuletFinishedSoFar((long) (getCurrentClops() * timeSpam));
		}

		if (getQuletExecList().size() == 0) {
			setPreviousTime(currentTime);
			return 0.0;
		}

		// check finished qulets
		double nextEvent = Double.MAX_VALUE;
		int finished = 0;
		List<ResQulet> toRemove = new ArrayList<ResQulet>();
		for (ResQulet rql : getQuletExecList()) {
			long remainingLength = rql.getRemainingQuletLayers();
			if (remainingLength == 0) {// finished: remove from the list
				toRemove.add(rql);
				quletFinish(rql);
				finished++;
			}
		}
		getQuletExecList().removeAll(toRemove);


		// check waiting qulets
		if (!getQuletWaitingList().isEmpty()) {
			for (int i = 0; i < finished; i++) {
				toRemove.clear();
				for (ResQulet rql : getQuletWaitingList()) {
					rql.setQuletStatus(Qulet.INEXEC);
					getQuletExecList().add(rql);
					toRemove.add(rql);
					break;
				}
				getQuletWaitingList().removeAll(toRemove);
			}
		}

		// estimate finish time of qulets
		for (ResQulet rql : getQuletExecList()) {
			double estimatedFinishTime = currentTime + (rql.getRemainingQuletLayers() / getCurrentClops()) * rql.getNumShots();
			if (estimatedFinishTime - currentTime < CloudSim.getMinTimeBetweenEvents()) {
				estimatedFinishTime = currentTime + CloudSim.getMinTimeBetweenEvents();
			}
			if (estimatedFinishTime < nextEvent) {
				nextEvent = estimatedFinishTime;
			}
		}
		setPreviousTime(currentTime);
		return nextEvent;
	}

	/**
	 * Gets the individual MIPS capacity available for each PE available for the scheduler,
         * considering that all PEs have the same capacity.
	 * 
	 * @param clopsShare list with MIPS share of each PE available to the scheduler
	 * @return the capacity of each PE
	 */
	protected double getCapacity(double clops) {
		double capacity = 0.0;
		int qpus = 0;
		capacity = clops;
		currentQPUs = qpus;

		int pesInUse = 0;
		for (ResQulet rcl : getQuletExecList()) {
			pesInUse += rcl.getNumberOfPes();
		}

		if (pesInUse > currentQPUs) {
			capacity /= pesInUse;
		} else {
			capacity /= currentQPUs;
		}
		return capacity;
	}

	@Override
	public Qulet quletCancel(int quletId) {
		boolean found = false;
		int position = 0;

		// First, looks in the finished queue
		found = false;
		for (ResQulet rcl : getQuletFinishedList()) {
			if (rcl.getQuletId() == quletId) {
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
		for (ResQulet rcl : getQuletExecList()) {
			if (rcl.getQuletId() == quletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResQulet rcl = getQuletExecList().remove(position);
			if (rcl.getRemainingQuletLayers() == 0) {
				quletFinish(rcl);
			} else {
				rcl.setQuletStatus(Qulet.CANCELED);
			}
			return rcl.getQulet();
		}

		// Now, looks in the paused queue
		found = false;
		position=0;
		for (ResQulet rcl : getQuletPausedList()) {
			if (rcl.getQuletId() == quletId) {
				found = true;
				rcl.setQuletStatus(Qulet.CANCELED);
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
	public boolean quletPause(int quletId) {
		boolean found = false;
		int position = 0;

		for (ResQulet rcl : getQuletExecList()) {
			if (rcl.getQuletId() == quletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			// remove qulet from the exec list and put it in the paused list
			ResQulet rcl = getQuletExecList().remove(position);
			if (rcl.getRemainingQuletLayers() == 0) {
				quletFinish(rcl);
			} else {
				rcl.setQuletStatus(Qulet.PAUSED);
				getQuletPausedList().add(rcl);
			}
			return true;
		}
		return false;
	}

	@Override
	public void quletFinish(ResQulet rcl) {
		rcl.setQuletStatus(Qulet.SUCCESS);
		rcl.finalizeQulet();
		getQuletFinishedList().add(rcl);
	}

	@Override
	public double quletResume(int quletId) {
		boolean found = false;
		int position = 0;

		// look for the qulet in the paused list
		for (ResQulet rcl : getQuletPausedList()) {
			if (rcl.getQuletId() == quletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResQulet rgl = getQuletPausedList().remove(position);
			rgl.setQuletStatus(Qulet.INEXEC);
			getQuletExecList().add(rgl);

			// calculate the expected time for qulet completion
			// first: how many PEs do we have?

			double remainingLength = rgl.getRemainingQuletLayers();
			double estimatedFinishTime = CloudSim.clock()
					+ (remainingLength / (getCapacity(getCurrentClops()) * rgl.getNumberOfPes()));

			return estimatedFinishTime;
		}

		return 0.0;
	}

	@Override
	public double quletSubmit(Qulet qulet, double fileTransferTime) {
		if(getQuletExecList().size() < 1) {
			ResQulet rql = new ResQulet(qulet);
			rql.setQuletStatus(Qulet.INEXEC);
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
		ResQulet rgl = getQuletExecList().remove(0);
		rgl.finalizeQulet();
		return rgl.getQulet();
	}

	@Override
	public List<Double> getCurrentRequestedClops() {
		List<Double> clopsShare = new ArrayList<Double>();
		return clopsShare;
	}

	@Override
	public double getTotalCurrentAvailableClopsForQulet(ResQulet rcl, double clops) {
            /*@todo It isn't being used any the the given parameters.*/
            return getCapacity(getCurrentClops());
	}

	@Override
	public double getTotalCurrentAllocatedClopsForQulet(ResQulet rcl, double time) {
                //@todo The method is not implemented, in fact
		return 0.0;
	}

	@Override
	public double getTotalCurrentRequestedClopsForQulet(ResQulet rcl, double time) {
                //@todo The method is not implemented, in fact
		// TODO Auto-generated method stub
		return 0.0;
	}

}
