package com.mrlonis.todo.todo_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoItemNotFoundException extends Exception {
    public TodoItemNotFoundException(String message) {
        super(message);
    }
}
