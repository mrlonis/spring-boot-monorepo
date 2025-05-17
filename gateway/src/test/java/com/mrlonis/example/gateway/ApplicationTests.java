package com.mrlonis.example.gateway;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;

class ApplicationTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testActuator_healthIsOk() {
        webTestClient
                .get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status")
                .isEqualTo(Status.UP.getCode());
    }

    @Test
    void testGatewayRoute() {
        webTestClient.get().uri("/google").exchange().expectStatus().isOk();
    }
}
