package com.mrlonis.example.security.test;

import com.mrlonis.example.security.SpringSecurityApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(SpringSecurityApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
