/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.power;

import java.util.List;
import java.util.Map;

import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.backends.classical.Storage;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.policies.vm.VmAllocationPolicy;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.core.predicates.PredicateType;
import org.iquantum.utils.Log;
/**
 * PowerDatacenterNonPowerAware is a class that represents a <b>non-power</b> aware data center in the
 * context of power-aware simulations.
 * 
 * <br/>If you are using any algorithms, policies or workload included in the power package please cite
 * the following paper:<br/>
 * 
 * <ul>
 * <li><a href="http://dx.doi.org/10.1002/cpe.1867">Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley & Sons, Ltd, New York, USA, 2012</a>
 * </ul>
 * 
 * @author Anton Beloglazov
 * @since iQuantum Toolkit 2.0
 */
public class PowerCDatacenterNonPowerAware extends PowerCDatacenter {

	/**
	 * Instantiates a new datacenter.
	 * 
	 * @param name the datacenter name
	 * @param characteristics the datacenter characteristics
	 * @param schedulingInterval the scheduling interval
	 * @param vmAllocationPolicy the vm provisioner
	 * @param storageList the storage list
	 * 
	 * @throws Exception the exception
	 */
	public PowerCDatacenterNonPowerAware(
			String name,
			CDatacenterCharacteristics characteristics,
			VmAllocationPolicy vmAllocationPolicy,
			List<Storage> storageList,
			double schedulingInterval) throws Exception {
		super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
	}

	@Override
	protected void updateCloudletProcessing() {
		if (getCloudletSubmitted() == -1 || getCloudletSubmitted() == iQuantum.clock()) {
			iQuantum.cancelAll(getId(), new PredicateType(iQuantumTags.VM_DATACENTER_EVENT));
			schedule(getId(), getSchedulingInterval(), iQuantumTags.VM_DATACENTER_EVENT);
			return;
		}
		double currentTime = iQuantum.clock();
		double timeframePower = 0.0;

		if (currentTime > getLastProcessTime()) {
			double timeDiff = currentTime - getLastProcessTime();
			double minTime = Double.MAX_VALUE;

			Log.printLine("\n");

			for (PowerHost host : this.<PowerHost> getHostList()) {
				Log.formatLine("%.2f: Host #%d", iQuantum.clock(), host.getId());

				double hostPower = 0.0;

				try {
					hostPower = host.getMaxPower() * timeDiff;
					timeframePower += hostPower;
				} catch (Exception e) {
					e.printStackTrace();
				}

				Log.formatLine(
						"%.2f: Host #%d utilization is %.2f%%",
						iQuantum.clock(),
						host.getId(),
						host.getUtilizationOfCpu() * 100);
				Log.formatLine(
						"%.2f: Host #%d energy is %.2f W*sec",
						iQuantum.clock(),
						host.getId(),
						hostPower);
			}

			Log.formatLine("\n%.2f: Consumed energy is %.2f W*sec\n", iQuantum.clock(), timeframePower);

			Log.printLine("\n\n--------------------------------------------------------------\n\n");

			for (PowerHost host : this.<PowerHost> getHostList()) {
				Log.formatLine("\n%.2f: Host #%d", iQuantum.clock(), host.getId());

				double time = host.updateVmsProcessing(currentTime); // inform VMs to update
																		// processing
				if (time < minTime) {
					minTime = time;
				}
			}

			setPower(getPower() + timeframePower);

			checkCloudletCompletion();

			/** Remove completed VMs **/
			for (PowerHost host : this.<PowerHost> getHostList()) {
				for (Vm vm : host.getCompletedVms()) {
					getVmAllocationPolicy().deallocateHostForVm(vm);
					getVmList().remove(vm);
					Log.printLine("VM #" + vm.getId() + " has been deallocated from host #" + host.getId());
				}
			}

			Log.printLine();

			if (!isDisableMigrations()) {
				List<Map<String, Object>> migrationMap = getVmAllocationPolicy().optimizeAllocation(
						getVmList());

				if (migrationMap != null) {
					for (Map<String, Object> migrate : migrationMap) {
						Vm vm = (Vm) migrate.get("vm");
						PowerHost targetHost = (PowerHost) migrate.get("host");
						PowerHost oldHost = (PowerHost) vm.getHost();

						if (oldHost == null) {
							Log.formatLine(
									"%.2f: Migration of VM #%d to Host #%d is started",
									iQuantum.clock(),
									vm.getId(),
									targetHost.getId());
						} else {
							Log.formatLine(
									"%.2f: Migration of VM #%d from Host #%d to Host #%d is started",
									iQuantum.clock(),
									vm.getId(),
									oldHost.getId(),
									targetHost.getId());
						}

						targetHost.addMigratingInVm(vm);
						incrementMigrationCount();

						/** VM migration delay = RAM / bandwidth + C (C = 10 sec) **/
						send(
								getId(),
								vm.getRam() / ((double) vm.getBw() / 8000) + 10,
								iQuantumTags.VM_MIGRATE,
								migrate);
					}
				}
			}

			// schedules an event to the next time
			if (minTime != Double.MAX_VALUE) {
				iQuantum.cancelAll(getId(), new PredicateType(iQuantumTags.VM_DATACENTER_EVENT));
				// iQuantum.cancelAll(getId(), iQuantum.SIM_ANY);
				send(getId(), getSchedulingInterval(), iQuantumTags.VM_DATACENTER_EVENT);
			}

			setLastProcessTime(currentTime);
		}
	}

}
