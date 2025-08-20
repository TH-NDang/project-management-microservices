package com.group.projectservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.group.projectservice.entity.Project;
import com.group.projectservice.external.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectWithUserDto {
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Project.ProjectStatus status;

    private Project.ProjectPriority priority;

    private User manager;

    private Double budget;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
