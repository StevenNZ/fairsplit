package com.fairsplit.security.util;

import com.fairsplit.model.User;
import com.fairsplit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserService userService;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByEmail(email);
    }

    public void checkOwnership(UUID targetUserId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(targetUserId)) {
            throw new AccessDeniedException("Unauthorized access");
        }
    }
}
