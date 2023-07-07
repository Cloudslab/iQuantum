/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.sdn.overbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.provisioners.PeProvisioner;

/**
 * The Class PeProvisionerSimple.
 * 
 * @author Anton Beloglazov
 * @since iQuantum Toolkit 2.0
 */
public class PeProvisionerOverbooking extends PeProvisioner {

	/** The pe table. */
	private Map<String, List<Double>> peTable;
	public static final double overbookingRatioMips = 4.0;	// 10% overbooking allowed for MIPS

	/**
	 * Creates the PeProvisionerSimple object.
	 * 
	 * @param availableMips the available mips
	 * 
	 * @pre $none
	 * @post $none
	 */
	public PeProvisionerOverbooking(double availableMips) {
		super(availableMips);
		
		setAvailableMips(PeProvisionerOverbooking.getOverbookedMips(availableMips));

		setPeTable(new HashMap<String, ArrayList<Double>>());
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#allocateMipsForVM(iQuantum.power.VM, int)
	 */
	@Override
	public boolean allocateMipsForVm(Vm vm, double mips) {
		return allocateMipsForVm(vm.getUid(), mips);
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#allocateMipsForVm(java.lang.String, double)
	 */
	@Override
	public boolean allocateMipsForVm(String vmUid, double mips) {
		if (getAvailableMips() < mips) {
			return false;
		}

		List<Double> allocatedMips;

		if (getPeTable().containsKey(vmUid)) {
			allocatedMips = getPeTable().get(vmUid);
		} else {
			allocatedMips = new ArrayList<Double>();
		}

		allocatedMips.add(mips);

		setAvailableMips(getAvailableMips() - mips);
		getPeTable().put(vmUid, allocatedMips);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#allocateMipsForVM(iQuantum.power.VM,
	 * java.util.ArrayList)
	 */
	@Override
	public boolean allocateMipsForVm(Vm vm, List<Double> mips) {
		int totalMipsToAllocate = 0;
		for (double _mips : mips) {
			totalMipsToAllocate += _mips;
		}

		if (getAvailableMips() + getTotalAllocatedMipsForVm(vm) < totalMipsToAllocate) {
			return false;
		}

		setAvailableMips(getAvailableMips() + getTotalAllocatedMipsForVm(vm) - totalMipsToAllocate);

		getPeTable().put(vm.getUid(), mips);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#deallocateMipsForAllVms()
	 */
	@Override
	public void deallocateMipsForAllVms() {
		super.deallocateMipsForAllVms();
		
		setAvailableMips(PeProvisionerOverbooking.getOverbookedMips(getMips()));	//Overbooking

		getPeTable().clear();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * iQuantum.provisioners.PeProvisioner#getAllocatedMipsForVMByVirtualPeId(iQuantum.power.VM,
	 * int)
	 */
	@Override
	public double getAllocatedMipsForVmByVirtualPeId(Vm vm, int peId) {
		if (getPeTable().containsKey(vm.getUid())) {
			try {
				return getPeTable().get(vm.getUid()).get(peId);
			} catch (Exception e) {
			}
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#getAllocatedMipsForVM(iQuantum.power.VM)
	 */
	@Override
	public List<Double> getAllocatedMipsForVm(Vm vm) {
		if (getPeTable().containsKey(vm.getUid())) {
			return getPeTable().get(vm.getUid());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#getTotalAllocatedMipsForVM(iQuantum.power.VM)
	 */
	@Override
	public double getTotalAllocatedMipsForVm(Vm vm) {
		if (getPeTable().containsKey(vm.getUid())) {
			double totalAllocatedMips = 0.0;
			for (double mips : getPeTable().get(vm.getUid())) {
				totalAllocatedMips += mips;
			}
			return totalAllocatedMips;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see iQuantum.provisioners.PeProvisioner#deallocateMipsForVM(iQuantum.power.VM)
	 */
	@Override
	public void deallocateMipsForVm(Vm vm) {
		if (getPeTable().containsKey(vm.getUid())) {
			for (double mips : getPeTable().get(vm.getUid())) {
				setAvailableMips(getAvailableMips() + mips);
			}
			getPeTable().remove(vm.getUid());
		}
	}

	/**
	 * Gets the pe table.
	 * 
	 * @return the peTable
	 */
	protected Map<String, List<Double>> getPeTable() {
		return peTable;
	}

	/**
	 * Sets the pe table.
	 * 
	 * @param peTable the peTable to set
	 */
	@SuppressWarnings("unchecked")
	protected void setPeTable(Map<String, ? extends List<Double>> peTable) {
		this.peTable = (Map<String, List<Double>>) peTable;
	}

	public static double getOverbookedMips(double availableMips) {
		double overbookedMips = availableMips * PeProvisionerOverbooking.overbookingRatioMips;
		return overbookedMips;		
	}

}
