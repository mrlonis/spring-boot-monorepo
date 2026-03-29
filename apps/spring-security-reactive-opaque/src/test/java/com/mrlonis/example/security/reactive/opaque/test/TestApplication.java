package com.mrlonis.example.security.reactive.opaque.test;

import com.mrlonis.example.security.reactive.opaque.SpringSecurityReactiveOpaqueApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    static void main(String[] args) {
        SpringApplication.from(SpringSecurityReactiveOpaqueApplication::main).run(args);
    }
}
