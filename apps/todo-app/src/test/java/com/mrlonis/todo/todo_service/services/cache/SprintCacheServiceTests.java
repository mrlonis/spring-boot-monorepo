package com.mrlonis.todo.todo_service.services.cache;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SprintCacheServiceTests {
    @InjectMocks
    private SprintCacheService sprintCacheService;

    @Mock
    private TodoItemRepository todoItemRepository;

    @AfterEach
    void tearDown() {
        sprintCacheService.evictAllSprintsCache();
    }

    @Test
    void testGetAllPis() {
        when(todoItemRepository.findAll())
                .thenReturn(List.of(TodoItem.builder().sprint(1).build()));

        List<Integer> result = sprintCacheService.getAllSprints();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());

        verify(todoItemRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPis_cached() {
        when(todoItemRepository.findAll())
                .thenReturn(List.of(TodoItem.builder().sprint(1).build()));

        List<Integer> result = sprintCacheService.getAllSprints();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());

        verify(todoItemRepository, times(1)).findAll();

        result = sprintCacheService.getAllSprints();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());

        verify(todoItemRepository, times(1)).findAll();
    }

    @Test
    void testEvictAllPisCache() {
        when(todoItemRepository.findAll())
                .thenReturn(List.of(TodoItem.builder().sprint(1).build()));

        List<Integer> result = sprintCacheService.getAllSprints();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());

        verify(todoItemRepository, times(1)).findAll();

        assertDoesNotThrow(() -> sprintCacheService.evictAllSprintsCache());

        result = sprintCacheService.getAllSprints();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());

        verify(todoItemRepository, times(2)).findAll();
    }
}
