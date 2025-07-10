package com.fairsplit.security.auth;

import com.fairsplit.dto.UserDto;
import com.fairsplit.model.User;
import com.fairsplit.repository.UserRepository;
import com.fairsplit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        UserDto registeredUser = userService.registerUser(request);

        return ResponseEntity.ok(registeredUser);
    }

}
