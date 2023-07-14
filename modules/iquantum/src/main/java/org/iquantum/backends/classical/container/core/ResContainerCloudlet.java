package org.iquantum.backends.classical.container.core;

import org.iquantum.tasks.CTask;
import org.iquantum.tasks.ResCTask;

/**
 * Created by sareh on 10/07/15.
 */
public class ResContainerCloudlet extends ResCTask {
    public ResContainerCloudlet(CTask CTask) {
        super(CTask);
    }

    public ResContainerCloudlet(CTask CTask, long startTime, int duration, int reservID) {
        super(CTask, startTime, duration, reservID);
    }


    public int getContainerId(){return((ContainerCTask)getCloudlet()).getContainerId();}
}
