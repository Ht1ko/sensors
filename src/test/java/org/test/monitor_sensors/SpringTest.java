package org.test.monitor_sensors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.test.monitor_sensors.repository.SensorRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringTest {
    @Autowired protected  MockMvc mockMvc;

    @Autowired protected ObjectMapper objectMapper;

    @Autowired protected SensorRepository sensorRepository;

}
