package com.mrlonis.todo.todo_service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TodoServiceApplicationTests {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    void contextLoads() {
        var result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
