-- Project Service Database Initialization Script

-- Create projects table if not exists
CREATE TABLE IF NOT EXISTS projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'PLANNING',
    priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    manager_id BIGINT NOT NULL,
    budget DECIMAL(15,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_projects_manager_id ON projects(manager_id);
CREATE INDEX IF NOT EXISTS idx_projects_status ON projects(status);
CREATE INDEX IF NOT EXISTS idx_projects_priority ON projects(priority);
CREATE INDEX IF NOT EXISTS idx_projects_start_date ON projects(start_date);
CREATE INDEX IF NOT EXISTS idx_projects_end_date ON projects(end_date);

-- Insert sample data
INSERT INTO projects (name, description, start_date, end_date, status, priority, manager_id, budget) VALUES
('E-commerce Platform', 'Development of a modern e-commerce platform with microservices architecture', '2025-01-01', '2025-06-30', 'IN_PROGRESS', 'HIGH', 2, 150000.00),
('Mobile App Development', 'Cross-platform mobile application for customer engagement', '2025-02-01', '2025-05-31', 'PLANNING', 'MEDIUM', 2, 80000.00),
('Data Analytics Dashboard', 'Business intelligence dashboard for real-time analytics', '2025-01-15', '2025-04-15', 'IN_PROGRESS', 'HIGH', 3, 120000.00),
('Website Redesign', 'Complete redesign of company website with modern UI/UX', '2025-03-01', '2025-05-01', 'PLANNING', 'MEDIUM', 3, 45000.00),
('API Integration Project', 'Integration with third-party APIs and services', '2024-12-01', '2025-02-28', 'IN_PROGRESS', 'CRITICAL', 2, 75000.00)
ON CONFLICT DO NOTHING;