package com.group.projectservice.mapper;

import com.group.projectservice.dto.ProjectWithUserDto;
import com.group.projectservice.entity.Project;
import com.group.projectservice.external.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProjectMapper {
    @Value("${user-service.url}")
    private String userServiceUrl;

    public ProjectWithUserDto convertToProjectWithUserDto(Project project) {
        RestTemplate restTemplate = new RestTemplate();
        String url = userServiceUrl + "/api/users/" + project.getManagerId();
        User user = restTemplate.getForObject(url, User.class);

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
