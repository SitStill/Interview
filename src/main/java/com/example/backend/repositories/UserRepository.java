package com.example.backend.repositories;

import com.example.backend.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    void save(User user);
}