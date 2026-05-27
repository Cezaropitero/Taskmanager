package org.example.taskmanagerbackend2.dto;

public class CreateUserRequest {
    private String email;
    private String password;

    public CreateUserRequest() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

