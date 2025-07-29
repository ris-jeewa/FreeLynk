package com.example.FreeLynk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.model.User;
import com.example.FreeLynk.service.AuthService;
import com.example.FreeLynk.enums.UserRole;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("Test endpoint called");
        return ResponseEntity.ok("Auth API is working!");
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkUserByEmail(@RequestParam String email) {

        try {
            boolean exists = authService.userExistsByEmail(email);
            logger.info("Email check result for {}: {}", email, exists);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            logger.error("Error checking email {}: {}", email, e.getMessage(), e);
            return ResponseEntity.internalServerError().body(false);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for email: {}", loginRequest.getEmail());
        User user = authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (user != null) {
            logger.info("Login successful for user: {}", user.getEmail());
            return ResponseEntity.ok(new LoginResponse("Login successful", user));
        } else {
            logger.warn("Login failed for email: {}", loginRequest.getEmail());
            return ResponseEntity.badRequest().body(new LoginResponse("Invalid credentials", null));
        }
    }

    // @GetMapping("/validate")
    // public ResponseEntity<?> validateUser(@RequestParam String email, @RequestParam String password) {
    //     logger.info("Validating user with email: {}", email);
    //     var userOptional = authService.validateUser(email, password);
    //     logger.info("User optional: {}", userOptional);

    //     if (userOptional.isPresent()) {
    //         logger.info("User validation successful for: {}", email);
    //         return ResponseEntity.ok(new LoginResponse("User validated successfully", userOptional.get()));
    //     } else {
    //         logger.warn("User validation failed for: {}", email);
    //         return ResponseEntity.badRequest().body(new LoginResponse("Invalid credentials", null));
    //     }
    // }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Registration attempt for email: {}", user.getEmail());
        try {
            User registeredUser = authService.registerUser(user);
            
            if (registeredUser != null) {
                logger.info("User registered successfully: {}", user.getEmail());
                return ResponseEntity.ok(new LoginResponse("User registered successfully", registeredUser));
            } else {
                logger.warn("Registration failed - email already exists: {}", user.getEmail());
                return ResponseEntity.badRequest().body(new LoginResponse("Email already exists", null));
            }
        } catch (Exception e) {
            logger.error("Error during registration for email: {}", user.getEmail(), e);
            return ResponseEntity.internalServerError().body(new LoginResponse("Registration failed: " + e.getMessage(), null));
        }
    }

    // Inner classes for request/response
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String message;
        private User user;

        public LoginResponse(String message, User user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }
}
