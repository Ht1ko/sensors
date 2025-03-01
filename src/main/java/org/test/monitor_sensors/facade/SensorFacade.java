package org.test.monitor_sensors.facade;

import org.springframework.http.ResponseEntity;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;

import java.util.UUID;

public interface SensorFacade {
    ResponseEntity<SensorDto> createSensor(SensorDto sensorDto);

    ResponseEntity<Void> deleteSensor(UUID sensorId);

    ResponseEntity<SensorDto> getSensorById(UUID sensorId);

    ResponseEntity<SensorDto> updateSensor(SensorDto sensorDto);

    ResponseEntity<FindSensorDto> findSensors(String name, String model,Integer page);
}
