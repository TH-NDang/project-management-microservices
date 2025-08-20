package com.group.taskservice.dto;

import com.group.taskservice.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    private Long assignedTo;

    @NotNull(message = "Created by is required")
    private Long createdBy;

    private Task.TaskStatus status;

    private Task.TaskPriority priority;

    private LocalDate dueDate;

    private Integer estimatedHours;

    private Integer actualHours;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
