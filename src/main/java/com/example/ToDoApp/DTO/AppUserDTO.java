package com.example.ToDoApp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AppUserDTO {
    private String email;
    private String name;

    private String username;
    private String role;
}
