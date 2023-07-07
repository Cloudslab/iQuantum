package org.iquantum.backends.classical.container.lists;

import org.iquantum.backends.classical.container.core.ContainerVm;
import org.iquantum.core.iQuantum;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sareh on 28/07/15.
 */
public class PowerContainerVmList extends ContainerVmList {

        /**
         * Sort by cpu utilization.
         *
         * @param vmList the vm list
         */
        public static <T extends ContainerVm> void sortByCpuUtilization(List<T> vmList) {
            Collections.sort(vmList, new Comparator<T>() {

                @Override
                public int compare(T a, T b) throws ClassCastException {
                    Double aUtilization = a.getTotalUtilizationOfCpuMips(iQuantum.clock());
                    Double bUtilization = b.getTotalUtilizationOfCpuMips(iQuantum.clock());
                    return bUtilization.compareTo(aUtilization);
                }
            });
        }

    }


