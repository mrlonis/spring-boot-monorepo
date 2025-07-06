package com.mrlonis.todo.todo_service.services;

import static java.util.stream.Collectors.groupingBy;

import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.entities.PrUrl;
import com.mrlonis.todo.todo_service.entities.TestingUrl;
import com.mrlonis.todo.todo_service.entities.TodoItem;
import com.mrlonis.todo.todo_service.exceptions.TodoItemNotFoundException;
import com.mrlonis.todo.todo_service.mappers.TodoItemMapper;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TodoItemService {
    private TodoItemRepository todoItemRepository;
    private PrUrlRepository prUrlRepository;
    private TestingUrlRepository testingUrlRepository;
    private MetadataService metadataService;
    private TodoItemMapper todoItemMapper;

    @Transactional
    public List<TodoItemDto> getTodoItems() {
        return todoItemRepository.findAll().stream()
                .map(todoItem -> todoItemMapper.mapTodoItemToTodoItemDto(todoItem))
                .toList();
    }

    @Transactional
    public TodoItemDto createOrUpdateTodoItem(TodoItemDto todoItemDto) throws TodoItemNotFoundException {
        log.info("Received the following TodoItemDto: {}", todoItemDto);
        TodoItem todoItem;
        if (todoItemDto.getId() != null) {
            Optional<TodoItem> todoItemToUpdate = todoItemRepository.findById(todoItemDto.getId());
            if (todoItemToUpdate.isEmpty()) {
                throw new TodoItemNotFoundException("TodoItem with id " + todoItemDto.getId() + " not found");
            }
            todoItem = todoItemToUpdate.get();
        } else {
            todoItem = TodoItem.builder().build();
        }
        todoItem.setTitle(todoItemDto.getTitle())
                .setJiraUrl(todoItemDto.getJiraUrl())
                .setCloudForgeConsoleUrl(todoItemDto.getCloudForgeConsoleUrl())
                .setReleaseRequestUrl(todoItemDto.getReleaseRequestUrl())
                .setCompleted(todoItemDto.isCompleted())
                .setOneNoteUrl(todoItemDto.getOneNoteUrl())
                .setCompletedOn(todoItemDto.getCompletedOn())
                .setPi(todoItemDto.getPi())
                .setSprint(todoItemDto.getSprint())
                .setType(todoItemDto.getType())
                .setArchived(todoItemDto.isArchived());
        if (todoItemDto.getCreatedOn() != null) {
            todoItem.setCreatedOn(todoItemDto.getCreatedOn());
        }

        todoItem = todoItemRepository.saveAndFlush(todoItem);
        // TODO - It is possible I decide Sprints & PIs cannot be modified, so this might only need to happen on create
        metadataService.evictAllCaches();

        if (todoItemDto.getPrUrls() != null && !todoItemDto.getPrUrls().isEmpty()) {
            for (String prUrl : todoItemDto.getPrUrls()) {
                Optional<PrUrl> prUrlToUpdate = prUrlRepository.findByUrlAndTodoItemId(prUrl, todoItem.getId());
                if (prUrlToUpdate.isEmpty()) {
                    PrUrl newPrUrl =
                            PrUrl.builder().url(prUrl).todoItem(todoItem).build();
                    prUrlRepository.saveAndFlush(newPrUrl);
                } else {
                    PrUrl prUrlToUpdateValue = prUrlToUpdate.get();
                    prUrlToUpdateValue.setUrl(prUrl);
                    prUrlRepository.saveAndFlush(prUrlToUpdateValue);
                }
            }
        }

        if (todoItemDto.getUrlsUsedForTesting() != null
                && !todoItemDto.getUrlsUsedForTesting().isEmpty()) {
            for (String testingUrl : todoItemDto.getUrlsUsedForTesting()) {
                Optional<TestingUrl> testingUrlToUpdate =
                        testingUrlRepository.findByUrlAndTodoItemId(testingUrl, todoItem.getId());
                if (testingUrlToUpdate.isEmpty()) {
                    TestingUrl newTestingUrl = TestingUrl.builder()
                            .url(testingUrl)
                            .todoItem(todoItem)
                            .build();
                    testingUrlRepository.saveAndFlush(newTestingUrl);
                } else {
                    TestingUrl testingUrlToUpdateValue = testingUrlToUpdate.get();
                    testingUrlToUpdateValue.setUrl(testingUrl);
                    testingUrlRepository.saveAndFlush(testingUrlToUpdateValue);
                }
            }
        }

        return todoItemMapper.mapTodoItemToTodoItemDto(todoItemRepository.getReferenceById(todoItem.getId()));
    }

    @Transactional
    public Map<String, List<TodoItemDto>> getTodoItemsByPi() {
        return todoItemRepository.findAll().stream()
                .map(todoItem -> todoItemMapper.mapTodoItemToTodoItemDto(todoItem))
                .collect(groupingBy(TodoItemDto::getPi));
    }

    @Transactional
    public Map<String, Map<Integer, List<TodoItemDto>>> getTodoItemsByPiAndBySprint(
            Boolean hideCompleted, Boolean archived) {
        var temp = todoItemRepository.findAll().stream()
                .map(todoItem -> todoItemMapper.mapTodoItemToTodoItemDto(todoItem));
        if (Boolean.TRUE.equals(hideCompleted) && !Boolean.TRUE.equals(archived)) {
            temp = temp.filter(todoItemDto -> !todoItemDto.isCompleted());
        }
        if (Boolean.TRUE.equals(archived)) {
            temp = temp.filter(TodoItemDto::isArchived);
        } else {
            temp = temp.filter(todoItemDto -> !todoItemDto.isArchived());
        }
        return temp.collect(groupingBy(TodoItemDto::getPi, groupingBy(TodoItemDto::getSprint)));
    }
}
