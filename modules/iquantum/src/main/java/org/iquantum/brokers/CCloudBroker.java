package org.iquantum.brokers;

import org.iquantum.core.SimEntity;
import org.iquantum.core.SimEvent;
import org.iquantum.core.iQuantum;
import org.iquantum.core.iQuantumTags;
import org.iquantum.datacenters.CCloudDatacenter;
import org.iquantum.datacenters.CDatacenterCharacteristics;
import org.iquantum.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classical Cloud Broker
 */
public class CCloudBroker extends CBroker{
    /**
     * Created a new DatacenterBroker object.
     *
     * @param name name to be associated with this entity (as required by {@link SimEntity} class)
     * @throws Exception the exception
     * @pre name != null
     * @post $none
     */
    public CCloudBroker(String name) throws Exception {
        super(name);
    }
    @Override
    protected void processResourceCharacteristicsRequest(SimEvent ev) {
        List<Integer> datacenterIdsList = iQuantum.getCloudResourceList();
        List<Integer> cloudDatacenterIdsList = new ArrayList<>();
        assert datacenterIdsList != null;
        for (Integer datacenterId : datacenterIdsList) {
            if (iQuantum.getEntity(datacenterId) instanceof CCloudDatacenter) {
                cloudDatacenterIdsList.add(datacenterId);
            }
        }
        setDatacenterIdsList(cloudDatacenterIdsList);
        setDatacenterCharacteristicsList(new HashMap<Integer, CDatacenterCharacteristics>());

        Log.printConcatLine(iQuantum.clock(), ": ", getName(), ": Cloud Resource List received with ",
                getDatacenterIdsList().size(), " resource(s)");

        for (Integer datacenterId : getDatacenterIdsList()) {
            sendNow(datacenterId, iQuantumTags.RESOURCE_CHARACTERISTICS, getId());
        }
    }
}
