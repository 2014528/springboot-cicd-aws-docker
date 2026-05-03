package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.TaskRequest;
import com.example.taskmanagementapi.entity.Task;
import com.example.taskmanagementapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.taskmanagementapi.entity.TaskStatus;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    // TaskService contains the actual task logic
    private final TaskService taskService;

    // POST /api/tasks
    // Used to create a new task for the logged-in user
    @PostMapping
    public Task createTask(@RequestBody TaskRequest request) {

        // Send request data to service and return saved task
        return taskService.createTask(request);
    }
    // GET /api/tasks
// Used to get tasks of logged-in user
// Supports filtering, pagination, and sorting
    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(required = false) TaskStatus status, // optional filter by status
            @RequestParam(defaultValue = "0") int page, // page number starts from 0
            @RequestParam(defaultValue = "5") int size, // number of tasks per page
            @RequestParam(defaultValue = "dueDate") String sortBy, // field used for sorting
            @RequestParam(defaultValue = "asc") String direction // asc or desc
    ) {
        return taskService.getTasks(status, page, size, sortBy, direction);
    }
    // PUT /api/tasks/{id}
// Used to update an existing task
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest request
    ) {
        return taskService.updateTask(id, request);
    }
    // DELETE /api/tasks/{id}
// Used to delete an existing task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {

        // Send task ID to service for deleting
        taskService.deleteTask(id);

        // Send simple success message
        return "Task deleted successfully";
    }
}