package org.example.taskmanagerbackend2.service;

import org.example.taskmanagerbackend2.dto.CreateTaskRequest;
import org.example.taskmanagerbackend2.dto.TaskResponse;
import org.example.taskmanagerbackend2.dto.UpdateTaskRequest;
import org.example.taskmanagerbackend2.entity.Task;
import org.example.taskmanagerbackend2.entity.TaskStatus;
import org.example.taskmanagerbackend2.exception.TaskNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.example.taskmanagerbackend2.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.TODO);
        Task saved = (Task)this.taskRepository.save(task);
        return this.mapToResponse(saved);
    }

    public List<TaskResponse> getAllTasks() {
        return this.taskRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = (Task)this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return this.mapToResponse(task);
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task task = (Task)this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return this.mapToResponse((Task)this.taskRepository.save(task));
    }

    public TaskResponse updateStatus(Long id, TaskStatus status) {
        Task task = (Task)this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        return this.mapToResponse((Task)this.taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        return response;
    }
}
