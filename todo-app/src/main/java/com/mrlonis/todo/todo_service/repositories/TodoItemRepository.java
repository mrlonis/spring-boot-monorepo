package com.mrlonis.todo.todo_service.repositories;

import com.mrlonis.todo.todo_service.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {}
