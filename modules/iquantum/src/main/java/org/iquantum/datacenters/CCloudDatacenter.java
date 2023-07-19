package org.iquantum.datacenters;

import org.iquantum.backends.classical.Storage;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.policies.vm.VmAllocationPolicy;
import org.iquantum.utils.Log;

import java.util.List;

public class CCloudDatacenter extends CDatacenter {

    public CCloudDatacenter(
            String name,
            CDatacenterCharacteristics characteristics,
            VmAllocationPolicy vmAllocationPolicy,
            List<Storage> storageList,
            double schedulingInterval) throws Exception {
        super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
    }
}
