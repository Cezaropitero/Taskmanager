package org.example.taskmanagerbackend2.dto;

public class UpdateTaskRequest {
    private String title;
    private String description;

    public UpdateTaskRequest() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

