package com.fairsplit.service.impl;

import com.fairsplit.dto.UserDto;
import com.fairsplit.exception.ResourceNotFoundException;
import com.fairsplit.mapper.UserMapper;
import com.fairsplit.model.User;
import com.fairsplit.repository.UserRepository;
import com.fairsplit.security.auth.LoginRequest;
import com.fairsplit.security.auth.RegisterRequest;
import com.fairsplit.security.jwt.JWTService;
import com.fairsplit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with this id :" + id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(UUID id, UserDto updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with this id :" + id));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public UserDto registerUser(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(encoder.encode(request.getPassword()));

        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public boolean checkUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public String authenticateUser(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            return jwtService.generateToken(request.getEmail());
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
