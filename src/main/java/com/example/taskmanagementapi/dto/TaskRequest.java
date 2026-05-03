package com.example.taskmanagementapi.dto;

import com.example.taskmanagementapi.entity.Priority;
import com.example.taskmanagementapi.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    // title of the task
    private String title;

    // details about the task
    private String description;

    // task priority: LOW, MEDIUM, HIGH
    private Priority priority;

    // deadline date of the task
    private LocalDate dueDate;

    // task status: TODO, IN_PROGRESS, DONE
    private TaskStatus status;
}