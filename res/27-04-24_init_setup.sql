USE `attend-db`;

-- Roles for different types of users
CREATE TABLE role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO role (name) VALUES ('admin'), ('student'), ('lecturer');

-- User details
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    studentNumber VARCHAR(50),
    roleId INT NOT NULL,
    FOREIGN KEY (roleId) REFERENCES role(id)
);

-- Faculty details
CREATE TABLE faculty (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- User to faculty mapping
CREATE TABLE user_faculty (
    userId INT NOT NULL,
    facultyId INT NOT NULL,
    PRIMARY KEY (userId, facultyId),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (facultyId) REFERENCES faculty(id)
);

-- Courses offered
CREATE TABLE course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lecturerId INT NOT NULL,
    facultyId INT NOT NULL,
    FOREIGN KEY (lecturerId) REFERENCES user(id),
    FOREIGN KEY (facultyId) REFERENCES faculty(id)
);

-- User to course mapping
CREATE TABLE user_course (
    userId INT NOT NULL,
    courseId INT NOT NULL,
    PRIMARY KEY (userId, courseId),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId) REFERENCES course(id)
);

-- Categories of absence reasons
CREATE TABLE absence_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO absence_category (name) VALUES ('illness'), ('family emergency'), ('personal reasons');

-- Types of attendance
CREATE TABLE attendance_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO attendance_type (name) VALUES ('present'), ('absent'), ('excused');

-- Attendance records
CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    courseId INT NOT NULL,
    userId INT NOT NULL,
    attendanceTypeId INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (courseId) REFERENCES course(id),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (attendanceTypeId) REFERENCES attendance_type(id)
);

CREATE TABLE code_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO code_type (name) VALUES ('check-in'), ('check-out');

-- Session management for code generation and check-in
CREATE TABLE course_code (
    courseId INT NOT NULL PRIMARY KEY ,
    type INT NOT NULL PRIMARY KEY,
    time DATETIME NOT NULL,
    timeOffset INT NOT NULL,
    attendanceCode VARCHAR(20),
    FOREIGN KEY (courseId) REFERENCES course(id),
    FOREIGN KEY (type) REFERENCES code_type(id)
);

-- Tracking user code scans
CREATE TABLE code_scan (
    userId INT NOT NULL PRIMARY KEY,
    courseId INT NOT NULL PRIMARY KEY,
    type INT NOT NULL PRIMARY KEY,
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId, type) REFERENCES course_code(courseId, type)
);


-- Reasons for late or no code scan
CREATE TABLE code_scan_unexpected (
    userId INT NOT NULL PRIMARY KEY,
    courseId INT NOT NULL PRIMARY KEY,
    type INT NOT NULL PRIMARY KEY,
    reason INT NOT NULL,
    proofDocument VARCHAR(255),
    accepted BOOLEAN NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId, type) REFERENCES course_code(courseId, type),
    FOREIGN KEY (reason) REFERENCES absence_category(id)
);
