package com.mrlonis.todo.todo_service.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.enums.TodoItemType;
import com.mrlonis.todo.todo_service.test.TestcontainersConfiguration;
import java.time.ZonedDateTime;
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
class TodoItemRepositoryTests {
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
    }

    @Test
    void contextLoads() {
        assertEmpty();
    }

    private void assertEmpty() {
        List<TodoItem> result = todoItemRepository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveAndFlush() {
        TodoItem expected = buildObject();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertObjects(expected, actual);
    }

    private TodoItem buildObject() {
        return TodoItem.builder()
                .title("title")
                .jiraUrl("jiraUrl")
                .cloudForgeConsoleUrl("cloudForgeConsoleUrl")
                .releaseRequestUrl("releaseRequestUrl")
                .completed(false)
                .oneNoteUrl("oneNoteUrl")
                .createdOn(ZonedDateTime.now())
                .completedOn(ZonedDateTime.now())
                .pi("pi")
                .sprint(0)
                .type(TodoItemType.ASSIGNED)
                .archived(false)
                .build();
    }

    private void assertObjects(TodoItem expected, TodoItem actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getJiraUrl(), actual.getJiraUrl());
        assertEquals(expected.getCloudForgeConsoleUrl(), actual.getCloudForgeConsoleUrl());
        assertEquals(expected.getReleaseRequestUrl(), actual.getReleaseRequestUrl());
        assertEquals(expected.isCompleted(), actual.isCompleted());
        assertEquals(expected.getOneNoteUrl(), actual.getOneNoteUrl());
        assertEquals(expected.getCreatedOn(), actual.getCreatedOn());
        assertEquals(expected.getCompletedOn(), actual.getCompletedOn());
        assertEquals(expected.getPi(), actual.getPi());
        assertEquals(expected.getSprint(), actual.getSprint());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.isArchived(), actual.isArchived());
    }

    @Test
    void testDelete() {
        TodoItem expected = buildObject();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertObjects(expected, actual);

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
        TodoItem expected = buildObject();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertObjects(expected, actual);

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
        TodoItem expected = buildObject();
        assertNull(expected.getId());

        TodoItem actual = todoItemRepository.saveAndFlush(expected);
        assertNotNull(actual.getId());

        assertObjects(expected, actual);

        // Add a TestingUrl to the TodoItem
        TestingUrl testingUrl =
                TestingUrl.builder().url("testingUrl").todoItem(actual).build();
        assertNull(testingUrl.getId());

        actual.getUrlsUsedForTesting().add(testingUrl);
        actual = todoItemRepository.saveAndFlush(actual);

        // Verify the TestingUrl was saved
        assertEquals(1, actual.getUrlsUsedForTesting().size());

        // Verify the TestingUrl is present in the database
        Optional<TestingUrl> actualTestingUrl = testingUrlRepository.findById(
                actual.getUrlsUsedForTesting().getFirst().getId());
        assertNotNull(actualTestingUrl);
        assertTrue(actualTestingUrl.isPresent());
    }
}
