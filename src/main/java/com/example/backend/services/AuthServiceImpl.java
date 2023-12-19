package com.example.backend.services;

import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            // Simulate JWT generation (replace with actual JWT logic)
            return "fake.jwt.token";
        }
        return null;
    }

    @Override
    public void registerUser(String username, String password) {
        // 注册用户
        userRepository.registerUser(username, password);
    }
}
