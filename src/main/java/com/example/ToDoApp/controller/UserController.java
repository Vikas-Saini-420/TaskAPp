package com.example.ToDoApp.controller;

import com.example.ToDoApp.model.AppUser;
import com.example.ToDoApp.service.UserInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/user")
@Tag(name = "AppUser Management", description = "AppUser API")
public class UserController {

    @Autowired
    UserInterface userInterface;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser u = userInterface.getUserById(id);
        if(u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser) {
        AppUser u = userInterface.addUser(appUser);
        if(u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        AppUser u = userInterface.deleteUser(id);
        if(u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
