package com.mrlonis.example.oauth2.opaque.test;

import com.mrlonis.example.oauth2.opaque.OAuth2AuthorizationServerOpaqueApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(OAuth2AuthorizationServerOpaqueApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
