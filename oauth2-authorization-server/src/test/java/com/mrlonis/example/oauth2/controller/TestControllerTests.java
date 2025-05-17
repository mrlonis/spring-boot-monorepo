package com.mrlonis.example.oauth2.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class TestControllerTests extends AbstractMockWebServerTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    @WithMockUser
    void testController_authenticated() throws Exception {
        mockMvc.perform(get("/v1/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void testController_unauthenticated() throws Exception {
        mockMvc.perform(get("/v1/test")).andExpect(status().isUnauthorized());
    }
}
