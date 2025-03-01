package org.test.monitor_sensors.service;

import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;

import java.util.Optional;
import java.util.UUID;

public interface SensorService {
    SensorDto createSensor(SensorDto sensorDto);

    Optional<SensorDto> updateSensor(SensorDto sensorDto);

    void deleteSensor(UUID sensorId);

    Optional<SensorDto> getSensorById(UUID sensorId);

    FindSensorDto findSensors(String name, String model, Integer page);
}
