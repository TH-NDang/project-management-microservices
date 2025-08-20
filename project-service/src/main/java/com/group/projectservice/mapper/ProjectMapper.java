package com.group.projectservice.mapper;

import com.group.projectservice.dto.ProjectWithUserDto;
import com.group.projectservice.entity.Project;
import com.group.projectservice.external.User;

import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectWithUserDto convertToProjectWithUserDto(Project project, User user) {
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

}
