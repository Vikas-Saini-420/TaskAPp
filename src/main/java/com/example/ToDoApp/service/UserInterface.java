package com.example.ToDoApp.service;

import com.example.ToDoApp.model.AppUser;

import java.util.List;

public interface UserInterface {

    public AppUser getUser(String username);

    public List<AppUser> getAllUsers();

    public AppUser getUserById(Long id);

    public AppUser getUserByEmail(String email);
    public AppUser addUser(AppUser appUser);

    public AppUser updateUser(AppUser appUser, Long userId);

    public AppUser deleteUser(Long id);

}
