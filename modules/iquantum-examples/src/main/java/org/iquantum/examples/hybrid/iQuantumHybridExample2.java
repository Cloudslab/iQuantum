/**
 * iQuantum Hybrid Example
 * This example shows how to create a Classical Datacenter with a single host and VM (similar to CloudSimExample 1) and
 * a Quantum Datacenter with a 7-qubit quantum nodes following the topology of IBM Oslo automatically from the datasheet.
 * Then, it creates a CBroker, two classical tasks (CTask), a QBroker and four QTasks to
 * be submitted to these datacenter. Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.hybrid;

import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.backends.classical.Vm;
import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.brokers.CBroker;
import org.iquantum.brokers.CCloudBroker;
import org.iquantum.brokers.QBroker;
import org.iquantum.brokers.QCloudBroker;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.CDatacenter;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.datacenters.QDatacenter;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.models.UtilizationModel;
import org.iquantum.models.UtilizationModelFull;
import org.iquantum.policies.ctasks.CloudletSchedulerSpaceShared;
import org.iquantum.policies.qtasks.QTaskSchedulerSpaceShared;
import org.iquantum.policies.vm.VmAllocationPolicySimple;
import org.iquantum.policies.vm.VmSchedulerTimeShared;
import org.iquantum.provisioners.BwProvisionerSimple;
import org.iquantum.provisioners.PeProvisionerSimple;
import org.iquantum.provisioners.RamProvisionerSimple;
import org.iquantum.tasks.CTask;
import org.iquantum.tasks.QTask;
import org.iquantum.utils.Log;

import java.text.DecimalFormat;
import java.util.*;

public class iQuantumHybridExample2 {
    /** The cloudlet list. */
    private static List<CTask> CTaskList;
    /** The vmlist. */
    private static List<Vm> vmlist;
    private static List<QTask> QTaskList;

    private static  List<QNode> qNodeList;

    public static void main(String[] args) throws Exception {
        System.out.println("Start the iQuantum Hybrid Example");

        // Step 1: Initialize the core simulation package. It should be called before creating any entities.
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = true;  // trace events
        iQuantum.init(num_user, calendar, trace_flag);

        // Step 2: Create a CDatacenter and a QDatacenter
        CDatacenter CDatacenter0 = createDatacenter("CDatacenter_0");

        QDatacenter qDatacenter = createQDatacenter("QDatacenter_0");


        // Step 3: Create a CBroker and a QBroker
        CCloudBroker cBroker = createBroker();
        int brokerId = cBroker.getId();
        QCloudBroker qBroker = createQBroker();
        CloudGateway cloudGateway = new CloudGateway("CloudGateway", cBroker, qBroker);

        // Step 4: Create a VM and submit to CDatacenter
        vmlist = new ArrayList<Vm>();

        // VM description
        int vmid = 0;
        int mips = 1000;
        long size = 10000; // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name

        // create VM
        Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());

        // add the VM to the vmList
        vmlist.add(vm);

        // submit vm list to the broker
        cBroker.submitVmList(vmlist);

        // Step 5: Create two CTasks
        CTaskList = new ArrayList<CTask>();

        // Cloudlet properties
        int id = 0;
        long length = 400000;
        long fileSize = 300;
        long outputSize = 300;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        CTask CTask =
                new CTask(id, length, pesNumber, fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        CTask.setUserId(brokerId);
        CTask.setVmId(vmid);

        CTask CTask2 =
                new CTask(1, length, pesNumber, fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        CTask2.setUserId(brokerId);

        // add CTasks to the list
        CTaskList.add(CTask);
        CTaskList.add(CTask2);

        // Step 6: Create 4 QTasks
        QTaskList = createQTaskList(qDatacenter, qBroker);

        // Step 7: Submit all tasks to brokers
        cloudGateway.submitTasks(CTaskList, QTaskList);
//        cBroker.submitCloudletList(CTaskList);
//        qBroker.submitQTaskList(QTaskList);

        // Step 8: Start the simulation
        iQuantum.startSimulation();

        // Step 9: Stop the simulation
        iQuantum.stopSimulation();

        // Step 10: Print the results when simulation is over
        List<CTask> cList = cBroker.getCloudletReceivedList();
        printCloudletList(cList);

        List<QTask> qList = qBroker.getQTaskReceivedList();
        printQTaskList(qList);

        Log.printLine("iQuantum Hybrid Example finished!");
    }

    /**
     * CLASSICAL PART
     */
    /**
     * Creates the datacenter.
     *
     * @param name the name
     *
     * @return the datacenter
     */
    private static CDatacenter createDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 1000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 0;
        int ram = 2048; // host memory (MB)
        long storage = 1000000; // host storage
        int bw = 10000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        ); // This is our machine

        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        String arch = "x86"; // system architecture
        String os = "Linux"; // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
        // devices by now

        CDatacenterCharacteristics characteristics = new CDatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        CDatacenter CDatacenter = null;
        try {
            CDatacenter = new CDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CDatacenter;
    }

    // We strongly encourage users to develop their own broker policies, to
    // submit vms and cloudlets according
    // to the specific rules of the simulated scenario
    /**
     * Creates the broker.
     *
     * @return the datacenter broker
     */
    private static CCloudBroker createBroker() {
        CCloudBroker broker = null;
        try {
            broker = new CCloudBroker("CCBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    /**
     * Prints the Cloudlet objects.
     *
     * @param list list of Cloudlets
     */
    private static void printCloudletList(List<CTask> list) {
        int size = list.size();
        CTask CTask;

        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("CTask ID" + indent + "Status" + indent
                + "CDCenter ID" + indent + "VM ID" + indent + indent + "Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            CTask = list.get(i);
            Log.print(indent + CTask.getCloudletId() + indent + indent);

            if (CTask.getCloudletStatus() == CTask.SUCCESS) {
                Log.print("SUCCESS");

                Log.printLine(indent + indent + CTask.getResourceId()
                        + indent + indent + indent + CTask.getVmId()
                        + indent + indent
                        + dft.format(CTask.getActualCPUTime()) + indent
                        + indent + dft.format(CTask.getExecStartTime())
                        + indent + indent
                        + dft.format(CTask.getFinishTime()));
            }
        }
    }

    /**
     * QUANTUM PART
     */

    private static List<QTask> createQTaskList(QDatacenter qDatacenter, QBroker qBroker) {
        List<QTask> QTaskList = new ArrayList<>();
        // Create QTask 1
        List<int[]> ql1Edges = new ArrayList<>();
        ql1Edges.add(new int[]{0, 1});
        ql1Edges.add(new int[]{1, 0});
        ql1Edges.add(new int[]{1, 2});
        ql1Edges.add(new int[]{1, 3});
        ql1Edges.add(new int[]{2, 1});
        ql1Edges.add(new int[]{3, 1});
        ql1Edges.add(new int[]{3, 4});
        ql1Edges.add(new int[]{4, 3});
        ql1Edges.add(new int[]{4, 5});
        ql1Edges.add(new int[]{5, 4});
        QubitTopology ql1Topology = new QubitTopology(6, ql1Edges);
        ArrayList<String> ql1Gates = new ArrayList<>(Arrays.asList("cx", "rz", "x"));
        QTask QTask1 = new QTask(1,4, 26, 3000, ql1Gates, ql1Topology);
        // Set QBroker for QTask
        QTask1.setBrokerId(qBroker.getId());
        // Set QNode for QTask
        QTask1.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Create QTask 2
        List<int[]> ql2Edges = new ArrayList<>();
        ql2Edges.add(new int[]{0, 1});
        ql2Edges.add(new int[]{1, 0});
        ql2Edges.add(new int[]{1, 2});
        ql2Edges.add(new int[]{2, 1});
        QubitTopology ql2Topology = new QubitTopology(3, ql2Edges);
        ArrayList<String> ql2Gates = new ArrayList<>(Arrays.asList("cx", "rz", "x","sx"));
        QTask QTask2 = new QTask(2,3, 29, 1000, ql2Gates, ql2Topology);
        // Set QBroker for QTask 2
        QTask2.setBrokerId(qBroker.getId());
        // Set QNode for QTask 2
        QTask2.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Create QTask 3
        QTask QTask3 = new QTask(3,3, 58, 4000, ql1Gates, ql2Topology);
        // Set QBroker for QTask 3
        QTask3.setBrokerId(qBroker.getId());
        // Set QNode for QTask 3
        QTask3.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Create QTask 4
        QTask QTask4 = new QTask(4,8, 78, 2000, ql1Gates, ql2Topology);
        // Set QBroker for QTask 4
        QTask4.setBrokerId(qBroker.getId());
        // Set QNode for QTask 4
        QTask4.setQNodeId(qDatacenter.getCharacteristics().getQNodeList().get(0).getId());

        // Add all QTasks to the list
        QTaskList.add(QTask1);
        QTaskList.add(QTask2);
        QTaskList.add(QTask3);
        QTaskList.add(QTask4);

        return QTaskList;
    }

    /**
     * Create a QBroker
     * @return QBroker
     */
    private static QCloudBroker createQBroker() {
        QCloudBroker qBroker = null;
        try {
            qBroker = new QCloudBroker("QCBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    /**
     * Create a QDatacenter with single quantum node (IBM Oslo)
     * @param name name of the QDatacenter
     * @return QDatacenter
     */
    private static QDatacenter createQDatacenter(String name) {
        // Automatically create two quantum nodes (IBM Hanoi and IBM Geneva) from the dataset
        QNode qNode1 = IBMQNode.createNode(0,"ibm_oslo",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1));
        double timeZone = 0.0;
        double costPerSec = 3.0;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QDatacenter qDatacenter = new QDatacenter(name, characteristics);
        return qDatacenter;
    }

    /**
     * Print the list of QTasks after the simulation
     * @param list list of QTasks
     */
    private static void printQTaskList(List<QTask> list) {
        int size = list.size();
        QTask QTask;

        String indent = "   ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("QTask ID" + indent + "Status" + indent
                + "QDCenter" + indent + "QNode ID" + indent + "Execution Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            QTask = list.get(i);
            Log.print(indent + QTask.getQTaskId() + indent + indent);
            if (QTask.getQTaskStatus() == QTask.SUCCESS) {
                Log.print("SUCCESS");
                Log.printLine(indent + indent + QTask.getResourceId()
                        + indent + indent + indent + QTask.getQNodeId()
                        + indent + indent + indent + dft.format(QTask.getActualQPUTime())
                        + indent + indent + indent + indent + dft.format(QTask.getExecStartTime())
                        + indent + indent + indent + dft.format(QTask.getFinishTime()));
            }
            else {
                Log.printLine(QTask.getQTaskStatusString());
            }
        }
    }

}

