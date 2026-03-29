package com.mrlonis.todo.todo_service.repositories;

import static com.mrlonis.todo.todo_service.test.TestUtilities.assertTodoItems;
import static com.mrlonis.todo.todo_service.test.TestUtilities.buildDefaultTodoItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.entities.ValidationUrl;
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
class TodoItemRepositoryIT {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private PrUrlRepository prUrlRepository;

    @Autowired
    private ValidationUrlRepository validationUrlRepository;

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
        todoItemRepository.flush();

        prUrlRepository.deleteAll();
        prUrlRepository.flush();

        validationUrlRepository.deleteAll();
        validationUrlRepository.flush();
    }

    @Test
    void contextLoads() {
        assertEmpty();
    }

    private void assertEmpty() {
        List<TodoItem> result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        List<PrUrl> prUrls = prUrlRepository.findAll();
        assertNotNull(prUrls);
        assertTrue(prUrls.isEmpty());

        List<ValidationUrl> validationUrls = validationUrlRepository.findAll();
        assertNotNull(validationUrls);
        assertTrue(validationUrls.isEmpty());
    }

    @Test
    void testSaveAndFlush() {
        TodoItem expected = buildDefaultTodoItem();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertTodoItems(expected, actual);
    }

    @Test
    void testDelete() {
        TodoItem expected = buildDefaultTodoItem();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertTodoItems(expected, actual);

        todoItemRepository.delete(actual);
        todoItemRepository.flush();

        Optional<TodoItem> result = todoItemRepository.findById(actual.getId());
        assertNotNull(result);
        assertTrue(result.isEmpty());

        assertEmpty();
    }

    @Test
    void testSaveAndFlush_withPrUrls() {
        // Create a TodoItem with no PrUrls
        TodoItem expected = buildDefaultTodoItem();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertTodoItems(expected, actual);

        // Add a PrUrl to the TodoItem
        PrUrl prUrl = PrUrl.builder().url("url").todoItem(actual).build();
        assertNull(prUrl.getId());

        actual.getPrUrls().add(prUrl);
        actual = todoItemRepository.saveAndFlush(actual);

        // Verify the PrUrl was saved
        assertEquals(1, actual.getPrUrls().size());

        // Verify the PrUrl is present in the database
        Optional<PrUrl> actualPrUrl =
                prUrlRepository.findById(actual.getPrUrls().getFirst().getId());
        assertNotNull(actualPrUrl);
        assertTrue(actualPrUrl.isPresent());
    }

    @Test
    void testSaveAndFlush_withUrlsUsedForTesting() {
        // Create a TodoItem with no TestingUrls
        TodoItem expected = buildDefaultTodoItem();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertTodoItems(expected, actual);

        // Add a TestingUrl to the TodoItem
        ValidationUrl validationUrl =
                ValidationUrl.builder().url("testingUrl").todoItem(actual).build();
        assertNull(validationUrl.getId());

        actual.getUrlsUsedForTesting().add(validationUrl);
        actual = todoItemRepository.saveAndFlush(actual);

        // Verify the TestingUrl was saved
        assertEquals(1, actual.getUrlsUsedForTesting().size());

        // Verify the TestingUrl is present in the database
        Optional<ValidationUrl> actualTestingUrl = validationUrlRepository.findById(
                actual.getUrlsUsedForTesting().getFirst().getId());
        assertNotNull(actualTestingUrl);
        assertTrue(actualTestingUrl.isPresent());
    }
}
