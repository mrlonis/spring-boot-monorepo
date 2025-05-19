package com.mrlonis.example.gateway.oauth2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;

class OAuth2LoginTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void shouldRedirectToLogin() {
        webTestClient.get().uri("/").exchange().expectStatus().is3xxRedirection();
    }
}
