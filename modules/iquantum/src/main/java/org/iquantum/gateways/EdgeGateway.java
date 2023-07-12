package org.iquantum.gateways;

import org.iquantum.brokers.CCloudBroker;
import org.iquantum.brokers.QCloudBroker;

/**
 * Edge Gateway
 */
public class EdgeGateway extends CloudGateway{
    public EdgeGateway(String name, CCloudBroker cBroker, QCloudBroker qBroker) throws Exception {
        super(name, cBroker, qBroker);
    }

    public void offloadTasks(CloudGateway cloudGateway) {
        cloudGateway.submitTasks(getCTaskList(), getQTaskList());
    }

}
