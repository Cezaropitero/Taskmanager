package org.example.taskmanagerbackend2.controller;

import org.example.taskmanagerbackend2.dto.CreateTaskRequest;
import org.example.taskmanagerbackend2.dto.TaskResponse;
import org.example.taskmanagerbackend2.dto.UpdateStatusRequest;
import org.example.taskmanagerbackend2.dto.UpdateTaskRequest;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.taskmanagerbackend2.service.TaskService;

@RestController
@RequestMapping({"/tasks"})
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
        return this.taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    @GetMapping({"/{id}"})
    public TaskResponse getTaskById(@PathVariable Long id) {
        return this.taskService.getTaskById(id);
    }

    @PutMapping({"/{id}"})
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        return this.taskService.updateTask(id, request);
    }

    @PatchMapping({"/{id}/status"})
    public TaskResponse updateStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        return this.taskService.updateStatus(id, request.getStatus());
    }

    @DeleteMapping({"/{id}"})
    public void deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }
}


