package org.iquantum.backends.classical.container.resourceAllocators;

import org.iquantum.backends.classical.container.containerPlacementPolicies.ContainerPlacementPolicy;
import org.iquantum.backends.classical.container.core.Container;
import org.iquantum.backends.classical.container.core.ContainerVm;

import java.util.*;

/**
 * Created by sareh on 16/12/15.
 */
public class ContainerAllocationPolicyRS extends  PowerContainerAllocationPolicySimple{
    /** The vm table. */


    private ContainerPlacementPolicy containerPlacementPolicy;


    public ContainerAllocationPolicyRS(ContainerPlacementPolicy containerPlacementPolicy1) {
        super();
        setContainerPlacementPolicy(containerPlacementPolicy1);
    }


    @Override
    public ContainerVm findVmForContainer(Container container) {

        Set<ContainerVm> excludedVmList = new HashSet<>();
        int tries = 0;
        boolean found = false;
        do{

            ContainerVm containerVm = getContainerPlacementPolicy().getContainerVm(getContainerVmList(), container,excludedVmList);
            if(containerVm == null){

                return null;
            }
            if (containerVm.isSuitableForContainer(container)) {
                found = true;
                return containerVm;
            }
            else {
                    excludedVmList.add(containerVm);
                    tries ++;
                }

            } while (!found & tries < getContainerVmList().size());

        return null;
    }



    public ContainerPlacementPolicy getContainerPlacementPolicy() {
        return this.containerPlacementPolicy;
    }

    public void setContainerPlacementPolicy(ContainerPlacementPolicy containerPlacementPolicy) {
        this.containerPlacementPolicy = containerPlacementPolicy;
    }


}
