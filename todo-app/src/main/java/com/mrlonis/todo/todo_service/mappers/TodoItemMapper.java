package com.mrlonis.todo.todo_service.mappers;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class TodoItemMapper {
    private final PrUrlRepository prUrlRepository;
    private final TestingUrlRepository testingUrlRepository;

    @Transactional
    public TodoItemDto mapTodoItemToTodoItemDto(TodoItem todoItem) {
        TodoItemDto dto = TodoItemDto.builder()
                .id(todoItem.getId())
                .title(todoItem.getTitle())
                .jiraUrl(todoItem.getJiraUrl())
                .cloudForgeConsoleUrl(todoItem.getCloudForgeConsoleUrl())
                .releaseRequestUrl(todoItem.getReleaseRequestUrl())
                .completed(todoItem.isCompleted())
                .oneNoteUrl(todoItem.getOneNoteUrl())
                .createdOn(todoItem.getCreatedOn())
                .completedOn(todoItem.getCompletedOn())
                .pi(todoItem.getPi())
                .sprint(todoItem.getSprint())
                .type(todoItem.getType())
                .archived(todoItem.isArchived())
                .build();

        List<String> prUrls = new ArrayList<>();
        for (PrUrl prUrl : todoItem.getPrUrls()) {
            Optional<PrUrl> savedPrUrl = prUrlRepository.findById(prUrl.getId());
            savedPrUrl.ifPresent(url -> prUrls.add(url.getUrl()));
        }
        dto.setPrUrls(prUrls);

        List<String> testingUrls = new ArrayList<>();
        for (TestingUrl testingUrl : todoItem.getUrlsUsedForTesting()) {
            Optional<TestingUrl> savedTestingUrl = testingUrlRepository.findById(testingUrl.getId());
            savedTestingUrl.ifPresent(url -> testingUrls.add(url.getUrl()));
        }
        dto.setUrlsUsedForTesting(testingUrls);

        return dto;
    }
}
