package com.mrlonis.todo.todo_service.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.test.TestcontainersConfiguration;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class PrUrlRepositoryTests {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private PrUrlRepository prUrlRepository;

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
        todoItemRepository.flush();

        prUrlRepository.deleteAll();
        prUrlRepository.flush();
    }

    @Test
    void contextLoads() {
        assertEmpty();
    }

    private void assertEmpty() {
        List<TodoItem> result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        assertPrUrlRepositoryEmpty();
    }

    private void assertPrUrlRepositoryEmpty() {
        List<PrUrl> prUrls = prUrlRepository.findAll();
        assertNotNull(prUrls);
        assertTrue(prUrls.isEmpty());
    }

    @Test
    void testSaveAndFlush() {
        TodoItem expectedTodoItem = TodoItemRepositoryTests.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryTests.assertObjects(expectedTodoItem, actualTodoItem);

        PrUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        PrUrl actual = prUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);
    }

    private static PrUrl buildObject(TodoItem todoItem) {
        return PrUrl.builder().url("url").todoItem(todoItem).build();
    }

    private static void assertObjects(PrUrl expected, PrUrl actual) {
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getTodoItem().getId(), actual.getTodoItem().getId());
    }

    @Test
    void testDelete() {
        TodoItem expectedTodoItem = TodoItemRepositoryTests.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryTests.assertObjects(expectedTodoItem, actualTodoItem);

        PrUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        PrUrl actual = prUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);

        prUrlRepository.delete(actual);
        prUrlRepository.flush();

        Optional<PrUrl> result = prUrlRepository.findById(actual.getId());
        assertNotNull(result);
        assertTrue(result.isEmpty());

        assertPrUrlRepositoryEmpty();
    }

    @Test
    void testFindByUrlAndTodoItemId() {
        TodoItem expectedTodoItem = TodoItemRepositoryTests.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryTests.assertObjects(expectedTodoItem, actualTodoItem);

        PrUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        PrUrl actual = prUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);

        Optional<PrUrl> result = prUrlRepository.findByUrlAndTodoItemId(actual.getUrl(), actualTodoItem.getId());
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertObjects(expected, result.get());
    }
}
