# Product Overview

This is a **Project Management System** built with microservices architecture. The system manages users, projects, and tasks in a distributed environment.

## Core Features

- **User Management**: User registration, authentication, and role-based access (ADMIN, MANAGER, USER)
- **Project Management**: Create and manage projects with team assignments
- **Task Management**: Task creation, assignment, status tracking, and deadline management

## Business Domain

The system follows a typical project management workflow where:
- Users are assigned to projects
- Projects contain multiple tasks
- Tasks can be assigned to users with priorities and due dates
- Task status progression: TODO → IN_PROGRESS → DONE/CANCELLED

## Service Boundaries

- **User Service**: Handles user authentication, profiles, and role management
- **Project Service**: Manages project lifecycle and team assignments  
- **Task Service**: Handles task creation, assignment, and progress tracking

Each service operates independently with its own database and API endpoints.