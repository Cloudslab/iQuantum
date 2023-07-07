package org.iquantum.backends.classical.container.resourceAllocators;

import org.iquantum.backends.classical.container.core.Container;

import java.util.List;
import java.util.Map;

/**
 * Created by sareh on 16/07/15.
 */
public class PowerContainerAllocationPolicySimple extends PowerContainerAllocationPolicy {


    public PowerContainerAllocationPolicySimple() {
        super();
    }

    @Override
    public List<Map<String, Object>> optimizeAllocation(List<? extends Container> containerList) {
        return null;
    }
}
