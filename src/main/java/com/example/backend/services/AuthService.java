package com.example.backend.services;


public interface AuthService {
    String authenticate(String username, String password);
    // 添加注册用户的方法
    void registerUser(String username, String password);
}
