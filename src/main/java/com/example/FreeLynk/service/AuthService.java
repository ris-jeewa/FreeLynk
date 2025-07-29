package com.example.FreeLynk.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.User;
import com.example.FreeLynk.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@AllArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Check if user exists by email
     * @param email user's email
     * @return Optional<User> containing user if found
     */
    public Optional<User> checkUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Validate user credentials for login
     * @param email user's email
     * @param password user's password
     * @return Optional<User> containing user if credentials are valid
     */
    public Optional<User> validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        logger.info("User found for email {}: {}", email, userOptional.isPresent());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            logger.info("Stored password hash: {}", user.getPassword());
            logger.info("Input password: {}", password);
            
            // For now, using simple string comparison. In production, use passwordEncoder.matches()
            // boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
            boolean passwordMatches = password.equals(user.getPassword());
            logger.info("Password matches: {}", passwordMatches);
            
            if (passwordMatches) {
                logger.info("User validation successful for: {}", email);
                return userOptional;
            } else {
                logger.warn("Password validation failed for user: {}", email);
            }
        } else {
            logger.warn("No user found with email: {}", email);
        }
        
        return Optional.empty();
    }
    
    /**
     * Handle successful login
     * @param email user's email
     * @param password user's password
     * @return User object if login is successful, null otherwise
     */
    public User loginUser(String email, String password) {
        Optional<User> userOptional = validateUser(email, password);

        logger.info("User>>>>>>> : {}", userOptional.isPresent(),userOptional);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Here you can add additional login logic like:
            // - Update last login timestamp
            // - Generate JWT token
            // - Log login activity
            return user;
        }
        
        return null;
    }
    
    /**
     * Check if user exists by email (boolean response)
     * @param email user's email
     * @return true if user exists, false otherwise
     */
    public boolean userExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    
    /**
     * Register a new user
     * @param user user object to register
     * @return User object if registration is successful, null if email already exists
     */
    public User registerUser(User user) {
        // Check if user already exists
        if (userExistsByEmail(user.getEmail())) {
            return null;
        }
        
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save user to database
        return userRepository.save(user);
    }
}
