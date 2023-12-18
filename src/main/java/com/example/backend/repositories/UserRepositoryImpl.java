package com.example.backend.repositories;

import com.example.backend.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> userDatabase = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userDatabase.get(username));
    }

    @Override
    public void save(User user) {
        userDatabase.put(user.getUsername(), user);
    }
}