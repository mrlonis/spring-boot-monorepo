package com.mrlonis.todo.todo_service.services.cache;

import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SprintCacheService {
    private TodoItemRepository todoItemRepository;

    @Cacheable("allSprints")
    public List<Integer> getAllPis() {
        var todoItems = todoItemRepository.findAll();
        Set<Integer> pis = todoItems.stream().map(TodoItem::getSprint).collect(Collectors.toUnmodifiableSet());
        return pis.stream().toList();
    }

    @CacheEvict(value = "allSprints", allEntries = true)
    public void evictAllSprintsCache() {
        log.info("Evicting allSprints cache");
    }
}
