package com.mrlonis.todo.todo_service.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.todo.todo_service.TestUtils;
import com.mrlonis.todo.todo_service.dtos.TodoItemDto;
import com.mrlonis.todo.todo_service.enums.TodoItemType;
import com.mrlonis.todo.todo_service.repositories.PrUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TestingUrlRepository;
import com.mrlonis.todo.todo_service.repositories.TodoItemRepository;
import com.mrlonis.todo.todo_service.utils.Constants;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TodoItemTests {
    private static final String EXPECTED_TODO_ITEM_JSON_PATTERN =
            "{\"id\":<id>,\"title\":\"fake\",\"jiraUrl\":\"fake\",\"prUrls\":[\"fake1\",\"fake2\",\"fake3\"],\"cloudForgeConsoleUrl\":\"fake\",\"releaseRequestUrl\":\"fake\",\"urlsUsedForTesting\":[\"fake1\",\"fake2\",\"fake3\"],\"completed\":false,\"oneNoteUrl\":\"fake\",\"createdOn\":\"<createdOn>\",\"completedOn\":\"<completedOn>\",\"pi\":\"fake\",\"sprint\":1,\"type\":\"ASSIGNED\",\"archived\":false}";

    @LocalServerPort
    private int port;

    private WebClient webClient;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private PrUrlRepository prUrlRepository;

    @Autowired
    private TestingUrlRepository testingUrlRepository;

    @BeforeEach
    void setUp() {
        webClient = WebClient.builder().baseUrl("http://localhost:" + port).build();
    }

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
        todoItemRepository.flush();
        prUrlRepository.deleteAll();
        prUrlRepository.flush();
        testingUrlRepository.deleteAll();
        testingUrlRepository.flush();
    }

    @Test
    void testSerialization() {
        TodoItem todoItem = TestUtils.createSaveAndAssertTodoItem(
                todoItemRepository,
                TestUtils.FAKE,
                TestUtils.FAKE,
                TestUtils.FAKE,
                TestUtils.FAKE,
                false,
                TestUtils.FAKE);
        TestUtils.incrementTodoItemCount();
        PrUrl prUrl1 = TestUtils.createSaveAndAssertPrUrl(prUrlRepository, todoItem, 1);
        assertNotNull(prUrl1);
        PrUrl prUrl2 = TestUtils.createSaveAndAssertPrUrl(prUrlRepository, todoItem, 2);
        assertNotNull(prUrl2);
        PrUrl prUrl3 = TestUtils.createSaveAndAssertPrUrl(prUrlRepository, todoItem, 3);
        assertNotNull(prUrl3);
        TestingUrl testingUrl1 = TestUtils.createSaveAndAssertTestingUrl(testingUrlRepository, todoItem, 1);
        assertNotNull(testingUrl1);
        TestingUrl testingUrl2 = TestUtils.createSaveAndAssertTestingUrl(testingUrlRepository, todoItem, 2);
        assertNotNull(testingUrl2);
        TestingUrl testingUrl3 = TestUtils.createSaveAndAssertTestingUrl(testingUrlRepository, todoItem, 3);
        assertNotNull(testingUrl3);
        assertTrue(TestUtils.callApiAndAssertJson(webClient, getExpectedTodoItemsJson(TestUtils.TODO_ITEM_COUNT)));
    }

    @Test
    void testDeserialization() {
        TodoItemDto todoItemDto = TodoItemDto.builder()
                .title(TestUtils.FAKE)
                .jiraUrl(TestUtils.FAKE)
                .prUrls(List.of(TestUtils.FAKE + "1", TestUtils.FAKE + "2", TestUtils.FAKE + "3"))
                .cloudForgeConsoleUrl(TestUtils.FAKE)
                .releaseRequestUrl(TestUtils.FAKE)
                .urlsUsedForTesting(List.of(TestUtils.FAKE + "1", TestUtils.FAKE + "2", TestUtils.FAKE + "3"))
                .completed(false)
                .oneNoteUrl(TestUtils.FAKE)
                .createdOn(TestUtils.CREATED_ON)
                .completedOn(TestUtils.COMPLETED_ON)
                .pi(TestUtils.FAKE)
                .sprint(TestUtils.SPRINT)
                .type(TodoItemType.ASSIGNED)
                .archived(false)
                .build();
        TodoItem todoItems = webClient
                .post()
                .uri("/api/todo/item")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(todoItemDto)
                .retrieve()
                .bodyToMono(TodoItem.class)
                .block();
        assertNotNull(todoItems);
        TestUtils.incrementTodoItemCount();

        // Call same Get logic used in other tests
        assertTrue(TestUtils.callApiAndAssertJson(webClient, getExpectedTodoItemsJson(TestUtils.TODO_ITEM_COUNT)));
    }

    private String getExpectedTodoItemJson(Long id) {
        return EXPECTED_TODO_ITEM_JSON_PATTERN
                .replace("<id>", id.toString())
                .replace("<createdOn>", TestUtils.CREATED_ON.format(Constants.DATE_TIME_FORMATTER))
                .replace("<completedOn>", TestUtils.COMPLETED_ON.format(Constants.DATE_TIME_FORMATTER));
    }

    private String getExpectedTodoItemsJson(Long id) {
        return "[" + getExpectedTodoItemJson(id) + "]";
    }
}
