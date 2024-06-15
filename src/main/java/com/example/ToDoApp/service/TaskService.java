package com.example.ToDoApp.service;

import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements TaskInterface {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public void addTask(String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate) {
        Task task = new Task(title, description, completed, userId, listId, dueDate);
        taskRepository.save(task);
        return;
    }

    @Override
    public void addTask(String title, String description, Long userId, Long listId, LocalDate dueDate) {
        Task task = new Task(title, description, false, userId, listId, dueDate);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate) {
        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setTitle(title);
            task.setDescription(description);
            task.setCompleted(completed);
            task.setUserId(userId);
            task.setListId(listId);
            task.setDueDate(dueDate);
            taskRepository.save(task);
        }
    }

    @Override
    public void updateTask(Long id, String title, String description, Long userId, Long listId, LocalDate dueDate) {

        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setTitle(title);
            task.setDescription(description);
            task.setUserId(userId);
            task.setListId(listId);
            task.setDueDate(dueDate);
            taskRepository.save(task);
        }
    }

    @Override
    public Task deleteTask(Long id) {

        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            taskRepository.delete(task);
        }
        return task;
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public List<Task> getTasksByListId(Long listId) {
        return taskRepository.getTasksByListId(listId);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.getTasksByUserId(userId);
    }

    @Override
    public List<Task> getTasksByDueDate(LocalDate dueDate) {
        return taskRepository.getTasksByDueDate(dueDate);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, Long id) {
        Task oldTask = taskRepository.getTaskById(id);
        if(oldTask != null) {
            oldTask.setTitle(task.getTitle());
            oldTask.setDescription(task.getDescription());
            oldTask.setCompleted(task.isCompleted());
            oldTask.setUserId(task.getUserId());
            oldTask.setListId(task.getListId());
            oldTask.setDueDate(task.getDueDate());
            taskRepository.save(oldTask);
        }
        return oldTask;
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task completeTask(Long id) {
        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setCompleted(true);
            taskRepository.save(task);
        }
        return task;
    }
}
