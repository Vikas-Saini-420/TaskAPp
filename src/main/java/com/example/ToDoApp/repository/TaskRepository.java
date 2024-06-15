package com.example.ToDoApp.repository;

import com.example.ToDoApp.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT t FROM Task t WHERE t.appUser.id = :userId")
    List<Task> getTasksByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.listId = ?1")
    List<Task> getTasksByListId(Long listId);

    @Query("SELECT t FROM Task t WHERE t.dueDate = ?1")
    List<Task> getTasksByDueDate(LocalDate dueDate);

    @Query("SELECT t FROM Task t")
    List<Task> getAllTasks();

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.appUser.id = :userId")
    void deleteByUserId(Long userId);

}
