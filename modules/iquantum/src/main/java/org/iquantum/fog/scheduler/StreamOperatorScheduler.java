package org.iquantum.fog.scheduler;

import java.util.List;

import org.iquantum.backends.classical.Pe;
import org.iquantum.sdn.overbooking.VmSchedulerTimeSharedOverbookingEnergy;

public class StreamOperatorScheduler extends VmSchedulerTimeSharedOverbookingEnergy{

	public StreamOperatorScheduler(List<? extends Pe> pelist) {
		super(pelist);
	}
}
