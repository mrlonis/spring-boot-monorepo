package com.mrlonis.example.gateway;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.gateway.test.AbstractMockWebServerIT;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Slf4j
class RouteIT extends AbstractMockWebServerIT {
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
    void testGoogle() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value()));

        var result = webTestClient
                .get()
                .uri("/google/robots.txt")
                .header(HttpHeaders.ACCEPT, "text/html;charset=ISO-8859-1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .exists("Set-Cookie")
                .expectBody(String.class)
                .returnResult();

        RecordedRequest googleRequest1 = assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
        assertNotNull(googleRequest1);
        assertAll(
                () -> assertEquals("GET", googleRequest1.getMethod()),
                () -> assertEquals("/robots.txt", googleRequest1.getPath()));

        // Cookie Processing
        var rawSetCookieHeaders = result.getResponseHeaders().get("Set-Cookie");
        assertNotNull(rawSetCookieHeaders);
        for (var header : rawSetCookieHeaders) {
            log.info("Raw Set-Cookie: {}", header);
        }
        assertEquals(1, rawSetCookieHeaders.size());

        assertTrue(rawSetCookieHeaders.stream().anyMatch(header -> header.startsWith("CUSTOM-SESSION-NAME=")));

        var sessionCookieHeader = rawSetCookieHeaders.stream()
                .filter(header -> header.startsWith("CUSTOM-SESSION-NAME="))
                .findFirst()
                .orElseThrow();

        String sessionValue = sessionCookieHeader.split(";")[0].split("=")[1];
        assertNotNull(sessionValue);

        // Make a 2nd request with the session cookie
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value()));
        webTestClient
                .get()
                .uri("/google/robots.txt")
                .header(HttpHeaders.ACCEPT, "text/html;charset=ISO-8859-1")
                .cookie("CUSTOM-SESSION-NAME", sessionValue)
                .exchange()
                .expectStatus()
                .isOk();

        RecordedRequest googleRequest2 = assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
        assertNotNull(googleRequest2);
        assertAll(
                () -> assertEquals("GET", googleRequest2.getMethod()),
                () -> assertEquals("/robots.txt", googleRequest2.getPath()));
    }
}
