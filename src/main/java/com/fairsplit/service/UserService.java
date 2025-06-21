package com.fairsplit.service;

import com.fairsplit.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(UUID id);
}
