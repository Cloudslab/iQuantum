/*
 * Title:        iQuantumSDN
 * Description:  SDN extension for iQuantum
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2015, The University of Melbourne, Australia
 */
package org.iquantum.sdn;

import org.iquantum.tasks.CTask;

/**
 * Processing activity to compute in VM. Basically a wrapper of CTask. 
 *  
 * @author Jungmin Son
 * @author Rodrigo N. Calheiros
 * @since iQuantumSDN 1.0
 */
public class Processing implements Activity {

	long requestId;
	CTask cl;
	
	public Processing(CTask cl){
		this.cl=cl;
	}
	
	public CTask getCloudlet(){
		return cl;
	}
}