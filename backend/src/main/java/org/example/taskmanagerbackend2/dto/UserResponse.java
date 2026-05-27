package org.example.taskmanagerbackend2.dto;

public class UserResponse {
    private Long id;
    private String email;

    public UserResponse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

