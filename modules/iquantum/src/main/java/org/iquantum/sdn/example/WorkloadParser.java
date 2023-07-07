/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.iquantum.tasks.CTask;
import org.iquantum.models.UtilizationModel;
import org.iquantum.sdn.Processing;
import org.iquantum.sdn.Request;
import org.iquantum.sdn.Transmission;

/**
 * Parse [request].csv file. 
 * 
 * File format : req_time, vm_name(1), pkt_size(1), cloudlet_len(1), 
 *                         vm_name(2), pkt_size(2), cloudlet_len(2),
 *                         ...
 * 
 * @author Jungmin Son
 * @since iQuantumSDN 1.0
 */

public class WorkloadParser {
	private final Map<String, Integer> vmNames;
	private final Map<String, Integer> flowNames;
	private String file;
	private static int reqId = 0;
	private static int cloudletId = 0;
	private int userId;
	private UtilizationModel utilizationModel;
	private List<Workload> workloads;
	private List<CTask> lastCTasks;
	private List<CTask> allCTasks;
	
	public WorkloadParser(String file, int userId, UtilizationModel cloudletUtilModel, 
			Map<String, Integer> vmNameIdMap, Map<String, Integer> flowNameIdMap) {
		this.file = file;
		this.userId = userId;
		this.utilizationModel = cloudletUtilModel;
		this.vmNames = vmNameIdMap;
		this.flowNames = flowNameIdMap;
		
		startParsing();
	}
	
	public List<Workload> getWorkloads() {
		return this.workloads;
	}
	
	public List<CTask> getLastCTasks() {
		// Returns cloudlets that is done at last for each workload
		return this.lastCTasks;
	}
	
	public List<CTask> getAllCTasks() {
		// Returns cloudlets that is done at last for each workload
		return this.allCTasks;
	}
	
	
	private int getVmId(String vmName) {
		Integer vmId = this.vmNames.get(vmName);
		if(vmId == null) {
			System.err.println("Cannot find VM name:"+vmName);
			return -1;
		}
		return vmId;
	}

	private CTask generateCTask(int vmId, int length) {
		int peNum=1;
		long fileSize = 300;
		long outputSize = 300;
		CTask cloudlet= new CTask(cloudletId++, length, peNum, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet.setUserId(userId);
		cloudlet.setVmId(vmId);

		return cloudlet;
	}
	
	// Cloud_Len -> /FlowId/ -> ToVmId -> PktSize
	private Request parseRequest(int fromVmId, Queue<String> lineitems) {
		if(lineitems.size() <= 0)
		{
			System.err.println("No REQUEST! ERROR");
			return null;
		}
		
		long cloudletLen = Long.parseLong(lineitems.poll());

		Request req = new Request(reqId++, userId);
		CTask cl = generateCTask(fromVmId, (int) cloudletLen);
		this.allCTasks.add(cl);
		
		Processing proc = new Processing(cl);
		req.addActivity(proc);
		
		if(lineitems.size() != 0) {
			// Has more requests after this. Create a transmission and add
			String linkName = lineitems.poll();
			Integer flowId = this.flowNames.get(linkName);
			
			if(flowId == null) {
				throw new IllegalArgumentException("No such link name in virtual.json:"+linkName);
			}
			
			String vmName = lineitems.poll();
			int toVmId = getVmId(vmName);
			
			long pktSize = Long.parseLong(lineitems.poll());
			
			Request nextReq = parseRequest(toVmId, lineitems);
			
			Transmission trans = new Transmission(fromVmId, toVmId, pktSize, flowId, nextReq);
			req.addActivity(trans);
		} else {
			// this is the last request.
			this.lastCTasks.add(cl);
		}
		return req;
	}
	
	private void startParsing() {
		this.workloads = new ArrayList<Workload>();
		this.lastCTasks = new ArrayList<CTask>();
		this.allCTasks = new ArrayList<CTask>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line;
		try {
			@SuppressWarnings("unused")
			String head=br.readLine();
			//System.out.println("Headline: "+ head);
			
			while ((line = br.readLine()) != null) {
				//System.out.println("parsing:"+line);
				
				Workload tr = new Workload();
				
				String[] splitLine = line.split(",");
				Queue<String> lineitems = new LinkedList<String>(Arrays.asList(splitLine));
				
				tr.time = Double.parseDouble(lineitems.poll());
				
				String vmName = lineitems.poll();
				tr.submitVmId = getVmId(vmName);
				
				tr.submitPktSize = Integer.parseInt(lineitems.poll());
				
				tr.request = parseRequest(tr.submitVmId, lineitems);
				
				workloads.add(tr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
