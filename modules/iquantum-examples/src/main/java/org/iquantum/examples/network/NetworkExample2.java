/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */


package org.iquantum.examples.network;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.iquantum.brokers.CBroker;
import org.iquantum.datacenters.CDatacenter;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.tasks.CTask;
import org.iquantum.policies.cctasks.CloudletSchedulerTimeShared;
import org.iquantum.backends.classical.Host;
import org.iquantum.network.NetworkTopology;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelFull;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.policies.vm.VmAllocationPolicySimple;
import org.iquantum.policies.vm.VmSchedulerTimeShared;
import org.iquantum.core.iQuantum;
import org.iquantum.provisioners.BwProvisionerSimple;
import org.iquantum.provisioners.PeProvisionerSimple;
import org.iquantum.provisioners.RamProvisionerSimple;
import org.iquantum.utils.Log;
/**
 * A simple example showing how to create
 * two datacenters with one host and a
 * network topology each and run two cloudlets
 * on them.
 */
public class NetworkExample2 {

	/** The cloudlet list. */
	private static List<CTask> CTaskList;

	/** The vmlist. */
	private static List<Vm> vmlist;

	/**
	 * Creates main() to run this example
	 */
	public static void main(String[] args) {

		Log.printLine("Starting NetworkExample2...");

		try {
			// First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 1;   // number of cloud users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;  // mean trace events

			// Initialize the CloudSim library
			iQuantum.init(num_user, calendar, trace_flag);

			// Second step: Create Datacenters
			//Datacenters are the resource providers in iQuantum. We need at list one of them to run a CloudSim simulation
			CDatacenter CDatacenter0 = createDatacenter("Datacenter_0");
			CDatacenter CDatacenter1 = createDatacenter("Datacenter_1");

			//Third step: Create Broker
			CBroker broker = createBroker();
			int brokerId = broker.getId();

			//Fourth step: Create one virtual machine
			vmlist = new ArrayList<Vm>();

			//VM description
			int vmid = 0;
			int mips = 250;
			long size = 10000; //image size (MB)
			int ram = 512; //vm memory (MB)
			long bw = 1000;
			int pesNumber = 1; //number of cpus
			String vmm = "Xen"; //VMM name

			//create two VMs
			Vm vm1 = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

			//the second VM will have twice the priority of VM1 and so will receive twice CPU time
			vmid++;
			Vm vm2 = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

			//add the VMs to the vmList
			vmlist.add(vm1);
			vmlist.add(vm2);

			//submit vm list to the broker
			broker.submitVmList(vmlist);


			//Fifth step: Create two Cloudlets
			CTaskList = new ArrayList<CTask>();

			//Cloudlet properties
			int id = 0;
			long length = 40000;
			long fileSize = 300;
			long outputSize = 300;
			UtilizationModel utilizationModel = new UtilizationModelFull();

			CTask CTask1 = new CTask(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			CTask1.setUserId(brokerId);

			id++;
			CTask CTask2 = new CTask(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			CTask2.setUserId(brokerId);

			//add the cloudlets to the list
			CTaskList.add(CTask1);
			CTaskList.add(CTask2);

			//submit cloudlet list to the broker
			broker.submitCloudletList(CTaskList);


			//bind the cloudlets to the vms. This way, the broker
			// will submit the bound cloudlets only to the specific VM
			broker.bindCloudletToVm(CTask1.getCloudletId(),vm1.getId());
			broker.bindCloudletToVm(CTask2.getCloudletId(),vm2.getId());


			//Sixth step: configure network
			//load the network topology file
			NetworkTopology.buildNetworkTopology("topology.brite");

			//maps CloudSim entities to BRITE entities
			//Datacenter0 will correspond to BRITE node 0
			int briteNode=0;
			NetworkTopology.mapNode(CDatacenter0.getId(),briteNode);

			//Datacenter1 will correspond to BRITE node 2
			briteNode=2;
			NetworkTopology.mapNode(CDatacenter1.getId(),briteNode);

			//Broker will correspond to BRITE node 3
			briteNode=3;
			NetworkTopology.mapNode(broker.getId(),briteNode);


			// Sixth step: Starts the simulation
			iQuantum.startSimulation();


			// Final step: Print results when simulation is over
			List<CTask> newList = broker.getCloudletReceivedList();

			iQuantum.stopSimulation();

			printCloudletList(newList);

			Log.printLine("NetworkExample2 finished!");
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

		int mips = 1000;

		// 3. Create PEs and add these into a list
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Host with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 2048; //host memory (MB)
		long storage = 1000000; //host storage
		int bw = 10000;


		//in this example, the VMAllocatonPolicy in use is Time Shared with priorities. It means that VMs
		//receive time shares accroding to their priority.
		hostList.add(
				new Host(
					hostId,
					new RamProvisionerSimple(ram),
					new BwProvisionerSimple(bw),
					storage,
					peList,
					new VmSchedulerTimeShared(peList)
				)
			); // This is our machine

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
				arch, os, vmm, hostList, time_zone, cost, costPerMem,
				costPerStorage, costPerBw);


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
	private static CBroker createBroker(){

		CBroker broker = null;
		try {
			broker = new CBroker("Broker");
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

		for (int i = 0; i < size; i++) {
			CTask = list.get(i);
			Log.print(indent + CTask.getCloudletId() + indent + indent);

			if (CTask.getCloudletStatus() == CTask.SUCCESS){
				Log.print("SUCCESS");

				DecimalFormat dft = new DecimalFormat("###.##");
				Log.printLine( indent + indent + CTask.getResourceId() + indent + indent + indent + CTask.getVmId() +
						indent + indent + dft.format(CTask.getActualCPUTime()) + indent + indent + dft.format(CTask.getExecStartTime())+
						indent + indent + dft.format(CTask.getFinishTime()));
			}
		}

	}
}
