/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn.power;


/**
 * Interface to manage host history.
 * 
 * @author Jungmin Son
 * @since iQuantumSDN 1.0
 */
public interface PowerUtilizationMaxHostInterface {
	void logMaxNumHostsUsed();
	int getMaxNumHostsUsed();
}
