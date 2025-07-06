package com.mrlonis.todo.todo_service.controllers;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.exceptions.TodoItemNotFoundException;
import com.mrlonis.todo.todo_service.services.TodoItemService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TodoItemsController {
    private TodoItemService todoItemService;

    @GetMapping("/items")
    public List<TodoItemDto> getTodoItems() {
        return todoItemService.getTodoItems();
    }

    @PostMapping("/item")
    public TodoItemDto createOrUpdateTodoItem(@RequestBody TodoItemDto todoItemDto) throws TodoItemNotFoundException {
        return todoItemService.createOrUpdateTodoItem(todoItemDto);
    }

    @GetMapping("/itemsByPi")
    public Map<String, List<TodoItemDto>> getTodoItemsByPi() {
        return todoItemService.getTodoItemsByPi();
    }

    @GetMapping("/itemsByPiAndBySprint")
    public Map<String, Map<Integer, List<TodoItemDto>>> getTodoItemsByPiAndBySprint(
            @RequestParam(required = false) Boolean hideCompleted, @RequestParam(required = false) Boolean archived) {
        return todoItemService.getTodoItemsByPiAndBySprint(hideCompleted, archived);
    }
}
