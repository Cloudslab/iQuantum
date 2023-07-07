/*
 * Title:        iQuantum Toolkit
 * Description:  iQuantum (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.iquantum.network.datacenter;

import java.util.ArrayList;
import java.util.Map;

import org.iquantum.tasks.CTask;
import org.iquantum.models.UtilizationModel;

/**
 * NetworkCloudlet class extends Cloudlet to support simulation of complex applications. Each such
 * a network Cloudlet represents a task of the application. Each task consists of several stages.
 * 
 * <br/>Please refer to following publication for more details:<br/>
 * <ul>
 * <li><a href="http://dx.doi.org/10.1109/UCC.2011.24">Saurabh Kumar Garg and Rajkumar Buyya, NetworkiQuantum: Modelling Parallel Applications in Cloud
 * Simulations, Proceedings of the 4th IEEE/ACM International Conference on Utility and Cloud
 * Computing (UCC 2011, IEEE CS Press, USA), Melbourne, Australia, December 5-7, 2011.</a>
 * </ul>
 * 
 * @author Saurabh Kumar Garg
 * @since iQuantum Toolkit 1.0
 * @todo Attributes should be private
 * @todo The different cloudlet classes should have a class hierarchy, by means
 * of a super class and/or interface.
 */
public class NetworkCTask extends CTask implements Comparable<Object> {
        /** Time when cloudlet will be submitted. */
	public double submittime; 

        /** Time when cloudlet finishes execution. */
	public double finishtime; 

        /** Execution time for cloudlet. */
	public double exetime; 

        /** Number of cloudlet's stages . */
	public double numStage;

        /** Current stage of cloudlet execution. */
	public int currStagenum; 

        /** Star time of the current stage. 
         */
	public double timetostartStage;

        /** Time spent in the current stage. 
         */
	public double timespentInStage; 

        /** 
         * @todo It doesn't appear to be used. 
        */
	public Map<Double, HostPacket> timeCommunicate;

        /** All stages which cloudlet execution. */
	public ArrayList<TaskStage> stages; 
        
        /**
         * Cloudlet's memory.
         * @todo Required, allocated, used memory?
         * It doesn't appear to be used.
         */
	long memory;

        /**
         * Cloudlet's start time.
         */
	public double starttime;

	public NetworkCTask(
			int cloudletId,
			long cloudletLength,
			int pesNumber,
			long cloudletFileSize,
			long cloudletOutputSize,
			long memory,
			UtilizationModel utilizationModelCpu,
			UtilizationModel utilizationModelRam,
			UtilizationModel utilizationModelBw) {
		super(
				cloudletId,
				cloudletLength,
				pesNumber,
				cloudletFileSize,
				cloudletOutputSize,
				utilizationModelCpu,
				utilizationModelRam,
				utilizationModelBw);

		currStagenum = -1;
		this.memory = memory;
		stages = new ArrayList<TaskStage>();
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	public double getSubmittime() {
		return submittime;
	}

}
