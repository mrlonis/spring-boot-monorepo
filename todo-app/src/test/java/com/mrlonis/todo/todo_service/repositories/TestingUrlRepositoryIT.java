package com.mrlonis.todo.todo_service.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.entities.TestingUrl;
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
class TestingUrlRepositoryIT {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TestingUrlRepository testingUrlRepository;

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
        todoItemRepository.flush();

        testingUrlRepository.deleteAll();
        testingUrlRepository.flush();
    }

    @Test
    void contextLoads() {
        assertEmpty();
    }

    private void assertEmpty() {
        List<TodoItem> result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        assertTestingUrlRepositoryEmpty();
    }

    private void assertTestingUrlRepositoryEmpty() {
        List<TestingUrl> testingUrls = testingUrlRepository.findAll();
        assertNotNull(testingUrls);
        assertTrue(testingUrls.isEmpty());
    }

    @Test
    void testSaveAndFlush() {
        TodoItem expectedTodoItem = TodoItemRepositoryIT.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryIT.assertObjects(expectedTodoItem, actualTodoItem);

        TestingUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        TestingUrl actual = testingUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);
    }

    private static TestingUrl buildObject(TodoItem todoItem) {
        return TestingUrl.builder().url("url").todoItem(todoItem).build();
    }

    private static void assertObjects(TestingUrl expected, TestingUrl actual) {
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getTodoItem().getId(), actual.getTodoItem().getId());
    }

    @Test
    void testDelete() {
        TodoItem expectedTodoItem = TodoItemRepositoryIT.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryIT.assertObjects(expectedTodoItem, actualTodoItem);

        TestingUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        TestingUrl actual = testingUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);

        testingUrlRepository.delete(actual);
        testingUrlRepository.flush();

        Optional<TestingUrl> result = testingUrlRepository.findById(actual.getId());
        assertNotNull(result);
        assertTrue(result.isEmpty());

        assertTestingUrlRepositoryEmpty();
    }

    @Test
    void testFindByUrlAndTodoItemId() {
        TodoItem expectedTodoItem = TodoItemRepositoryIT.buildObject();
        assertNull(expectedTodoItem.getId());

        TodoItem actualTodoItem = todoItemRepository.saveAndFlush(expectedTodoItem);
        assertNotNull(actualTodoItem.getId());

        TodoItemRepositoryIT.assertObjects(expectedTodoItem, actualTodoItem);

        TestingUrl expected = buildObject(actualTodoItem);
        assertNull(expected.getId());

        TestingUrl actual = testingUrlRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());
        assertObjects(expected, actual);

        Optional<TestingUrl> result =
                testingUrlRepository.findByUrlAndTodoItemId(actual.getUrl(), actualTodoItem.getId());
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertObjects(expected, result.get());
    }
}
