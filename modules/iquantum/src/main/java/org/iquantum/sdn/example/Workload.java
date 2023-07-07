/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn.example;

import org.iquantum.sdn.Request;

/**
 * Class to keep workload information parsed from files.
 * This class is used in WorkloadParser
 * 
 * @author Jungmin Son
 * @since iQuantumSDN 1.0
 */
public class Workload {
	public int appId;
	public double time;
	public int submitVmId;
	public int submitPktSize;
	public Request request;	
}
