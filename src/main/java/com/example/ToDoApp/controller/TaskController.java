package com.example.ToDoApp.controller;

import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.service.TaskInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/tasks")
public class TaskController {

    @Autowired
    TaskInterface taskInterface;

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
         List<Task> taskList = taskInterface.getAllTasks();
         if(taskList.isEmpty()) {
             return ResponseEntity.noContent().build();
         } else return ResponseEntity.ok(taskList);
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<Task>> getTasksByListId(@PathVariable Long listId) {
        List<Task> taskList = taskInterface.getTasksByListId(listId);
        if(taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
       Task task = taskInterface.getTask(id);
       if(task == null) {
           return ResponseEntity.notFound().build();
       } else return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskInterface.addTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Task newTask = taskInterface.updateTask(task, id);
        if(newTask == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskInterface.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        Task task = taskInterface.completeTask(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }
    }

}
