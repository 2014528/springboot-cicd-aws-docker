package com.example.taskmanagementapi.repository;

import com.example.taskmanagementapi.entity.Task;
import com.example.taskmanagementapi.entity.TaskStatus;
import com.example.taskmanagementapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByUser(User user, Pageable pageable);

    Page<Task> findByUserAndStatus(User user, TaskStatus status, Pageable pageable);
}