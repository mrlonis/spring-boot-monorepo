package com.mrlonis.time.util;

import com.mrlonis.time.MySqlTimestampToDatetimeApplication;
import org.springframework.boot.SpringApplication;

/**
 * This is a basic test application to show how to set up a Spring Boot application with Testcontainers. It is used to
 * test the application with a MySQL database using Testcontainers. It is very similar to running the application with
 * the local profile, but it uses Testcontainers to start up a MySQL database in a Docker container. This can easily be
 * run in IntelliJ by clicking the green arrow next to the main method and selecting Run 'TestApplication.main()'.
 */
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.from(MySqlTimestampToDatetimeApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
