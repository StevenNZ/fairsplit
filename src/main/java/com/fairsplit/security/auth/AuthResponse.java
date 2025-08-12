package com.fairsplit.security.auth;

import com.fairsplit.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserDto user;
}
