package com.mrlonis.todo.todo_service.mappers;

import static com.mrlonis.todo.todo_service.test.TestUtilities.buildDefaultTodoItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
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
class TodoItemMapperIT {
    @Autowired
    private TodoItemMapper todoItemMapper;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    void testMapTodoItemToTodoItemDto() {
        TodoItem todoItem = buildDefaultTodoItem();

        TodoItemDto todoItemDto = todoItemMapper.mapTodoItemToTodoItemDto(todoItem);

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
        TodoItem todoItem = buildDefaultTodoItem();
        todoItem.getPrUrls()
                .add(PrUrl.builder()
                        .url("https://example.com/pr1")
                        .todoItem(todoItem)
                        .build());
        todoItem.getPrUrls()
                .add(PrUrl.builder()
                        .url("https://example.com/pr2")
                        .todoItem(todoItem)
                        .build());

        TodoItemDto todoItemDto = todoItemMapper.mapTodoItemToTodoItemDto(todoItemRepository.saveAndFlush(todoItem));

        assertEquals(2, todoItemDto.getPrUrls().size());
        assertEquals("https://example.com/pr1", todoItemDto.getPrUrls().get(0));
        assertEquals("https://example.com/pr2", todoItemDto.getPrUrls().get(1));
    }

    @Test
    void testMapTodoItemToTodoItemDto_withUrlsUsedForTesting() {
        TodoItem todoItem = buildDefaultTodoItem();
        todoItem.getUrlsUsedForTesting()
                .add(TestingUrl.builder()
                        .url("https://example.com/test1")
                        .todoItem(todoItem)
                        .build());
        todoItem.getUrlsUsedForTesting()
                .add(TestingUrl.builder()
                        .url("https://example.com/test2")
                        .todoItem(todoItem)
                        .build());

        TodoItemDto todoItemDto = todoItemMapper.mapTodoItemToTodoItemDto(todoItemRepository.saveAndFlush(todoItem));

        assertEquals(2, todoItemDto.getUrlsUsedForTesting().size());
        assertEquals(
                "https://example.com/test1", todoItemDto.getUrlsUsedForTesting().get(0));
        assertEquals(
                "https://example.com/test2", todoItemDto.getUrlsUsedForTesting().get(1));
    }
}
