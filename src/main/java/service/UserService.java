package service;

import com.example.vidium.dto.UserDto;
import com.example.vidium.dto.request.UserRequest;
import com.example.vidium.dto.response.UserReponse;

public interface UserService {
    UserDto getUser(String username);
    UserDto updateUser(Long userId, UserRequest updateRequest);
    void deactivate(Long userId);
    void updateLastLogin(Long userId);
    UserReponse getUserPublicProfile(String username);
}
