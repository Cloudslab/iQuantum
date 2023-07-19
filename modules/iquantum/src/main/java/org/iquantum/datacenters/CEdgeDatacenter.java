package org.iquantum.datacenters;

import org.iquantum.backends.classical.Storage;
import org.iquantum.policies.vm.VmAllocationPolicy;

import java.util.List;

public class CEdgeDatacenter extends CDatacenter {
    public CEdgeDatacenter(
            String name,
            CDatacenterCharacteristics characteristics,
            VmAllocationPolicy vmAllocationPolicy,
            List<Storage> storageList,
            double schedulingInterval) throws Exception {
        super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
    }
}
