/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn;

import java.util.List;
import java.util.Map;

import org.iquantum.tasks.CTask;
import org.iquantum.datacenters.CDatacenter;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Storage;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.policies.vm.VmAllocationPolicy;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.core.SimEvent;

/**
 * Extended class of CDatacenter that supports processing SDN-specific events.
 * In addtion to the default CDatacenter, it processes Request submission to VM,
 * and application deployment request. 
 * 
 * @author Jungmin Son
 * @author Rodrigo N. Calheiros
 * @since iQuantumSDN 1.0
 */
public class SDNDatacenter extends CDatacenter {

	NetworkOperatingSystem nos;
	
	public SDNDatacenter(String name, CDatacenterCharacteristics characteristics, VmAllocationPolicy vmAllocationPolicy, List<Storage> storageList, double schedulingInterval, NetworkOperatingSystem nos) throws Exception {
		super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
		
		this.nos=nos;
		//nos.init();
	}
	
	public void addVm(Vm vm){
		getVmList().add(vm);
		if (vm.isBeingInstantiated()) vm.setBeingInstantiated(false);
		vm.updateVmProcessing(iQuantum.clock(), getVmAllocationPolicy().getHost(vm).getVmScheduler().getAllocatedMipsForVm(vm));
	}
		
	@Override
	protected void processVmCreate(SimEvent ev, boolean ack) {
		super.processVmCreate(ev, ack);
		if(ack) {
			send(nos.getId(), iQuantum.getMinTimeBetweenEvents(), iQuantumTags.VM_CREATE_ACK, ev.getData());
		}
			
	}
	
	@Override
	public void processOtherEvent(SimEvent ev){
		switch(ev.getTag()){
			case Constants.REQUEST_SUBMIT: processRequest((Request) ev.getData()); break;
			case Constants.APPLICATION_SUBMIT: processApplication(ev.getSource(),(String) ev.getData()); break;
			default: System.out.println("Unknown event recevied by SdnCDatacenter. Tag:"+ev.getTag());
		}
	}

	@Override
	protected void checkCloudletCompletion() {
		if(!nos.isApplicationDeployed())
		{
			super.checkCloudletCompletion();
			return;
		}
		
		List<? extends Host> list = getVmAllocationPolicy().getHostList();
		for (int i = 0; i < list.size(); i++) {
			Host host = list.get(i);
			for (Vm vm : host.getVmList()) {
				while (vm.getCloudletScheduler().isFinishedCloudlets()) {
					CTask cl = vm.getCloudletScheduler().getNextFinishedCloudlet();
					if (cl != null) {
						int hostAddress = nos.getHostAddressByVmId(cl.getVmId());
						sendNow(hostAddress, iQuantumTags.CLOUDLET_RETURN, cl);
					}
				}
			}
		}
	}
	
	private void processRequest(Request req) {//Request received from user. Send to SdnHost
		Activity ac = req.getNextActivity();
		if(ac instanceof Processing) {
			CTask cl = ((Processing) ac).getCloudlet();
			int hostAddress = nos.getHostAddressByVmId(cl.getVmId());
			
			//for this first package, size doesn't matter
			Package pkg = new Package(super.getId(), cl.getVmId(), -1, -1, req);
			sendNow(hostAddress, Constants.SDN_PACKAGE, pkg);
		}
		else {
			System.err.println("Request should start with Processing!!");
		}
	}
	
	private void processApplication(int userId, String filename) {
		nos.deployApplication(userId,filename);
		send(userId, iQuantum.getMinTimeBetweenEvents(), Constants.APPLICATION_SUBMIT_ACK, filename);
	}
	
	public Map<String, Integer> getVmNameIdTable() {
		return this.nos.getVmNameIdTable();
	}
	public Map<String, Integer> getFlowNameIdTable() {
		return this.nos.getFlowNameIdTable();
	}
}
