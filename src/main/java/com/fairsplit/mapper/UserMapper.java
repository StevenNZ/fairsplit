package com.fairsplit.mapper;

import com.fairsplit.dto.UserDto;
import com.fairsplit.model.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
