package com.group.projectservice.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
    
    public ProjectNotFoundException(Long id) {
        super("Project not found with id: " + id);
    }
    
    public ProjectNotFoundException(String field, String value) {
        super("Project not found with " + field + ": " + value);
    }
}