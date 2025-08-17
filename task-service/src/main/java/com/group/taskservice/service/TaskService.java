package com.group.taskservice.service;

import com.group.taskservice.dto.TaskDto;
import com.group.taskservice.entity.Task;
import com.group.taskservice.exception.InvalidTaskDataException;
import com.group.taskservice.exception.TaskAssignmentException;
import com.group.taskservice.exception.TaskNotFoundException;
import com.group.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto);
    }

    public List<TaskDto> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByAssignedTo(Long assignedTo) {
        return taskRepository.findByAssignedTo(assignedTo).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDate.now()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        validateTaskData(taskDto);
        Task task = convertToEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        validateTaskData(taskDto);

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setAssignedTo(taskDto.getAssignedTo());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setPriority(taskDto.getPriority());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setEstimatedHours(taskDto.getEstimatedHours());
        existingTask.setActualHours(taskDto.getActualHours());

        Task updatedTask = taskRepository.save(existingTask);
        return convertToDto(updatedTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public List<TaskDto> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContaining(title).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        if (task.getStatus() == Task.TaskStatus.DONE) {
            throw new TaskAssignmentException("Cannot assign a completed task");
        }

        task.setAssignedTo(userId);
        Task updatedTask = taskRepository.save(task);
        return convertToDto(updatedTask);
    }

    public TaskDto updateTaskStatus(Long taskId, Task.TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        validateStatusTransition(task.getStatus(), status);

        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return convertToDto(updatedTask);
    }

    private TaskDto convertToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getProjectId(),
                task.getAssignedTo(),
                task.getCreatedBy(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getEstimatedHours(),
                task.getActualHours(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }

    private Task convertToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setProjectId(taskDto.getProjectId());
        task.setAssignedTo(taskDto.getAssignedTo());
        task.setCreatedBy(taskDto.getCreatedBy());
        task.setStatus(taskDto.getStatus() != null ? taskDto.getStatus() : Task.TaskStatus.TODO);
        task.setPriority(taskDto.getPriority() != null ? taskDto.getPriority() : Task.TaskPriority.MEDIUM);
        task.setDueDate(taskDto.getDueDate());
        task.setEstimatedHours(taskDto.getEstimatedHours());
        task.setActualHours(taskDto.getActualHours());
        return task;
    }

    private void validateTaskData(TaskDto taskDto) {
        if (taskDto.getEstimatedHours() != null && taskDto.getEstimatedHours() < 0) {
            throw new InvalidTaskDataException("Estimated hours cannot be negative");
        }

        if (taskDto.getActualHours() != null && taskDto.getActualHours() < 0) {
            throw new InvalidTaskDataException("Actual hours cannot be negative");
        }

        if (taskDto.getDueDate() != null && taskDto.getDueDate().isBefore(LocalDate.now())) {
            throw new InvalidTaskDataException("Due date cannot be in the past");
        }
    }

    private void validateStatusTransition(Task.TaskStatus currentStatus, Task.TaskStatus newStatus) {
        if (currentStatus == Task.TaskStatus.DONE && newStatus != Task.TaskStatus.DONE) {
            throw new InvalidTaskDataException("Cannot change status of a completed task");
        }

        if (currentStatus == Task.TaskStatus.CANCELLED && newStatus != Task.TaskStatus.CANCELLED) {
            throw new InvalidTaskDataException("Cannot change status of a cancelled task");
        }
    }
}