package com.example.vidium.dto.request;

import lombok.Data;;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String currentPassword;
    private String newPassword;

}
