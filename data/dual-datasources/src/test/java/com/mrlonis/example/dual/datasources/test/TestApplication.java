package com.mrlonis.example.dual.datasources.test;

import com.mrlonis.example.dual.datasources.DualDatasourcesApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    static void main(String[] args) {
        SpringApplication.from(DualDatasourcesApplication::main).run(args);
    }
}
