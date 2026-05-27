package org.example.taskmanagerbackend2.service;

import org.example.taskmanagerbackend2.dto.CreateUserRequest;
import org.example.taskmanagerbackend2.dto.LoginRequest;
import org.example.taskmanagerbackend2.dto.UserResponse;
import org.example.taskmanagerbackend2.entity.User;
import org.example.taskmanagerbackend2.exception.InvalidLoginException;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.example.taskmanagerbackend2.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(CreateUserRequest request) {
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                if (this.userRepository.existsByEmail(request.getEmail())) {
                    throw new IllegalArgumentException("Email is already registered");
                } else {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    return this.mapToResponse((User)this.userRepository.save(user));
                }
            } else {
                throw new IllegalArgumentException("Password is required");
            }
        } else {
            throw new IllegalArgumentException("Email is required");
        }
    }

    public UserResponse login(LoginRequest request) {
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                User user = (User)this.userRepository.findByEmail(request.getEmail()).orElseThrow(InvalidLoginException::new);
                if (!Objects.equals(user.getPassword(), request.getPassword())) {
                    throw new InvalidLoginException();
                } else {
                    return this.mapToResponse(user);
                }
            } else {
                throw new InvalidLoginException();
            }
        } else {
            throw new InvalidLoginException();
        }
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;
    }
}

