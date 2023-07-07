/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.distributions;

/**
 * Interface to be implemented by a random number generator.
 * 
 * @author Marcos Dias de Assuncao
 * @since iQuantum Toolkit 1.0
 */
public interface ContinuousDistribution {

	/**
	 * Generate a new pseudo random number.
	 * 
	 * @return the next pseudo random number in the sequence,
         * following the implemented distribution.
	 */
	double sample();

}
