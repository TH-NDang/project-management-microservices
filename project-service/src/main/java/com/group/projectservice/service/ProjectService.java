package com.group.projectservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.group.projectservice.dto.ProjectDto;
import com.group.projectservice.dto.ProjectWithUserDto;
import com.group.projectservice.entity.Project;
import com.group.projectservice.exception.InvalidProjectDataException;
import com.group.projectservice.exception.ProjectNotFoundException;
import com.group.projectservice.external.User;
import com.group.projectservice.mapper.ProjectMapper;
import com.group.projectservice.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProjectWithUserDto> getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        return Optional.of(projectMapper.convertToProjectWithUserDto(project));
    }

    public List<ProjectDto> getProjectsByManagerId(Long managerId) {
        return projectRepository.findByManagerId(managerId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getProjectsByStatus(Project.ProjectStatus status) {
        return projectRepository.findByStatus(status).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getOverdueProjects() {
        return projectRepository.findOverdueProjects(LocalDate.now()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        validateProjectDates(projectDto);
        Project project = convertToEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        validateProjectDates(projectDto);

        existingProject.setName(projectDto.getName());
        existingProject.setDescription(projectDto.getDescription());
        existingProject.setStartDate(projectDto.getStartDate());
        existingProject.setEndDate(projectDto.getEndDate());
        existingProject.setStatus(projectDto.getStatus());
        existingProject.setPriority(projectDto.getPriority());
        existingProject.setBudget(projectDto.getBudget());

        Project updatedProject = projectRepository.save(existingProject);
        return convertToDto(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
    }

    public List<ProjectDto> searchProjectsByName(String name) {
        return projectRepository.findByNameContaining(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProjectDto convertToDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getPriority(),
                project.getManagerId(),
                project.getBudget(),
                project.getCreatedAt(),
                project.getUpdatedAt());
    }

    private Project convertToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setStatus(projectDto.getStatus() != null ? projectDto.getStatus() : Project.ProjectStatus.PLANNING);
        project.setPriority(
                projectDto.getPriority() != null ? projectDto.getPriority() : Project.ProjectPriority.MEDIUM);
        project.setManagerId(projectDto.getManagerId());
        project.setBudget(projectDto.getBudget());
        return project;
    }

    private void validateProjectDates(ProjectDto projectDto) {
        if (projectDto.getStartDate() != null && projectDto.getEndDate() != null) {
            if (projectDto.getStartDate().isAfter(projectDto.getEndDate())) {
                throw new InvalidProjectDataException("Start date cannot be after end date");
            }
        }

        if (projectDto.getBudget() != null && projectDto.getBudget() < 0) {
            throw new InvalidProjectDataException("Budget cannot be negative");
        }
    }
}
