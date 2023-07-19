package org.iquantum.brokers;

import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.datacenters.CEdgeDatacenter;
import org.iquantum.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classical Edge Broker
 */
public class CEdgeBroker extends CBroker{
    public CEdgeBroker(String name) throws Exception {
        super(name);
    }

    @Override
    protected void processResourceCharacteristicsRequest(SimEvent ev) {
        List<Integer> datacenterIdsList = iQuantum.getCloudResourceList();
        List<Integer> edgeDatacenterIdsList = new ArrayList<>();
        assert datacenterIdsList != null;
        for (Integer datacenterId : datacenterIdsList) {
            if (iQuantum.getEntity(datacenterId) instanceof CEdgeDatacenter) {
                edgeDatacenterIdsList.add(datacenterId);
            }
        }
        setDatacenterIdsList(edgeDatacenterIdsList);
        setDatacenterCharacteristicsList(new HashMap<Integer, CDatacenterCharacteristics>());

        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cloud Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.RESOURCE_CHARACTERISTICS, getId());
        }
    }
}
