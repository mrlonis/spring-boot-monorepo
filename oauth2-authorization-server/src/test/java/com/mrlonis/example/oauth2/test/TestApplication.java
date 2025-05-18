package com.mrlonis.example.oauth2.test;

import com.mrlonis.example.oauth2.OAuth2AuthorizationServerApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(OAuth2AuthorizationServerApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
