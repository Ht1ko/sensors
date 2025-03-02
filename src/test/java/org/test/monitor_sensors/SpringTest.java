package org.test.monitor_sensors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.test.monitor_sensors.repository.SensorRepository;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class SpringTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected SensorRepository sensorRepository;

    static {
        TestContainer.POSTGRESQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void setTestDataProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", TestContainer.POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", TestContainer.POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", TestContainer.POSTGRESQL_CONTAINER::getPassword);

        registry.add("spring.flyway.url", TestContainer.POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.flyway.user", TestContainer.POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.flyway.password", TestContainer.POSTGRESQL_CONTAINER::getPassword);
    }
}
