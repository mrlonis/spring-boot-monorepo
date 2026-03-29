package com.mrlonis.example.oauth2.opaque;

import com.mrlonis.example.oauth2.opaque.config.properties.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class OAuth2AuthorizationServerOpaqueApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthorizationServerOpaqueApplication.class, args);
    }
}
