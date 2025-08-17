# Project Management Microservices

Hệ thống quản lý dự án với kiến trúc microservices sử dụng Spring Boot và PostgreSQL.

## Cấu trúc dự án

```
├── user-service/          # User microservice (Port: 8081)
├── project-service/       # Project microservice (Port: 8082)  
├── task-service/          # Task microservice (Port: 8083)
├── docker-compose.yml     # Docker Compose configuration
├── init-scripts/          # Database initialization scripts
└── README.md
```

## Công nghệ sử dụng

- **Spring Boot 3.5.4** - Framework chính
- **PostgreSQL 15** - Database
- **Docker & Docker Compose** - Containerization
- **Maven** - Build tool
- **Lombok** - Code generation

## Khởi chạy hệ thống

### 1. Khởi động databases với Docker Compose

```bash
# Khởi động tất cả databases
docker-compose up -d

# Kiểm tra trạng thái containers
docker-compose ps

# Xem logs
docker-compose logs -f
```

### 2. Khởi động các microservices

```bash
# User Service (Port: 8081)
cd user-service
./mvnw spring-boot:run

# Project Service (Port: 8082) 
cd project-service
./mvnw spring-boot:run

# Task Service (Port: 8083)
cd task-service
./mvnw spring-boot:run
```

## Database Configuration

| Service | Database | Port | URL |
|---------|----------|------|-----|
| User Service | user_service_db | 5432 | jdbc:postgresql://localhost:5432/user_service_db |
| Project Service | project_service_db | 5433 | jdbc:postgresql://localhost:5433/project_service_db |
| Task Service | task_service_db | 5434 | jdbc:postgresql://localhost:5434/task_service_db |

## pgAdmin Access

- **URL**: http://localhost:8080
- **Email**: admin@example.com
- **Password**: admin123

### Kết nối databases trong pgAdmin:

1. **User Service DB**:
   - Host: user-service-db
   - Port: 5432
   - Database: user_service_db
   - Username: postgres
   - Password: password

2. **Project Service DB**:
   - Host: project-service-db
   - Port: 5432
   - Database: project_service_db
   - Username: postgres
   - Password: password

3. **Task Service DB**:
   - Host: task-service-db
   - Port: 5432
   - Database: task_service_db
   - Username: postgres
   - Password: password

## API Endpoints

### User Service (http://localhost:8081)
- `GET /api/users` - Lấy danh sách users
- `GET /api/users/{id}` - Lấy user theo ID
- `POST /api/users` - Tạo user mới
- `PUT /api/users/{id}` - Cập nhật user
- `DELETE /api/users/{id}` - Xóa user

### Project Service (http://localhost:8082)
- `GET /api/projects` - Lấy danh sách projects
- `GET /api/projects/{id}` - Lấy project theo ID
- `POST /api/projects` - Tạo project mới
- `PUT /api/projects/{id}` - Cập nhật project
- `DELETE /api/projects/{id}` - Xóa project

### Task Service (http://localhost:8083)
- `GET /api/tasks` - Lấy danh sách tasks
- `GET /api/tasks/{id}` - Lấy task theo ID
- `POST /api/tasks` - Tạo task mới
- `PUT /api/tasks/{id}` - Cập nhật task
- `DELETE /api/tasks/{id}` - Xóa task

## Sample Data

Hệ thống đã được cấu hình với dữ liệu mẫu:

### Users:
- admin (ADMIN)
- manager1, manager2 (MANAGER)
- user1, user2, user3 (USER)

### Projects:
- E-commerce Platform
- Mobile App Development
- Data Analytics Dashboard
- Website Redesign
- API Integration Project

### Tasks:
- Các task được gán cho các project và user tương ứng

## Dừng hệ thống

```bash
# Dừng databases
docker-compose down

# Dừng databases và xóa volumes (mất dữ liệu)
docker-compose down -v

# Dừng Spring Boot services
Ctrl + C trong terminal của từng service
```

## Troubleshooting

### Lỗi kết nối database:
1. Kiểm tra Docker containers đang chạy: `docker-compose ps`
2. Kiểm tra logs: `docker-compose logs [service-name]`
3. Restart containers: `docker-compose restart`

### Port conflicts:
- Thay đổi port mapping trong docker-compose.yml nếu port đã được sử dụng
- Cập nhật application.properties tương ứng

### Memory issues:
- Tăng memory cho Docker Desktop
- Giảm số lượng containers chạy đồng thời