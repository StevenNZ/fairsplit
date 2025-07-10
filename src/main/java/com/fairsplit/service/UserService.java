package com.fairsplit.service;

import com.fairsplit.dto.UserDto;
import com.fairsplit.security.auth.RegisterRequest;

import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(UUID id);

    UserDto updateUser(UUID id, UserDto updatedUser);

    UserDto registerUser(RegisterRequest userDto);
}
