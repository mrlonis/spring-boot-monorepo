package com.mrlonis.todo.todo_service.repositories;

import com.mrlonis.todo.todo_service.entities.PrUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrUrlRepository extends JpaRepository<PrUrl, Long> {
    Optional<PrUrl> findByUrlAndTodoItemId(String url, Long todoItemId);
}
