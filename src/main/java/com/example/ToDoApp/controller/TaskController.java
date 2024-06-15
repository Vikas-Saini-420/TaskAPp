package com.example.ToDoApp.controller;

import com.example.ToDoApp.DTO.TaskDTO;
import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.service.TaskInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("app/v1/tasks")
@Tag(name = "Task Management", description = "Task Management API")
public class TaskController {

    @Autowired
    TaskInterface taskInterface;

    @Operation(summary = "Get all tasks")
    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskList = taskInterface.getAllTasks();
        if(taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok(taskList);
    }

    @Operation(summary = "Get tasks by list ID")
    @GetMapping("/list/{listId}")
    public ResponseEntity<List<TaskDTO>> getTasksByListId(@PathVariable Long listId) {
        List<TaskDTO> taskList = taskInterface.getTasksByListId(listId);
        if(taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok(taskList);
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
       TaskDTO task = taskInterface.getTask(id);
       if(task == null) {
           return ResponseEntity.notFound().build();
       } else return ResponseEntity.ok(task);
    }

    @Operation(summary = "Add a new task")
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO task, @RequestParam Long userId) {
        return ResponseEntity.ok(taskInterface.addTask(task, userId));
    }

    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Task newTask = taskInterface.updateTask(task, id);
        if(newTask == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(newTask);
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskInterface.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Complete task")
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        Task task = taskInterface.completeTask(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }
    }

    @Operation(summary = "get task for user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long userId) {
        List<TaskDTO> taskList = taskInterface.getTasksByUserId(userId);
        if(taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok(taskList);
    }

}
