package com.fairsplit.mapper;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.dto.UserDto;
import com.fairsplit.model.Expense;
import com.fairsplit.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        Set<UUID> expenseIds = user.getExpenses() == null ? new HashSet<>() :
                user.getExpenses().stream()
                        .map(Expense::getId)
                        .collect(Collectors.toSet());

        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                expenseIds
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword(),
                new ArrayList<>() // or null if managed elsewhere
        );    }
}
