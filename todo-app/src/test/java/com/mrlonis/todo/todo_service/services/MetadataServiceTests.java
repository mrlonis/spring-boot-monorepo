package com.mrlonis.todo.todo_service.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mrlonis.todo.todo_service.services.cache.PiCacheService;
import com.mrlonis.todo.todo_service.services.cache.SprintCacheService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MetadataServiceTests {
    @InjectMocks
    private MetadataService metadataService;

    @Mock
    private PiCacheService piCacheService;

    @Mock
    private SprintCacheService sprintCacheService;

    @Test
    void testGetAllPis() {
        when(piCacheService.getAllPis()).thenReturn(List.of("PI1", "PI2", "PI3"));

        List<String> result = metadataService.getAllPis();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testGetAllSprints() {
        when(sprintCacheService.getAllPis()).thenReturn(List.of(1, 2, 3));

        List<Integer> result = metadataService.getAllSprints();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testEvictAllCaches() {
        doNothing().when(piCacheService).evictAllPisCache();
        doNothing().when(sprintCacheService).evictAllSprintsCache();

        assertDoesNotThrow(() -> metadataService.evictAllCaches());
    }
}
