package org.iquantum.fog.placement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iquantum.core.iQuantum;
import org.iquantum.fog.application.AppEdge;
import org.iquantum.fog.application.AppModule;
import org.iquantum.fog.application.Application;
import org.iquantum.fog.entities.Actuator;
import org.iquantum.fog.entities.FogDevice;
import org.iquantum.fog.entities.Sensor;
import org.iquantum.fog.entities.Tuple;
import org.iquantum.fog.application.AppEdge;
import org.iquantum.fog.application.AppModule;
import org.iquantum.fog.application.Application;

public class ModulePlacementOnlyCloud extends ModulePlacement{
	
	private List<Sensor> sensors;
	private List<Actuator> actuators;
	private int cloudId;
	
	public ModulePlacementOnlyCloud(List<FogDevice> fogDevices, List<Sensor> sensors, List<Actuator> actuators, Application application){
		this.setFogDevices(fogDevices);
		this.setApplication(application);
		this.setSensors(sensors);
		this.setActuators(actuators);
		this.setModuleToDeviceMap(new HashMap<String, List<Integer>>());
		this.setDeviceToModuleMap(new HashMap<Integer, List<AppModule>>());
		this.setModuleInstanceCountMap(new HashMap<Integer, Map<String, Integer>>());
		this.cloudId = iQuantum.getEntityId("cloud");
		mapModules();
		computeModuleInstanceCounts();
	}
	
	private void computeModuleInstanceCounts(){
		FogDevice cloud = getDeviceById(iQuantum.getEntityId("cloud"));
		getModuleInstanceCountMap().put(cloud.getId(), new HashMap<String, Integer>());
		
		for(Sensor sensor : getSensors()){
			String sensorType = sensor.getSensorName();
			if(!getModuleInstanceCountMap().get(cloud.getId()).containsKey(sensorType))
				getModuleInstanceCountMap().get(cloud.getId()).put(sensorType, 0);
			getModuleInstanceCountMap().get(cloud.getId()).put(sensorType, getModuleInstanceCountMap().get(cloud.getId()).get(sensorType)+1);
		}
		
		for(Actuator actuator : getActuators()){
			String actuatorType = actuator.getActuatorType();
			if(!getModuleInstanceCountMap().get(cloud.getId()).containsKey(actuatorType))
				getModuleInstanceCountMap().get(cloud.getId()).put(actuatorType, 0);
			getModuleInstanceCountMap().get(cloud.getId()).put(actuatorType, getModuleInstanceCountMap().get(cloud.getId()).get(actuatorType)+1);
		}
		
		while(!isModuleInstanceCalculationComplete()){
			for(AppModule module : getApplication().getModules()){
				int maxInstances = 0;
				for(AppEdge edge : getApplication().getEdges()){
					if(!getModuleInstanceCountMap().get(cloudId).containsKey(edge.getSource()))
						continue;
					if(edge.getDestination().equals(module.getName()) && edge.getDirection()==Tuple.UP){
						maxInstances = Math.max(maxInstances, getModuleInstanceCountMap().get(cloudId).get(edge.getSource()));
					}
				}
				getModuleInstanceCountMap().get(cloudId).put(module.getName(), maxInstances);
			}
		}
		System.out.println(getModuleInstanceCountMap());
	}

	private boolean isModuleInstanceCalculationComplete() {
		for(AppModule module : getApplication().getModules()){
			if(!getModuleInstanceCountMap().get(cloudId).containsKey(module.getName()))
				return false;
		}
		return true;
	}

	@Override
	protected void mapModules() {
		List<AppModule> modules = getApplication().getModules();
		for(AppModule module : modules){
			FogDevice cloud = getDeviceById(cloudId);
			createModuleInstanceOnDevice(module, cloud);
		}
	}

	public List<Actuator> getActuators() {
		return actuators;
	}

	public void setActuators(List<Actuator> actuators) {
		this.actuators = actuators;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
}
