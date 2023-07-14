package org.iquantum.examples.cloudsim.power.planetlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.iquantum.tasks.CTask;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelNull;
import org.iquantum.models.UtilizationModelPlanetLabInMemory;
import org.iquantum.examples.cloudsim.power.Constants;

/**
 * A helper class for the running examples for the PlanetLab workload.
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
public class PlanetLabHelper {

	/**
	 * Creates the cloudlet list planet lab.
	 * 
	 * @param brokerId the broker id
	 * @param inputFolderName the input folder name
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 */
	public static List<CTask> createCloudletListPlanetLab(int brokerId, String inputFolderName)
			throws FileNotFoundException {
		List<CTask> list = new ArrayList<CTask>();

		long fileSize = 300;
		long outputSize = 300;
		UtilizationModel utilizationModelNull = new UtilizationModelNull();

		File inputFolder = new File(inputFolderName);
		File[] files = inputFolder.listFiles();

		for (int i = 0; i < files.length; i++) {
			CTask CTask = null;
			try {
				CTask = new CTask(
						i,
						Constants.CLOUDLET_LENGTH,
						Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						new UtilizationModelPlanetLabInMemory(
								files[i].getAbsolutePath(),
								Constants.SCHEDULING_INTERVAL), utilizationModelNull, utilizationModelNull);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			CTask.setUserId(brokerId);
			CTask.setVmId(i);
			list.add(CTask);
		}

		return list;
	}

}
