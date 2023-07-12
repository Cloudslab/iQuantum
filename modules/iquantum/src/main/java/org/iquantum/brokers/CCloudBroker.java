package org.iquantum.brokers;

import org.iquantum.core.SimEntity;

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
}
