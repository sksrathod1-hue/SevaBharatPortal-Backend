package com.sevabharat.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // Added for /api/status
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevabharat.portal.model.User;
import com.sevabharat.portal.repository.UserRepository;

/**
 * Handles user authentication endpoints (registration, login, etc.) and API status check.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inject the AuthenticationManager bean for processing logins
    @Autowired
    private AuthenticationManager authenticationManager; 

    /**
     * Endpoint for new user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Step 1: Basic validation
        if (user.getUsername() == null || user.getPassword() == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Username and password are required.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Step 2: Check existence
            if (userRepository.existsByUsername(user.getUsername())) {
                return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
            }
            
            // Step 3: Prevent NOT NULL DB Errors
            if (user.getIncome() == null) {
                user.setIncome(0L); 
            }
            
            // Step 4: Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Step 5: Save
            userRepository.save(user);

            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

        } catch (Exception e) {
            // CRITICAL DEBUG STEP: Print the exact database error to the console
            System.err.println("--- REGISTRATION FAILED: CHECK STACK TRACE BELOW ---");
            e.printStackTrace(); 
            System.err.println("-----------------------------------------------------");
            
            return new ResponseEntity<>("Internal server error during registration. Check server console for details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for user login.
     */
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User loginDetails) {
        try {
            // 1. Authenticate user credentials against the database hash
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDetails.getUsername(),
                    loginDetails.getPassword()
                )
            );

            // 2. Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>("User logged in successfully!", HttpStatus.OK);

        } catch (Exception e) {
            // This catches exceptions like BadCredentialsException
            System.err.println("--- LOGIN FAILED ---");
            e.printStackTrace();
            System.err.println("--------------------");
            return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The API status check endpoint, moved here from HomeController to avoid conflicts.
     */
    @GetMapping("/api/status")
    public String apiStatus() {
        return "Welcome to Seva Bharat Portal! The application is running correctly.";
    }
}