package org.iquantum.brokers;

import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.datacenters.QDatacenterCharacteristics;
import org.iquantum.datacenters.QEdgeDatacenter;
import org.iquantum.gateways.CloudGateway;
import org.iquantum.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Quantum Edge Broker
 */
public class QEdgeBroker extends QBroker{

    public QEdgeBroker(String name) throws Exception {
        super(name);
    }

    @Override
    protected void processQResourceCharacteristicsRequest(SimEvent ev) {
        List<Integer> qdatacenterIdsList = iQuantum.getQuantumResourceList();
        List<Integer> qcloudDatacenterIdsList = new ArrayList<>();
        assert qdatacenterIdsList != null;
        for (Integer qdatacenterId : qdatacenterIdsList) {
            if (iQuantum.getEntity(qdatacenterId) instanceof QEdgeDatacenter) {
                qcloudDatacenterIdsList.add(qdatacenterId);
            }
        }
        setDatacenterIdsList(qcloudDatacenterIdsList);
        setDatacenterCharacteristicsList(new HashMap<Integer, QDatacenterCharacteristics>());

        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Quantum Edge Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.QRESOURCE_CHARACTERISTICS, getId());
        }
    }
}
