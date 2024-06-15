package com.example.ToDoApp.repository;

import com.example.ToDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    Task getTaskById(Long id);

    @Query("SELECT t FROM Task t WHERE t.title = ?1")
    List<Task> getTasksByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.description = ?1")
    List<Task> getTasksByDescription(String description);

    @Query("SELECT t FROM Task t WHERE t.completed = ?1")
    List<Task> getTasksByCompleted(boolean completed);

    @Query("SELECT t FROM Task t WHERE t.userId = ?1")
    List<Task> getTasksByUserId(Long userId);

    @Query("SELECT t FROM Task t WHERE t.listId = ?1")
    List<Task> getTasksByListId(Long listId);

    @Query("SELECT t FROM Task t WHERE t.dueDate = ?1")
    List<Task> getTasksByDueDate(LocalDate dueDate);

    @Query("SELECT t FROM Task t")
    List<Task> getAllTasks();


}
