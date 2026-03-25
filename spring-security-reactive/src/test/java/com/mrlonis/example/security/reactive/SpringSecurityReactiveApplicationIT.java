package com.mrlonis.example.security.reactive;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.security.reactive.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;

class SpringSecurityReactiveApplicationIT extends AbstractMockWebServerIT {
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
