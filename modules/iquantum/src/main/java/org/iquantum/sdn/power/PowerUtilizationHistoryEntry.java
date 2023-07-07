/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */

package org.iquantum.sdn.power;

/**
 *  To log utilization history, this class holds power utilization information
 * 
 * @author Jungmin Son
 * @since iQuantumSDN 1.0
 */
public class PowerUtilizationHistoryEntry {
	public double startTime;
	public double usedMips;
	public PowerUtilizationHistoryEntry(double t, double m) { startTime=t; usedMips=m;}
}
