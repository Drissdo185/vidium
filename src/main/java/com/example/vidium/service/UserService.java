package com.example.vidium.service;

import com.example.vidium.dto.UserDto;
import com.example.vidium.dto.request.UserRequest;
import com.example.vidium.dto.response.UserResponse;
import com.example.vidium.model.User;

public interface UserService {
    UserDto createUser(UserRequest request);
    UserDto getUser(String username);
    UserDto updateUser(Long userId, UserRequest updateRequest);
    void deactivate(Long userId);
    void updateLastLogin(Long userId);
    UserResponse getUserPublicProfile(String username);
}
