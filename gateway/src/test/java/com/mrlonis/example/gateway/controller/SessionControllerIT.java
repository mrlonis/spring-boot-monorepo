package com.mrlonis.example.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

import com.mrlonis.example.gateway.test.AbstractMockWebServerIT;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Slf4j
class SessionControllerIT extends AbstractMockWebServerIT {
    private static boolean openidConfigurationRequestReceived = false;

    @Test
    void contextLoads() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }
    }

    @Test
    void getSessionId_whenNotAuthenticated_isFound() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

        webTestClient.get().uri("/v1/session/id").exchange().expectStatus().isFound();
    }

    @Test
    @WithMockUser
    void getSessionId_whenAuthenticated_succeeds() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

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
    @WithMockUser
    void getSessionId_whenAuthenticated_succeeds_andCanBeReUsed() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

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
