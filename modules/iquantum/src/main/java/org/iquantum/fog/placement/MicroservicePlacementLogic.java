package org.iquantum.fog.placement;

import org.iquantum.fog.application.Application;
import org.iquantum.fog.entities.FogDevice;
import org.iquantum.fog.entities.PlacementRequest;
import org.iquantum.fog.application.Application;

import java.util.List;
import java.util.Map;

/**
 * Created by Samodha Pallewatta
 */
public interface MicroservicePlacementLogic {
    PlacementLogicOutput run(List<FogDevice> fogDevices, Map<String, Application> applicationInfo, Map<Integer, Map<String, Double>> resourceAvailability, List<PlacementRequest> pr);
    void updateResources(Map<Integer, Map<String, Double>> resourceAvailability);
    void postProcessing();
}
