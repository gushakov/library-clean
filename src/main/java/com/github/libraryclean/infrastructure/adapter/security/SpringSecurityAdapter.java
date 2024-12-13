package com.github.libraryclean.infrastructure.adapter.security;

import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.ports.security.SecurityOperationsOutputPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityAdapter implements SecurityOperationsOutputPort {
    @Override
    public PatronId usernameOfLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("User not authenticated");
        }

        // username of the currently logged-in user is the ID of the patron
        return PatronId.of(((User) authentication.getPrincipal()).getUsername());
    }
}
