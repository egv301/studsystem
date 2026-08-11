# Student Management System

A single-page student management application built with Spring Boot and React. The system separates work by role:

- **Admin** users manage groups, students, subjects, and student-subject registrations.
- **Teacher** users manage subject assignments, record attendance, and save points for student assignments.

The backend exposes a JWT-protected REST API and stores data in an in-memory H2 database. The frontend is a Vite React application that can run separately during development or be built into Spring Boot static assets.

## Technologies

### Backend

- Java 11
- Spring Boot 2.7.18
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- JSON Web Tokens with `jjwt` 0.11.5
- H2 in-memory database
- Maven

### Frontend

- React 19
- React DOM 19
- Vite 7
- `@vitejs/plugin-react`
- Plain CSS

## Project Structure

```text
.
├── frontend/                 # React + Vite frontend source
├── src/main/java/            # Spring Boot backend source
├── src/main/resources/       # Application config, seed data, static frontend build
├── pom.xml                   # Maven backend configuration
└── README.md
```

## Requirements

- Java 11 or newer
- Maven 3.6+
- Node.js and npm

## How to Run

### 1. Run the Backend

From the project root:

```bash
mvn spring-boot:run
```

The backend starts at:

```text
http://localhost:8080
```

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

Use these H2 settings:

```text
JDBC URL: jdbc:h2:mem:studsystem
Username: sa
Password:
```

The database is seeded from `src/main/resources/data.sql` on startup.

### 2. Run the Frontend in Development

In a second terminal:

```bash
cd frontend
npm install
npm run dev
```

The Vite development server starts at:

```text
http://localhost:5173
```

During frontend development, Vite proxies `/api` and `/h2-console` requests to `http://localhost:8080`.

### 3. Build Frontend Static Assets

```bash
cd frontend
npm install
npm run build
```

The frontend build output is intended to be served by Spring Boot from `src/main/resources/static`.

### 4. Package the Backend

```bash
mvn clean package
```

Run the packaged application:

```bash
java -jar target/studsystem-1.0-SNAPSHOT.jar
```

## Authentication

Authentication uses JWT bearer tokens.

1. Call `POST /api/login` with a username and password.
2. Read the returned `token`.
3. Send protected requests with:

```http
Authorization: Bearer <token>
```

Role-based access:

- `/api/admin/**` requires `ROLE_ADMIN`.
- `/api/teacher/**` requires `ROLE_TEACHER`.
- `/api/login`, `/api/registration`, static assets, and the H2 console are public.

Seed users are created in `src/main/resources/data.sql`:

- `admin` with `ROLE_ADMIN`
- `teacher.math` with `ROLE_TEACHER`
- `teacher.history` with `ROLE_TEACHER`

All seeded users use the password `admin`.

## API Documentation

Base URL:

```text
http://localhost:8080
```

All request and response bodies use JSON unless noted otherwise.

### Common Error Responses

Validation errors return HTTP `400` with field messages:

```json
{
  "title": "Title should not be empty."
}
```

Application errors return an error message:

```json
{
  "errorMessage": "Entity not found"
}
```

Unauthorized requests return HTTP `401`.

## Public API

### Login

```http
POST /api/login
```

Request:

```json
{
  "username": "admin",
  "password": "admin"
}
```

Response:

```json
{
  "roleName": "ROLE_ADMIN",
  "token": "jwt-token"
}
```

### Register User

Newly registered users are assigned the teacher role by default.

```http
POST /api/registration
```

Request:

```json
{
  "username": "new.teacher",
  "password": "password",
  "confirmPassword": "password",
  "email": "teacher@example.com"
}
```

Response:

```json
{
  "id": 4,
  "username": "new.teacher",
  "email": "teacher@example.com"
}
```

## Admin API

All admin endpoints require:

```http
Authorization: Bearer <ROLE_ADMIN token>
```

### Groups

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/admin/group-list` | List all groups. |
| `GET` | `/api/admin/group/{group_id}` | Get one group. |
| `POST` | `/api/admin/add-group` | Create a group. |
| `PUT` | `/api/admin/update-group` | Update a group. |
| `DELETE` | `/api/admin/delete-group/{group_id}` | Delete a group. |

Group request body:

```json
{
  "id": 1,
  "title": "CS-101"
}
```

For create requests, `id` can be omitted.

### Students

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/admin/student-list` | List students as DTOs. |
| `GET` | `/api/admin/student-list/{group_id}` | List students in a group. |
| `GET` | `/api/admin/showAddStudentForm` | List groups for the student form. |
| `GET` | `/api/admin/showUpdateStudentForm/{student_id}` | Get a student and available groups for update. |
| `GET` | `/api/admin/student/{student_id}` | Get one student. |
| `POST` | `/api/admin/add-student` | Create a student. |
| `PUT` | `/api/admin/update-student` | Update a student. |
| `DELETE` | `/api/admin/delete-student/{student_id}` | Delete a student. |

Student request body:

```json
{
  "id": 1,
  "firstname": "Askar",
  "lastname": "Mamatov",
  "group": 1
}
```

For create requests, `id` can be omitted.

### Subjects

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/admin/subject-list` | List all subjects. |
| `GET` | `/api/admin/showAddSubjectForm` | List teachers for the subject form. |
| `GET` | `/api/admin/subject/{subject_id}` | Get a subject and available teachers for update. |
| `POST` | `/api/admin/add-subject` | Create a subject. |
| `PUT` | `/api/admin/update-subject` | Update a subject. |
| `DELETE` | `/api/admin/delete-subject/{subject_id}` | Delete a subject. |

Subject request body:

```json
{
  "id": 1,
  "title": "Mathematics",
  "teacher": 2
}
```

For create requests, `id` can be omitted.

### Student Subject Registration

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/admin/getStudentsSubjects/{student_id}` | Get assigned and available subjects for a student. |
| `POST` | `/api/admin/addSubjectToStudent` | Assign a subject to a student. |
| `DELETE` | `/api/admin/removeSubjectFromStudent` | Remove a subject from a student. |

Student-subject request body:

```json
{
  "student": 1,
  "subject": 3
}
```

`GET /api/admin/getStudentsSubjects/{student_id}` response:

```json
{
  "student": 1,
  "studentsSubject": [
    {
      "id": 1,
      "title": "Mathematics"
    }
  ],
  "subjectsToRegister": [
    {
      "id": 2,
      "title": "History"
    }
  ]
}
```

## Teacher API

All teacher endpoints require:

```http
Authorization: Bearer <ROLE_TEACHER token>
```

### Teacher Subjects

```http
GET /api/teacher/teachers-subjects
```

Returns subjects assigned to the authenticated teacher.

Response:

```json
[
  {
    "id": 1,
    "title": "Mathematics",
    "teacher": 2
  }
]
```

### Assignments

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/teacher/assignment-list/{subject_id}` | List assignments for a subject. |
| `GET` | `/api/teacher/assignment/{assignment_id}` | Get one assignment. |
| `POST` | `/api/teacher/add-assignment` | Create an assignment. |
| `PUT` | `/api/teacher/update-assignment` | Update an assignment. |
| `DELETE` | `/api/teacher/delete-assignment/{subjectAssignment_id}` | Delete an assignment. |

Assignment request body:

```json
{
  "id": 1,
  "title": "Algebra homework",
  "subject": 1
}
```

For create requests, `id` can be omitted.

### Attendance

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/teacher/getAttendanceList/{subject_id}/{date}` | Get attendance for a subject on a date. |
| `POST` | `/api/teacher/attendance` | Create or update attendance for one student. |

Date path variables use ISO format:

```text
YYYY-MM-DD
```

Attendance request body:

```json
{
  "student": 1,
  "subject": 1,
  "date": "2026-08-10",
  "attended": true
}
```

Attendance list response:

```json
[
  {
    "studentId": 1,
    "firstName": "Askar",
    "lastName": "Mamatov",
    "attended": true,
    "date": "2026-08-10"
  }
]
```

### Points

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/teacher/getPointsList/{assignment_id}` | Get point-entry data for an assignment. |
| `POST` | `/api/teacher/addPointsToAssignment` | Create or update points for one student assignment. |

Points request body:

```json
{
  "student": 1,
  "subjectAssignment": 1,
  "points": 88
}
```

Points list response:

```json
{
  "assignmentId": 1,
  "assignmentTitle": "Algebra homework",
  "pointsAssignmentList": [
    {
      "student": 1,
      "studentFirstname": "Askar",
      "studentLastname": "Mamatov",
      "points": 88
    }
  ]
}
```

## Example API Usage

Login:

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'
```

Create a group:

```bash
curl -X POST http://localhost:8080/api/admin/add-group \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"title":"CS-103"}'
```

Load teacher subjects:

```bash
curl http://localhost:8080/api/teacher/teachers-subjects \
  -H "Authorization: Bearer <token>"
```
