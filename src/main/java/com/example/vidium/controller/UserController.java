package com.example.vidium.controller;

import com.example.vidium.dto.UserDto;
import com.example.vidium.dto.request.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.vidium.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}
