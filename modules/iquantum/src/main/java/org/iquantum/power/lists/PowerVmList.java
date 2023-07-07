/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.power.lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.core.iQuantum;
import org.iquantum.lists.VmList;

/**
 * PowerVmList is a collection of operations on lists of power-enabled VMs.
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
 * 
 * @author Anton Beloglazov
 * @since iQuantum Toolkit 2.0
 * @todo It is a list, so it would be better inside the org.iquantum.lists package.
 * This class in fact doesn't use a list or PowerVm, but a list of Vm.
 * The used methods are just of the Vm class, thus doesn't have
 * a reason to create another class. This classes don't either stores lists of VM,
 * they only perform operations on lists given by parameter.
 * So, the method of this class would be moved to the VmList class
 * and the class erased.
 */
public class PowerVmList extends VmList {

	/**
	 * Sort a given list of VMs by cpu utilization.
	 * 
	 * @param vmList the vm list to be sorted
	 */
	public static <T extends Vm> void sortByCpuUtilization(List<T> vmList) {
		Collections.sort(vmList, new Comparator<T>() {

			@Override
			public int compare(T a, T b) throws ClassCastException {
				Double aUtilization = a.getTotalUtilizationOfCpuMips(iQuantum.clock());
				Double bUtilization = b.getTotalUtilizationOfCpuMips(iQuantum.clock());
				return bUtilization.compareTo(aUtilization);
			}
		});
	}

}
