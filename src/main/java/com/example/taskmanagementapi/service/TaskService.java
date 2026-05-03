package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.TaskRequest;
import com.example.taskmanagementapi.entity.Task;
import com.example.taskmanagementapi.entity.TaskStatus;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.repository.TaskRepository;
import com.example.taskmanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    // Used to save and fetch tasks from database
    private final TaskRepository taskRepository;

    // Used to fetch currently logged-in user from database
    private final UserRepository userRepository;

    public Task createTask(TaskRequest request) {

        // Get the user who is currently logged in
        User user = getCurrentUser();

        // Create new Task object from request data
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())

                // If status is not provided, set default status as TODO
                .status(request.getStatus() == null ? TaskStatus.TODO : request.getStatus())

                // Attach this task to the logged-in user
                .user(user)
                .build();

        // Save task in database and return saved task
        return taskRepository.save(task);
    }

    public Page<Task> getTasks(
            TaskStatus status,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        // Get the currently logged-in user
        User user = getCurrentUser();

        // Decide sorting direction: ascending or descending
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Create pagination object
        Pageable pageable = PageRequest.of(page, size, sort);

        // If status is provided, filter by status
        if (status != null) {
            return taskRepository.findByUserAndStatus(user, status, pageable);
        }

        // If no status is provided, return all tasks of logged-in user
        return taskRepository.findByUser(user, pageable);
    }

    public Task getTaskById(Long id) {

        // Get the currently logged-in user
        User user = getCurrentUser();

        // Find task by ID from database
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if this task belongs to the logged-in user
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        // Return task only if it belongs to current user
        return task;
    }
    public Task updateTask(Long id, TaskRequest request) {

        // First, find the task by ID
        // getTaskById() also checks if task belongs to logged-in user
        Task task = getTaskById(id);

        // Update old task values with new request values
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setStatus(request.getStatus());

        // Save updated task in database
        return taskRepository.save(task);
    }
    public void deleteTask(Long id) {

        // First, find the task and check ownership
        Task task = getTaskById(id);

        // Delete task from database
        taskRepository.delete(task);
    }
    private User getCurrentUser() {

        // Get email of logged-in user from Spring Security context
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // Find full user details from database using email
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}