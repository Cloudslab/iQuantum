package org.iquantum.backends.classical.container.schedulers;

/**
 * Created by sareh on 14/07/15.
 */
import org.iquantum.tasks.CTask;
import org.iquantum.core.Consts;
import org.iquantum.tasks.ResCTask;
import org.iquantum.core.iQuantum;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class ContainerCloudletSchedulerDynamicWorkload extends ContainerCloudletSchedulerTimeShared {

        /** The mips. */
        private double mips;

        /** The number of PEs. */
        private int numberOfPes;

        /** The total mips. */
        private double totalMips;

        /** The under allocated mips. */
        private Map<String, Double> underAllocatedMips;

        /** The cache previous time. */
        private double cachePreviousTime;

        /** The cache current requested mips. */
        private List<Double> cacheCurrentRequestedMips;

        /**
         * Instantiates a new vM scheduler time shared.
         *
         * @param mips the mips
         * @param numberOfPes the pes number
         */
        public ContainerCloudletSchedulerDynamicWorkload (double mips, int numberOfPes) {
            super();
            setMips(mips);
            setNumberOfPes(numberOfPes);
            setTotalMips(getNumberOfPes() * getMips());
            setUnderAllocatedMips(new HashMap<String, Double>());
            setCachePreviousTime(-1);
        }

        /**
         * Updates the processing of cloudlets running under management of this scheduler.
         *
         * @param currentTime current simulation time
         * @param mipsShare array with MIPS share of each Pe available to the scheduler
         * @return time predicted completion time of the earliest finishing cloudlet, or 0 if there is
         *         no next events
         * @pre currentTime >= 0
         * @post $none
         */
        @Override
        public double updateContainerProcessing(double currentTime, List<Double> mipsShare) {
            setCurrentMipsShare(mipsShare);

            double timeSpan = currentTime - getPreviousTime();
            double nextEvent = Double.MAX_VALUE;
            List<ResCTask> cloudletsToFinish = new ArrayList<>();

            for (ResCTask rcl : getCloudletExecList()) {
                rcl.updateCloudletFinishedSoFar((long) (timeSpan
                        * getTotalCurrentAllocatedMipsForCloudlet(rcl, getPreviousTime()) * Consts.MILLION));

                if (rcl.getRemainingCloudletLength() == 0) { // finished: remove from the list
                    cloudletsToFinish.add(rcl);
                } else { // not finish: estimate the finish time
                    double estimatedFinishTime = getEstimatedFinishTime(rcl, currentTime);
                    if (estimatedFinishTime - currentTime < iQuantum.getMinTimeBetweenEvents()) {
                        estimatedFinishTime = currentTime + iQuantum.getMinTimeBetweenEvents();
                    }
                    if (estimatedFinishTime < nextEvent) {
                        nextEvent = estimatedFinishTime;
                    }
                }
            }

            for (ResCTask rgl : cloudletsToFinish) {
                getCloudletExecList().remove(rgl);
                cloudletFinish(rgl);
            }

            setPreviousTime(currentTime);

            if (getCloudletExecList().isEmpty()) {
                return 0;
            }

            cloudletsToFinish.clear();

            return nextEvent;
        }

        /**
         * Receives an cloudlet to be executed in the VM managed by this scheduler.
         *
         * @param cl the cl
         * @return predicted completion time
         * @pre _gl != null
         * @post $none
         */
        @Override
        public double cloudletSubmit(CTask cl) {
            return cloudletSubmit(cl, 0);
        }

        /**
         * Receives an cloudlet to be executed in the VM managed by this scheduler.
         *
         * @param cl the cl
         * @param fileTransferTime the file transfer time
         * @return predicted completion time
         * @pre _gl != null
         * @post $none
         */
        @Override
        public double cloudletSubmit(CTask cl, double fileTransferTime) {
            ResCTask rcl = new ResCTask(cl);
            rcl.setCloudletStatus(CTask.INEXEC);

            for (int i = 0; i < cl.getNumberOfPes(); i++) {
                rcl.setMachineAndPeId(0, i);
            }

            getCloudletExecList().add(rcl);
            return getEstimatedFinishTime(rcl, getPreviousTime());
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
         * Get utilization created by all cloudlets.
         *
         * @param time the time
         * @return total utilization
         */
        @Override
        public double getTotalUtilizationOfCpu(double time) {
            double totalUtilization = 0;
            for (ResCTask rcl : getCloudletExecList()) {
                totalUtilization += rcl.getCloudlet().getUtilizationOfCpu(time);
            }
            return totalUtilization;
        }

        /**
         * Gets the current mips.
         *
         * @return the current mips
         */
        @Override
        public List<Double> getCurrentRequestedMips() {
            if (getCachePreviousTime() == getPreviousTime()) {
                return getCacheCurrentRequestedMips();
            }
            List<Double> currentMips = new ArrayList<>();
            double totalMips = getTotalUtilizationOfCpu(getPreviousTime()) * getTotalMips();
            double mipsForPe = totalMips / getNumberOfPes();

            for (int i = 0; i < getNumberOfPes(); i++) {
                currentMips.add(mipsForPe);
            }

            setCachePreviousTime(getPreviousTime());
            setCacheCurrentRequestedMips(currentMips);

            return currentMips;
        }

        /**
         * Gets the current mips.
         *
         * @param rcl the rcl
         * @param time the time
         * @return the current mips
         */
        @Override
        public double getTotalCurrentRequestedMipsForCloudlet(ResCTask rcl, double time) {
            return rcl.getCloudlet().getUtilizationOfCpu(time) * getTotalMips();
        }

        /**
         * Gets the total current mips for the clouddlet.
         *
         * @param rcl the rcl
         * @param mipsShare the mips share
         * @return the total current mips
         */
        @Override
        public double getTotalCurrentAvailableMipsForCloudlet(ResCTask rcl, List<Double> mipsShare) {
            double totalCurrentMips = 0.0;
            if (mipsShare != null) {
                int neededPEs = rcl.getNumberOfPes();
                for (double mips : mipsShare) {
                    totalCurrentMips += mips;
                    neededPEs--;
                    if (neededPEs <= 0) {
                        break;
                    }
                }
            }
            return totalCurrentMips;
        }

        /**
         * Gets the current mips.
         *
         * @param rcl the rcl
         * @param time the time
         * @return the current mips
         */
        @Override
        public double getTotalCurrentAllocatedMipsForCloudlet(ResCTask rcl, double time) {
            double totalCurrentRequestedMips = getTotalCurrentRequestedMipsForCloudlet(rcl, time);
            double totalCurrentAvailableMips = getTotalCurrentAvailableMipsForCloudlet(rcl, getCurrentMipsShare());
            if (totalCurrentRequestedMips > totalCurrentAvailableMips) {
                return totalCurrentAvailableMips;
            }
            return totalCurrentRequestedMips;
        }

        /**
         * Update under allocated mips for cloudlet.
         *
         * @param rcl the rgl
         * @param mips the mips
         */
        public void updateUnderAllocatedMipsForCloudlet(ResCTask rcl, double mips) {
            if (getUnderAllocatedMips().containsKey(rcl.getUid())) {
                mips += getUnderAllocatedMips().get(rcl.getUid());
            }
            getUnderAllocatedMips().put(rcl.getUid(), mips);
        }

        /**
         * Get estimated cloudlet completion time.
         *
         * @param rcl the rcl
         * @param time the time
         * @return the estimated finish time
         */
        public double getEstimatedFinishTime(ResCTask rcl, double time) {
            return time
                    + ((rcl.getRemainingCloudletLength()) / getTotalCurrentAllocatedMipsForCloudlet(rcl, time));
        }

        /**
         * Gets the total current mips.
         *
         * @return the total current mips
         */
        public int getTotalCurrentMips() {
            int totalCurrentMips = 0;
            for (double mips : getCurrentMipsShare()) {
                totalCurrentMips += mips;
            }
            return totalCurrentMips;
        }

        /**
         * Sets the total mips.
         *
         * @param mips the new total mips
         */
        public void setTotalMips(double mips) {
            totalMips = mips;
        }

        /**
         * Gets the total mips.
         *
         * @return the total mips
         */
        public double getTotalMips() {
            return totalMips;
        }

        /**
         * Sets the pes number.
         *
         * @param pesNumber the new pes number
         */
        public void setNumberOfPes(int pesNumber) {
            numberOfPes = pesNumber;
        }

        /**
         * Gets the pes number.
         *
         * @return the pes number
         */
        public int getNumberOfPes() {
            return numberOfPes;
        }

        /**
         * Sets the mips.
         *
         * @param mips the new mips
         */
        public void setMips(double mips) {
            this.mips = mips;
        }

        /**
         * Gets the mips.
         *
         * @return the mips
         */
        public double getMips() {
            return mips;
        }

        /**
         * Sets the under allocated mips.
         *
         * @param underAllocatedMips the under allocated mips
         */
        public void setUnderAllocatedMips(Map<String, Double> underAllocatedMips) {
            this.underAllocatedMips = underAllocatedMips;
        }

        /**
         * Gets the under allocated mips.
         *
         * @return the under allocated mips
         */
        public Map<String, Double> getUnderAllocatedMips() {
            return underAllocatedMips;
        }

        /**
         * Gets the cache previous time.
         *
         * @return the cache previous time
         */
        protected double getCachePreviousTime() {
            return cachePreviousTime;
        }

        /**
         * Sets the cache previous time.
         *
         * @param cachePreviousTime the new cache previous time
         */
        protected void setCachePreviousTime(double cachePreviousTime) {
            this.cachePreviousTime = cachePreviousTime;
        }

        /**
         * Gets the cache current requested mips.
         *
         * @return the cache current requested mips
         */
        protected List<Double> getCacheCurrentRequestedMips() {
            return cacheCurrentRequestedMips;
        }

        /**
         * Sets the cache current requested mips.
         *
         * @param cacheCurrentRequestedMips the new cache current requested mips
         */
        protected void setCacheCurrentRequestedMips(List<Double> cacheCurrentRequestedMips) {
            this.cacheCurrentRequestedMips = cacheCurrentRequestedMips;
        }

    }


