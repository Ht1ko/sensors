package org.test.monitor_sensors.facade.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.test.monitor_sensors.facade.SensorFacade;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;
import org.test.monitor_sensors.service.SensorService;
import org.test.monitor_sensors.utils.RandomTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.test.monitor_sensors.utils.model.SensorDtoTestUtil.generateValidSensorDto;

@ExtendWith(MockitoExtension.class)
class SensorFacadeImplTest {
    private SensorFacade sensorFacade;
    @Mock
    private SensorService sensorService;

    @BeforeEach
    void init() {
        sensorFacade = new SensorFacadeImpl(sensorService);
    }

    @Test
    void createSensor() {
        final SensorDto sensorDto = generateValidSensorDto();
        final ResponseEntity<SensorDto> sensor = sensorFacade.createSensor(sensorDto);
        assertThat(sensor.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    void deleteSensor() {
        final ResponseEntity<Void> sensor = sensorFacade.deleteSensor(UUID.randomUUID());
        assertThat(sensor.getStatusCode(), is(HttpStatus.OK));
        verify(sensorService, times(1)).deleteSensor(any());
    }

    @Test
    void getSensorById() {
        when(sensorService.getSensorById(any())).thenReturn(Optional.of(generateValidSensorDto()));
        final ResponseEntity<SensorDto> sensor = sensorFacade.getSensorById(UUID.randomUUID());
        assertThat(sensor.getStatusCode(), is(HttpStatus.OK));
        verify(sensorService, times(1)).getSensorById(any());
    }

    @Test
    void getSensorByIdNotFound() {
        when(sensorService.getSensorById(any())).thenReturn(Optional.empty());
        final ResponseEntity<SensorDto> sensor = sensorFacade.getSensorById(UUID.randomUUID());
        assertThat(sensor.getStatusCode(), is(HttpStatus.NOT_FOUND));
        verify(sensorService, times(1)).getSensorById(any());
    }

    @Test
    void updateSensor() {
        final SensorDto sensorDto = generateValidSensorDto();
        final ResponseEntity<SensorDto> sensor = sensorFacade.createSensor(sensorDto);
        assertThat(sensor.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    void updateSensorNotFound() {
        when(sensorService.updateSensor(any())).thenReturn(Optional.empty());
        final SensorDto sensorDto = generateValidSensorDto();
        final ResponseEntity<SensorDto> sensor = sensorFacade.updateSensor(sensorDto);
        assertThat(sensor.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void findSensors() {

        final String name = RandomTestUtils.getRandomString(2);
        final String model = RandomTestUtils.getRandomString(2);
        final ResponseEntity<FindSensorDto> sensor = sensorFacade.findSensors(name, model, 1);
        assertThat(sensor.getStatusCode(), is(HttpStatus.OK));
        verify(sensorService, times(1)).findSensors(any(), any(), any());
    }
}