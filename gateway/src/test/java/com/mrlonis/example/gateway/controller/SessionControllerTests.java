package com.mrlonis.example.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

@Slf4j
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
    void getSessionId_whenAuthenticated_succeeds_andCanBeReUsed() {
        var result = webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/v1/session/id")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .exists("Set-Cookie")
                .expectBody(String.class)
                .returnResult();

        // Cookie Processing
        var rawSetCookieHeaders = result.getResponseHeaders().get("Set-Cookie");
        assertNotNull(rawSetCookieHeaders);
        assertEquals(1, rawSetCookieHeaders.size());
        var rawSetCookieHeader = rawSetCookieHeaders.getFirst();
        log.info("Raw Set-Cookie: {}", rawSetCookieHeader);

        String sessionCookieValue = rawSetCookieHeader.split(";")[0].split("=")[1];
        assertNotNull(sessionCookieValue);
        log.info("Parsed SESSION value: {}", sessionCookieValue);

        // Capture Response Body
        String sessionId = result.getResponseBody();
        assertNotNull(sessionId);
        assertTrue(StringUtils.isNotBlank(sessionId));
        assertEquals(sessionId, sessionCookieValue);

        // Make a 2nd request with the session cookie
        var sessionId2 = webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/v1/session/id")
                .cookie("CUSTOM-SESSION-NAME", sessionCookieValue)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(sessionId2);
        assertTrue(StringUtils.isNotBlank(sessionId2));

        assertEquals(sessionId, sessionId2);
        assertEquals(sessionId2, sessionCookieValue);
    }
}
