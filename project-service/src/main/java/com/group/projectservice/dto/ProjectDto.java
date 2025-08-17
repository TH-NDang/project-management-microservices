package com.group.projectservice.dto;

import com.group.projectservice.entity.Project;
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
public class ProjectDto {
    
    private Long id;
    
    @NotBlank(message = "Project name is required")
    private String name;
    
    private String description;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Project.ProjectStatus status;
    
    private Project.ProjectPriority priority;
    
    @NotNull(message = "Manager ID is required")
    private Long managerId;
    
    private Double budget;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}