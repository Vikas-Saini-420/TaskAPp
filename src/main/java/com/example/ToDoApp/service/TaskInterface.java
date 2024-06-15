package com.example.ToDoApp.service;

import com.example.ToDoApp.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskInterface {

    /**
     * Add a new task with the given details to a specified list for a user.
     *
     * @param  title       the title of the task
     * @param  description the description of the task
     * @param  completed   the completion status of the task
     * @param  userId      the ID of the user the task belongs to
     * @param  listId      the ID of the list the task is associated with
     * @param  dueDate     the due date of the task
     */
    public void addTask(String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate);

    /**
     * A description of the entire Java function.
     *
     * @param  title       description of parameter
     * @param  description description of parameter
     * @param  userId      description of parameter
     * @param  listId      description of parameter
     * @param  dueDate     description of parameter
     * @return             description of return value
     */
    public void addTask(String title, String description, Long userId, Long listId, LocalDate dueDate);

    /**
     * A description of the entire Java function.
     *
     * @param  id             description of parameter
     * @param  title          description of parameter
     * @param  description    description of parameter
     * @param  completed      description of parameter
     * @param  userId         description of parameter
     * @param  listId         description of parameter
     * @param  dueDate        description of parameter
     */
    public void updateTask(Long id, String title, String description, boolean completed, Long userId, Long listId, LocalDate dueDate);

    public void updateTask(Long id, String title, String description, Long userId, Long listId, LocalDate dueDate);
    public Task deleteTask(Long id);

    public Task getTask(Long id);

    public List<Task> getAllTasks();

    public List<Task> getTasksByListId(Long listId);

    public List<Task> getTasksByUserId(Long userId);

    public List<Task> getTasksByDueDate(LocalDate dueDate);

    public Task addTask(Task task);

    public Task updateTask(Task task, Long id);

    public void deleteTask(Task task);

    Task completeTask(Long id);
}
