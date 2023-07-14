/*
 * Title: iQuantum Toolkit Description: iQuantum (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * 
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.policies.ctasks;

import java.util.ArrayList;
import java.util.List;

import org.iquantum.tasks.ResCTask;
import org.iquantum.core.iQuantum;
import org.iquantum.core.Consts;
import org.iquantum.tasks.CTask;

/**
 * CloudletSchedulerTimeShared implements a policy of scheduling performed by a virtual machine
 * to run its {@link CTask Cloudlets}.
 * Cloudlets execute in time-shared manner in VM.
 * Each VM has to have its own instance of a CloudletScheduler.
 * 
 * @author Rodrigo N. Calheiros
 * @author Anton Beloglazov
 * @since iQuantum Toolkit 1.0
 */
public class CloudletSchedulerTimeShared extends CloudletScheduler {
	/** The number of PEs currently available for the VM using the scheduler,
         * according to the mips share provided to it by
         * {@link #updateVmProcessing(double, java.util.List)} method. */
	protected int currentCPUs;

	/**
	 * Creates a new CloudletSchedulerTimeShared object. This method must be invoked before starting
	 * the actual simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	public CloudletSchedulerTimeShared() {
		super();
		currentCPUs = 0;
	}

	@Override
	public double updateVmProcessing(double currentTime, List<Double> mipsShare) {
		setCurrentMipsShare(mipsShare);
		double timeSpam = currentTime - getPreviousTime();

		for (ResCTask rcl : getCloudletExecList()) {
			rcl.updateCloudletFinishedSoFar((long) (getCapacity(mipsShare) * timeSpam * rcl.getNumberOfPes() * Consts.MILLION));
		}

		if (getCloudletExecList().size() == 0) {
			setPreviousTime(currentTime);
			return 0.0;
		}

		// check finished cloudlets
		double nextEvent = Double.MAX_VALUE;
		List<ResCTask> toRemove = new ArrayList<ResCTask>();
		for (ResCTask rcl : getCloudletExecList()) {
			long remainingLength = rcl.getRemainingCloudletLength();
			if (remainingLength == 0) {// finished: remove from the list
				toRemove.add(rcl);
				cloudletFinish(rcl);
				continue;
			}
		}
		getCloudletExecList().removeAll(toRemove);

		// estimate finish time of cloudlets
		for (ResCTask rcl : getCloudletExecList()) {
			double estimatedFinishTime = currentTime
					+ (rcl.getRemainingCloudletLength() / (getCapacity(mipsShare) * rcl.getNumberOfPes()));
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

	/**
	 * Gets the individual MIPS capacity available for each PE available for the scheduler,
         * considering that all PEs have the same capacity.
	 * 
	 * @param mipsShare list with MIPS share of each PE available to the scheduler
	 * @return the capacity of each PE
	 */
	protected double getCapacity(List<Double> mipsShare) {
		double capacity = 0.0;
		int cpus = 0;
		for (Double mips : mipsShare) {
			capacity += mips;
			if (mips > 0.0) {
				cpus++;
			}
		}
		currentCPUs = cpus;

		int pesInUse = 0;
		for (ResCTask rcl : getCloudletExecList()) {
			pesInUse += rcl.getNumberOfPes();
		}

		if (pesInUse > currentCPUs) {
			capacity /= pesInUse;
		} else {
			capacity /= currentCPUs;
		}
		return capacity;
	}

	@Override
	public CTask cloudletCancel(int cloudletId) {
		boolean found = false;
		int position = 0;

		// First, looks in the finished queue
		found = false;
		for (ResCTask rcl : getCloudletFinishedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			return getCloudletFinishedList().remove(position).getCloudlet();
		}

		// Then searches in the exec list
		position=0;
		for (ResCTask rcl : getCloudletExecList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResCTask rcl = getCloudletExecList().remove(position);
			if (rcl.getRemainingCloudletLength() == 0) {
				cloudletFinish(rcl);
			} else {
				rcl.setCloudletStatus(CTask.CANCELED);
			}
			return rcl.getCloudlet();
		}

		// Now, looks in the paused queue
		found = false;
		position=0;
		for (ResCTask rcl : getCloudletPausedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				rcl.setCloudletStatus(CTask.CANCELED);
				break;
			}
			position++;
		}

		if (found) {
			return getCloudletPausedList().remove(position).getCloudlet();
		}

		return null;
	}

	@Override
	public boolean cloudletPause(int cloudletId) {
		boolean found = false;
		int position = 0;

		for (ResCTask rcl : getCloudletExecList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			// remove cloudlet from the exec list and put it in the paused list
			ResCTask rcl = getCloudletExecList().remove(position);
			if (rcl.getRemainingCloudletLength() == 0) {
				cloudletFinish(rcl);
			} else {
				rcl.setCloudletStatus(CTask.PAUSED);
				getCloudletPausedList().add(rcl);
			}
			return true;
		}
		return false;
	}

	@Override
	public void cloudletFinish(ResCTask rcl) {
		rcl.setCloudletStatus(CTask.SUCCESS);
		rcl.finalizeCloudlet();
		getCloudletFinishedList().add(rcl);
	}

	@Override
	public double cloudletResume(int cloudletId) {
		boolean found = false;
		int position = 0;

		// look for the cloudlet in the paused list
		for (ResCTask rcl : getCloudletPausedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			ResCTask rgl = getCloudletPausedList().remove(position);
			rgl.setCloudletStatus(CTask.INEXEC);
			getCloudletExecList().add(rgl);

			// calculate the expected time for cloudlet completion
			// first: how many PEs do we have?

			double remainingLength = rgl.getRemainingCloudletLength();
			double estimatedFinishTime = iQuantum.clock()
					+ (remainingLength / (getCapacity(getCurrentMipsShare()) * rgl.getNumberOfPes()));

			return estimatedFinishTime;
		}

		return 0.0;
	}

	@Override
	public double cloudletSubmit(CTask CTask, double fileTransferTime) {
		ResCTask rcl = new ResCTask(CTask);
		rcl.setCloudletStatus(CTask.INEXEC);
		for (int i = 0; i < CTask.getNumberOfPes(); i++) {
			rcl.setMachineAndPeId(0, i);
		}

		getCloudletExecList().add(rcl);

		// use the current capacity to estimate the extra amount of
		// time to file transferring. It must be added to the cloudlet length
		double extraSize = getCapacity(getCurrentMipsShare()) * fileTransferTime;
		long length = (long) (CTask.getCloudletLength() + extraSize);
		CTask.setCloudletLength(length);

		return CTask.getCloudletLength() / getCapacity(getCurrentMipsShare());
	}

	@Override
	public double cloudletSubmit(CTask CTask) {
		return cloudletSubmit(CTask, 0.0);
	}

	@Override
	public int getCloudletStatus(int cloudletId) {
		for (ResCTask rcl : getCloudletExecList()) {
			if (rcl.getCloudletId() == cloudletId) {
				return rcl.getCloudletStatus();
			}
		}
		for (ResCTask rcl : getCloudletPausedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				return rcl.getCloudletStatus();
			}
		}
		return -1;
	}

	@Override
	public double getTotalUtilizationOfCpu(double time) {
                /*
                 * @todo 
                 */
		double totalUtilization = 0;
		for (ResCTask gl : getCloudletExecList()) {
			totalUtilization += gl.getCloudlet().getUtilizationOfCpu(time);
		}
		return totalUtilization;
	}

	@Override
	public boolean isFinishedCloudlets() {
		return getCloudletFinishedList().size() > 0;
	}

	@Override
	public CTask getNextFinishedCloudlet() {
		if (getCloudletFinishedList().size() > 0) {
			return getCloudletFinishedList().remove(0).getCloudlet();
		}
		return null;
	}

	@Override
	public int runningCloudlets() {
		return getCloudletExecList().size();
	}

	@Override
	public CTask migrateCloudlet() {
		ResCTask rgl = getCloudletExecList().remove(0);
		rgl.finalizeCloudlet();
		return rgl.getCloudlet();
	}

	@Override
	public List<Double> getCurrentRequestedMips() {
		List<Double> mipsShare = new ArrayList<Double>();
		return mipsShare;
	}

	@Override
	public double getTotalCurrentAvailableMipsForCloudlet(ResCTask rcl, List<Double> mipsShare) {
            /*@todo It isn't being used any the the given parameters.*/
            return getCapacity(getCurrentMipsShare());
	}

	@Override
	public double getTotalCurrentAllocatedMipsForCloudlet(ResCTask rcl, double time) {
                //@todo The method is not implemented, in fact
		return 0.0;
	}

	@Override
	public double getTotalCurrentRequestedMipsForCloudlet(ResCTask rcl, double time) {
                //@todo The method is not implemented, in fact
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double getCurrentRequestedUtilizationOfRam() {
		double ram = 0;
		for (ResCTask cloudlet : cloudletExecList) {
			ram += cloudlet.getCloudlet().getUtilizationOfRam(iQuantum.clock());
		}
		return ram;
	}

	@Override
	public double getCurrentRequestedUtilizationOfBw() {
		double bw = 0;
		for (ResCTask cloudlet : cloudletExecList) {
			bw += cloudlet.getCloudlet().getUtilizationOfBw(iQuantum.clock());
		}
		return bw;
	}

}
