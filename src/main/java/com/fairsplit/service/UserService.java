package com.fairsplit.service;

import com.fairsplit.dto.UserDto;
import com.fairsplit.security.auth.LoginRequest;
import com.fairsplit.security.auth.RegisterRequest;

import java.util.UUID;

public interface UserService {
    UserDto getUserById(UUID id);

    UserDto updateUser(UUID id, UserDto updatedUser);

    UserDto registerUser(RegisterRequest userDto);

    boolean checkUserExist(String email);

    String verify(LoginRequest request);
}
