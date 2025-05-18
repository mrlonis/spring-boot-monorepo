package com.mrlonis.time;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.time.util.TestcontainersConfigurations;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * This is a basic context load test to ensure that the application can start up and that the Spring context is able to
 * load
 */
class MySqlTimestampToDatetimeApplicationTests {
    @SpringBootTest
    abstract static class BaseMySqlTimestampToDatetimeApplicationTest {
        @Test
        void contextLoads() {
            assertTrue(true);
        }
    }

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL5_7.class)
    class MySqlTimestampToDatetimeApplicationMySQL5_7Tests extends BaseMySqlTimestampToDatetimeApplicationTest {}

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL8.class)
    class MySqlTimestampToDatetimeApplicationMySQL8Tests extends BaseMySqlTimestampToDatetimeApplicationTest {}

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL9_0.class)
    class MySqlTimestampToDatetimeApplicationMySQL9Tests extends BaseMySqlTimestampToDatetimeApplicationTest {}
}
