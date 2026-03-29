package com.mrlonis.todo.todo_service.services.cache;

import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PiCacheService {
    private static final AtomicReference<List<String>> cache = new AtomicReference<>(null);
    private TodoItemRepository todoItemRepository;

    public List<String> getAllPis() {
        if (cache.get() == null) {
            cache.set(
                    todoItemRepository.findAll().stream()
                            .map(TodoItem::getPi)
                            .collect(Collectors.toUnmodifiableSet())
                            .stream()
                            .toList());
            return cache.get();
        }
        return cache.get();
    }

    public void evictAllPisCache() {
        log.info("Evicting allPis cache");
        cache.set(null);
        log.info("Evicted allPis cache");
    }
}
