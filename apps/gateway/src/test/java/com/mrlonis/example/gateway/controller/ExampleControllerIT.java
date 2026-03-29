package com.mrlonis.example.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mrlonis.example.gateway.test.AbstractMockWebServerIT;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ExampleControllerIT extends AbstractMockWebServerIT {
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
    @WithMockUser
    void testController_authenticated() {
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

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
        if (!openidConfigurationRequestReceived) {
            RecordedRequest openidConfigurationRequest =
                    assertDoesNotThrow(() -> mockWebServer.takeRequest(5, TimeUnit.SECONDS));
            assertNotNull(openidConfigurationRequest);
            assertAll(
                    () -> assertEquals("GET", openidConfigurationRequest.getMethod()),
                    () -> assertEquals("/.well-known/openid-configuration", openidConfigurationRequest.getPath()));
            openidConfigurationRequestReceived = true;
        }

        webTestClient.get().uri("/v1/test").exchange().expectStatus().isFound();
    }
}
