package com.fairsplit.service.impl;

import com.fairsplit.dto.UserDto;
import com.fairsplit.mapper.UserMapper;
import com.fairsplit.model.User;
import com.fairsplit.repository.UserRepository;
import com.fairsplit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }
}
