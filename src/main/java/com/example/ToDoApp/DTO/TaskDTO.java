package com.example.ToDoApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private Long listId;
    private LocalDate createdDate = LocalDate.now();
    private LocalDate dueDate;
}
