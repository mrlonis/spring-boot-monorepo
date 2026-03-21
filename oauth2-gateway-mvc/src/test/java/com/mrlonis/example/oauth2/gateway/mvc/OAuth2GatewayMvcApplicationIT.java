package com.mrlonis.example.oauth2.gateway.mvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mrlonis.example.oauth2.gateway.mvc.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;

public class OAuth2GatewayMvcApplicationIT extends AbstractMockWebServerIT {
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testActuator_healthIsOk() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Status.UP.getCode()));
    }
}
