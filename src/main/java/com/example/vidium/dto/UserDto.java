package com.example.vidium.dto;

import com.example.vidium.model.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.vidium.model.User}
 */
@Data
public class UserDto implements Serializable {
    Long id;
    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    String bio;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Boolean isActive;
    User.UserRole role;
    LocalDateTime lastLoginAt;
}