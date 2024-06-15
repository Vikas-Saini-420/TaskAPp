package com.example.ToDoApp.service;

import com.example.ToDoApp.model.AppUser;
import com.example.ToDoApp.repository.TaskRepository;
import com.example.ToDoApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppUser getUser(String username) {
        AppUser u = userRepository.findByUsername(username);
        return u;
    }

    @Override
    public List<AppUser> getAllUsers() {
        List<AppUser> appUsers = userRepository.findAll();
        return appUsers;
    }

    @Override
    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AppUser getUserByEmail(String email) {
        AppUser u = userRepository.findByEmail(email);
        return u;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        AppUser u = userRepository.save(appUser);
        return u;
    }

    @Override
    public AppUser updateUser(AppUser appUser, Long userId) {
        AppUser u = userRepository.findById(userId).orElse(null);
        if(u != null) {
            u.setEmail(appUser.getEmail());
            u.setRole(appUser.getRole());
            u.setName(appUser.getName());
            u.setPassword(appUser.getPassword());
            return userRepository.save(u);
        }
        return u;
    }

    @Transactional
    @Override
    public AppUser deleteUser(Long id) {
        AppUser u = userRepository.findById(id).orElse(null);
        if(u != null) {
            userRepository.delete(u);
        }
        taskRepository.deleteByUserId(id);
        return u;
    }

}
