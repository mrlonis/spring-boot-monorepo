package com.mrlonis.todo.todo_service.controllers;

import static com.mrlonis.todo.todo_service.test.TestUtilities.assertTodoItems;
import static com.mrlonis.todo.todo_service.test.TestUtilities.buildDefaultTodoItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.mappers.TodoItemMapper;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import com.mrlonis.todo.todo_service.test.TestcontainersConfiguration;
import java.util.Map;
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

    @Autowired
    private TodoItemMapper todoItemMapper;

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
    void testGetTodoItems_whenThereAreNoTodoItems() {
        webClient
                .get()
                .uri("/api/todo/items")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TodoItemDto.class)
                .hasSize(0);
    }

    @Test
    void testGetTodoItems_whenThereAreTodoItems() {
        TodoItem expected = buildDefaultTodoItem();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertTodoItems(expected, actual);

        webClient
                .get()
                .uri("/api/todo/items")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TodoItemDto.class)
                .hasSize(1);
    }

    @Test
    void testCreateOrUpdateTodoItem_create() {
        TodoItem todoItem = buildDefaultTodoItem();
        TodoItemDto todoItemDto = todoItemMapper.mapTodoItemToTodoItemDto(todoItem);

        webClient
                .post()
                .uri("/api/todo/item")
                .bodyValue(todoItemDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TodoItemDto.class)
                .value(actual -> {
                    assertNotNull(actual);
                    assertNotNull(actual.getId());
                });
    }

    @Test
    void testGetTodoItemsByPi_whenThereAreNoTodoItems() {
        webClient
                .get()
                .uri("/api/todo/itemsByPi")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(0, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPi_whenThereAreTodoItems() {
        TodoItem todoItem = buildDefaultTodoItem();
        todoItemRepository.saveAndFlush(todoItem);

        webClient
                .get()
                .uri("/api/todo/itemsByPi")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(1, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_whenThereAreNoTodoItems() {
        webClient
                .get()
                .uri("/api/todo/itemsByPiAndBySprint")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(0, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_whenThereAreTodoItems() {
        TodoItem todoItem = buildDefaultTodoItem();
        todoItemRepository.saveAndFlush(todoItem);

        webClient
                .get()
                .uri("/api/todo/itemsByPiAndBySprint")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(1, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_whenThereAreTodoItems_butWeHideCompleted() {
        TodoItem todoItem = buildDefaultTodoItem();
        todoItem.setCompleted(true);
        todoItemRepository.saveAndFlush(todoItem);

        webClient
                .get()
                .uri("/api/todo/itemsByPiAndBySprint?hideCompleted=true")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(0, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_whenThereAreNoArchivedTodoItems() {
        webClient
                .get()
                .uri("/api/todo/itemsByPiAndBySprint?archived=true")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(0, map.size());
                });
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_whenThereAreArchivedTodoItems() {
        TodoItem todoItem = buildDefaultTodoItem();
        todoItem.setArchived(true);
        todoItemRepository.saveAndFlush(todoItem);

        webClient
                .get()
                .uri("/api/todo/itemsByPiAndBySprint?archived=true")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .value(map -> {
                    assertNotNull(map);
                    assertEquals(1, map.size());
                });
    }
}
