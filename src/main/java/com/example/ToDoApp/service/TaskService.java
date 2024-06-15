package com.example.ToDoApp.service;

import com.example.ToDoApp.DTO.TaskDTO;
import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.model.AppUser;
import com.example.ToDoApp.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements TaskInterface {

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
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
    public TaskDTO getTask(Long id) {
        Task task = taskRepository.getTaskById(id);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        return tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();
    }

    @Override
    public List<TaskDTO> getTasksByListId(Long listId) {
        List<Task> tasks = taskRepository.getTasksByListId(listId);
        return tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        List<Task> tasks = taskRepository.getTasksByUserId(userId);
        return tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();
    }

    @Override
    public List<Task> getTasksByDueDate(LocalDate dueDate) {
        return taskRepository.getTasksByDueDate(dueDate);
    }

    @Override
    public Task addTask(TaskDTO task, Long id) {
        AppUser appUser = userService.getUserById(id);
        Task newtask = modelMapper.map(task, Task.class);
        if(appUser != null) {
            newtask.setAppUser(appUser);
            return taskRepository.save(newtask);
        } else throw new RuntimeException("AppUser not found");
    }

    @Override
    public Task updateTask(Task task, Long id) {
        Task oldTask = taskRepository.getTaskById(id);
        if(oldTask != null) {
            oldTask.setTitle(task.getTitle());
            oldTask.setDescription(task.getDescription());
            oldTask.setCompleted(task.isCompleted());
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
