package com.example.vidium.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String bio;
    private LocalDateTime createdAt;
    private Integer postCount;
}
