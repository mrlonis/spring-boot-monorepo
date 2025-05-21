package com.mrlonis.example.oauth2.gateway.mvc.test;

import com.mrlonis.example.oauth2.gateway.mvc.OAuth2GatewayMvcApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(OAuth2GatewayMvcApplication::main)
                //                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
