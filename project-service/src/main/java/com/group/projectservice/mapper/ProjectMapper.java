package com.group.projectservice.mapper;

import com.group.projectservice.client.ProjectClient;
import com.group.projectservice.dto.ProjectDto;
import com.group.projectservice.dto.ProjectWithUserDto;
import com.group.projectservice.entity.Project;
import com.group.projectservice.external.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    @Autowired
    private ProjectClient projectClient;

    public ProjectWithUserDto convertToProjectWithUserDto(Project project) {
        User user = projectClient.getUserById(project.getManagerId());

        return ProjectWithUserDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .priority(project.getPriority())
                .manager(user)
                .budget(project.getBudget())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    public ProjectDto convertToProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .priority(project.getPriority())
                .managerId(project.getManagerId())
                .budget(project.getBudget())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    public Project convertToProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .status(projectDto.getStatus())
                .priority(projectDto.getPriority())
                .managerId(projectDto.getManagerId())
                .budget(projectDto.getBudget())
                .createdAt(projectDto.getCreatedAt())
                .updatedAt(projectDto.getUpdatedAt())
                .build();
    }

}
