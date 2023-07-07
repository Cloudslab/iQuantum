package org.iquantum.backends.classical.container.hostSelectionPolicies;

import org.iquantum.backends.classical.container.core.ContainerHost;
import org.iquantum.backends.classical.container.core.PowerContainerHostUtilizationHistory;
import org.iquantum.core.iQuantum;

import java.util.List;
import java.util.Set;

/**
 * Created by sareh on 11/08/15.
 */
public class HostSelectionPolicyMostFull extends HostSelectionPolicy {

    @Override
    public ContainerHost getHost(List<ContainerHost> hostList, Object obj,Set<? extends ContainerHost> excludedHostList) {
        ContainerHost selectedHost = null;
        if(iQuantum.clock() >1.0){
        double maxUsage = Double.MIN_VALUE;
        for (ContainerHost host : hostList) {
            if (excludedHostList.contains(host)) {
                continue;
            }

            if (host instanceof PowerContainerHostUtilizationHistory) {
                double hostUtilization= ((PowerContainerHostUtilizationHistory) host).getUtilizationOfCpu();
                if (hostUtilization > maxUsage) {
                    maxUsage = hostUtilization;
                    selectedHost = host;

                }


            }
        }

        return selectedHost;
    }else {

//            At the simulation start all the VMs by leastFull algorithms.

            selectedHost = new HostSelectionPolicyFirstFit().getHost(hostList,obj ,excludedHostList);

            return selectedHost;
        }



    }


}
