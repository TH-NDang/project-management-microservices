# Project Structure

## Root Directory Layout

```
├── user-service/          # User microservice
├── project-service/       # Project microservice  
├── task-service/          # Task microservice
├── init-scripts/          # Database initialization SQL scripts
├── docker-compose.yml     # Container orchestration
├── .env                   # Environment variables
└── README.md             # Project documentation
```

## Service Structure Pattern

Each microservice follows the same Maven/Spring Boot structure:

```
service-name/
├── src/
│   ├── main/
│   │   ├── java/com/group/servicename/
│   │   │   ├── controller/        # REST controllers
│   │   │   ├── service/           # Business logic
│   │   │   ├── repository/        # Data access layer
│   │   │   ├── entity/            # JPA entities
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── exception/         # Custom exceptions
│   │   │   └── ServiceNameApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── target/                # Build output
├── pom.xml               # Maven configuration
├── mvnw & mvnw.cmd       # Maven wrapper
└── .gitignore
```

## Package Naming Convention

- Base package: `com.group.{servicename}`
- Service names: `userservice`, `projectservice`, `taskservice`
- Follow standard Spring Boot layered architecture

## Key Architectural Patterns

- **Controller-Service-Repository** pattern
- **DTO pattern** for API data transfer
- **Global exception handling** with `@ControllerAdvice`
- **Lombok annotations** for boilerplate code reduction
- **JPA entities** with proper relationships
- **Transactional services** with `@Transactional`

## Database Scripts

- Located in `init-scripts/` directory
- Named as `{service-name}-init.sql`
- Automatically executed on container startup
- Contains sample data for development

## Configuration Files

- Service configs: `application.properties` in each service
- Docker configs: `docker-compose.yml` and `.env`
- Build configs: `pom.xml` in each service