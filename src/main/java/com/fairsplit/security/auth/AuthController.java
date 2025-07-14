package com.fairsplit.security.auth;

import com.fairsplit.dto.UserDto;
import com.fairsplit.exception.ResourceNotFoundException;
import com.fairsplit.security.jwt.JWTService;
import com.fairsplit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.findUserByEmail(request.getEmail());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        } catch (ResourceNotFoundException e) {
            UserDto registeredUser = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        UserDto user = userService.authenticateUser(request);
        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getId()));
    }

}
