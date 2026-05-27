package org.example.taskmanagerbackend2.dto;

import org.example.taskmanagerbackend2.entity.TaskStatus;

public class UpdateStatusRequest {
    private TaskStatus status;

    public UpdateStatusRequest() {
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

