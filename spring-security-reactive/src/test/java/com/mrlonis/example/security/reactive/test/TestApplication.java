package com.mrlonis.example.security.reactive.test;

import com.mrlonis.example.security.reactive.SpringSecurityReactiveApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(SpringSecurityReactiveApplication::main).run(args);
    }
}
