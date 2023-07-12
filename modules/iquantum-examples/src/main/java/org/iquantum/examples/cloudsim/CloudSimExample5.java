/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */

package org.iquantum.examples.cloudsim;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.iquantum.brokers.CBroker;
import org.iquantum.datacenters.CDatacenter;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.tasks.CTask;
import org.iquantum.policies.ctasks.CloudletSchedulerTimeShared;
import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelFull;
import org.iquantum.backends.classical.Vm;
import org.iquantum.policies.vm.VmAllocationPolicySimple;
import org.iquantum.policies.vm.VmSchedulerSpaceShared;
import org.iquantum.core.iQuantum;
import org.iquantum.provisioners.BwProvisionerSimple;
import org.iquantum.provisioners.PeProvisionerSimple;
import org.iquantum.provisioners.RamProvisionerSimple;
import org.iquantum.utils.Log;

/**
 * A simple example showing how to create
 * two datacenters with one host each and
 * run cloudlets of two users on them.
 */
public class CloudSimExample5 {

	/** The cloudlet lists. */
	private static List<CTask> CTaskList1;
	private static List<CTask> CTaskList2;

	/** The vmlists. */
	private static List<Vm> vmlist1;
	private static List<Vm> vmlist2;

	/**
	 * Creates main() to run this example
	 */
	public static void main(String[] args) {

		Log.printLine("Starting CloudSimExample5...");

		try {
			// First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 2;   // number of cloud users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;  // mean trace events

			// Initialize the CloudSim library
			iQuantum.init(num_user, calendar, trace_flag);

			// Second step: Create Datacenters
			//Datacenters are the resource providers in iQuantum. We need at list one of them to run a CloudSim simulation
			@SuppressWarnings("unused")
			CDatacenter CDatacenter0 = createDatacenter("Datacenter_0");
			@SuppressWarnings("unused")
			CDatacenter CDatacenter1 = createDatacenter("Datacenter_1");

			//Third step: Create Brokers
			CBroker broker1 = createBroker(1);
			int brokerId1 = broker1.getId();

			CBroker broker2 = createBroker(2);
			int brokerId2 = broker2.getId();

			//Fourth step: Create one virtual machine for each broker/user
			vmlist1 = new ArrayList<Vm>();
			vmlist2 = new ArrayList<Vm>();

			//VM description
			int vmid = 0;
			int mips = 250;
			long size = 10000; //image size (MB)
			int ram = 512; //vm memory (MB)
			long bw = 1000;
			int pesNumber = 1; //number of cpus
			String vmm = "Xen"; //VMM name

			//create two VMs: the first one belongs to user1
			Vm vm1 = new Vm(vmid, brokerId1, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

			//the second VM: this one belongs to user2
			Vm vm2 = new Vm(vmid, brokerId2, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

			//add the VMs to the vmlists
			vmlist1.add(vm1);
			vmlist2.add(vm2);

			//submit vm list to the broker
			broker1.submitVmList(vmlist1);
			broker2.submitVmList(vmlist2);

			//Fifth step: Create two Cloudlets
			CTaskList1 = new ArrayList<CTask>();
			CTaskList2 = new ArrayList<CTask>();

			//Cloudlet properties
			int id = 0;
			long length = 40000;
			long fileSize = 300;
			long outputSize = 300;
			UtilizationModel utilizationModel = new UtilizationModelFull();

			CTask CTask1 = new CTask(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			CTask1.setUserId(brokerId1);

			CTask CTask2 = new CTask(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			CTask2.setUserId(brokerId2);

			//add the cloudlets to the lists: each cloudlet belongs to one user
			CTaskList1.add(CTask1);
			CTaskList2.add(CTask2);

			//submit cloudlet list to the brokers
			broker1.submitCloudletList(CTaskList1);
			broker2.submitCloudletList(CTaskList2);

			// Sixth step: Starts the simulation
			iQuantum.startSimulation();

			// Final step: Print results when simulation is over
			List<CTask> newList1 = broker1.getCloudletReceivedList();
			List<CTask> newList2 = broker2.getCloudletReceivedList();

			iQuantum.stopSimulation();

			Log.print("=============> User "+brokerId1+"    ");
			printCloudletList(newList1);

			Log.print("=============> User "+brokerId2+"    ");
			printCloudletList(newList2);

			Log.printLine("CloudSimExample5 finished!");
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.printLine("The simulation has been terminated due to an unexpected error");
		}
	}

	private static CDatacenter createDatacenter(String name){

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store
		//    our machine
		List<Host> hostList = new ArrayList<Host>();

		// 2. A Machine contains one or more PEs or CPUs/Cores.
		// In this example, it will have only one core.
		List<Pe> peList = new ArrayList<Pe>();

		int mips=1000;

		// 3. Create PEs and add these into a list.
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Host with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 2048; //host memory (MB)
		long storage = 1000000; //host storage
		int bw = 10000;


		//in this example, the VMAllocatonPolicy in use is SpaceShared. It means that only one VM
		//is allowed to run on each Pe. As each Host has only one Pe, only one VM can run on each Host.
		hostList.add(
    			new Host(
    				hostId,
    				new RamProvisionerSimple(ram),
    				new BwProvisionerSimple(bw),
    				storage,
    				peList,
    				new VmSchedulerSpaceShared(peList)
    			)
    		); // This is our first machine

		// 5. Create a DatacenterCharacteristics object that stores the
		//    properties of a data center: architecture, OS, list of
		//    Machines, allocation policy: time- or space-shared, time zone
		//    and its price (G$/Pe time unit).
		String arch = "x86";      // system architecture
		String os = "Linux";          // operating system
		String vmm = "Xen";
		double time_zone = 10.0;         // time zone this resource located
		double cost = 3.0;              // the cost of using processing in this resource
		double costPerMem = 0.05;		// the cost of using memory in this resource
		double costPerStorage = 0.001;	// the cost of using storage in this resource
		double costPerBw = 0.0;			// the cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

		CDatacenterCharacteristics characteristics = new CDatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


		// 6. Finally, we need to create a PowerDatacenter object.
		CDatacenter CDatacenter = null;
		try {
			CDatacenter = new CDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CDatacenter;
	}

	//We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
	//to the specific rules of the simulated scenario
	private static CBroker createBroker(int id){

		CBroker broker = null;
		try {
			broker = new CBroker("Broker"+id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}

	/**
	 * Prints the Cloudlet objects
	 * @param list  list of Cloudlets
	 */
	private static void printCloudletList(List<CTask> list) {
		int size = list.size();
		CTask CTask;

		String indent = "    ";
		Log.printLine();
		Log.printLine("========== OUTPUT ==========");
		Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
				"Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

		DecimalFormat dft = new DecimalFormat("###.##");
		for (int i = 0; i < size; i++) {
			CTask = list.get(i);
			Log.print(indent + CTask.getCloudletId() + indent + indent);

			if (CTask.getCloudletStatus() == CTask.SUCCESS){
				Log.print("SUCCESS");

				Log.printLine( indent + indent + CTask.getResourceId() + indent + indent + indent + CTask.getVmId() +
						indent + indent + dft.format(CTask.getActualCPUTime()) + indent + indent + dft.format(CTask.getExecStartTime())+
						indent + indent + dft.format(CTask.getFinishTime()));
			}
		}

	}
}
