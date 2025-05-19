package com.mrlonis.example.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class TestControllerTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    @WithMockUser
    void testController_authenticated() {
        assertEquals(
                "Hello World!",
                webTestClient
                        .get()
                        .uri("/v1/test")
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody());
    }

    @Test
    void testController_unauthenticated() {
        webTestClient.get().uri("/v1/test").exchange().expectStatus().isFound();
    }
}
