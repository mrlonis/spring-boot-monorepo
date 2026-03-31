package com.mrlonis.example.template.reactive.test;

import com.mrlonis.example.template.reactive.TemplateApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    static void main(String[] args) {
        SpringApplication.from(TemplateApplication::main).run(args);
    }
}
