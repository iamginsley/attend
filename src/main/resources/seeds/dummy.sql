-- Dummy data for absence_category table
INSERT INTO absence_category (name) VALUES
('Medical Leave'),
('Vacation'),
('Personal Leave');

-- Dummy data for code_type table
INSERT INTO code_type (name) VALUES
('QR Code'),
('Bar Code'),
('RFID');

-- Dummy data for course table
INSERT INTO course (name, parentCourseId) VALUES
('Introduction to Programming', NULL),
('Classical Mechanics', NULL),
('Shakespearean Literature', NULL);

-- Dummy data for faculty table
INSERT INTO faculty (attendanceRate, name) VALUES
(90, 'Faculty of Science'),
(85, 'Faculty of Arts'),
(92, 'Faculty of Engineering');

-- Dummy data for parent_course table
INSERT INTO parent_course (name, facultyId, lecturerId) VALUES
('Computer Science 101', 3, 5),
('Physics 101', 1, 6),
('English Literature 101', 2, 7);

-- Dummy data for role table
INSERT INTO role (name) VALUES
('Admin'),
('Instructor'),
('Student');

-- Dummy data for user table
INSERT INTO user (email, name, password, studentNumber, surname, username, roleId) VALUES
('admin@example.com', 'Admin', 'admin123', 'A12345', 'Doe', 'admin', 1),
('instructor@example.com', 'John', 'instructor123', 'B67890', 'Smith', 'instructor', 2),
('student1@example.com', 'Alice', 'student123', 'S12345', 'Johnson', 'alice', 3),
('student2@example.com', 'Bob', 'student123', 'S67890', 'Williams', 'bob', 3);

-- Dummy data for code_scan table
INSERT INTO code_scan (time, type, userId, courseId) VALUES
('2024-05-06 08:00:00', 1, 3, 1),
('2024-05-06 09:30:00', 2, 4, 3),
('2024-05-06 10:45:00', 3, 3, 1),
('2024-05-06 13:00:00', 1, 4, 3),
('2024-05-06 14:30:00', 2, 3, 1);

-- Dummy data for code_scan_unexpected table
INSERT INTO code_scan_unexpected (accepted, proofDocument, type, userId, courseId, reason) VALUES
(0, 'medical_certificate.pdf', 1, 4, 3, 1),
(0, 'personal_reason.pdf', 2, 3, 1, 3),
(0, 'vacation_proof.pdf', 1, 4, 3, 2);

-- Dummy data for course_code table
INSERT INTO course_code (attendanceCode, time, timeOffset, type, courseId) VALUES
('ABC123', '2024-05-06 08:00:00', 0, 1, 1),
('DEF456', '2024-05-06 09:00:00', 0, 2, 1),
('GHI789', '2024-05-06 10:00:00', 0, 3, 1),
('JKL012', '2024-05-06 11:00:00', 0, 1, 2),
('MNO345', '2024-05-06 12:00:00', 0, 2, 2),
('PQR678', '2024-05-06 13:00:00', 0, 3, 2);

-- Dummy data for user_course table
INSERT INTO user_course (exempt, userId, courseId) VALUES
(0, 3, 1),
(0, 4, 3);

-- Dummy data for user_faculty table
INSERT INTO user_faculty (userId, facultyId) VALUES
(2, 3),
(3, 1),
(4, 2);