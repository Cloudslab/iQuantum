package org.iquantum.backends.classical.container.resourceAllocators;


import org.iquantum.backends.classical.container.core.ContainerHost;
import org.iquantum.backends.classical.container.core.ContainerVm;
import org.iquantum.core.iQuantum;
import org.iquantum.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by sareh on 14/07/15.
 */
public abstract  class PowerContainerVmAllocationAbstract extends ContainerVmAllocationPolicy{

        /** The vm table. */
        private final Map<String, ContainerHost> vmTable = new HashMap<String, ContainerHost>();

        /**
         * Instantiates a new power vm allocation policy abstract.
         *
         * @param list the list
         */
        public PowerContainerVmAllocationAbstract(List<? extends ContainerHost> list) {
            super(list);
        }

        /*
         * (non-Javadoc)
         * @see org.iquantum.policies.vm.VmAllocationPolicy#allocateHostForVm(org.iquantum.backends.classical.vm.Vm)
         */
        @Override
        public boolean allocateHostForVm(ContainerVm containerVm) {
            return allocateHostForVm(containerVm, findHostForVm(containerVm));
        }

        /*
         * (non-Javadoc)
         * @see org.iquantum.policies.vm.VmAllocationPolicy#allocateHostForVm(org.iquantum.backends.classical.vm.Vm,
         * org.iquantum.backends.classical.Host)
         */
        @Override
        public boolean allocateHostForVm(ContainerVm containerVm, ContainerHost host) {
            if (host == null) {
                Log.formatLine("%.2f: No suitable host found for VM #" + containerVm.getId() + "\n", iQuantum.clock());
                return false;
            }
            if (host.containerVmCreate(containerVm)) { // if vm has been succesfully created in the host
                getVmTable().put(containerVm.getUid(), host);
                Log.formatLine(
                        "%.2f: VM #" + containerVm.getId() + " has been allocated to the host #" + host.getId(),
                        iQuantum.clock());
                return true;
            }
            Log.formatLine(
                    "%.2f: Creation of VM #" + containerVm.getId() + " on the host #" + host.getId() + " failed\n",
                    iQuantum.clock());
            return false;
        }

        /**
         * Find host for vm.
         *
         * @param containerVm the vm
         * @return the power host
         */
        public ContainerHost findHostForVm(ContainerVm containerVm) {
            for (ContainerHost host : this.<ContainerHost> getContainerHostList()) {
                if (host.isSuitableForContainerVm(containerVm)) {
                    return host;
                }
            }
            return null;
        }

        /*
         * (non-Javadoc)
         * @see org.iquantum.policies.vm.VmAllocationPolicy#deallocateHostForVm(org.iquantum.backends.classical.vm.Vm)
         */
        @Override
        public void deallocateHostForVm(ContainerVm containerVm) {
            ContainerHost host = getVmTable().remove(containerVm.getUid());
            if (host != null) {
                host.containerVmDestroy(containerVm);
            }
        }

        /*
         * (non-Javadoc)
         * @see org.iquantum.policies.vm.VmAllocationPolicy#getHost(org.iquantum.backends.classical.vm.Vm)
         */
        @Override
        public ContainerHost getHost(ContainerVm vm) {
            return getVmTable().get(vm.getUid());
        }

        /*
         * (non-Javadoc)
         * @see org.iquantum.policies.vm.VmAllocationPolicy#getHost(int, int)
         */
        @Override
        public ContainerHost getHost(int vmId, int userId) {
            return getVmTable().get(ContainerVm.getUid(userId, vmId));
        }

        /**
         * Gets the vm table.
         *
         * @return the vm table
         */
        public Map<String, ContainerHost> getVmTable() {
            return vmTable;
        }

    public List<ContainerVm> getOverUtilizedVms() {
        List<ContainerVm> vmList = new ArrayList<ContainerVm>();
        for (ContainerHost host : getContainerHostList()) {
            for (ContainerVm vm : host.getVmList()) {
                if (vm.getTotalUtilizationOfCpuMips(iQuantum.clock()) > vm.getTotalMips()) {
                    vmList.add(vm);

                }

            }

        }
        return vmList;
    }


}