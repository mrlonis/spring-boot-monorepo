package com.mrlonis.todo.todo_service.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pr_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todo_item_id")
    private Long todoItemId;

    @Column
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TodoItem todoItem;
}
