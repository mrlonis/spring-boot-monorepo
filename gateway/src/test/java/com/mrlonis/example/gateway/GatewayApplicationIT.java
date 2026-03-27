package com.mrlonis.example.gateway;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.gateway.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.health.contributor.Status;

class GatewayApplicationIT extends AbstractMockWebServerIT {
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
        webTestClient.get().uri("/google/robots.txt").exchange().expectStatus().isOk();
    }
}
