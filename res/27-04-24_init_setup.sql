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
    name VARCHAR(50) NOT NULL,
    attendanceRate DECIMAL(5, 2) NOT NULL
);

-- User to faculty mapping
CREATE TABLE user_faculty (
    userId INT NOT NULL,
    facultyId INT NOT NULL,
    PRIMARY KEY (userId, facultyId),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (facultyId) REFERENCES faculty(id)
);

-- Courses offered by faculty
CREATE TABLE parent_course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    facultyId INT NOT NULL,
    lecturerId INT NOT NULL,
    FOREIGN KEY (facultyId) REFERENCES faculty(id),
    FOREIGN KEY (lecturerId) REFERENCES user(id)
);

-- Courses offered
CREATE TABLE course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    parentCourseId INT NOT NULL,
    FOREIGN KEY (parentCourseId) REFERENCES parent_course(id)
);

-- User to course mapping
CREATE TABLE user_course (
    userId INT NOT NULL,
    courseId INT NOT NULL,
    exempt BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (userId, courseId),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId) REFERENCES parent_course(id)
);

-- Categories of absence reasons
CREATE TABLE absence_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO absence_category (name) VALUES ('illness'), ('family emergency'), ('personal reasons');

-- Types of codes
CREATE TABLE code_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO code_type (name) VALUES ('check-in'), ('check-out');

-- Session management for code generation and check-in
CREATE TABLE course_code (
    courseId INT NOT NULL,
    type INT NOT NULL,
    time DATETIME NOT NULL,
    timeOffset INT NOT NULL,
    attendanceCode VARCHAR(20),
    PRIMARY KEY (courseId, type),
    FOREIGN KEY (courseId) REFERENCES course(id),
    FOREIGN KEY (type) REFERENCES code_type(id)
);

-- Tracking user code scans
CREATE TABLE code_scan (
    userId INT NOT NULL,
    courseId INT NOT NULL,
    type INT NOT NULL,
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (userId, courseId, type),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId, type) REFERENCES course_code(courseId, type)
);


-- Reasons for late or no code scan
CREATE TABLE code_scan_unexpected (
    userId INT NOT NULL,
    courseId INT NOT NULL,
    type INT NOT NULL,
    reason INT NOT NULL,
    proofDocument VARCHAR(255),
    accepted BOOLEAN NOT NULL,
    PRIMARY KEY (userId, courseId, type),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (courseId, type) REFERENCES course_code(courseId, type),
    FOREIGN KEY (reason) REFERENCES absence_category(id)
);

-- Types of attendance
CREATE TABLE attendance_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO attendance_type (name) VALUES ('present'), ('absent'), ('excused');

-- View for attendance records
CREATE VIEW attendance_overview AS
SELECT
    u.id AS userId,
    u.name AS userName,
    u.surname AS userSurname,
    pc.id AS parentCourseId,
    pc.name AS parentCourseName,
    f.id AS facultyId,
    f.name AS facultyName,
    f.attendanceRate AS requiredAttendanceRate,
    uc.exempt AS isExempt,
    COUNT(DISTINCT cs.time) AS totalSessions,
    SUM(CASE
            WHEN csu.accepted IS TRUE THEN 1
            WHEN csu.accepted IS FALSE THEN 0
            WHEN csu.userId IS NULL THEN 1  -- Assuming no record in csu means the scan was expected and valid
            ELSE 0
        END) AS attendedSessions,
    CASE
        WHEN uc.exempt = TRUE THEN TRUE
        ELSE (SUM(CASE
                      WHEN csu.accepted IS TRUE THEN 1
                      WHEN csu.accepted IS FALSE THEN 0
                      WHEN csu.userId IS NULL THEN 1
                      ELSE 0
            END) / COUNT(DISTINCT cs.time)) * 100 >= f.attendanceRate
        END AS meetsAttendanceRequirements
FROM
    user u
        JOIN
    user_course uc ON u.id = uc.userId
        JOIN
    course c ON uc.courseId = c.id
        JOIN
    parent_course pc ON c.parentCourseId = pc.id
        JOIN
    faculty f ON pc.facultyId = f.id
        JOIN
    code_scan cs ON cs.userId = u.id AND cs.courseId = c.id
        LEFT JOIN
    code_scan_unexpected csu ON cs.userId = csu.userId AND cs.courseId = csu.courseId AND cs.type = csu.type
GROUP BY
    u.id, pc.id, uc.exempt;



