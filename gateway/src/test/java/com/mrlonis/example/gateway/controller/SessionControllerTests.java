package com.mrlonis.example.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SessionControllerTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void getSessionId_whenNotAuthenticated_isFound() {
        webTestClient.get().uri("/v1/session/id").exchange().expectStatus().isFound();
    }

    @Test
    void getSessionId_whenAuthenticated_succeeds() {
        var response = webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/v1/session/id")
                .exchange()
                .expectStatus()
                .isOk();

        String sessionId = response.expectBody(String.class).returnResult().getResponseBody();

        assertNotNull(sessionId);
        assertTrue(StringUtils.isNotBlank(sessionId));
    }

    @Test
    @Disabled("Disabled until we can figure out how to get the cookie")
    void getSessionId_whenAuthenticated_succeeds_andCanBeReUsed() {
        var response = webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/v1/session/id")
                .exchange()
                .expectStatus()
                .isOk();

        response.expectCookie().exists("JSESSIONID");
    }
}
