package com.mrlonis.todo.todo_service.mappers;

import static com.mrlonis.todo.todo_service.repositories.TodoItemRepositoryIT.buildObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import org.junit.jupiter.api.Test;

class TodoItemMapperTests {
    @Test
    void testMapTodoItemToTodoItemDto() {
        TodoItem todoItem = buildObject();

        TodoItemDto todoItemDto = TodoItemMapper.mapTodoItemToTodoItemDto(todoItem);

        assertEquals(todoItem.getTitle(), todoItemDto.getTitle());
        assertEquals(todoItem.getJiraUrl(), todoItemDto.getJiraUrl());
        assertEquals(todoItem.getCloudForgeConsoleUrl(), todoItemDto.getCloudForgeConsoleUrl());
        assertEquals(todoItem.getReleaseRequestUrl(), todoItemDto.getReleaseRequestUrl());
        assertEquals(todoItem.isCompleted(), todoItemDto.isCompleted());
        assertEquals(todoItem.getOneNoteUrl(), todoItemDto.getOneNoteUrl());
        assertEquals(todoItem.getCreatedOn(), todoItemDto.getCreatedOn());
        assertEquals(todoItem.getCompletedOn(), todoItemDto.getCompletedOn());
        assertEquals(todoItem.getPi(), todoItemDto.getPi());
        assertEquals(todoItem.getSprint(), todoItemDto.getSprint());
        assertEquals(todoItem.getType(), todoItemDto.getType());
        assertEquals(todoItem.isArchived(), todoItemDto.isArchived());
    }

    @Test
    void testMapTodoItemToTodoItemDto_withPrUrls() {
        TodoItem todoItem = buildObject();
        todoItem.getPrUrls().add(PrUrl.builder().url("https://example.com/pr1").build());
        todoItem.getPrUrls().add(PrUrl.builder().url("https://example.com/pr2").build());

        TodoItemDto todoItemDto = TodoItemMapper.mapTodoItemToTodoItemDto(todoItem);

        assertEquals(2, todoItemDto.getPrUrls().size());
        assertEquals("https://example.com/pr1", todoItemDto.getPrUrls().get(0));
        assertEquals("https://example.com/pr2", todoItemDto.getPrUrls().get(1));
    }

    @Test
    void testMapTodoItemToTodoItemDto_withUrlsUsedForTesting() {
        TodoItem todoItem = buildObject();
        todoItem.getUrlsUsedForTesting()
                .add(TestingUrl.builder().url("https://example.com/test1").build());
        todoItem.getUrlsUsedForTesting()
                .add(TestingUrl.builder().url("https://example.com/test2").build());

        TodoItemDto todoItemDto = TodoItemMapper.mapTodoItemToTodoItemDto(todoItem);

        assertEquals(2, todoItemDto.getUrlsUsedForTesting().size());
        assertEquals(
                "https://example.com/test1", todoItemDto.getUrlsUsedForTesting().get(0));
        assertEquals(
                "https://example.com/test2", todoItemDto.getUrlsUsedForTesting().get(1));
    }
}
