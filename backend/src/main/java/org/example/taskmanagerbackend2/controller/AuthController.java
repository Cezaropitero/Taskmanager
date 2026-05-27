package org.example.taskmanagerbackend2.controller;

import org.example.taskmanagerbackend2.dto.CreateUserRequest;
import org.example.taskmanagerbackend2.dto.LoginRequest;
import org.example.taskmanagerbackend2.dto.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.example.taskmanagerbackend2.service.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping({"/register", "/auth/register", "/api/auth/register"})
    public UserResponse register(@RequestBody CreateUserRequest request) {
        return this.authService.register(request);
    }

    @PostMapping({"/login", "/auth/login", "/api/auth/login"})
    public UserResponse login(@RequestBody LoginRequest request) {
        return this.authService.login(request);
    }
}

