/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn.example;

import java.util.ArrayList;
import java.util.List;

import org.iquantum.tasks.CTask;
import org.iquantum.utils.Log;
import org.iquantum.models.UtilizationModelFull;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.sdn.Constants;
import org.iquantum.sdn.SDNDatacenter;

/**
 * Broker class for iQuantumSDN example. This class represents a broker (Service Provider)
 * who uses the Cloud data center.
 * 
 * @author Jungmin Son
 * @since iQuantumSDN 1.0
 */
public class SDNBroker extends SimEntity {

	private SDNDatacenter datacenter = null;
	private String applicationFileName = null;
	private List<String> workloadFileNames=null;

	private List<CTask> cloudletList;
	private List<Workload> workloads;
	
	public SDNBroker(String name) throws Exception {
		super(name);
		this.workloadFileNames = new ArrayList<String>();
		this.cloudletList = new ArrayList<CTask>();
		this.workloads = new ArrayList<Workload>();
	}
	
	@Override
	public void startEntity() {
		sendNow(this.datacenter.getId(), Constants.APPLICATION_SUBMIT, this.applicationFileName);
	}
	@Override
	public void shutdownEntity() {
		List<Vm> vmList = this.datacenter.getVmList();
		for(Vm vm:vmList) {
			Log.printLine(iQuantum.clock() + ": " + getName() + ": Shuttingdown.. VM:" + vm.getId());
		}
	}
	public void submitDeployApplication(SDNDatacenter dc, String filename) {
		this.datacenter = dc;
		this.applicationFileName = filename;
	}
	
	public void submitRequests(String filename) {
		this.workloadFileNames.add(filename);
	}

	@Override
	public void processEvent(SimEvent ev) {
		int tag = ev.getTag();
		
		switch(tag){
			case iQuantumTags.VM_CREATE_ACK: 	processVmCreate(ev);			break;
			case Constants.APPLICATION_SUBMIT_ACK: 		applicationSubmitCompleted(ev); break;
			case Constants.REQUEST_COMPLETED:	requestCompleted(ev); break;
			default: System.out.println("Unknown event received by "+super.getName()+". Tag:"+ev.getTag());
		}
	}
	private void processVmCreate(SimEvent ev) {
		
	}
	
	private void requestCompleted(SimEvent ev) {
		
	}
	
	public List<CTask> getCloudletReceivedList() {
		return cloudletList;
	}

	public static int appId = 0;
	
	private void applicationSubmitCompleted(SimEvent ev) {
		for(String workloadFileName:this.workloadFileNames) {
			scheduleRequest(workloadFileName);
			SDNBroker.appId++;
		}
	}
	
	private void scheduleRequest(String workloadFile) {
		WorkloadParser rp = new WorkloadParser(workloadFile, this.getId(), new UtilizationModelFull(), 
				this.datacenter.getVmNameIdTable(), this.datacenter.getFlowNameIdTable());
		
		for(Workload wl: rp.getWorkloads()) {
			send(this.datacenter.getId(), wl.time, Constants.REQUEST_SUBMIT, wl.request);
			wl.appId = SDNBroker.appId;
		}
		
		this.cloudletList.addAll(rp.getAllCTasks());
		this.workloads.addAll(rp.getWorkloads());
	}
	
	public List<Workload> getWorkloads() {
		return this.workloads;
	}
	/*
	private static int reqId=0; 
	private void scheduleRequestTest() {
		
		cloudletList = new ArrayList<CTask>();
		int cloudletId = 0;
		
		List<Vm> vmList = this.datacenter.getVmList();
		
		Vm vm1 = vmList.get(0);
		Vm vm2 = vmList.get(1);
		Vm vm3 = vmList.get(2);

		///////////////////////////////////////
		// req = vm1:p1 -> tr1 -> vm2:p2 -> tr2 -> vm3:p3 -> tr3 -> vm1:p4
		// req                    r1               r2               r3    
		long fileSize = 300;
		long outputSize = 300;
		UtilizationModel utilizationModel = new UtilizationModelFull();
		
		CTask cloudlet1 = new CTask(cloudletId++, 4000, 1, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		CTask cloudlet2 = new CTask(cloudletId++, 30000, 1, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		CTask cloudlet3 = new CTask(cloudletId++, 6000, 1, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		CTask cloudlet4 = new CTask(cloudletId++, 10000, 1, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet1.setUserId(getId());
		cloudlet2.setUserId(getId());
		cloudlet3.setUserId(getId());
		cloudlet4.setUserId(getId());
		cloudlet1.setVmId(vm1.getId());
		cloudletList.add(cloudlet1);
		cloudletList.add(cloudlet2);
		cloudletList.add(cloudlet3);
		cloudletList.add(cloudlet4);
		Processing p1 = new Processing(cloudlet1);
		Processing p2 = new Processing(cloudlet2);
		Processing p3 = new Processing(cloudlet3);
		Processing p4 = new Processing(cloudlet4);

		Request req = new Request(reqId++, getId(), getId());
		Request r1 = new Request(reqId++, getId(), getId());
		Request r2 = new Request(reqId++, getId(), getId());
		Request r3 = new Request(reqId++, getId(), getId());
		
		r3.addActivity(p4);
		
		Transmission tr3 = new Transmission(vm3.getId(), vm1.getId(), 30000, r3);
		r2.addActivity(p3);
		r2.addActivity(tr3);
		
		Transmission tr2 = new Transmission(vm2.getId(), vm3.getId(), 7000, r2);
		r1.addActivity(p2);
		r1.addActivity(tr2);

		Transmission tr1 = new Transmission(vm1.getId(), vm2.getId(), 3000, r1);
		req.addActivity(p1);
		req.addActivity(tr1);
		sendNow(this.datacenter.getId(), Constants.REQUEST_SUBMIT, req);
	}

	*/
}
