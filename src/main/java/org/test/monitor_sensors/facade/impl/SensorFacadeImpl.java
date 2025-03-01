package org.test.monitor_sensors.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.test.monitor_sensors.facade.SensorFacade;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;
import org.test.monitor_sensors.service.SensorService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorFacadeImpl implements SensorFacade {
    private final SensorService sensorService;

    @Override
    public ResponseEntity<SensorDto> createSensor(final SensorDto sensorDto) {
        final SensorDto sensor = sensorService.createSensor(sensorDto);
        return ResponseEntity.ok(sensor);
    }

    @Override
    public ResponseEntity<Void> deleteSensor(final UUID sensorId) {
        sensorService.deleteSensor(sensorId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SensorDto> getSensorById(final UUID sensorId) {
        final Optional<SensorDto> sensorById = sensorService.getSensorById(sensorId);
        return sensorById.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<SensorDto> updateSensor(final SensorDto sensorDto) {
        final Optional<SensorDto> updated = sensorService.updateSensor(sensorDto);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FindSensorDto> findSensors(final String name, final String model, final Integer page) {
        final FindSensorDto sensors = sensorService.findSensors(name, model, page);
        return ResponseEntity.ok(sensors);
    }
}
