package com.mrlonis.example.oauth2.opaque;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.example.oauth2.opaque.test.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class OAuth2AuthorizationServerOpaqueApplicationTests {
    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
