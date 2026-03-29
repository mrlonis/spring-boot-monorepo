package com.mrlonis.todo.todo_service.services;

import com.mrlonis.todo.todo_service.services.cache.PiCacheService;
import com.mrlonis.todo.todo_service.services.cache.SprintCacheService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MetadataService {
    private PiCacheService piCacheService;
    private SprintCacheService sprintCacheService;

    public List<String> getAllPis() {
        return piCacheService.getAllPis();
    }

    public List<Integer> getAllSprints() {
        return sprintCacheService.getAllSprints();
    }

    public void evictAllCaches() {
        piCacheService.evictAllPisCache();
        sprintCacheService.evictAllSprintsCache();
    }
}
