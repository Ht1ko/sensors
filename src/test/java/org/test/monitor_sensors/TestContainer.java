package org.test.monitor_sensors;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class TestContainer {

    private static final String IMAGE_VERSION = "postgres:15.4";
    private static final String DATABASE_NAME="sensor";
    private static final String DATABASE_USERNAME="root";
    private static final String DATABASE_PASSWORD="sensor";

    @Container
    public static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
             new PostgreSQLContainer(IMAGE_VERSION)
                     .withDatabaseName(DATABASE_NAME)
                     .withUsername(DATABASE_USERNAME)
                     .withPassword(DATABASE_PASSWORD);
}
