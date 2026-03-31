package com.mrlonis.example.template.servlet.test;

import com.mrlonis.example.template.servlet.TemplateApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    static void main(String[] args) {
        SpringApplication.from(TemplateApplication::main).run(args);
    }
}
