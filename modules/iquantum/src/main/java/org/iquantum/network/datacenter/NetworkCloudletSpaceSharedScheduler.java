/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.network.datacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.iquantum.tasks.CTask;
import org.iquantum.policies.ctasks.CloudletScheduler;
import org.iquantum.tasks.ResCTask;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;

/**
 * CloudletSchedulerSpaceShared implements a policy of scheduling performed by a virtual machine
 * to run its {@link CTask Cloudlets}.
 * It consider that there will be only one cloudlet per VM. Other cloudlets will be in a waiting list.
 * We consider that file transfer from cloudlets waiting happens before cloudlet execution. I.e.,
 * even though cloudlets must wait for CPU, data transfer happens as soon as cloudlets are
 * submitted.
 * 
 * Each VM has to have its own instance of a CloudletScheduler.
 * 
 * @author Saurabh Kumar Garg
 * @author Saurabh Kumar Garg
 * @since iQuantum Toolkit 3.0
 * @todo Attributes should be private
 */
public class NetworkCloudletSpaceSharedScheduler extends CloudletScheduler {
	/** The current CPUs. */
	protected int currentCpus;

	/** The used PEs. */
	protected int usedPes;

        /**
         * The map of packets to send, where each key is a destination VM
         * and each value is the list of packets to sent to that VM.
         */
	public Map<Integer, List<HostPacket>> pkttosend;

        /**
         * The map of packets received, where each key is a sender VM
         * and each value is the list of packets sent by that VM.
         */
	public Map<Integer, List<HostPacket>> pktrecv;

	/**
	 * Creates a new CloudletSchedulerSpaceShared object. 
         * This method must be invoked before starting the actual simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	public NetworkCloudletSpaceSharedScheduler() {
		super();
		cloudletWaitingList = new ArrayList<ResCTask>();
		cloudletExecList = new ArrayList<ResCTask>();
		cloudletPausedList = new ArrayList<ResCTask>();
		cloudletFinishedList = new ArrayList<ResCTask>();
		usedPes = 0;
		currentCpus = 0;
		pkttosend = new HashMap<Integer, List<HostPacket>>();
		pktrecv = new HashMap<Integer, List<HostPacket>>();
	}

	@Override
	public double updateVmProcessing(double currentTime, List<Double> mipsShare) {
                /*@todo Method to long. Several "extract method" refactorings may be performed.*/
		setCurrentMipsShare(mipsShare);
		// update
		double capacity = 0.0;
		int cpus = 0;

		for (Double mips : mipsShare) { // count the CPUs available to the VMM
			capacity += mips;
			if (mips > 0) {
				cpus++;
			}
		}
		currentCpus = cpus;
		capacity /= cpus; // average capacity of each cpu

		for (ResCTask rcl : getCloudletExecList()) { // each machine in the
			// exec list has the
			// same amount of cpu

			NetworkCTask cl = (NetworkCTask) rcl.getCloudlet();

			// check status
			// if execution stage
			// update the cloudlet finishtime
			// CHECK WHETHER IT IS WAITING FOR THE PACKET
			// if packet received change the status of job and update the time.
			//
			if ((cl.currStagenum != -1)) {
				if (cl.currStagenum == NetworkConstants.FINISH) {
					break;
				}
				TaskStage st = cl.stages.get(cl.currStagenum);
				if (st.type == NetworkConstants.EXECUTION) {

					// update the time
					cl.timespentInStage = Math.round(iQuantum.clock() - cl.timetostartStage);
					if (cl.timespentInStage >= st.time) {
						changetonextstage(cl, st);
						// change the stage
					}
				}
				if (st.type == NetworkConstants.WAIT_RECV) {
					List<HostPacket> pktlist = pktrecv.get(st.peer);
					List<HostPacket> pkttoremove = new ArrayList<HostPacket>();
					if (pktlist != null) {
						Iterator<HostPacket> it = pktlist.iterator();
						HostPacket pkt = null;
						if (it.hasNext()) {
							pkt = it.next();
							// Asumption packet will not arrive in the same cycle
							if (pkt.reciever == cl.getVmId()) {
								pkt.recievetime = iQuantum.clock();
								st.time = iQuantum.clock() - pkt.sendtime;
								changetonextstage(cl, st);
								pkttoremove.add(pkt);
							}
						}
						pktlist.removeAll(pkttoremove);
						// if(pkt!=null)
						// else wait for recieving the packet
					}
				}

			} else {
				cl.currStagenum = 0;
				cl.timetostartStage = iQuantum.clock();

				if (cl.stages.get(0).type == NetworkConstants.EXECUTION) {
					NetDatacenterBroker.linkDC.schedule(
							NetDatacenterBroker.linkDC.getId(),
							cl.stages.get(0).time,
							iQuantumTags.VM_DATACENTER_EVENT);
				} else {
					NetDatacenterBroker.linkDC.schedule(
							NetDatacenterBroker.linkDC.getId(),
							0.0001,
							iQuantumTags.VM_DATACENTER_EVENT);
					// /sendstage///
				}
			}

		}

		if (getCloudletExecList().size() == 0 && getCloudletWaitingList().size() == 0) { // no
			// more cloudlets in this scheduler
			setPreviousTime(currentTime);
			return 0.0;
		}

		// update each cloudlet
		int finished = 0;
		List<ResCTask> toRemove = new ArrayList<ResCTask>();
		for (ResCTask rcl : getCloudletExecList()) {
			// rounding issue...
			if (((NetworkCTask) (rcl.getCloudlet())).currStagenum == NetworkConstants.FINISH) {
				// stage is changed and packet to send
				((NetworkCTask) (rcl.getCloudlet())).finishtime = iQuantum.clock();
				toRemove.add(rcl);
				cloudletFinish(rcl);
				finished++;
			}
		}
		getCloudletExecList().removeAll(toRemove);
		// add all the CloudletExecList in waitingList.
		// sort the waitinglist

		// for each finished cloudlet, add a new one from the waiting list
		if (!getCloudletWaitingList().isEmpty()) {
			for (int i = 0; i < finished; i++) {
				toRemove.clear();
				for (ResCTask rcl : getCloudletWaitingList()) {
					if ((currentCpus - usedPes) >= rcl.getNumberOfPes()) {
						rcl.setCloudletStatus(CTask.INEXEC);
						for (int k = 0; k < rcl.getNumberOfPes(); k++) {
							rcl.setMachineAndPeId(0, i);
						}
						getCloudletExecList().add(rcl);
						usedPes += rcl.getNumberOfPes();
						toRemove.add(rcl);
						break;
					}
				}
				getCloudletWaitingList().removeAll(toRemove);
			}// for(cont)
		}

		// estimate finish time of cloudlets in the execution queue
		double nextEvent = Double.MAX_VALUE;
		for (ResCTask rcl : getCloudletExecList()) {
			double remainingLength = rcl.getRemainingCloudletLength();
			double estimatedFinishTime = currentTime + (remainingLength / (capacity * rcl.getNumberOfPes()));
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
         * Changes a cloudlet to the next stage.
         * 
         * @todo It has to be corrected the method name case. Method too long
         * to understand what is its responsibility.*/
	private void changetonextstage(NetworkCTask cl, TaskStage st) {
		cl.timespentInStage = 0;
		cl.timetostartStage = iQuantum.clock();
		int currstage = cl.currStagenum;
		if (currstage >= (cl.stages.size() - 1)) {
			cl.currStagenum = NetworkConstants.FINISH;
		} else {
			cl.currStagenum = currstage + 1;
			int i = 0;
			for (i = cl.currStagenum; i < cl.stages.size(); i++) {
				if (cl.stages.get(i).type == NetworkConstants.WAIT_SEND) {
					HostPacket pkt = new HostPacket(
							cl.getVmId(),
							cl.stages.get(i).peer,
							cl.stages.get(i).data,
							iQuantum.clock(),
							-1,
							cl.getCloudletId(),
							cl.stages.get(i).vpeer);
					List<HostPacket> pktlist = pkttosend.get(cl.getVmId());
					if (pktlist == null) {
						pktlist = new ArrayList<HostPacket>();
					}
					pktlist.add(pkt);
					pkttosend.put(cl.getVmId(), pktlist);

				} else {
					break;
				}

			}
			NetDatacenterBroker.linkDC.schedule(
					NetDatacenterBroker.linkDC.getId(),
					0.0001,
					iQuantumTags.VM_DATACENTER_EVENT);
			if (i == cl.stages.size()) {
				cl.currStagenum = NetworkConstants.FINISH;
			} else {
				cl.currStagenum = i;
				if (cl.stages.get(i).type == NetworkConstants.EXECUTION) {
					NetDatacenterBroker.linkDC.schedule(
							NetDatacenterBroker.linkDC.getId(),
							cl.stages.get(i).time,
							iQuantumTags.VM_DATACENTER_EVENT);
				}

			}
		}

	}

	@Override
	public CTask cloudletCancel(int cloudletId) {
		// First, looks in the finished queue
		for (ResCTask rcl : getCloudletFinishedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				getCloudletFinishedList().remove(rcl);
				return rcl.getCloudlet();
			}
		}

		// Then searches in the exec list
		for (ResCTask rcl : getCloudletExecList()) {
			if (rcl.getCloudletId() == cloudletId) {
				getCloudletExecList().remove(rcl);
				if (rcl.getRemainingCloudletLength() == 0.0) {
					cloudletFinish(rcl);
				} else {
					rcl.setCloudletStatus(CTask.CANCELED);
				}
				return rcl.getCloudlet();
			}
		}

		// Now, looks in the paused queue
		for (ResCTask rcl : getCloudletPausedList()) {
			if (rcl.getCloudletId() == cloudletId) {
				getCloudletPausedList().remove(rcl);
				return rcl.getCloudlet();
			}
		}

		// Finally, looks in the waiting list
		for (ResCTask rcl : getCloudletWaitingList()) {
			if (rcl.getCloudletId() == cloudletId) {
				rcl.setCloudletStatus(CTask.CANCELED);
				getCloudletWaitingList().remove(rcl);
				return rcl.getCloudlet();
			}
		}

		return null;

	}

	@Override
	public boolean cloudletPause(int cloudletId) {
		boolean found = false;
		int position = 0;

		// first, looks for the cloudlet in the exec list
		for (ResCTask rcl : getCloudletExecList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			// moves to the paused list
			ResCTask rgl = getCloudletExecList().remove(position);
			if (rgl.getRemainingCloudletLength() == 0.0) {
				cloudletFinish(rgl);
			} else {
				rgl.setCloudletStatus(CTask.PAUSED);
				getCloudletPausedList().add(rgl);
			}
			return true;

		}

		// now, look for the cloudlet in the waiting list
		position = 0;
		found = false;
		for (ResCTask rcl : getCloudletWaitingList()) {
			if (rcl.getCloudletId() == cloudletId) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			// moves to the paused list
			ResCTask rgl = getCloudletWaitingList().remove(position);
			if (rgl.getRemainingCloudletLength() == 0.0) {
				cloudletFinish(rgl);
			} else {
				rgl.setCloudletStatus(CTask.PAUSED);
				getCloudletPausedList().add(rgl);
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
		usedPes -= rcl.getNumberOfPes();
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
			ResCTask rcl = getCloudletPausedList().remove(position);

			// it can go to the exec list
			if ((currentCpus - usedPes) >= rcl.getNumberOfPes()) {
				rcl.setCloudletStatus(CTask.INEXEC);
				for (int i = 0; i < rcl.getNumberOfPes(); i++) {
					rcl.setMachineAndPeId(0, i);
				}

				long size = rcl.getRemainingCloudletLength();
				size *= rcl.getNumberOfPes();
				rcl.getCloudlet().setCloudletLength(size);

				getCloudletExecList().add(rcl);
				usedPes += rcl.getNumberOfPes();

				// calculate the expected time for cloudlet completion
				double capacity = 0.0;
				int cpus = 0;
				for (Double mips : getCurrentMipsShare()) {
					capacity += mips;
					if (mips > 0) {
						cpus++;
					}
				}
				currentCpus = cpus;
				capacity /= cpus;

				long remainingLength = rcl.getRemainingCloudletLength();
				double estimatedFinishTime = iQuantum.clock()
						+ (remainingLength / (capacity * rcl.getNumberOfPes()));

				return estimatedFinishTime;
			} else {// no enough free PEs: go to the waiting queue
				rcl.setCloudletStatus(CTask.QUEUED);

				long size = rcl.getRemainingCloudletLength();
				size *= rcl.getNumberOfPes();
				rcl.getCloudlet().setCloudletLength(size);

				getCloudletWaitingList().add(rcl);
				return 0.0;
			}

		}

		// not found in the paused list: either it is in in the queue, executing
		// or not exist
		return 0.0;

	}

	@Override
	public double cloudletSubmit(CTask CTask, double fileTransferTime) {
		// it can go to the exec list
		if ((currentCpus - usedPes) >= CTask.getNumberOfPes()) {
			ResCTask rcl = new ResCTask(CTask);
			rcl.setCloudletStatus(CTask.INEXEC);
			for (int i = 0; i < CTask.getNumberOfPes(); i++) {
				rcl.setMachineAndPeId(0, i);
			}

			getCloudletExecList().add(rcl);
			usedPes += CTask.getNumberOfPes();
		} else {// no enough free PEs: go to the waiting queue
			ResCTask rcl = new ResCTask(CTask);
			rcl.setCloudletStatus(CTask.QUEUED);
			getCloudletWaitingList().add(rcl);
			return 0.0;
		}

		// calculate the expected time for cloudlet completion
		double capacity = 0.0;
		int cpus = 0;
		for (Double mips : getCurrentMipsShare()) {
			capacity += mips;
			if (mips > 0) {
				cpus++;
			}
		}

		currentCpus = cpus;
		capacity /= cpus;

		// use the current capacity to estimate the extra amount of
		// time to file transferring. It must be added to the cloudlet length
		double extraSize = capacity * fileTransferTime;
		long length = CTask.getCloudletLength();
		length += extraSize;
		CTask.setCloudletLength(length);
		return CTask.getCloudletLength() / capacity;
	}

	@Override
	public double cloudletSubmit(CTask CTask) {
		cloudletSubmit(CTask, 0);
		return 0;
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

		for (ResCTask rcl : getCloudletWaitingList()) {
			if (rcl.getCloudletId() == cloudletId) {
				return rcl.getCloudletStatus();
			}
		}

		return -1;
	}

	@Override
	public double getTotalUtilizationOfCpu(double time) {
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
		ResCTask rcl = getCloudletExecList().remove(0);
		rcl.finalizeCloudlet();
		CTask cl = rcl.getCloudlet();
		usedPes -= cl.getNumberOfPes();
		return cl;
	}

	@Override
	public List<Double> getCurrentRequestedMips() {
		List<Double> mipsShare = new ArrayList<Double>();
		if (getCurrentMipsShare() != null) {
			for (Double mips : getCurrentMipsShare()) {
				mipsShare.add(mips);
			}
		}
		return mipsShare;
	}

	@Override
	public double getTotalCurrentAvailableMipsForCloudlet(ResCTask rcl, List<Double> mipsShare) {
                /*@todo The param rcl is not being used.*/
		double capacity = 0.0;
		int cpus = 0;
		for (Double mips : mipsShare) { // count the cpus available to the vmm
			capacity += mips;
			if (mips > 0) {
				cpus++;
			}
		}
		currentCpus = cpus;
		capacity /= cpus; // average capacity of each cpu
		return capacity;
	}

	@Override
	public double getTotalCurrentAllocatedMipsForCloudlet(ResCTask rcl, double time) {
                //@todo The method doesn't appear to be implemented in fact
		return 0.0;
	}

	@Override
	public double getTotalCurrentRequestedMipsForCloudlet(ResCTask rcl, double time) {
                //@todo The method doesn't appear to be implemented in fact
		return 0.0;
	}

	@Override
	public double getCurrentRequestedUtilizationOfBw() {
                //@todo The method doesn't appear to be implemented in fact
		return 0;
	}

	@Override
	public double getCurrentRequestedUtilizationOfRam() {
                //@todo The method doesn't appear to be implemented in fact
		return 0;
	}

}
