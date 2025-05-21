package com.mrlonis.example.security.opaque.test;

import com.mrlonis.example.security.opaque.SpringSecurityOpaqueApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(SpringSecurityOpaqueApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
