/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.models;

/**
 * The UtilizationModelNull class is a simple model, according to which a Cloudlet always require
 * zero capacity for a given resource all the time.
 * 
 * @author Anton Beloglazov
 * @since iQuantum Toolkit 2.0
 */
public class UtilizationModelNull implements UtilizationModel {

	@Override
	public double getUtilization(double time) {
		return 0;
	}

}
