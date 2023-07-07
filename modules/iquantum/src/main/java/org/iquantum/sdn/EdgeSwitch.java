/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */

package org.iquantum.sdn;

/**
 * Represent edge switch
 * 
 * @author Jungmin Son
 * @author Rodrigo N. Calheiros
 * @since iQuantumSDN 1.0
 */
public class EdgeSwitch extends Switch {

	public EdgeSwitch(String name,int bw, long iops, int upports, int downports, NetworkOperatingSystem nos) {
		super(name, bw, iops, upports, downports, nos);
	}
	
}
