# Technology Stack

## Core Technologies

- **Java 24** - Programming language
- **Spring Boot 3.5.4** - Main framework
- **Maven** - Build tool and dependency management
- **PostgreSQL 15** - Database (separate DB per service)
- **Docker & Docker Compose** - Containerization and orchestration

## Key Dependencies

- **Spring Boot Starter Web** - REST API development
- **Spring Boot Starter Data JPA** - Database access and ORM
- **Spring Boot Starter Validation** - Input validation
- **Spring Boot DevTools** - Development productivity
- **Lombok** - Code generation (getters, setters, constructors)
- **PostgreSQL Driver** - Database connectivity

## Service Ports

- User Service: `8081`
- Project Service: `8082`
- Task Service: `8083`
- pgAdmin: `8080`

## Database Configuration

Each service has its own PostgreSQL database:
- User Service DB: `localhost:5432`
- Project Service DB: `localhost:5433`
- Task Service DB: `localhost:5434`

## Common Commands

### Database Setup
```bash
# Start all databases
docker-compose up -d

# Check container status
docker-compose ps

# View logs
docker-compose logs -f

# Stop databases
docker-compose down
```

### Service Development
```bash
# Run a service (from service directory)
./mvnw spring-boot:run

# Build service
./mvnw clean package

# Run tests
./mvnw test

# Clean build
./mvnw clean compile
```

### Maven Wrapper
- Use `./mvnw` on Unix/Linux/Mac
- Use `mvnw.cmd` on Windows
- Maven wrapper ensures consistent Maven version across environments