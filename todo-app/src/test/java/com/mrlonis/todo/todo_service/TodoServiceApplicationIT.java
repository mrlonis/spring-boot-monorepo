package com.mrlonis.todo.todo_service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import com.mrlonis.todo.todo_service.test.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class TodoServiceApplicationIT {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    void contextLoads() {
        var result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
