package com.fairsplit.security.util;

import com.fairsplit.model.User;
import com.fairsplit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void checkOwnership(UUID targetUserId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(targetUserId)) {
            throw new AccessDeniedException("Unauthorized access");
        }
    }
}
