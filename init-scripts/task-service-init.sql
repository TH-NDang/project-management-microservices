-- Task Service Database Initialization Script

-- Create tasks table if not exists
CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    project_id BIGINT NOT NULL,
    assigned_to BIGINT,
    created_by BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'TODO',
    priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    due_date DATE,
    estimated_hours INTEGER,
    actual_hours INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_tasks_project_id ON tasks(project_id);
CREATE INDEX IF NOT EXISTS idx_tasks_assigned_to ON tasks(assigned_to);
CREATE INDEX IF NOT EXISTS idx_tasks_created_by ON tasks(created_by);
CREATE INDEX IF NOT EXISTS idx_tasks_status ON tasks(status);
CREATE INDEX IF NOT EXISTS idx_tasks_priority ON tasks(priority);
CREATE INDEX IF NOT EXISTS idx_tasks_due_date ON tasks(due_date);

-- Insert sample data
INSERT INTO tasks (title, description, project_id, assigned_to, created_by, status, priority, due_date, estimated_hours, actual_hours) VALUES
('Setup Database Schema', 'Create and configure database schema for e-commerce platform', 1, 4, 2, 'DONE', 'HIGH', '2025-01-10', 16, 14),
('Implement User Authentication', 'Develop user login and registration functionality', 1, 4, 2, 'IN_PROGRESS', 'HIGH', '2025-01-25', 24, 18),
('Design Product Catalog UI', 'Create user interface for product catalog', 1, 6, 2, 'TODO', 'MEDIUM', '2025-02-05', 32, NULL),
('API Development for Orders', 'Develop REST APIs for order management', 1, 5, 2, 'TODO', 'HIGH', '2025-02-15', 40, NULL),
('Mobile App Wireframes', 'Create wireframes and mockups for mobile application', 2, 6, 2, 'IN_PROGRESS', 'MEDIUM', '2025-02-10', 20, 12),
('Database Design for Analytics', 'Design database schema for analytics dashboard', 3, 4, 3, 'DONE', 'HIGH', '2025-01-20', 12, 10),
('Dashboard Frontend Development', 'Develop React-based dashboard frontend', 3, 5, 3, 'IN_PROGRESS', 'HIGH', '2025-03-01', 60, 25),
('Website Content Migration', 'Migrate existing content to new website structure', 4, 5, 3, 'TODO', 'LOW', '2025-03-15', 16, NULL),
('Third-party API Integration', 'Integrate payment gateway and shipping APIs', 5, 4, 2, 'IN_REVIEW', 'CRITICAL', '2025-02-20', 28, 26),
('Testing and Quality Assurance', 'Comprehensive testing of API integrations', 5, 5, 2, 'TODO', 'HIGH', '2025-02-25', 20, NULL)
ON CONFLICT DO NOTHING;