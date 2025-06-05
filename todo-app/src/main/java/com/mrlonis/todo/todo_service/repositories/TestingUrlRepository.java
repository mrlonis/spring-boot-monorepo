package com.mrlonis.todo.todo_service.repositories;

import com.mrlonis.todo.todo_service.entities.TestingUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingUrlRepository extends JpaRepository<TestingUrl, Long> {
    Optional<TestingUrl> findByUrlAndTodoItemId(String url, Long todoItemId);
}
