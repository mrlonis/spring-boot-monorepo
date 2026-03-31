package com.mrlonis.example.template.reactive;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.template.reactive.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;

class TemplateApplicationIT extends AbstractMockWebServerIT {
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
