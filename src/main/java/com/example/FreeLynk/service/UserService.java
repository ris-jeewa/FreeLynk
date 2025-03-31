package com.example.FreeLynk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.FreeLynk.dto.UserResponseDTO;
import com.example.FreeLynk.exception.ResourceAlreadyExistException;
import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.User;
import com.example.FreeLynk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserProfile(Long userId) {
        // Fetch user from the repository using userId
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user;
    }

    public User createUserProfile(User user){
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new ResourceAlreadyExistException("User already exists with this ID");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ResourceNotFoundException("No users found");
        }
        return users;
    }

}
