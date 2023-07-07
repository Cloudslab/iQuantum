package org.iquantum.backends.classical.container.vmSelectionPolicies;

import org.iquantum.backends.classical.container.core.ContainerVm;
import org.iquantum.backends.classical.container.core.PowerContainerHost;
import org.iquantum.backends.classical.container.core.PowerContainerVm;

import java.util.List;

/**
 * Created by sareh on 16/11/15.
 */
public class PowerContainerVmSelectionPolicyMaximumUsage extends PowerContainerVmSelectionPolicy {
    /*
     * (non-Javadoc)
     * @see
     * org.iquantum.experiments.power.PowerVmSelectionPolicy#getVmsToMigrate(org.cloudbus
     * .iQuantum.power.PowerHost)
     */
    @Override
    public ContainerVm getVmToMigrate(PowerContainerHost host) {
        List<PowerContainerVm> migratableContainers = getMigratableVms(host);
        if (migratableContainers.isEmpty()) {
            return null;
        }
        ContainerVm VmsToMigrate = null;
        double maxMetric = Double.MIN_VALUE;
        for (ContainerVm vm : migratableContainers) {
            if (vm.isInMigration()) {
                continue;
            }
            double metric = vm.getCurrentRequestedTotalMips();
            if (maxMetric < metric) {
                maxMetric = metric;
                VmsToMigrate = vm;
            }
        }
//        Log.formatLine("The Container To migrate is #%d from VmID %d from host %d", containerToMigrate.getId(),containerToMigrate.getVm().getId(), host.getId());
        return VmsToMigrate;
    }


}
