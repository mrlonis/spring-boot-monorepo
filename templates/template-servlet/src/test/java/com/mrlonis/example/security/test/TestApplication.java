package com.mrlonis.example.security.test;

import com.mrlonis.example.security.TemplateApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    static void main(String[] args) {
        SpringApplication.from(TemplateApplication::main).run(args);
    }
}
