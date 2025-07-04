package com.mrlonis.todo.todo_service.test;

import com.mrlonis.todo.todo_service.TodoServiceApplication;
import org.springframework.boot.SpringApplication;

public class TestTodoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.from(TodoServiceApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
