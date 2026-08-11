INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_TEACHER');

INSERT INTO users (id, username, firstname, lastname, password, email)
VALUES (1, 'admin', 'System', 'Administrator', '$2a$12$389cY8YwP2.jGVMX8bSxfuCF.EKp5rvvItHWkKarFODLjWEKCa/pa', 'admin@example.com');
INSERT INTO users (id, username, firstname, lastname, password, email)
VALUES (2, 'teacher.math', 'Aida', 'Toktosunova', '$2a$12$389cY8YwP2.jGVMX8bSxfuCF.EKp5rvvItHWkKarFODLjWEKCa/pa', 'aida.teacher@example.com');
INSERT INTO users (id, username, firstname, lastname, password, email)
VALUES (3, 'teacher.history', 'Bek', 'Sadykov', '$2a$12$389cY8YwP2.jGVMX8bSxfuCF.EKp5rvvItHWkKarFODLjWEKCa/pa', 'bek.teacher@example.com');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);

INSERT INTO groups (id, title) VALUES (1, 'CS-101');
INSERT INTO groups (id, title) VALUES (2, 'CS-102');
INSERT INTO groups (id, title) VALUES (3, 'SE-201');

INSERT INTO students (id, firstname, lastname, group_id) VALUES (1, 'Askar', 'Mamatov', 1);
INSERT INTO students (id, firstname, lastname, group_id) VALUES (2, 'Meerim', 'Isakova', 1);
INSERT INTO students (id, firstname, lastname, group_id) VALUES (3, 'Daniyar', 'Osmonov', 2);
INSERT INTO students (id, firstname, lastname, group_id) VALUES (4, 'Aigerim', 'Nurdinova', 2);
INSERT INTO students (id, firstname, lastname, group_id) VALUES (5, 'Timur', 'Bakytov', 3);

INSERT INTO subjects (id, title, teacher_id) VALUES (1, 'Mathematics', 2);
INSERT INTO subjects (id, title, teacher_id) VALUES (2, 'History', 3);
INSERT INTO subjects (id, title, teacher_id) VALUES (3, 'Programming Basics', 2);

INSERT INTO students_subjects (student_id, subject_id) VALUES (1, 1);
INSERT INTO students_subjects (student_id, subject_id) VALUES (1, 3);
INSERT INTO students_subjects (student_id, subject_id) VALUES (2, 1);
INSERT INTO students_subjects (student_id, subject_id) VALUES (2, 2);
INSERT INTO students_subjects (student_id, subject_id) VALUES (3, 1);
INSERT INTO students_subjects (student_id, subject_id) VALUES (3, 3);
INSERT INTO students_subjects (student_id, subject_id) VALUES (4, 2);
INSERT INTO students_subjects (student_id, subject_id) VALUES (5, 3);

INSERT INTO subject_assignments (id, title, subject_id) VALUES (1, 'Algebra homework', 1);
INSERT INTO subject_assignments (id, title, subject_id) VALUES (2, 'Geometry quiz', 1);
INSERT INTO subject_assignments (id, title, subject_id) VALUES (3, 'Ancient history essay', 2);
INSERT INTO subject_assignments (id, title, subject_id) VALUES (4, 'Java basics task', 3);

INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (1, TRUE, DATE '2026-08-10', 1, 1);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (2, TRUE, DATE '2026-08-10', 2, 1);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (3, FALSE, DATE '2026-08-10', 3, 1);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (4, TRUE, DATE '2026-08-10', 2, 2);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (5, TRUE, DATE '2026-08-10', 4, 2);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (6, TRUE, DATE '2026-08-10', 1, 3);
INSERT INTO attendance (id, attended, date, student_id, subject_id) VALUES (7, FALSE, DATE '2026-08-10', 5, 3);

INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (1, 88, 1, 1);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (2, 94, 1, 2);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (3, 76, 1, 3);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (4, 91, 3, 2);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (5, 83, 3, 4);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (6, 97, 4, 1);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (7, 79, 4, 3);
INSERT INTO points_for_assignments (id, points, assignment_id, student_id) VALUES (8, 85, 4, 5);

ALTER TABLE roles ALTER COLUMN id RESTART WITH 3;
ALTER TABLE users ALTER COLUMN id RESTART WITH 4;
ALTER TABLE groups ALTER COLUMN id RESTART WITH 4;
ALTER TABLE students ALTER COLUMN id RESTART WITH 6;
ALTER TABLE subjects ALTER COLUMN id RESTART WITH 4;
ALTER TABLE subject_assignments ALTER COLUMN id RESTART WITH 5;
ALTER TABLE attendance ALTER COLUMN id RESTART WITH 8;
ALTER TABLE points_for_assignments ALTER COLUMN id RESTART WITH 9;
