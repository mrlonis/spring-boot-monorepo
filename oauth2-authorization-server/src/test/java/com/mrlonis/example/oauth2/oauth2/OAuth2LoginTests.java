package com.mrlonis.example.oauth2.oauth2;

import static com.mrlonis.example.oauth2.test.TestUtils.generateCodeChallenge;
import static com.mrlonis.example.oauth2.test.TestUtils.generateCodeVerifier;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

class OAuth2LoginTests extends AbstractMockWebServerTests {
    @Test
    void testOAuth2_authorize_notLoggedIn() throws Exception {
        String clientId = "postman-public-client";
        String redirectUri = "/login/oauth2/code/postman-public-client";
        String scope = "openid profile email";
        String state = "test-state";
        String codeVerifier = generateCodeVerifier();
        String codeChallenge = generateCodeChallenge(codeVerifier);

        assertDoesNotThrow(() -> mockMvc.perform(get("/oauth2/authorize")
                        .queryParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", scope)
                        .queryParam("state", state)
                        .queryParam("code_challenge", codeChallenge)
                        .queryParam("code_challenge_method", "S256"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login")));
    }

    @Test
    @WithMockUser
    void testOAuth2_authorize_alreadyLoggedIn() throws Exception {
        String clientId = "postman-public-client";
        String redirectUri = "/login/oauth2/code/postman-public-client";
        String scope = "openid profile email";
        String state = "test-state";
        String codeVerifier = generateCodeVerifier();
        String codeChallenge = generateCodeChallenge(codeVerifier);

        assertDoesNotThrow(() -> mockMvc.perform(get("/oauth2/authorize")
                        .queryParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", scope)
                        .queryParam("state", state)
                        .queryParam("code_challenge", codeChallenge)
                        .queryParam("code_challenge_method", "S256"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)));
    }

    @Test
    @WithMockUser
    void testOAuth2_authorize_alreadyLoggedIn_fullPkceFlow() throws Exception {
        String clientId = "test-public-client";
        String redirectUri = "/login/oauth2/code/test-public-client";
        String scope = "openid profile email";
        String state = "test-state";
        String codeVerifier = generateCodeVerifier();
        String codeChallenge = generateCodeChallenge(codeVerifier);

        MvcResult result = assertDoesNotThrow(() -> mockMvc.perform(get("/oauth2/authorize")
                                .queryParam("response_type", "code")
                                .queryParam("client_id", clientId)
                                .queryParam("redirect_uri", redirectUri)
                                .queryParam("scope", scope)
                                .queryParam("state", state)
                                .queryParam("code_challenge", codeChallenge)
                                .queryParam("code_challenge_method", "S256"))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andExpect(
                                redirectedUrlPattern("/login/oauth2/code/test-public-client?code=*&state=test-state")))
                .andReturn();

        String location = result.getResponse().getHeader(HttpHeaders.LOCATION);
        assertNotNull(location);
        assertTrue(location.contains("code="));

        String code = UriComponentsBuilder.fromUriString(location)
                .build()
                .getQueryParams()
                .getFirst("code");
        mockMvc.perform(post("/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("grant_type", "authorization_code")
                        .param("code", code)
                        .param("redirect_uri", redirectUri)
                        .param("client_id", clientId)
                        .param("code_verifier", codeVerifier))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists());
    }

    @Test
    @WithMockUser
    void testOAuth2_token_invalidCodeVerifier() throws Exception {
        // reuse test-public-client code issuance
        String clientId = "test-public-client";
        String redirectUri = "/login/oauth2/code/test-public-client";
        String codeVerifier = generateCodeVerifier();
        String codeChallenge = generateCodeChallenge(codeVerifier);

        MvcResult result = mockMvc.perform(get("/oauth2/authorize")
                        .queryParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", "openid")
                        .queryParam("state", "x")
                        .queryParam("code_challenge", codeChallenge)
                        .queryParam("code_challenge_method", "S256"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assertNotNull(location);
        String code = UriComponentsBuilder.fromUriString(location)
                .build()
                .getQueryParams()
                .getFirst("code");

        // tamper with the code_verifier
        mockMvc.perform(post("/oauth2/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("grant_type", "authorization_code")
                        .param("code", code)
                        .param("redirect_uri", redirectUri)
                        .param("client_id", clientId)
                        .param("code_verifier", "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("invalid_grant"));
    }
}
