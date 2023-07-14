package org.iquantum.backends.classical.container.schedulers;

import org.iquantum.tasks.CTask;
import org.iquantum.core.Consts;
import org.iquantum.tasks.ResCTask;
import org.iquantum.core.iQuantum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sareh on 10/07/15.
 */
public class ContainerCloudletSchedulerTimeShared extends ContainerCloudletScheduler {

    /**
     * The current cp us.
     */
    protected int currentCPUs;

    /**
     * Creates a new ContainerCloudletSchedulerTimeShared object. This method must be invoked before starting
     * the actual simulation.
     *
     * @pre $none
     * @post $none
     */
    public ContainerCloudletSchedulerTimeShared() {
        super();
        currentCPUs = 0;
    }

    /**
     * Updates the processing of cloudlets running under management of this scheduler.
     *
     * @param currentTime current simulation time
     * @param mipsShare   array with MIPS share of each processor available to the scheduler
     * @return time predicted completion time of the earliest finishing cloudlet, or 0 if there is
     * no next events
     * @pre currentTime >= 0
     * @post $none
     */
    @Override
    public double updateContainerProcessing(double currentTime, List<Double> mipsShare) {
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
        List<ResCTask> toRemove = new ArrayList<>();
        for (ResCTask rcl : getCloudletExecList()) {
            long remainingLength = rcl.getRemainingCloudletLength();
            if (remainingLength == 0) {// finished: remove from the list
                toRemove.add(rcl);
                cloudletFinish(rcl);
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

        toRemove.clear();
        setPreviousTime(currentTime);
        return nextEvent;
    }

    /**
     * Gets the capacity.
     *
     * @param mipsShare the mips share
     * @return the capacity
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

    /**
     * Cancels execution of a cloudlet.
     *
     * @param cloudletId ID of the cloudlet being cancealed
     * @return the canceled cloudlet, $null if not found
     * @pre $none
     * @post $none
     */
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
        position = 0;
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
        position = 0;
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

    /**
     * Pauses execution of a cloudlet.
     *
     * @param cloudletId ID of the cloudlet being paused
     * @return $true if cloudlet paused, $false otherwise
     * @pre $none
     * @post $none
     */
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

    /**
     * Processes a finished cloudlet.
     *
     * @param rcl finished cloudlet
     * @pre rgl != $null
     * @post $none
     */
    @Override
    public void cloudletFinish(ResCTask rcl) {
        rcl.setCloudletStatus(CTask.SUCCESS);
        rcl.finalizeCloudlet();
        getCloudletFinishedList().add(rcl);
    }

    /**
     * Resumes execution of a paused cloudlet.
     *
     * @param cloudletId ID of the cloudlet being resumed
     * @return expected finish time of the cloudlet, 0.0 if queued
     * @pre $none
     * @post $none
     */
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


    /**
     * Receives an cloudlet to be executed in the VM managed by this scheduler.
     *
     * @param CTask         the submited cloudlet
     * @param fileTransferTime time required to move the required files from the SAN to the VM
     * @return expected finish time of this cloudlet
     * @pre gl != null
     * @post $none
     */
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

    /*
     * (non-Javadoc)
     * @see iQuantum.CloudletScheduler#cloudletSubmit(iQuantum.Cloudlet)
     */
    @Override
    public double cloudletSubmit(CTask CTask) {
        return cloudletSubmit(CTask, 0.0);
    }

    /**
     * Gets the status of a cloudlet.
     *
     * @param cloudletId ID of the cloudlet
     * @return status of the cloudlet, -1 if cloudlet not found
     * @pre $none
     * @post $none
     */
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

    /**
     * Get utilization created by all cloudlets.
     *
     * @param time the time
     * @return total utilization
     */
    @Override
    public double getTotalUtilizationOfCpu(double time) {
        double totalUtilization = 0;
        for (ResCTask gl : getCloudletExecList()) {
            totalUtilization += gl.getCloudlet().getUtilizationOfCpu(time);
        }
        return totalUtilization;
    }

    /**
     * Informs about completion of some cloudlet in the VM managed by this scheduler.
     *
     * @return $true if there is at least one finished cloudlet; $false otherwise
     * @pre $none
     * @post $none
     */
    @Override
    public boolean isFinishedCloudlets() {
        return getCloudletFinishedList().size() > 0;
    }

    /**
     * Returns the next cloudlet in the finished list, $null if this list is empty.
     *
     * @return a finished cloudlet
     * @pre $none
     * @post $none
     */
    @Override
    public CTask getNextFinishedCloudlet() {
        if (getCloudletFinishedList().size() > 0) {
            return getCloudletFinishedList().remove(0).getCloudlet();
        }
        return null;
    }

    /**
     * Returns the number of cloudlets runnning in the virtual machine.
     *
     * @return number of cloudlets runnning
     * @pre $none
     * @post $none
     */
    @Override
    public int runningCloudlets() {
        return getCloudletExecList().size();
    }

    /**
     * Returns one cloudlet to migrate to another vm.
     *
     * @return one running cloudlet
     * @pre $none
     * @post $none
     */
    @Override
    public CTask migrateCloudlet() {
        ResCTask rgl = getCloudletExecList().remove(0);
        rgl.finalizeCloudlet();
        return rgl.getCloudlet();
    }


    /*
     * (non-Javadoc)
     * @see iQuantum.CloudletScheduler#getCurrentRequestedMips()
     */
    @Override
    public List<Double> getCurrentRequestedMips() {
        return new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * @see iQuantum.CloudletScheduler#getTotalCurrentAvailableMipsForCloudlet(iQuantum.ResCloudlet,
     * java.util.List)
     */
    @Override
    public double getTotalCurrentAvailableMipsForCloudlet(ResCTask rcl, List<Double> mipsShare) {
        return getCapacity(getCurrentMipsShare());
    }

    /*
     * (non-Javadoc)
     * @see iQuantum.CloudletScheduler#getTotalCurrentAllocatedMipsForCloudlet(iQuantum.ResCloudlet,
     * double)
     */
    @Override
    public double getTotalCurrentAllocatedMipsForCloudlet(ResCTask rcl, double time) {
        return 0.0;
    }

    /*
     * (non-Javadoc)
     * @see iQuantum.CloudletScheduler#getTotalCurrentRequestedMipsForCloudlet(iQuantum.ResCloudlet,
     * double)
     */
    @Override
    public double getTotalCurrentRequestedMipsForCloudlet(ResCTask rcl, double time) {
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





