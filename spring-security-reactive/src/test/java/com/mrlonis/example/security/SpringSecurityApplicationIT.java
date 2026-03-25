package com.mrlonis.example.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.security.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;

class SpringSecurityApplicationIT extends AbstractMockWebServerIT {
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
                .isEqualTo("UP");
    }
}
