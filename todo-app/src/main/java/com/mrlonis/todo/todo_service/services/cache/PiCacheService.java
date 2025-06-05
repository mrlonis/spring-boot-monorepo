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
public class PiCacheService {
    private TodoItemRepository todoItemRepository;

    @Cacheable("allPis")
    public List<String> getAllPis() {
        var todoItems = todoItemRepository.findAll();
        Set<String> pis = todoItems.stream().map(TodoItem::getPi).collect(Collectors.toUnmodifiableSet());
        return pis.stream().toList();
    }

    @CacheEvict(value = "allPis", allEntries = true)
    public void evictAllPisCache() {
        log.info("Evicting allPis cache");
    }
}
