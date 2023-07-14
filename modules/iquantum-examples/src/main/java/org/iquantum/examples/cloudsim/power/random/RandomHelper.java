/*
 *
 */
package org.iquantum.examples.cloudsim.power.random;

import java.util.ArrayList;
import java.util.List;

import org.iquantum.tasks.CTask;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelNull;
import org.iquantum.models.UtilizationModelStochastic;
import org.iquantum.examples.cloudsim.power.Constants;

/**
 * The Helper class for the random workload.
 * 
 * If you are using any algorithms, policies or workload included in the power package please cite
 * the following paper:
 * 
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley and Sons, Ltd, New York, USA, 2012
 * 
 * @author Anton Beloglazov
 * @since Jan 5, 2012
 */
public class RandomHelper {

	/**
	 * Creates the cloudlet list.
	 * 
	 * @param brokerId the broker id
	 * @param cloudletsNumber the cloudlets number
	 * 
	 * @return the list< cloudlet>
	 */
	public static List<CTask> createCloudletList(int brokerId, int cloudletsNumber) {
		List<CTask> list = new ArrayList<CTask>();

		long fileSize = 300;
		long outputSize = 300;
		long seed = RandomConstants.CLOUDLET_UTILIZATION_SEED;
		UtilizationModel utilizationModelNull = new UtilizationModelNull();

		for (int i = 0; i < cloudletsNumber; i++) {
			CTask CTask = null;
			if (seed == -1) {
				CTask = new CTask(
						i,
						Constants.CLOUDLET_LENGTH,
						Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						new UtilizationModelStochastic(),
						utilizationModelNull,
						utilizationModelNull);
			} else {
				CTask = new CTask(
						i,
						Constants.CLOUDLET_LENGTH,
						Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						new UtilizationModelStochastic(seed * i),
						utilizationModelNull,
						utilizationModelNull);
			}
			CTask.setUserId(brokerId);
			CTask.setVmId(i);
			list.add(CTask);
		}

		return list;
	}

}
