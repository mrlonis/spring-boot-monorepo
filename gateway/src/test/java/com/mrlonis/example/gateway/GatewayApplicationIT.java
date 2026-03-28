package com.mrlonis.example.gateway;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mrlonis.example.gateway.test.AbstractMockWebServerIT;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.health.contributor.Status;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GatewayApplicationIT extends AbstractMockWebServerIT {
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
    void testActuator_healthIsOk() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

        webTestClient
                .get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status")
                .isEqualTo(Status.UP.getCode());
    }

    @Test
    void testGatewayRoute() {
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

        webTestClient.get().uri("/google/robots.txt").exchange().expectStatus().isOk();

        RecordedRequest googleRequest = assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
        assertNotNull(googleRequest);
        assertAll(
                () -> assertEquals("GET", googleRequest.getMethod()),
                () -> assertEquals("/robots.txt", googleRequest.getPath()));
    }
}
