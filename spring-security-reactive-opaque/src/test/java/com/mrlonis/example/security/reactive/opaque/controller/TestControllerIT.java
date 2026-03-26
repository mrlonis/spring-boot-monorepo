package com.mrlonis.example.security.reactive.opaque.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.security.reactive.opaque.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class TestControllerIT extends AbstractMockWebServerIT {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    @WithMockUser
    void testController_authenticated() throws Exception {
        webTestClient
                .get()
                .uri("/v1/test")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Hello World!");
    }

    @Test
    void testController_unauthenticated() throws Exception {
        webTestClient.get().uri("/v1/test").exchange().expectStatus().isUnauthorized();
    }
}
