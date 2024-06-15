package com.example.ToDoApp.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: Add validation

    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private boolean completed;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;
    private Long listId;
    private LocalDate createdDate = LocalDate.now();
    @NotNull
    private LocalDate dueDate;

    public Task(String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.listId = listId;
        this.dueDate = dueDate;
    }

    public Task(String title, String description, Long userId, Long listId, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.listId = listId;
        this.dueDate = dueDate;
    }
}
