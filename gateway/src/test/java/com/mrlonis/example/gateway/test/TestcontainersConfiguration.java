package com.mrlonis.example.gateway.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestcontainersConfiguration {
    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(
                redisContainer().getHost(), redisContainer().getFirstMappedPort());
    }

    @Bean
    @ServiceConnection(name = "redis")
    GenericContainer<?> redisContainer() {
        return new GenericContainer<>(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
    }
}
