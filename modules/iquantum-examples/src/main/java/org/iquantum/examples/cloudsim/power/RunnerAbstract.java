package org.iquantum.examples.cloudsim.power;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.iquantum.tasks.CTask;
import org.iquantum.brokers.CBroker;
import org.iquantum.backends.classical.vm.Vm;
import org.iquantum.policies.vm.VmAllocationPolicy;
import org.iquantum.core.iQuantum;
import org.iquantum.power.PowerCDatacenter;
import org.iquantum.power.PowerHost;
import org.iquantum.power.PowerVmAllocationPolicyMigrationAbstract;
import org.iquantum.power.PowerVmAllocationPolicyMigrationInterQuartileRange;
import org.iquantum.power.PowerVmAllocationPolicyMigrationLocalRegression;
import org.iquantum.power.PowerVmAllocationPolicyMigrationLocalRegressionRobust;
import org.iquantum.power.PowerVmAllocationPolicyMigrationMedianAbsoluteDeviation;
import org.iquantum.power.PowerVmAllocationPolicyMigrationStaticThreshold;
import org.iquantum.power.PowerVmAllocationPolicySimple;
import org.iquantum.power.PowerVmSelectionPolicy;
import org.iquantum.power.PowerVmSelectionPolicyMaximumCorrelation;
import org.iquantum.power.PowerVmSelectionPolicyMinimumMigrationTime;
import org.iquantum.power.PowerVmSelectionPolicyMinimumUtilization;
import org.iquantum.power.PowerVmSelectionPolicyRandomSelection;
import org.iquantum.utils.Log;
/**
 * The Class RunnerAbstract.
 * 
 * If you are using any algorithms, policies or workload included in the power package, please cite
 * the following paper:
 * 
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley & Sons, Ltd, New York, USA, 2012
 * 
 * @author Anton Beloglazov
 */
public abstract class RunnerAbstract {

	/** The enable output. */
	private static boolean enableOutput;

	/** The broker. */
	protected static CBroker broker;

	/** The cloudlet list. */
	protected static List<CTask> CTaskList;

	/** The vm list. */
	protected static List<Vm> vmList;

	/** The host list. */
	protected static List<PowerHost> hostList;

	/**
	 * Run.
	 * 
	 * @param enableOutput the enable output
	 * @param outputToFile the output to file
	 * @param inputFolder the input folder
	 * @param outputFolder the output folder
	 * @param workload the workload
	 * @param vmAllocationPolicy the vm allocation policy
	 * @param vmSelectionPolicy the vm selection policy
	 * @param parameter the parameter
	 */
	public RunnerAbstract(
			boolean enableOutput,
			boolean outputToFile,
			String inputFolder,
			String outputFolder,
			String workload,
			String vmAllocationPolicy,
			String vmSelectionPolicy,
			String parameter) {
		try {
			initLogOutput(
					enableOutput,
					outputToFile,
					outputFolder,
					workload,
					vmAllocationPolicy,
					vmSelectionPolicy,
					parameter);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		init(inputFolder + "/" + workload);
		start(
				getExperimentName(workload, vmAllocationPolicy, vmSelectionPolicy, parameter),
				outputFolder,
				getVmAllocationPolicy(vmAllocationPolicy, vmSelectionPolicy, parameter));
	}

	/**
	 * Inits the log output.
	 * 
	 * @param enableOutput the enable output
	 * @param outputToFile the output to file
	 * @param outputFolder the output folder
	 * @param workload the workload
	 * @param vmAllocationPolicy the vm allocation policy
	 * @param vmSelectionPolicy the vm selection policy
	 * @param parameter the parameter
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 */
	protected void initLogOutput(
			boolean enableOutput,
			boolean outputToFile,
			String outputFolder,
			String workload,
			String vmAllocationPolicy,
			String vmSelectionPolicy,
			String parameter) throws IOException, FileNotFoundException {
		setEnableOutput(enableOutput);
		Log.setDisabled(!isEnableOutput());
		if (isEnableOutput() && outputToFile) {
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			File folder2 = new File(outputFolder + "/log");
			if (!folder2.exists()) {
				folder2.mkdir();
			}

			File file = new File(outputFolder + "/log/"
					+ getExperimentName(workload, vmAllocationPolicy, vmSelectionPolicy, parameter) + ".txt");
			file.createNewFile();
			Log.setOutput(new FileOutputStream(file));
		}
	}

	/**
	 * Inits the simulation.
	 * 
	 * @param inputFolder the input folder
	 */
	protected abstract void init(String inputFolder);

	/**
	 * Starts the simulation.
	 * 
	 * @param experimentName the experiment name
	 * @param outputFolder the output folder
	 * @param vmAllocationPolicy the vm allocation policy
	 */
	protected void start(String experimentName, String outputFolder, VmAllocationPolicy vmAllocationPolicy) {
		System.out.println("Starting " + experimentName);

		try {
			PowerCDatacenter datacenter = (PowerCDatacenter) Helper.createDatacenter(
					"Datacenter",
					PowerCDatacenter.class,
					hostList,
					vmAllocationPolicy);

			datacenter.setDisableMigrations(false);

			broker.submitVmList(vmList);
			broker.submitCloudletList(CTaskList);

			iQuantum.terminateSimulation(Constants.SIMULATION_LIMIT);
			double lastClock = iQuantum.startSimulation();

			List<CTask> newList = broker.getCloudletReceivedList();
			Log.printLine("Received " + newList.size() + " cloudlets");

			iQuantum.stopSimulation();

			Helper.printResults(
					datacenter,
					vmList,
					lastClock,
					experimentName,
					Constants.OUTPUT_CSV,
					outputFolder);

		} catch (Exception e) {
			e.printStackTrace();
			Log.printLine("The simulation has been terminated due to an unexpected error");
			System.exit(0);
		}

		Log.printLine("Finished " + experimentName);
	}

	/**
	 * Gets the experiment name.
	 * 
	 * @param args the args
	 * @return the experiment name
	 */
	protected String getExperimentName(String... args) {
		StringBuilder experimentName = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			if (args[i].isEmpty()) {
				continue;
			}
			if (i != 0) {
				experimentName.append("_");
			}
			experimentName.append(args[i]);
		}
		return experimentName.toString();
	}

	/**
	 * Gets the vm allocation policy.
	 * 
	 * @param vmAllocationPolicyName the vm allocation policy name
	 * @param vmSelectionPolicyName the vm selection policy name
	 * @param parameterName the parameter name
	 * @return the vm allocation policy
	 */
	protected VmAllocationPolicy getVmAllocationPolicy(
			String vmAllocationPolicyName,
			String vmSelectionPolicyName,
			String parameterName) {
		VmAllocationPolicy vmAllocationPolicy = null;
		PowerVmSelectionPolicy vmSelectionPolicy = null;
		if (!vmSelectionPolicyName.isEmpty()) {
			vmSelectionPolicy = getVmSelectionPolicy(vmSelectionPolicyName);
		}
		double parameter = 0;
		if (!parameterName.isEmpty()) {
			parameter = Double.valueOf(parameterName);
		}
		if (vmAllocationPolicyName.equals("iqr")) {
			PowerVmAllocationPolicyMigrationAbstract fallbackVmSelectionPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					0.7);
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationInterQuartileRange(
					hostList,
					vmSelectionPolicy,
					parameter,
					fallbackVmSelectionPolicy);
		} else if (vmAllocationPolicyName.equals("mad")) {
			PowerVmAllocationPolicyMigrationAbstract fallbackVmSelectionPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					0.7);
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationMedianAbsoluteDeviation(
					hostList,
					vmSelectionPolicy,
					parameter,
					fallbackVmSelectionPolicy);
		} else if (vmAllocationPolicyName.equals("lr")) {
			PowerVmAllocationPolicyMigrationAbstract fallbackVmSelectionPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					0.7);
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationLocalRegression(
					hostList,
					vmSelectionPolicy,
					parameter,
					Constants.SCHEDULING_INTERVAL,
					fallbackVmSelectionPolicy);
		} else if (vmAllocationPolicyName.equals("lrr")) {
			PowerVmAllocationPolicyMigrationAbstract fallbackVmSelectionPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					0.7);
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationLocalRegressionRobust(
					hostList,
					vmSelectionPolicy,
					parameter,
					Constants.SCHEDULING_INTERVAL,
					fallbackVmSelectionPolicy);
		} else if (vmAllocationPolicyName.equals("thr")) {
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					parameter);
		} else if (vmAllocationPolicyName.equals("dvfs")) {
			vmAllocationPolicy = new PowerVmAllocationPolicySimple(hostList);
		} else {
			System.out.println("Unknown VM allocation policy: " + vmAllocationPolicyName);
			System.exit(0);
		}
		return vmAllocationPolicy;
	}

	/**
	 * Gets the vm selection policy.
	 * 
	 * @param vmSelectionPolicyName the vm selection policy name
	 * @return the vm selection policy
	 */
	protected PowerVmSelectionPolicy getVmSelectionPolicy(String vmSelectionPolicyName) {
		PowerVmSelectionPolicy vmSelectionPolicy = null;
		if (vmSelectionPolicyName.equals("mc")) {
			vmSelectionPolicy = new PowerVmSelectionPolicyMaximumCorrelation(
					new PowerVmSelectionPolicyMinimumMigrationTime());
		} else if (vmSelectionPolicyName.equals("mmt")) {
			vmSelectionPolicy = new PowerVmSelectionPolicyMinimumMigrationTime();
		} else if (vmSelectionPolicyName.equals("mu")) {
			vmSelectionPolicy = new PowerVmSelectionPolicyMinimumUtilization();
		} else if (vmSelectionPolicyName.equals("rs")) {
			vmSelectionPolicy = new PowerVmSelectionPolicyRandomSelection();
		} else {
			System.out.println("Unknown VM selection policy: " + vmSelectionPolicyName);
			System.exit(0);
		}
		return vmSelectionPolicy;
	}

	/**
	 * Sets the enable output.
	 * 
	 * @param enableOutput the new enable output
	 */
	public void setEnableOutput(boolean enableOutput) {
		RunnerAbstract.enableOutput = enableOutput;
	}

	/**
	 * Checks if is enable output.
	 * 
	 * @return true, if is enable output
	 */
	public boolean isEnableOutput() {
		return enableOutput;
	}

}
