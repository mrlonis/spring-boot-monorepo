package com.mrlonis.todo.todo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoServiceApplication {

    public static void main(String[] args) {
        System.out.println("JVM default zone: " + java.time.ZoneId.systemDefault());
        System.out.println("user.timezone property: " + System.getProperty("user.timezone"));

        SpringApplication.run(TodoServiceApplication.class, args);
    }
}
