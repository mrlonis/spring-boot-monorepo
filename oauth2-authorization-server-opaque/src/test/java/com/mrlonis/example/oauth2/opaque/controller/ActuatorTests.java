package com.mrlonis.example.oauth2.opaque.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.opaque.test.AbstractMockWebServerTests;
import org.junit.jupiter.api.Test;

class ActuatorTests extends AbstractMockWebServerTests {
    @Test
    void testActuator_healthIsOk() {
        assertDoesNotThrow(() -> mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP")));
    }
}
