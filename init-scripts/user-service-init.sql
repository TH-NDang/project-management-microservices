-- User Service Database Initialization Script

-- Create users table if not exists
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_status ON users(status);

-- Insert sample data
INSERT INTO users (username, email, full_name, phone_number, role, status) VALUES
('admin', 'admin@example.com', 'System Administrator', '+1234567890', 'ADMIN', 'ACTIVE'),
('manager1', 'manager1@example.com', 'Project Manager One', '+1234567891', 'MANAGER', 'ACTIVE'),
('manager2', 'manager2@example.com', 'Project Manager Two', '+1234567892', 'MANAGER', 'ACTIVE'),
('user1', 'user1@example.com', 'Developer One', '+1234567893', 'USER', 'ACTIVE'),
('user2', 'user2@example.com', 'Developer Two', '+1234567894', 'USER', 'ACTIVE'),
('user3', 'user3@example.com', 'Designer One', '+1234567895', 'USER', 'ACTIVE')
ON CONFLICT (username) DO NOTHING;