package org.iquantum.backends.classical.container.lists;

import org.iquantum.backends.classical.container.core.Container;
import org.iquantum.core.iQuantum;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sareh on 31/07/15.
 */
public class PowerContainerList {


    /**
     * Sort by cpu utilization.
     *
     * @param containerList the vm list
     */
    public static <T extends Container> void sortByCpuUtilization(List<T> containerList) {
        Collections.sort(containerList, new Comparator<T>() {

            @Override
            public int compare(T a, T b) throws ClassCastException {
                Double aUtilization = a.getTotalUtilizationOfCpuMips(iQuantum.clock());
                Double bUtilization = b.getTotalUtilizationOfCpuMips(iQuantum.clock());
                return bUtilization.compareTo(aUtilization);
            }
        });
    }

}
