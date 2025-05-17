package com.mrlonis.flowable.example;

import org.springframework.boot.SpringApplication;

public class TestExampleSpringBootFlowableEngineAppApplication {
    public static void main(String[] args) {
        SpringApplication.from(ExampleSpringBootFlowableEngineAppApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
