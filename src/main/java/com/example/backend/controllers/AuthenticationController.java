package com.example.backend;

import com.example.backend.services.AuthService;
import io.javalin.http.Context;

public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    public void login(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        String token = authService.authenticate(username, password);

        if (token != null) {
            ctx.json("Token: " + token);
        } else {
            ctx.status(401).json("Authentication failed");
        }
    }
}
