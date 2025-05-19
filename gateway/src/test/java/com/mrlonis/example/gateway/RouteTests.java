package com.mrlonis.example.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

import com.mrlonis.example.gateway.test.AbstractMockWebServerTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

@Slf4j
class RouteTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    @Disabled
    void testGoogle() {
        var result = webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/google")
                .header(HttpHeaders.ACCEPT, "text/html;charset=ISO-8859-1")
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
        assertEquals(2, rawSetCookieHeaders.size());
        for (var header : rawSetCookieHeaders) {
            log.info("Raw Set-Cookie: {}", header);
        }

        assertTrue(rawSetCookieHeaders.stream().anyMatch(header -> header.startsWith("CUSTOM-SESSION-NAME=")));

        var sessionCookieHeader = rawSetCookieHeaders.stream()
                .filter(header -> header.startsWith("CUSTOM-SESSION-NAME="))
                .findFirst()
                .orElseThrow();

        String sessionValue = sessionCookieHeader.split(";")[0].split("=")[1];
        assertNotNull(sessionValue);

        // Make a 2nd request with the session cookie
        webTestClient
                .mutateWith(mockOidcLogin())
                .get()
                .uri("/google")
                .header(HttpHeaders.ACCEPT, "text/html;charset=ISO-8859-1")
                .cookie("CUSTOM-SESSION-NAME", sessionValue)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
