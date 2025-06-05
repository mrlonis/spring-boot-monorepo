package com.mrlonis.todo.todo_service.mappers;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoItemMapper {
    public static TodoItemDto mapTodoItemToTodoItemDto(TodoItem todoItem) {
        return TodoItemDto.builder()
                .id(todoItem.getId())
                .title(todoItem.getTitle())
                .jiraUrl(todoItem.getJiraUrl())
                .prUrls(todoItem.getPrUrls())
                .cloudForgeConsoleUrl(todoItem.getCloudForgeConsoleUrl())
                .releaseRequestUrl(todoItem.getReleaseRequestUrl())
                .urlsUsedForTesting(todoItem.getUrlsUsedForTesting())
                .completed(todoItem.isCompleted())
                .oneNoteUrl(todoItem.getOneNoteUrl())
                .createdOn(todoItem.getCreatedOn())
                .completedOn(todoItem.getCompletedOn())
                .pi(todoItem.getPi())
                .sprint(todoItem.getSprint())
                .type(todoItem.getType())
                .archived(todoItem.isArchived())
                .build();
    }
}
