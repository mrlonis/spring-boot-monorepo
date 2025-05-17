package com.mrlonis.example.oauth2.oauth2;

import static com.mrlonis.example.oauth2.test.TestUtils.generateCodeChallenge;
import static com.mrlonis.example.oauth2.test.TestUtils.generateCodeVerifier;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class OAuth2LogoutTests extends AbstractMockWebServerTests {
    @Test
    @WithMockUser
    void testOIDC_logout_flow() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }

    @Test
    @WithMockUser
    void testOIDC_logout_then_relogin() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));

        mockMvc.perform(get("/oauth2/authorize")
                        .queryParam("response_type", "code")
                        .queryParam("client_id", "test-public-client")
                        .queryParam("redirect_uri", "/login/oauth2/code/test-public-client")
                        .queryParam("scope", "openid")
                        .queryParam("state", "123")
                        .queryParam("code_challenge", generateCodeChallenge(generateCodeVerifier()))
                        .queryParam("code_challenge_method", "S256"))
                .andExpect(status().is3xxRedirection()); // should redirect back to login
    }
}
