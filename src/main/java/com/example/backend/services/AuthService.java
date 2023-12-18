package com.example.backend.services;

import com.example.backend.models.User;

public interface AuthService {
    String authenticate(String username, String password);
}
