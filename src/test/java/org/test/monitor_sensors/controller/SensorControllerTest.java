package org.test.monitor_sensors.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.test.monitor_sensors.SpringTest;
import org.test.monitor_sensors.dto.entity.SensorEntity;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.test.monitor_sensors.utils.model.SensorDtoTestUtil.generateValidSensorDto;
import static org.test.monitor_sensors.utils.model.SensorDtoTestUtil.generateValidSensorEntity;

class SensorControllerTest extends SpringTest {

    @AfterEach
    void clean() {
        sensorRepository.deleteAll();
    }

    @Test
    void addSensor() throws Exception {
        final SensorDto dto = generateValidSensorDto();
        final String request = objectMapper.writeValueAsString(dto);


        final MvcResult mvcResult = mockMvc.perform(
                        post("/sensors")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)).andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        final SensorDto resultDto = objectMapper.readValue(response, SensorDto.class);
        assertThat(resultDto.getDescription(), is(dto.getDescription()));
        assertThat(resultDto.getLocation(), is(dto.getLocation()));
        assertThat(resultDto.getUnit(), is(dto.getUnit()));
        assertThat(resultDto.getModel(), is(dto.getModel()));
        assertThat(resultDto.getName(), is(dto.getName()));
        assertThat(resultDto.getType(), is(dto.getType()));
        assertThat(resultDto.getRange().getTo(), is(dto.getRange().getTo()));
        assertThat(resultDto.getRange().getFrom(), is(dto.getRange().getFrom()));
    }

    @Test
    void deleteSensor() throws Exception {
        final SensorEntity build = generateValidSensorEntity();
        final SensorEntity save = sensorRepository.save(build);

        mockMvc.perform(
                        delete("/sensors/{sensorId}", save.getId())
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        final List<SensorEntity> all = sensorRepository.findAll();
        assertThat(all.size(), is(0));
    }

    @Test
    void findSensors() throws Exception {
        final SensorEntity build = generateValidSensorEntity();
        final SensorEntity save = sensorRepository.save(build);

        final MvcResult mvcResult = mockMvc.perform(
                        get("/sensors/find")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        final FindSensorDto resultDto = objectMapper.readValue(response, FindSensorDto.class);
        assertThat(resultDto.getSensors().size(), is(1));
        final SensorDto sensorDto = resultDto.getSensors().getFirst();
        assertThat(sensorDto.getDescription(), is(save.getDescription()));
        assertThat(sensorDto.getLocation(), is(save.getLocation()));
        assertThat(sensorDto.getUnit().getValue(), is(save.getUnit().getValue()));
        assertThat(sensorDto.getModel(), is(save.getModel()));
        assertThat(sensorDto.getName(), is(save.getName()));
        assertThat(sensorDto.getType().getValue(), is(save.getType().getValue()));
        assertThat(sensorDto.getRange().getTo(), is(save.getRange().getTo()));
        assertThat(sensorDto.getRange().getFrom(), is(save.getRange().getFrom()));
    }@Test
    void findSensorsWithSpecs() throws Exception {
        final SensorEntity build = generateValidSensorEntity();
        final SensorEntity save = sensorRepository.save(build);

        final MvcResult mvcResult = mockMvc.perform(
                        get("/sensors/find")
                                .param("name",save.getName())
                                .param("model",save.getModel())
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        final FindSensorDto resultDto = objectMapper.readValue(response, FindSensorDto.class);
        assertThat(resultDto.getSensors().size(), is(1));
        final SensorDto sensorDto = resultDto.getSensors().getFirst();
        assertThat(sensorDto.getDescription(), is(save.getDescription()));
        assertThat(sensorDto.getLocation(), is(save.getLocation()));
        assertThat(sensorDto.getUnit().getValue(), is(save.getUnit().getValue()));
        assertThat(sensorDto.getModel(), is(save.getModel()));
        assertThat(sensorDto.getName(), is(save.getName()));
        assertThat(sensorDto.getType().getValue(), is(save.getType().getValue()));
        assertThat(sensorDto.getRange().getTo(), is(save.getRange().getTo()));
        assertThat(sensorDto.getRange().getFrom(), is(save.getRange().getFrom()));
    }

    @Test
    void getSensorById() throws Exception {
        final SensorEntity build = generateValidSensorEntity();
        final SensorEntity save = sensorRepository.save(build);


        final MvcResult mvcResult = mockMvc.perform(
                        get("/sensors/{sensorId}", save.getId())
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        final SensorDto resultDto = objectMapper.readValue(response, SensorDto.class);
        assertThat(resultDto.getDescription(), is(save.getDescription()));
        assertThat(resultDto.getLocation(), is(save.getLocation()));
        assertThat(resultDto.getUnit().getValue(), is(save.getUnit().getValue()));
        assertThat(resultDto.getModel(), is(save.getModel()));
        assertThat(resultDto.getName(), is(save.getName()));
        assertThat(resultDto.getType().getValue(), is(save.getType().getValue()));
        assertThat(resultDto.getRange().getTo(), is(save.getRange().getTo()));
        assertThat(resultDto.getRange().getFrom(), is(save.getRange().getFrom()));
    }

    @Test
    void updateSensor() throws Exception {
        final SensorEntity build = generateValidSensorEntity();
        final SensorEntity save = sensorRepository.save(build);
        final SensorDto dto = generateValidSensorDto();
        ReflectionTestUtils.setField(dto, "id", save.getId());
        ReflectionTestUtils.setField(dto.getRange(), "id", save.getRange().getId());
        final String request = objectMapper.writeValueAsString(dto);


        final MvcResult mvcResult = mockMvc.perform(
                        put("/sensors")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)).andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        final SensorDto resultDto = objectMapper.readValue(response, SensorDto.class);
        assertThat(resultDto.getDescription(), is(dto.getDescription()));
        assertThat(resultDto.getLocation(), is(dto.getLocation()));
        assertThat(resultDto.getUnit(), is(dto.getUnit()));
        assertThat(resultDto.getModel(), is(dto.getModel()));
        assertThat(resultDto.getName(), is(dto.getName()));
        assertThat(resultDto.getType(), is(dto.getType()));
        assertThat(resultDto.getRange().getTo(), is(dto.getRange().getTo()));
        assertThat(resultDto.getRange().getFrom(), is(dto.getRange().getFrom()));
    }
}