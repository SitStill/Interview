package com.example.backend.controllers;

import com.example.backend.models.User;
import com.example.backend.services.AuthService;
import io.javalin.http.Context;

public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    public void login(Context ctx) {
        // 使用 ctx.bodyAsClass 解析 JSON 数据
        User userCredentials = ctx.bodyAsClass(User.class);

        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        String token = authService.authenticate(username, password);

        if (token != null) {
            ctx.json("Token: " + token);
        } else {
            ctx.status(401).json("Authentication failed");
        }
    }
}
