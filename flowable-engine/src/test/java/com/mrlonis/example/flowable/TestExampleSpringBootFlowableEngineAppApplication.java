package com.mrlonis.example.flowable;

import org.springframework.boot.SpringApplication;

public class TestExampleSpringBootFlowableEngineAppApplication {
    public static void main(String[] args) {
        SpringApplication.from(FlowableEngineApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
