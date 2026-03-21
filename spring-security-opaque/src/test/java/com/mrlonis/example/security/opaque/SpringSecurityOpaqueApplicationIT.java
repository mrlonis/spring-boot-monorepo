package com.mrlonis.example.security.opaque;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.security.opaque.test.AbstractMockWebServerIT;
import org.junit.jupiter.api.Test;

public class SpringSecurityOpaqueApplicationIT extends AbstractMockWebServerIT {
    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
