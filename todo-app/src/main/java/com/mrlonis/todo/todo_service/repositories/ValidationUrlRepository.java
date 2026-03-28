package com.mrlonis.todo.todo_service.repositories;

import com.mrlonis.todo.todo_service.entities.ValidationUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationUrlRepository extends JpaRepository<ValidationUrl, Long> {
    Optional<ValidationUrl> findByUrlAndTodoItemId(String url, Long todoItemId);
}
