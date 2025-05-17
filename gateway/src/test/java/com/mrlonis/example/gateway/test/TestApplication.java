package com.mrlonis.example.gateway.test;

import com.mrlonis.example.gateway.GatewayApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(GatewayApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
