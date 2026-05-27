package org.example.taskmanagerbackend2.repository;

import org.example.taskmanagerbackend2.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

