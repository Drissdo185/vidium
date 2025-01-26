package com.example.vidium.service.impl;

import com.example.vidium.dto.UserDto;
import com.example.vidium.dto.request.UserRequest;
import com.example.vidium.dto.response.UserResponse;
import com.example.vidium.model.User;
import com.example.vidium.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.vidium.service.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserRequest createRequest){
        if(userRepository.existsByUsername(createRequest.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(createRequest.getEmail())){
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(createRequest.getUsername());
        user.setEmail(createRequest.getEmail());
        user.setPassword(createRequest.getPassword());
        user.setFirstName(createRequest.getFirstName());
        user.setLastName(createRequest.getLastName());
        user.setBio(createRequest.getBio());
        user.setCreatedAt(LocalDateTime.now());
        user.setIsActive(true);
        user.setRole(User.UserRole.USER);

        return convertToDto(userRepository.save(user));

    }

    @Override
    public UserDto getUser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long userId, UserRequest updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updateRequest.getFirstName() != null) {
            user.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            user.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getBio() != null) {
            user.setBio(updateRequest.getBio());
        }

        if (updateRequest.getCurrentPassword() != null && updateRequest.getNewPassword() != null) {
            if (passwordEncoder.matches(updateRequest.getCurrentPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
            } else {
                throw new RuntimeException("Current password is incorrect");
            }
        }

        return convertToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deactivate(Long userId) {
        userRepository.deactivateUser(userId);
    }

    @Override
    @Transactional
    public void updateLastLogin(Long userId) {
        userRepository.updateLastLoginTime(userId, LocalDateTime.now());
    }

    @Override
    public UserResponse getUserPublicProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse profile = new UserResponse();
        profile.setUsername(user.getUsername());
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());

        profile.setBio(user.getBio());
        profile.setCreatedAt(user.getCreatedAt());
        profile.setPostCount(userRepository.countUserPosts(user.getId()));

        return profile;
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBio(user.getBio());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setIsActive(user.getIsActive());
        dto.setRole(User.UserRole.valueOf(user.getRole().name()));
        return dto;
    }
}
