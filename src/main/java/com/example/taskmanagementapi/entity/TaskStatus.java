package com.example.taskmanagementapi.entity;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}

//enum here because we have only three statuses and it is easier to manage them as an enum rather than a separate entity.
// It also helps to ensure that only valid statuses are assigned to tasks.
// If we had more complex status management, we might consider a separate entity, but for this simple case, an enum is sufficient.
// The TaskStatus enum defines the possible statuses for a task:TODO, IN_PROGRESS, and DONE.
//  This allows us to easily manage and validate the status of tasks throughout the application.
// By using an enum, we can also take advantage of type safety and avoid issues with invalid status values being assigned to tasks.
