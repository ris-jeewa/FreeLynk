-- FreeLynk Sample Data
-- This file contains sample data for development and testing

-- Clear existing data (optional - uncomment if needed)
-- DELETE FROM payments;
-- DELETE FROM milestones;
-- DELETE FROM job_applications;
-- DELETE FROM projects;
-- DELETE FROM jobs;
-- DELETE FROM freelancer;
-- DELETE FROM auth;
-- DELETE FROM users;

-- Sample Users (passwords are BCrypt encoded "password123")
INSERT INTO users (name, email, role, password, bio, phone_number) VALUES
('John Smith', 'john.smith@example.com', 'CLIENT', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Looking for talented developers for my startup', '+1-555-0101'),
('Sarah Johnson', 'sarah.johnson@example.com', 'CLIENT', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Entrepreneur seeking web development expertise', '+1-555-0102'),
('Mike Chen', 'mike.chen@example.com', 'FREELANCER', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Full-stack developer with 5+ years experience', '+1-555-0103'),
('Emily Davis', 'emily.davis@example.com', 'FREELANCER', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Mobile app specialist and UI/UX designer', '+1-555-0104'),
('Alex Rodriguez', 'alex.rodriguez@example.com', 'FREELANCER', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Backend developer specializing in Java and Spring', '+1-555-0105'),
('Lisa Wang', 'lisa.wang@example.com', 'CLIENT', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Small business owner needing e-commerce solution', '+1-555-0106')
ON CONFLICT (email) DO NOTHING;

"$2a$10$b6hL2h3T2YLpkXNhvjVM4etWz4SG3XI39YmgaHVwqTCau4QsYwghi"
-- Sample Freelancer Profiles
INSERT INTO freelancer (user_id, title, location, rating, number_of_reviews, github_url, linkedin_url, portfolio_url, skills) VALUES
(3, 'Senior Full-Stack Developer', 'San Francisco, CA', 4.8, 23, 'https://github.com/mikechen', 'https://linkedin.com/in/mikechen', 'https://mikechen.dev', '["JavaScript", "React", "Node.js", "PostgreSQL", "AWS"]'),
(4, 'Mobile App Developer & UI/UX Designer', 'New York, NY', 4.9, 18, 'https://github.com/emilydavis', 'https://linkedin.com/in/emilydavis', 'https://emilydavis.design', '["React Native", "Flutter", "Figma", "Adobe XD", "iOS", "Android"]'),
(5, 'Backend Engineer', 'Austin, TX', 4.7, 15, 'https://github.com/alexrodriguez', 'https://linkedin.com/in/alexrodriguez', 'https://alexrodriguez.dev', '["Java", "Spring Boot", "Microservices", "Docker", "Kubernetes", "PostgreSQL"]')
ON CONFLICT (user_id) DO NOTHING;

-- Sample Jobs
INSERT INTO jobs (title, description, budget, client_id, status) VALUES
('E-commerce Website Development', 'Need a complete e-commerce website with payment integration, user authentication, and admin panel. Should be responsive and SEO-friendly.', 5000.00, 1, 'OPEN'),
('Mobile App for Food Delivery', 'Looking for a React Native developer to build a food delivery app with real-time tracking, payment processing, and push notifications.', 8000.00, 1, 'OPEN'),
('REST API Development', 'Need a robust REST API built with Spring Boot for a healthcare application. Must include authentication, authorization, and comprehensive documentation.', 3000.00, 2, 'OPEN'),
('UI/UX Design for SaaS Platform', 'Seeking a talented designer to create modern, user-friendly interfaces for a B2B SaaS platform. Need wireframes, mockups, and design system.', 2500.00, 2, 'OPEN'),
('Database Optimization', 'Need help optimizing our PostgreSQL database for better performance. Current database has 100k+ records and experiencing slow queries.', 1500.00, 6, 'OPEN')
ON CONFLICT DO NOTHING;

-- Sample Job Applications
INSERT INTO job_applications (job_id, freelancer_id, proposal, bid_amount, status) VALUES
(1, 3, 'I have extensive experience building e-commerce platforms. I can deliver a complete solution with Stripe payment integration, user management, and responsive design within 6 weeks.', 4800.00, 'PENDING'),
(1, 4, 'I specialize in creating beautiful, user-friendly e-commerce experiences. I can handle both the frontend design and development, ensuring a seamless user journey.', 5200.00, 'PENDING'),
(2, 4, 'I have built several food delivery apps using React Native. I can implement real-time tracking, push notifications, and integrate with payment gateways. Estimated delivery time: 8 weeks.', 7500.00, 'PENDING'),
(3, 5, 'I am a backend specialist with deep knowledge of Spring Boot and healthcare compliance requirements. I can build a secure, scalable API with comprehensive testing and documentation.', 2800.00, 'PENDING'),
(4, 4, 'I love creating intuitive SaaS interfaces! I can design a complete design system with components library, user flows, and interactive prototypes. Will deliver in 3 weeks.', 2400.00, 'PENDING'),
(5, 5, 'I have optimized databases handling millions of records. I can analyze your current schema, create indexes, optimize queries, and provide performance recommendations.', 1400.00, 'PENDING')
ON CONFLICT DO NOTHING;

-- Sample Projects (when applications are accepted)
INSERT INTO projects (job_id, freelancer_id, client_id, status) VALUES
(1, 3, 1, 'IN_PROGRESS'),
(3, 5, 2, 'IN_PROGRESS')
ON CONFLICT DO NOTHING;

-- Sample Milestones
INSERT INTO milestones (project_id, description, amount, status, due_date) VALUES
(1, 'Database design and API development', 1600.00, 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '7 days'),
(1, 'Frontend development and UI implementation', 1600.00, 'PENDING', CURRENT_TIMESTAMP + INTERVAL '14 days'),
(1, 'Payment integration and testing', 1600.00, 'PENDING', CURRENT_TIMESTAMP + INTERVAL '21 days'),
(2, 'API architecture and core endpoints', 1000.00, 'COMPLETED', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(2, 'Authentication and authorization system', 1000.00, 'IN_PROGRESS', CURRENT_TIMESTAMP + INTERVAL '7 days'),
(2, 'Documentation and testing', 1000.00, 'PENDING', CURRENT_TIMESTAMP + INTERVAL '14 days')
ON CONFLICT DO NOTHING;

-- Sample Payments
INSERT INTO payments (project_id, client_id, freelancer_id, amount, status, stripe_payment_id) VALUES
(1, 1, 3, 1600.00, 'RELEASED', 'pi_3NxYzK2eZvKYlo2C1gQ12345'),
(2, 2, 5, 1000.00, 'ESCROW', 'pi_3NxYzK2eZvKYlo2C1gQ67890')
ON CONFLICT DO NOTHING;

-- Update job status for projects that have started
UPDATE jobs SET status = 'IN_PROGRESS' WHERE id IN (1, 3);

-- Update application status for accepted applications
UPDATE job_applications SET status = 'ACCEPTED' WHERE (job_id = 1 AND freelancer_id = 3) OR (job_id = 3 AND freelancer_id = 5);
UPDATE job_applications SET status = 'REJECTED' WHERE (job_id = 1 AND freelancer_id = 4) OR (job_id = 3 AND freelancer_id = 3); 