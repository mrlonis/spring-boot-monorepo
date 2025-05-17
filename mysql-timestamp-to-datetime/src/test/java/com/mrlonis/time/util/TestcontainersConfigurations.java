package com.mrlonis.time.util;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.utility.DockerImageName;

public class TestcontainersConfigurations {
    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL5_7 {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:5.7"))
                    .withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL8_0 {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                    .withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL8 {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:8")).withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL9_0 {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:9.0"))
                    .withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL_LTS {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:lts"))
                    .withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    public static class TestcontainersConfigurationMySQL8_2 {
        @Bean
        @ServiceConnection
        MySQLContainer<?> mysqlContainer() {
            return new MySQLContainer<>(DockerImageName.parse("mysql:8.2"))
                    .withImagePullPolicy(PullPolicy.alwaysPull());
        }
    }
}
