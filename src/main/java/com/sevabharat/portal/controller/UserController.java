package com.sevabharat.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; // NEW IMPORT
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevabharat.portal.model.User;
import com.sevabharat.portal.repository.UserRepository;

/**
 * Handles endpoints related to fetching and managing user data (excluding auth).
 */
@RestController
@RequestMapping("/users")
public class UserController { 

    @Autowired
    private UserRepository userRepository;

    /**
     * NEW ENDPOINT: Returns details of the currently logged-in user.
     * Accessible at: http://localhost:8081/users/me
     * REQUIRES a valid session (successful login).
     */
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()") // This annotation protects the endpoint
    public ResponseEntity<User> getCurrentUser() {
        // 1. Get the username from the current session context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // 2. Look up the full User object in the database
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user != null) {
            // 3. Mask the sensitive password field before sending it back
            user.setPassword("******"); 
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // (Existing getUserById method would be below here...)
}
