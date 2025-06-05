package com.mrlonis.todo.todo_service.entities;

import com.mrlonis.todo.todo_service.enums.TodoItemType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.checkerframework.checker.nullness.qual.Nullable;

@Entity(name = "todo_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(name = "jira_url")
    @Nullable private String jiraUrl;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pr_url", joinColumns = @JoinColumn(name = "todo_item_id"))
    @Column(name = "url")
    @Builder.Default
    private List<String> prUrls = new LinkedList<>();

    @Column(name = "cloud_forge_console_url")
    @Nullable private String cloudForgeConsoleUrl;

    @Column(name = "release_request_url")
    @Nullable private String releaseRequestUrl;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "testing_url", joinColumns = @JoinColumn(name = "todo_item_id"))
    @Column(name = "url")
    @Builder.Default
    private List<String> urlsUsedForTesting = new LinkedList<>();

    @Column
    private boolean completed;

    @Column(name = "onenote_url")
    @Nullable private String oneNoteUrl;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "completed_on")
    private ZonedDateTime completedOn;

    @Column
    private String pi;

    @Column
    private int sprint;

    @Column
    private TodoItemType type;

    @Column
    private boolean archived;
}
