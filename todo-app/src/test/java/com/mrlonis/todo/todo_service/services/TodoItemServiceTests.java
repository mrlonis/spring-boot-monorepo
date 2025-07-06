package com.mrlonis.todo.todo_service.services;

import static com.mrlonis.todo.todo_service.test.TestUtilities.buildDefaultTodoItem;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.enums.TodoItemType;
import com.mrlonis.todo.todo_service.mappers.TodoItemMapper;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoItemServiceTests {
    @InjectMocks
    private TodoItemService todoItemService;

    @Mock
    private TodoItemRepository todoItemRepository;

    @Mock
    private PrUrlRepository prUrlRepository;

    @Mock
    private TestingUrlRepository testingUrlRepository;

    @Mock
    private MetadataService metadataService;

    @Mock
    private TodoItemMapper todoItemMapper;

    @Test
    void testGetTodoItems() {
        TodoItem expectedTodoItem = buildDefaultTodoItem();
        List<TodoItem> expectedTodoItems = List.of(expectedTodoItem);
        when(todoItemRepository.findAll()).thenReturn(expectedTodoItems);
        when(todoItemMapper.mapTodoItemToTodoItemDto(expectedTodoItem))
                .thenReturn(TodoItemDto.builder().build());

        List<TodoItemDto> result = todoItemService.getTodoItems();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testCreateOrUpdateTodoItem_create() {
        TodoItemDto todoItemDto = TodoItemDto.builder()
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
        TodoItem todoItem = buildDefaultTodoItem();
        todoItem.setId(1L);
        when(todoItemRepository.saveAndFlush(any(TodoItem.class))).thenReturn(todoItem);
        doNothing().when(metadataService).evictAllCaches();
        when(todoItemRepository.getReferenceById(1L)).thenReturn(todoItem);
        when(todoItemMapper.mapTodoItemToTodoItemDto(todoItem)).thenReturn(todoItemDto);

        TodoItemDto result = assertDoesNotThrow(() -> todoItemService.createOrUpdateTodoItem(todoItemDto));
        assertNotNull(result);
    }

    @Test
    void testGetTodoItemsByPi() {
        TodoItem expectedTodoItem = buildDefaultTodoItem();
        List<TodoItem> expectedTodoItems = List.of(expectedTodoItem);
        when(todoItemRepository.findAll()).thenReturn(expectedTodoItems);
        when(todoItemMapper.mapTodoItemToTodoItemDto(expectedTodoItem))
                .thenReturn(TodoItemDto.builder().pi("1").build());

        Map<String, List<TodoItemDto>> result = todoItemService.getTodoItemsByPi();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_doNotHideCompleted_notArchived() {
        when(todoItemRepository.findAll()).thenReturn(List.of(buildDefaultTodoItem()));
        when(todoItemMapper.mapTodoItemToTodoItemDto(any(TodoItem.class)))
                .thenReturn(TodoItemDto.builder().pi("1").sprint(0).build());

        Map<String, Map<Integer, List<TodoItemDto>>> result = todoItemService.getTodoItemsByPiAndBySprint(false, false);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_doHideCompleted_notArchived() {
        when(todoItemRepository.findAll()).thenReturn(List.of(buildDefaultTodoItem()));
        when(todoItemMapper.mapTodoItemToTodoItemDto(any(TodoItem.class)))
                .thenReturn(
                        TodoItemDto.builder().pi("1").sprint(0).completed(true).build());

        Map<String, Map<Integer, List<TodoItemDto>>> result = todoItemService.getTodoItemsByPiAndBySprint(true, false);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_doNotHideCompleted_archived() {
        when(todoItemRepository.findAll()).thenReturn(List.of(buildDefaultTodoItem()));
        when(todoItemMapper.mapTodoItemToTodoItemDto(any(TodoItem.class)))
                .thenReturn(
                        TodoItemDto.builder().pi("1").sprint(0).archived(true).build());

        Map<String, Map<Integer, List<TodoItemDto>>> result = todoItemService.getTodoItemsByPiAndBySprint(false, true);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetTodoItemsByPiAndBySprint_doHideCompleted_archived() {
        when(todoItemRepository.findAll()).thenReturn(List.of(buildDefaultTodoItem()));
        when(todoItemMapper.mapTodoItemToTodoItemDto(any(TodoItem.class)))
                .thenReturn(TodoItemDto.builder()
                        .pi("1")
                        .sprint(0)
                        .completed(true)
                        .archived(true)
                        .build());

        Map<String, Map<Integer, List<TodoItemDto>>> result = todoItemService.getTodoItemsByPiAndBySprint(true, true);
        assertNotNull(result);
        // Archived always returns items, even if completed
        assertEquals(1, result.size());
    }
}
