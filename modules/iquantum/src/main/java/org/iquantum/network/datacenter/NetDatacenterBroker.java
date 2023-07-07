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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.distributions.UniformDistr;
import org.iquantum.tasks.CTask;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.lists.VmList;
import org.iquantum.utils.Log;
/**
 * NetDatacentreBroker represents a broker acting on behalf of Datacenter provider. It hides VM
 * management, as vm creation, submission of cloudlets to these VMs and destruction of VMs. <br/>
 * <tt>NOTE</tt>: This class is an example only. It works on behalf of a provider not for users. 
 * One has to implement interaction with user broker to this broker.
 * 
 * @author Saurabh Kumar Garg
 * @since iQuantum Toolkit 3.0
 * @todo The class is not a broker acting on behalf of users, but on behalf
 * of a provider. Maybe this distinction would be explicit by 
 * different class hierarchy, such as UserDatacenterBroker and ProviderDatacenterBroker.
 */
public class NetDatacenterBroker extends SimEntity {

	// TODO: remove unnecessary variables

	/** The list of submitted VMs. */
	private List<? extends Vm> vmList;

	/** The list of created VMs. */
	private List<? extends Vm> vmsCreatedList;

	/** The list of submitted {@link NetworkCTask NetworkCloudlets}. */
	private List<? extends NetworkCTask> cloudletList;

	/** The list of submitted {@link AppCloudlet AppCloudlets}. */
	private List<? extends AppCloudlet> appCloudletList;

	/** The list of submitted {@link AppCloudlet AppCloudlets}.
         * @todo attribute appears to be redundant with {@link #appCloudletList}
         */
	private final Map<Integer, Integer> appCloudletRecieved;

	/** The list of submitted {@link CTask Cloudlets}.
         */
	private List<? extends CTask> cloudletSubmittedList;

	/** The list of received {@link CTask Cloudlets}.
         * @todo attribute appears to be redundant with {@link #cloudletSubmittedList}
         */
	private List<? extends CTask> cloudletReceivedList;

	/** The number of submitted cloudlets. */
	private int cloudletsSubmitted;

	/** The number of VMs requested. */
	private int vmsRequested;

	/** The acks sent to VMs. */
	private int vmsAcks;

	/** The number of VMs destroyed. */
	private int vmsDestroyed;

	/** The list of datacenter IDs. */
	private List<Integer> datacenterIdsList;

	/** The datacenter requested IDs list. 
         * @todo attribute appears to be redundant with {@link #datacenterIdsList}
         */
	private List<Integer> datacenterRequestedIdsList;

	/** The VMs to datacenters map where each key is a VM id
         * and the corresponding value is the datacenter where the VM is placed. */
	private Map<Integer, Integer> vmsToDatacentersMap;

	/** The datacenter characteristics map where each key 
         * is the datacenter id and each value is the datacenter itself. */
	private Map<Integer, CDatacenterCharacteristics> datacenterCharacteristicsList;

	public static NetworkCDatacenter linkDC;

	public boolean createvmflag = true;

	public static int cachedcloudlet = 0;

	/**
	 * Creates a new DatacenterBroker object.
	 * 
	 * @param name name to be associated with this entity
	 * 
	 * @throws Exception the exception
	 * 
	 * @pre name != null
	 * @post $none
	 */
	public NetDatacenterBroker(String name) throws Exception {
		super(name);

		setVmList(new ArrayList<NetworkVm>());
		setVmsCreatedList(new ArrayList<NetworkVm>());
		setCloudletList(new ArrayList<NetworkCTask>());
		setAppCloudletList(new ArrayList<AppCloudlet>());
		setCloudletSubmittedList(new ArrayList<CTask>());
		setCloudletReceivedList(new ArrayList<CTask>());
		appCloudletRecieved = new HashMap<Integer, Integer>();

		cloudletsSubmitted = 0;
		setVmsRequested(0);
		setVmsAcks(0);
		setVmsDestroyed(0);

		setDatacenterIdsList(new LinkedList<Integer>());
		setDatacenterRequestedIdsList(new ArrayList<Integer>());
		setVmsToDatacentersMap(new HashMap<Integer, Integer>());
		setDatacenterCharacteristicsList(new HashMap<Integer, CDatacenterCharacteristics>());

	}

	/**
	 * Sends to the broker the list with virtual machines that must be
	 * created.
	 * 
	 * @param list the list
	 * 
	 * @pre list !=null
	 * @post $none
	 */
	public void submitVmList(List<? extends Vm> list) {
		getVmList().addAll(list);
	}

	/**
	 * Sends to the broker the list of cloudlets.
	 * 
	 * @param list the list
	 * 
	 * @pre list !=null
	 * @post $none
	 */
	public void submitCloudletList(List<? extends NetworkCTask> list) {
		getCloudletList().addAll(list);
	}

	public static void setLinkDC(NetworkCDatacenter alinkDC) {
		linkDC = alinkDC;
	}

	/**
	 * Processes events available for this Broker.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != null
	 * @post $none
	 */
	@Override
	public void processEvent(SimEvent ev) {
		switch (ev.getTag()) {
		// Resource characteristics request
			case iQuantumTags.RESOURCE_CHARACTERISTICS_REQUEST:
				processResourceCharacteristicsRequest(ev);
				break;
			// Resource characteristics answer
			case iQuantumTags.RESOURCE_CHARACTERISTICS:
				processResourceCharacteristics(ev);
				break;
			// VM Creation answer

			// A finished cloudlet returned
			case iQuantumTags.CLOUDLET_RETURN:
				processCloudletReturn(ev);
				break;
			// if the simulation finishes
			case iQuantumTags.END_OF_SIMULATION:
				shutdownEntity();
				break;
			case iQuantumTags.NextCycle:
				if (NetworkConstants.BASE) {
					createVmsInDatacenterBase(linkDC.getId());
				}

				break;
			// other unknown tags are processed by this method
			default:
				processOtherEvent(ev);
				break;
		}
	}

	/**
	 * Processes the return of a request for the characteristics of a Datacenter.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != $null
	 * @post $none
	 */
	protected void processResourceCharacteristics(SimEvent ev) {
		CDatacenterCharacteristics characteristics = (CDatacenterCharacteristics) ev.getData();
		getDatacenterCharacteristicsList().put(characteristics.getId(), characteristics);

		if (getDatacenterCharacteristicsList().size() == getDatacenterIdsList().size()) {
			setDatacenterRequestedIdsList(new ArrayList<Integer>());
			createVmsInDatacenterBase(getDatacenterIdsList().get(0));
		}
	}

	/**
	 * Processes a request for the characteristics of a Datacenter.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != $null
	 * @post $none
	 */

	protected void processResourceCharacteristicsRequest(SimEvent ev) {
		setDatacenterIdsList(iQuantum.getCloudResourceList());
		setDatacenterCharacteristicsList(new HashMap<Integer, CDatacenterCharacteristics>());

		Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cloud Resource List received with ",
				getDatacenterIdsList().size(), " resource(s)");

		for (Integer datacenterId : getDatacenterIdsList()) {
			sendNow(datacenterId, iQuantumTags.RESOURCE_CHARACTERISTICS, getId());
		}
	}

	/**
	 * Processes the ack received due to a request for VM creation.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != null
	 * @post $none
	 */

	/**
	 * Processes a cloudlet return event.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != $null
	 * @post $none
	 */
	protected void processCloudletReturn(SimEvent ev) {
		CTask CTask = (CTask) ev.getData();
		getCloudletReceivedList().add(CTask);
		cloudletsSubmitted--;
		// all cloudlets executed
		if (getCloudletList().size() == 0 && cloudletsSubmitted == 0 && NetworkConstants.iteration > 10) {
			Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": All Cloudlets executed. Finishing...");
			clearDatacenters();
			finishExecution();
		} else { // some cloudlets haven't finished yet
			if (getAppCloudletList().size() > 0 && cloudletsSubmitted == 0) {
				// all the cloudlets sent finished. It means that some bount
				// cloudlet is waiting its VM be created
				clearDatacenters();
				createVmsInDatacenterBase(0);
			}

		}
	}

	/**
	 * Processes non-default received events that aren't processed by
         * the {@link #processEvent(org.iquantum.core.SimEvent)} method.
         * This method should be overridden by subclasses in other to process
         * new defined events.
	 * 
	 * @param ev a SimEvent object
	 * 
	 * @pre ev != null
	 * @post $none
	 */
	protected void processOtherEvent(SimEvent ev) {
		if (ev == null) {
			Log.printConcatLine(getName(), ".processOtherEvent(): Error - an event is null.");
			return;
		}

		Log.printConcatLine(getName(), ".processOtherEvent(): ",
				"Error - event unknown by this DatacenterBroker.");
	}

	/**
	 * Creates virtual machines in a datacenter and submit/schedule cloudlets to them.
	 * 
	 * @param datacenterId Id of the Datacenter to create the VMs
	 * 
	 * @pre $none
	 * @post $none
	 */
	protected void createVmsInDatacenterBase(int datacenterId) {
		// send as much vms as possible for this datacenter before trying the
		// next one
		int requestedVms = 0;

		// All host will have two VMs (assumption) VM is the minimum unit
		if (createvmflag) {
			CreateVMs(datacenterId);
			createvmflag = false;
		}

		// generate Application execution Requests
		for (int i = 0; i < 100; i++) {
			this.getAppCloudletList().add(
					new WorkflowApp(AppCloudlet.APP_Workflow, NetworkConstants.currentAppId, 0, 0, getId()));
			NetworkConstants.currentAppId++;

		}
		int k = 0;

		// schedule the application on VMs
		for (AppCloudlet app : this.getAppCloudletList()) {

			List<Integer> vmids = new ArrayList<Integer>();
			int numVms = linkDC.getVmList().size();
			UniformDistr ufrnd = new UniformDistr(0, numVms, 5);
			for (int i = 0; i < app.numbervm; i++) {

				int vmid = (int) ufrnd.sample();
				vmids.add(vmid);

			}

			if (vmids != null) {
				if (!vmids.isEmpty()) {

					app.createCloudletList(vmids);
					for (int i = 0; i < app.numbervm; i++) {
						app.clist.get(i).setUserId(getId());
						appCloudletRecieved.put(app.appID, app.numbervm);
						this.getCloudletSubmittedList().add(app.clist.get(i));
						cloudletsSubmitted++;

						// Sending cloudlet
						sendNow(
								getVmsToDatacentersMap().get(this.getVmList().get(0).getId()),
								iQuantumTags.CLOUDLET_SUBMIT,
								app.clist.get(i));
					}
					System.out.println("app" + (k++));
				}
			}

		}
		setAppCloudletList(new ArrayList<AppCloudlet>());
		if (NetworkConstants.iteration < 10) {

			NetworkConstants.iteration++;
			this.schedule(getId(), NetworkConstants.nexttime, iQuantumTags.NextCycle);
		}

		setVmsRequested(requestedVms);
		setVmsAcks(0);
	}

        /**
         * Creates virtual machines in a datacenter
         * @param datacenterId The id of the datacenter where to create the VMs.
         */
	private void CreateVMs(int datacenterId) {
		// two VMs per host
		int numVM = linkDC.getHostList().size() * NetworkConstants.maxhostVM;
		for (int i = 0; i < numVM; i++) {
			int vmid = i;
			int mips = 1;
			long size = 10000; // image size (MB)
			int ram = 512; // vm memory (MB)
			long bw = 1000;
			int pesNumber = NetworkConstants.HOST_PEs / NetworkConstants.maxhostVM;
			String vmm = "Xen"; // VMM name

			// create VM
			NetworkVm vm = new NetworkVm(
					vmid,
					getId(),
					mips,
					pesNumber,
					ram,
					bw,
					size,
					vmm,
					new NetworkCloudletSpaceSharedScheduler());
			linkDC.processVmCreateNetwork(vm);
			// add the VM to the vmList
			getVmList().add(vm);
			getVmsToDatacentersMap().put(vmid, datacenterId);
			getVmsCreatedList().add(VmList.getById(getVmList(), vmid));
		}
	}

	/**
	 * Sends request to destroy all VMs running on the datacenter.
	 * 
	 * @pre $none
	 * @post $none /** Destroy the virtual machines running in datacenters.
	 * 
	 * @pre $none
	 * @post $none
	 */
	protected void clearDatacenters() {
		for (Vm vm : getVmsCreatedList()) {
			Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Destroying VM #", vm.getId());
			sendNow(getVmsToDatacentersMap().get(vm.getId()), iQuantumTags.VM_DESTROY, vm);
		}

		getVmsCreatedList().clear();
	}

	/**
	 * Sends an internal event communicating the end of the simulation.
	 * 
	 * @pre $none
	 * @post $none
	 */
	private void finishExecution() {
		sendNow(getId(), iQuantumTags.END_OF_SIMULATION);
	}

	@Override
	public void shutdownEntity() {
		Log.printConcatLine(getName(), " is shutting down...");
	}

	@Override
	public void startEntity() {
		Log.printConcatLine(getName(), " is starting...");
		schedule(getId(), 0, iQuantumTags.RESOURCE_CHARACTERISTICS_REQUEST);
	}

	/**
	 * Gets the vm list.
	 * 
	 * @param <T> the generic type
	 * @return the vm list
	 */
	@SuppressWarnings("unchecked")
	public <T extends Vm> List<T> getVmList() {
		return (List<T>) vmList;
	}

	/**
	 * Sets the vm list.
	 * 
	 * @param <T> the generic type
	 * @param vmList the new vm list
	 */
	protected <T extends Vm> void setVmList(List<T> vmList) {
		this.vmList = vmList;
	}

	/**
	 * Gets the cloudlet list.
	 * 
	 * @param <T> the generic type
	 * @return the cloudlet list
	 */
	@SuppressWarnings("unchecked")
	public <T extends NetworkCTask> List<T> getCloudletList() {
		return (List<T>) cloudletList;
	}

	/**
	 * Sets the cloudlet list.
	 * 
	 * @param <T> the generic type
	 * @param cloudletList the new cloudlet list
	 */
	protected <T extends NetworkCTask> void setCloudletList(List<T> cloudletList) {
		this.cloudletList = cloudletList;
	}

	@SuppressWarnings("unchecked")
	public <T extends AppCloudlet> List<T> getAppCloudletList() {
		return (List<T>) appCloudletList;
	}

	public <T extends AppCloudlet> void setAppCloudletList(List<T> appCloudletList) {
		this.appCloudletList = appCloudletList;
	}

	/**
	 * Gets the cloudlet submitted list.
	 * 
	 * @param <T> the generic type
	 * @return the cloudlet submitted list
	 */
	@SuppressWarnings("unchecked")
	public <T extends CTask> List<T> getCloudletSubmittedList() {
		return (List<T>) cloudletSubmittedList;
	}

	/**
	 * Sets the cloudlet submitted list.
	 * 
	 * @param <T> the generic type
	 * @param cloudletSubmittedList the new cloudlet submitted list
	 */
	protected <T extends CTask> void setCloudletSubmittedList(List<T> cloudletSubmittedList) {
		this.cloudletSubmittedList = cloudletSubmittedList;
	}

	/**
	 * Gets the cloudlet received list.
	 * 
	 * @param <T> the generic type
	 * @return the cloudlet received list
	 */
	@SuppressWarnings("unchecked")
	public <T extends CTask> List<T> getCloudletReceivedList() {
		return (List<T>) cloudletReceivedList;
	}

	/**
	 * Sets the cloudlet received list.
	 * 
	 * @param <T> the generic type
	 * @param cloudletReceivedList the new cloudlet received list
	 */
	protected <T extends CTask> void setCloudletReceivedList(List<T> cloudletReceivedList) {
		this.cloudletReceivedList = cloudletReceivedList;
	}

	/**
	 * Gets the vm list.
	 * 
	 * @param <T> the generic type
	 * @return the vm list
	 */
	@SuppressWarnings("unchecked")
	public <T extends Vm> List<T> getVmsCreatedList() {
		return (List<T>) vmsCreatedList;
	}

	/**
	 * Sets the vm list.
	 * 
	 * @param <T> the generic type
	 * @param vmsCreatedList the vms created list
	 */
	protected <T extends Vm> void setVmsCreatedList(List<T> vmsCreatedList) {
		this.vmsCreatedList = vmsCreatedList;
	}

	/**
	 * Gets the vms requested.
	 * 
	 * @return the vms requested
	 */
	protected int getVmsRequested() {
		return vmsRequested;
	}

	/**
	 * Sets the vms requested.
	 * 
	 * @param vmsRequested the new vms requested
	 */
	protected void setVmsRequested(int vmsRequested) {
		this.vmsRequested = vmsRequested;
	}

	/**
	 * Gets the vms acks.
	 * 
	 * @return the vms acks
	 */
	protected int getVmsAcks() {
		return vmsAcks;
	}

	/**
	 * Sets the vms acks.
	 * 
	 * @param vmsAcks the new vms acks
	 */
	protected void setVmsAcks(int vmsAcks) {
		this.vmsAcks = vmsAcks;
	}

	/**
	 * Increment vms acks.
	 */
	protected void incrementVmsAcks() {
		vmsAcks++;
	}

	/**
	 * Gets the vms destroyed.
	 * 
	 * @return the vms destroyed
	 */
	protected int getVmsDestroyed() {
		return vmsDestroyed;
	}

	/**
	 * Sets the vms destroyed.
	 * 
	 * @param vmsDestroyed the new vms destroyed
	 */
	protected void setVmsDestroyed(int vmsDestroyed) {
		this.vmsDestroyed = vmsDestroyed;
	}

	/**
	 * Gets the datacenter ids list.
	 * 
	 * @return the datacenter ids list
	 */
	protected List<Integer> getDatacenterIdsList() {
		return datacenterIdsList;
	}

	/**
	 * Sets the datacenter ids list.
	 * 
	 * @param datacenterIdsList the new datacenter ids list
	 */
	protected void setDatacenterIdsList(List<Integer> datacenterIdsList) {
		this.datacenterIdsList = datacenterIdsList;
	}

	/**
	 * Gets the vms to datacenters map.
	 * 
	 * @return the vms to datacenters map
	 */
	protected Map<Integer, Integer> getVmsToDatacentersMap() {
		return vmsToDatacentersMap;
	}

	/**
	 * Sets the vms to datacenters map.
	 * 
	 * @param vmsToDatacentersMap the vms to datacenters map
	 */
	protected void setVmsToDatacentersMap(Map<Integer, Integer> vmsToDatacentersMap) {
		this.vmsToDatacentersMap = vmsToDatacentersMap;
	}

	/**
	 * Gets the datacenter characteristics list.
	 * 
	 * @return the datacenter characteristics list
	 */
	protected Map<Integer, CDatacenterCharacteristics> getDatacenterCharacteristicsList() {
		return datacenterCharacteristicsList;
	}

	/**
	 * Sets the datacenter characteristics list.
	 * 
	 * @param datacenterCharacteristicsList the datacenter characteristics list
	 */
	protected void setDatacenterCharacteristicsList(
			Map<Integer, CDatacenterCharacteristics> datacenterCharacteristicsList) {
		this.datacenterCharacteristicsList = datacenterCharacteristicsList;
	}

	/**
	 * Gets the datacenter requested ids list.
	 * 
	 * @return the datacenter requested ids list
	 */
	protected List<Integer> getDatacenterRequestedIdsList() {
		return datacenterRequestedIdsList;
	}

	/**
	 * Sets the datacenter requested ids list.
	 * 
	 * @param datacenterRequestedIdsList the new datacenter requested ids list
	 */
	protected void setDatacenterRequestedIdsList(List<Integer> datacenterRequestedIdsList) {
		this.datacenterRequestedIdsList = datacenterRequestedIdsList;
	}

}
