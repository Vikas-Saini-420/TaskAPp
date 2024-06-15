package com.example.ToDoApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: Add validation

    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private boolean completed;
    private Long userId;
    private Long listId;
    private LocalDate createdDate = LocalDate.now();
    @NotNull
    private LocalDate dueDate;

    public Task(String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
        this.listId = listId;
        this.dueDate = dueDate;
    }

    public Task(String title, String description, Long userId, Long listId, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.listId = listId;
        this.dueDate = dueDate;
    }
}
