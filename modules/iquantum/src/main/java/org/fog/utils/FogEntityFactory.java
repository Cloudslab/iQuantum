package org.fog.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.power.PowerHost;
import org.iquantum.power.models.PowerModelLinear;
import org.iquantum.provisioners.RamProvisionerSimple;
import org.iquantum.sdn.overbooking.BwProvisionerOverbooking;
import org.iquantum.sdn.overbooking.PeProvisionerOverbooking;
import org.fog.entities.FogDevice;
import org.fog.entities.FogDeviceCharacteristicsC;
import org.fog.policy.AppModuleAllocationPolicy;
import org.fog.scheduler.StreamOperatorScheduler;

public class FogEntityFactory {

	public static FogDevice createFogDevice(String name, int mips, double uplinkBandwidth, double downlinkBandwidth, double latency, double ratePerMips) {

		List<Pe> peList = new ArrayList<Pe>();
		peList.add(new Pe(0, new PeProvisionerOverbooking(mips))); // need to store Pe id and MIPS Rating

		int hostId = FogUtils.generateEntityId();
		int ram = 2048; // host memory (MB)
		long storage = 1000000; // host storage
		int bw = 10000;

		PowerHost host = new PowerHost(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerOverbooking(bw),
				storage,
				peList,
				new StreamOperatorScheduler(peList),
				new PowerModelLinear(100, 40)
			);

		List<Host> hostList = new ArrayList<Host>();
		hostList.add(host);

		String arch = "x86"; // system architecture
		String os = "Linux"; // operating system
		String vmm = "Xen";
		double time_zone = 10.0; // time zone this resource located
		double cost = 3.0; // the cost of using processing in this resource
		double costPerMem = 0.05; // the cost of using memory in this resource
		double costPerStorage = 0.001; // the cost of using storage in this
										// resource
		double costPerBw = 0.0; // the cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
													// devices by now

		FogDeviceCharacteristicsC characteristics = new FogDeviceCharacteristicsC(
				arch, os, vmm, host, time_zone, cost, costPerMem,
				costPerStorage, costPerBw);

		FogDevice fogdevice = null;
		try {
			fogdevice = new FogDevice(name, characteristics, 
					new AppModuleAllocationPolicy(hostList), storageList, 10, uplinkBandwidth, downlinkBandwidth, latency, ratePerMips);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fogdevice;
	}

	
}
