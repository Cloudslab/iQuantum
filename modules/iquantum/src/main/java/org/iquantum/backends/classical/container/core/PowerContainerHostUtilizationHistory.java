package org.iquantum.backends.classical.container.core;

import org.iquantum.backends.classical.container.containerVmProvisioners.ContainerVmBwProvisioner;
import org.iquantum.backends.classical.container.containerVmProvisioners.ContainerVmPe;
import org.iquantum.backends.classical.container.containerVmProvisioners.ContainerVmRamProvisioner;
import org.iquantum.backends.classical.container.schedulers.ContainerVmScheduler;
import org.iquantum.power.models.PowerModel;
import org.iquantum.utils.MathUtil;

import java.util.List;

/**
 * Created by sareh on 15/07/15.
 */
public class PowerContainerHostUtilizationHistory extends PowerContainerHost {

    /**
     * Instantiates a new power host utilization history.
     *
     * @param id             the id
     * @param ramProvisioner the ram provisioner
     * @param bwProvisioner  the bw provisioner
     * @param storage        the storage
     * @param peList         the pe list
     * @param vmScheduler    the vm scheduler
     * @param powerModel     the power model
     */
    public PowerContainerHostUtilizationHistory(
            int id,
            ContainerVmRamProvisioner ramProvisioner,
            ContainerVmBwProvisioner bwProvisioner,
            long storage,
            List<? extends ContainerVmPe> peList,
            ContainerVmScheduler vmScheduler,
            PowerModel powerModel) {
        super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler, powerModel);
    }

    /**
     * Gets the host utilization history.
     *
     * @return the host utilization history
     */
    public double[] getUtilizationHistory() {
        double[] utilizationHistory = new double[PowerContainerVm.HISTORY_LENGTH];
        double hostMips = getTotalMips();
        for (PowerContainerVm vm : this.<PowerContainerVm>getVmList()) {
            for (int i = 0; i < vm.getUtilizationHistory().size(); i++) {
                utilizationHistory[i] += vm.getUtilizationHistory().get(i) * vm.getMips() / hostMips;
            }
        }
        return MathUtil.trimZeroTail(utilizationHistory);
    }

}

