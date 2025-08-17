# Hướng dẫn sử dụng Postman Collection cho Project Management System

## Tổng quan

Dự án này bao gồm 3 microservices với các API endpoints tương ứng:

- **User Service** (Port 8081): Quản lý người dùng
- **Project Service** (Port 8082): Quản lý dự án  
- **Task Service** (Port 8083): Quản lý công việc

## Cách import vào Postman

### 1. Import Collection
1. Mở Postman
2. Click **Import** 
3. Chọn file `Project-Management-APIs.postman_collection.json`
4. Click **Import**

### 2. Import Environment (Tùy chọn)
1. Click **Import** 
2. Chọn file `Project-Management-Environment.postman_environment.json`
3. Click **Import**
4. Chọn environment "Project Management Environment" từ dropdown ở góc phải trên

## Cấu trúc API

### User Service APIs (Port 8081)

#### Endpoints chính:
- `GET /api/users` - Lấy danh sách tất cả users
- `GET /api/users/{id}` - Lấy user theo ID
- `GET /api/users/username/{username}` - Lấy user theo username
- `GET /api/users/email/{email}` - Lấy user theo email
- `GET /api/users/role/{role}` - Lấy users theo role
- `POST /api/users` - Tạo user mới
- `PUT /api/users/{id}` - Cập nhật user
- `DELETE /api/users/{id}` - Xóa user

#### User Roles:
- `ADMIN` - Quản trị viên
- `MANAGER` - Quản lý dự án
- `USER` - Người dùng thường

#### User Status:
- `ACTIVE` - Đang hoạt động
- `INACTIVE` - Không hoạt động
- `SUSPENDED` - Bị tạm ngưng

### Project Service APIs (Port 8082)

#### Endpoints chính:
- `GET /api/projects` - Lấy danh sách tất cả projects
- `GET /api/projects/{id}` - Lấy project theo ID
- `GET /api/projects/manager/{managerId}` - Lấy projects theo manager
- `GET /api/projects/status/{status}` - Lấy projects theo trạng thái
- `GET /api/projects/overdue` - Lấy projects quá hạn
- `GET /api/projects/search?name={name}` - Tìm kiếm project theo tên
- `POST /api/projects` - Tạo project mới
- `PUT /api/projects/{id}` - Cập nhật project
- `DELETE /api/projects/{id}` - Xóa project

#### Project Status:
- `PLANNING` - Đang lên kế hoạch
- `IN_PROGRESS` - Đang thực hiện
- `ON_HOLD` - Tạm dừng
- `COMPLETED` - Hoàn thành
- `CANCELLED` - Đã hủy

#### Project Priority:
- `LOW` - Thấp
- `MEDIUM` - Trung bình
- `HIGH` - Cao
- `CRITICAL` - Khẩn cấp

### Task Service APIs (Port 8083)

#### Endpoints chính:
- `GET /api/tasks` - Lấy danh sách tất cả tasks
- `GET /api/tasks/{id}` - Lấy task theo ID
- `GET /api/tasks/project/{projectId}` - Lấy tasks theo project
- `GET /api/tasks/assigned/{userId}` - Lấy tasks được giao cho user
- `GET /api/tasks/status/{status}` - Lấy tasks theo trạng thái
- `GET /api/tasks/overdue` - Lấy tasks quá hạn
- `GET /api/tasks/search?title={title}` - Tìm kiếm task theo tiêu đề
- `POST /api/tasks` - Tạo task mới
- `PUT /api/tasks/{id}` - Cập nhật task
- `PUT /api/tasks/{id}/assign/{userId}` - Giao task cho user
- `PUT /api/tasks/{id}/status/{status}` - Cập nhật trạng thái task
- `DELETE /api/tasks/{id}` - Xóa task

#### Task Status:
- `TODO` - Chưa làm
- `IN_PROGRESS` - Đang làm
- `IN_REVIEW` - Đang review
- `DONE` - Hoàn thành
- `CANCELLED` - Đã hủy

#### Task Priority:
- `LOW` - Thấp
- `MEDIUM` - Trung bình
- `HIGH` - Cao
- `URGENT` - Khẩn cấp

## Cách sử dụng

### 1. Khởi động services
```bash
# Khởi động databases
docker-compose up -d

# Khởi động từng service (trong terminal riêng)
cd user-service && ./mvnw spring-boot:run
cd project-service && ./mvnw spring-boot:run  
cd task-service && ./mvnw spring-boot:run
```

### 2. Test APIs theo thứ tự
1. **Tạo Users trước** (cần có manager và users)
2. **Tạo Projects** (cần managerId từ bước 1)
3. **Tạo Tasks** (cần projectId từ bước 2 và assignedTo từ bước 1)

### 3. Ví dụ workflow
1. Tạo user với role MANAGER
2. Tạo user với role USER
3. Tạo project với managerId từ bước 1
4. Tạo task với projectId từ bước 3 và assignedTo từ bước 2
5. Cập nhật trạng thái task từ TODO → IN_PROGRESS → DONE

## Lưu ý quan trọng

- Đảm bảo tất cả services đang chạy trước khi test
- Kiểm tra ports không bị conflict (8081, 8082, 8083)
- Dữ liệu mẫu có thể được load từ các file init-scripts/*.sql
- Sử dụng Content-Type: application/json cho các request POST/PUT
- Các trường bắt buộc phải có trong request body
- ID references phải tồn tại (managerId, projectId, assignedTo, createdBy)

## Troubleshooting

- Nếu API trả về 404: Kiểm tra service có đang chạy không
- Nếu API trả về 400: Kiểm tra request body và validation
- Nếu API trả về 500: Kiểm tra logs của service tương ứng
- Nếu không connect được database: Kiểm tra docker-compose có chạy không