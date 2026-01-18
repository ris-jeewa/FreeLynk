-- FreeLynk Database Schema
-- This file contains the complete database schema for the FreeLynk application

-- Drop tables if they exist (for development purposes)
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS milestones CASCADE;
DROP TABLE IF EXISTS job_applications CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS jobs CASCADE;
DROP TABLE IF EXISTS freelancer CASCADE;
DROP TABLE IF EXISTS auth CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) CHECK (role IN ('CLIENT', 'FREELANCER')),
    profile_picture_url TEXT,
    bio VARCHAR(1000),
    phone_number VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create jobs table
CREATE TABLE jobs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    budget DECIMAL(10,2) NOT NULL CHECK (budget > 0),
    status VARCHAR(20) DEFAULT 'OPEN' CHECK (status IN ('OPEN', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
    client_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create job_applications table
CREATE TABLE job_applications (
    id BIGSERIAL PRIMARY KEY,
    job_id BIGINT NOT NULL,
    freelancer_id BIGINT NOT NULL,
    proposal TEXT,
    bid_amount DECIMAL(10,2) CHECK (bid_amount > 0),
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
    FOREIGN KEY (freelancer_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(job_id, freelancer_id) -- Prevent multiple applications from same freelancer
);

-- Create projects table
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    job_id BIGINT NOT NULL,
    freelancer_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    status VARCHAR(20) DEFAULT 'IN_PROGRESS' CHECK (status IN ('IN_PROGRESS', 'COMPLETED', 'DISPUTED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
    FOREIGN KEY (freelancer_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(job_id) -- One project per job
);

-- Create milestones table
CREATE TABLE milestones (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'COMPLETED', 'CANCELLED')),
    due_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

-- Create payments table
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    freelancer_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    status VARCHAR(20) DEFAULT 'ESCROW' CHECK (status IN ('ESCROW', 'RELEASED', 'REFUNDED')),
    stripe_payment_id VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (freelancer_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create freelancer table
CREATE TABLE freelancer (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    title VARCHAR(255),
    location VARCHAR(255),
    rating DECIMAL(3,2) DEFAULT 0.0 CHECK (rating >= 0 AND rating <= 5),
    number_of_reviews BIGINT DEFAULT 0 CHECK (number_of_reviews >= 0),
    github_url VARCHAR(500),
    linkedin_url VARCHAR(500),
    portfolio_url VARCHAR(500),
    skills JSONB, -- Store skills as JSON array
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create auth table (legacy/backup table)
CREATE TABLE auth (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_jobs_client_id ON jobs(client_id);
CREATE INDEX idx_jobs_status ON jobs(status);
CREATE INDEX idx_jobs_created_at ON jobs(created_at);
CREATE INDEX idx_job_applications_job_id ON job_applications(job_id);
CREATE INDEX idx_job_applications_freelancer_id ON job_applications(freelancer_id);
CREATE INDEX idx_job_applications_status ON job_applications(status);
CREATE INDEX idx_projects_job_id ON projects(job_id);
CREATE INDEX idx_projects_freelancer_id ON projects(freelancer_id);
CREATE INDEX idx_projects_client_id ON projects(client_id);
CREATE INDEX idx_projects_status ON projects(status);
CREATE INDEX idx_milestones_project_id ON milestones(project_id);
CREATE INDEX idx_milestones_status ON milestones(status);
CREATE INDEX idx_milestones_due_date ON milestones(due_date);
CREATE INDEX idx_payments_project_id ON payments(project_id);
CREATE INDEX idx_payments_status ON payments(status);
CREATE INDEX idx_payments_stripe_id ON payments(stripe_payment_id);
CREATE INDEX idx_freelancer_user_id ON freelancer(user_id);
CREATE INDEX idx_freelancer_rating ON freelancer(rating);

-- Create triggers for updated_at timestamps
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON users
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_jobs_updated_at BEFORE UPDATE ON jobs
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_projects_updated_at BEFORE UPDATE ON projects
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_payments_updated_at BEFORE UPDATE ON payments
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_freelancer_updated_at BEFORE UPDATE ON freelancer
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Insert sample data (optional - for development)
-- Sample users
INSERT INTO users (name, email, role, password) VALUES
('John Client', 'john.client@example.com', 'CLIENT', '$2a$10$encoded_password_hash'),
('Jane Freelancer', 'jane.freelancer@example.com', 'FREELANCER', '$2a$10$encoded_password_hash'),
('Bob Developer', 'bob.developer@example.com', 'FREELANCER', '$2a$10$encoded_password_hash');

-- Sample freelancer profile
INSERT INTO freelancer (user_id, title, location, rating, number_of_reviews, skills) VALUES
(2, 'Full Stack Developer', 'New York, NY', 4.8, 15, '["Java", "Spring Boot", "React", "PostgreSQL"]'),
(3, 'Mobile App Developer', 'San Francisco, CA', 4.9, 23, '["React Native", "Flutter", "iOS", "Android"]');

-- Sample jobs
INSERT INTO jobs (title, description, budget, client_id) VALUES
('Build a REST API', 'Need a REST API built with Spring Boot and PostgreSQL', 1500.00, 1),
('Mobile App Development', 'Looking for a React Native developer to build a mobile app', 3000.00, 1);

-- Sample job applications
INSERT INTO job_applications (job_id, freelancer_id, proposal, bid_amount) VALUES
(1, 2, 'I have extensive experience with Spring Boot and can deliver this project within 2 weeks.', 1400.00),
(1, 3, 'I can build this API with modern best practices and comprehensive testing.', 1600.00),
(2, 3, 'I specialize in React Native and have built similar apps before.', 2800.00);

-- Comments explaining the schema
COMMENT ON TABLE users IS 'Stores user account information for both clients and freelancers';
COMMENT ON TABLE jobs IS 'Stores job postings created by clients';
COMMENT ON TABLE job_applications IS 'Stores applications submitted by freelancers for jobs';
COMMENT ON TABLE projects IS 'Stores active projects when a job application is accepted';
COMMENT ON TABLE milestones IS 'Stores project milestones for payment tracking';
COMMENT ON TABLE payments IS 'Stores payment transactions and their status';
COMMENT ON TABLE freelancer IS 'Stores additional profile information for freelancers';
COMMENT ON TABLE auth IS 'Legacy authentication table (can be removed if not needed)';

COMMENT ON COLUMN users.role IS 'User role: CLIENT or FREELANCER';
COMMENT ON COLUMN jobs.status IS 'Job status: OPEN, IN_PROGRESS, COMPLETED, CANCELLED';
COMMENT ON COLUMN job_applications.status IS 'Application status: PENDING, ACCEPTED, REJECTED';
COMMENT ON COLUMN projects.status IS 'Project status: IN_PROGRESS, COMPLETED, DISPUTED';
COMMENT ON COLUMN milestones.status IS 'Milestone status: PENDING, COMPLETED, CANCELLED';
COMMENT ON COLUMN payments.status IS 'Payment status: ESCROW, RELEASED, REFUNDED';
COMMENT ON COLUMN freelancer.skills IS 'JSON array of freelancer skills'; 