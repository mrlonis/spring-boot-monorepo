package com.mrlonis.example.oauth2.opaque.oauth2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.opaque.test.AbstractMockWebServerTests;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class OAuth2TokenTests extends AbstractMockWebServerTests {
    @Test
    void testOAuth2_token_gateway() {
        String clientId = "gateway-client";
        String clientSecret = "gateway-secret";
        String scope = "gateway";
        String basicAuth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        assertDoesNotThrow(() -> mockMvc.perform(post("/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                        .content("grant_type=client_credentials&scope="
                                + URLEncoder.encode(scope, StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists()));
    }

    @Test
    void testOAuth2_token_gateway_invalidScope() {
        String clientId = "gateway-client";
        String clientSecret = "gateway-secret";
        String scope = "fake";
        String basicAuth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        assertDoesNotThrow(() -> mockMvc.perform(post("/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                        .content("grant_type=client_credentials&scope="
                                + URLEncoder.encode(scope, StandardCharsets.UTF_8)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("invalid_scope")));
    }

    @Test
    void testOAuth2_token_invalidClientId() {
        String clientId = "fake";
        String clientSecret = "fake";
        String scope = "fake";
        String basicAuth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        assertDoesNotThrow(() -> mockMvc.perform(post("/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                        .content("grant_type=client_credentials&scope="
                                + URLEncoder.encode(scope, StandardCharsets.UTF_8)))
                .andExpect(status().isUnauthorized()));
    }
}
