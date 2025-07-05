package com.mrlonis.todo.todo_service.controllers;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import com.mrlonis.todo.todo_service.test.TestcontainersConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class TodoItemsControllerIT {
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private PrUrlRepository prUrlRepository;

    @Autowired
    private TestingUrlRepository testingUrlRepository;

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
        todoItemRepository.flush();

        prUrlRepository.deleteAll();
        prUrlRepository.flush();

        testingUrlRepository.deleteAll();
        testingUrlRepository.flush();
    }

    @Test
    void testGetTodoItems_whenThereAreNoTodoTodoItems() {
        webClient
                .get()
                .uri("/api/todo/items")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TodoItemDto.class)
                .hasSize(0);
    }
}
