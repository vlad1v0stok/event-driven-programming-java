CREATE DATABASE university_db;
USE university_db;

-- Table 1: Programs (Degrees offered)
CREATE TABLE programs (
    program_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for the program
    program_name VARCHAR(100) NOT NULL,         -- Name of the degree
    required_units INT NOT NULL                 -- How many units to graduate
);

-- Insert some sample programs
INSERT INTO programs (program_name, required_units) VALUES
('Bachelor of Computer Science', 56),
('Diploma in IT', 24);

-- Table 2: Students
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,         -- Unique ID for the student
    registration_number VARCHAR(20) UNIQUE NOT NULL,   -- School ID (e.g., S101)
    first_name VARCHAR(50) NOT NULL,                   -- First name
    last_name VARCHAR(50) NOT NULL,                    -- Last name
    email VARCHAR(100) UNIQUE NOT NULL,                -- Email address
    program_id INT,                                    -- Links student to a program
    FOREIGN KEY (program_id) REFERENCES programs(program_id)  -- Database relationship
);
