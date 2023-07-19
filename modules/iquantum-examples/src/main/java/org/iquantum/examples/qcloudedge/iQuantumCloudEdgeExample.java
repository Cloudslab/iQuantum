/**
 * iQuantum Cloud Edge Example
 * This example demonstrates the use of iQuantum in a hybrid cloud-edge environment.
 * It creates a CEDatacenter, a CCDatacenter, a QEDatacenter, and a QDatacenter, and then creates all respective brokers
 * and gateways for the cloud and edge environments.
 * It also creates a CTask and a QTask to be submitted to the CCloudBroker and QCloudBroker respectively.
 * Finally, it starts the simulation and prints the results.
 */

package org.iquantum.examples.qcloudedge;

import org.iquantum.backends.classical.Host;
import org.iquantum.backends.classical.Pe;
import org.iquantum.backends.classical.Storage;
import org.iquantum.backends.classical.Vm;
import org.iquantum.backends.quantum.IBMQNode;
import org.iquantum.backends.quantum.QNode;
import org.iquantum.backends.quantum.qubittopologies.QubitTopology;
import org.iquantum.brokers.*;
import org.iquantum.core.iQuantum;
import org.iquantum.datacenters.*;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.gateways.EdgeGateway;
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

public class iQuantumCloudEdgeExample {
    /** The CTask list. */
    private static List<CTask> CTaskList;
    /** The vmlist. */
    private static List<Vm> vmclist;
    private static List<Vm> vmelist;
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
        CCloudDatacenter CCDatacenter = createCDatacenter("CCloudDatacenter");
        CEdgeDatacenter CEDatacenter = createEDatacenter("CEdgeDatacenter");

        QCloudDatacenter QCDatacenter = createQCDatacenter("QCloudDatacenter");
        QEdgeDatacenter QEDatacenter = createQEDatacenter("QEdgeDatacenter");

        // Step 3: Create a CBroker and a QBroker
        CCloudBroker ccBroker = createBroker();
        int ccBrokerId = ccBroker.getId();
        CEdgeBroker ceBroker = createEBroker();
        int ceBrokerId = ceBroker.getId();

        QCloudBroker qcBroker = createQCBroker();
        QEdgeBroker qeBroker = createQEBroker();

        CloudGateway cloudGateway = new CloudGateway("CloudGateway", ccBroker, qcBroker);
        EdgeGateway edgeGateway = new EdgeGateway("EdgeGateway", ceBroker, qeBroker);

        // Step 4: Create a VM and submit to CDatacenter and EDatacenter
        vmclist = new ArrayList<Vm>();
        vmelist = new ArrayList<Vm>();
        // VM description
        int vmid = 0;
        int mips = 1000;
        long size = 10000; // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name

        // create 2 Vms (vmc for cloud and vme for edge)
        Vm vmc = new Vm(0, ccBrokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());
        Vm vme = new Vm(1, ceBrokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());

        // add the VM to the vmLists
        vmclist.add(vmc);
        vmelist.add(vme);

        // submit vm list to the broker
        ccBroker.submitVmList(vmclist);
        ceBroker.submitVmList(vmelist);

        // Step 5: Create two CTasks
        CTaskList = new ArrayList<CTask>();

        // CTasks properties
        int id = 0;
        long length = 400000;
        long fileSize = 300;
        long outputSize = 300;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        CTask CTask =
                new CTask(id, length, pesNumber, fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        CTask.setUserId(ceBrokerId);

        CTask CTask2 =
                new CTask(1, length, pesNumber, fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        CTask2.setUserId(ceBrokerId);

        // add CTasks to the list
        CTaskList.add(CTask);
        CTaskList.add(CTask2);

        // Step 6: Create 4 QTasks
        QTaskList = createQTaskList(QEDatacenter, qeBroker);

        // Step 7: Submit all tasks to brokers
        edgeGateway.submitTasks(CTaskList, QTaskList);

        // Step 8: Start the simulation
        iQuantum.startSimulation();

        // Step 9: Stop the simulation
        iQuantum.stopSimulation();

        // Step 10: Print the results when simulation is over
        List<CTask> cList = ceBroker.getCloudletReceivedList();
        printCloudletList(cList);

        List<QTask> qList = qeBroker.getQTaskReceivedList();
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
    private static CCloudDatacenter createCDatacenter(String name) {

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
        CCloudDatacenter ccDatacenter = null;
        try {
            ccDatacenter = new CCloudDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ccDatacenter;
    }

    private static CEdgeDatacenter createEDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 1000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(1, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 1;
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
        CEdgeDatacenter ceDatacenter = null;
        try {
            ceDatacenter = new CEdgeDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ceDatacenter;
    }

    // We strongly encourage users to develop their own broker policies, to
    // submit vms and CTasks according
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

    private static CEdgeBroker createEBroker() {
        CEdgeBroker broker = null;
        try {
            broker = new CEdgeBroker("CEBroker");
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
    private static QCloudBroker createQCBroker() {
        QCloudBroker qBroker = null;
        try {
            qBroker = new QCloudBroker("QCBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return qBroker;
    }

    private static QEdgeBroker createQEBroker() {
        QEdgeBroker qBroker = null;
        try {
            qBroker = new QEdgeBroker("QEBroker");
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
    private static QCloudDatacenter createQCDatacenter(String name) {
        QNode qNode1 = IBMQNode.createNode(1,"ibm_hanoi",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1));
        double timeZone = 0.0;
        double costPerSec = 1.6;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QCloudDatacenter qDatacenter = new QCloudDatacenter(name, characteristics);
        return qDatacenter;
    }

    private static QEdgeDatacenter createQEDatacenter(String name) {
        QNode qNode1 = IBMQNode.createNode(0,"ibm_oslo",new QTaskSchedulerSpaceShared());
        qNodeList = new ArrayList<>();
        qNodeList.addAll(Arrays.asList(qNode1));
        double timeZone = 0.0;
        double costPerSec = 1.6;

        // Create a QDatacenter with two 7-qubit quantum nodes (IBM Hanoi and IBM Geneva)
        QDatacenterCharacteristics characteristics = new QDatacenterCharacteristics(qNodeList, timeZone, costPerSec);
        QEdgeDatacenter qDatacenter = new QEdgeDatacenter(name, characteristics);
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

