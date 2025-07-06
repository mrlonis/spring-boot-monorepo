package com.mrlonis.todo.todo_service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.enums.TodoItemType;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtilities {
    public static TodoItem buildDefaultTodoItem() {
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

    public static void assertTodoItems(TodoItem expected, TodoItem actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getJiraUrl(), actual.getJiraUrl());

        if (expected.getPrUrls() == null) {
            assertNull(actual.getPrUrls());
        } else {
            assertNotNull(actual.getPrUrls());
            assertEquals(expected.getPrUrls().size(), actual.getPrUrls().size());
            for (int i = 0; i < expected.getPrUrls().size(); i++) {
                assertPrUrls(expected.getPrUrls().get(i), actual.getPrUrls().get(i));
            }
        }

        assertEquals(expected.getCloudForgeConsoleUrl(), actual.getCloudForgeConsoleUrl());
        assertEquals(expected.getReleaseRequestUrl(), actual.getReleaseRequestUrl());

        if (expected.getUrlsUsedForTesting() == null) {
            assertNull(actual.getUrlsUsedForTesting());
        } else {
            assertNotNull(actual.getUrlsUsedForTesting());
            assertEquals(
                    expected.getUrlsUsedForTesting().size(),
                    actual.getUrlsUsedForTesting().size());
            for (int i = 0; i < expected.getUrlsUsedForTesting().size(); i++) {
                assertTestingUrls(
                        expected.getUrlsUsedForTesting().get(i),
                        actual.getUrlsUsedForTesting().get(i));
            }
        }

        assertEquals(expected.isCompleted(), actual.isCompleted());
        assertEquals(expected.getOneNoteUrl(), actual.getOneNoteUrl());
        assertEquals(expected.getCreatedOn(), actual.getCreatedOn());
        assertEquals(expected.getCompletedOn(), actual.getCompletedOn());
        assertEquals(expected.getPi(), actual.getPi());
        assertEquals(expected.getSprint(), actual.getSprint());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.isArchived(), actual.isArchived());
    }

    public static void assertPrUrls(PrUrl expected, PrUrl actual) {
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getTodoItem().getId(), actual.getTodoItem().getId());
    }

    public static void assertTestingUrls(TestingUrl expected, TestingUrl actual) {
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getTodoItem().getId(), actual.getTodoItem().getId());
    }
}
