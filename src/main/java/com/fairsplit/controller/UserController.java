package com.fairsplit.controller;

import com.fairsplit.dto.UserDto;
import com.fairsplit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }
}
