package org.test.monitor_sensors.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.monitor_sensors.facade.SensorFacade;
import org.test.monitor_sensors.openapi.api.SensorsApi;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;

import java.util.UUID;
@RestController
@RequestMapping("/sensors")
public class SensorController implements SensorsApi {
    @Autowired private SensorFacade sensorFacade;

    @Override
    @PostMapping
    public ResponseEntity<SensorDto> addSensor(@Valid @RequestBody final SensorDto sensorDto) {
        return sensorFacade.createSensor(sensorDto);
    }

    @Override
    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> deleteSensor(@PathVariable("sensorId") final UUID sensorId) {
        return sensorFacade.deleteSensor(sensorId);
    }

    @Override
    @GetMapping("/find")
    public ResponseEntity<FindSensorDto> findSensors(final String name, final String model,Integer page) {

        return sensorFacade.findSensors(name,model,page);
    }

    @Override
    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorDto> getSensorById(@PathVariable("sensorId") final UUID sensorId) {
        return sensorFacade.getSensorById(sensorId);
    }

    @Override
    @PutMapping
    public ResponseEntity<SensorDto> updateSensor( @Valid @RequestBody final SensorDto sensorDto) {
        return sensorFacade.updateSensor(sensorDto);
    }
}
