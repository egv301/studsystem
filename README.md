# Spring boot application for student management system 
Single page application for student management system using Spring Boot and React.
App represents system with two roles: Teachers and Admin

Admin can:
- Add/Edit/Remove Groups
- Add/Edit/Remove Students
- Add/Edit/Remove Subjects
  
Teachers can:
- Add/Edit/Remove Subject assignments
- Put marks for assignments
- Taking student attendance

### Technologies: 
- Spring Boot
- Spring Data JPA
- Spring Validation API
- H2 database
- React frontend with Vite
- Spring Security role-based authentication using JWT token

### Frontend
Frontend source files are in `frontend`.

Run locally:
```bash
cd frontend
npm install
npm run dev
```

Build static assets for Spring Boot:
```bash
cd frontend
npm run build
```

Spring Boot does not run frontend commands automatically. Build or run the frontend separately when needed.

Run backend independently:
```bash
mvn spring-boot:run
```

When frontend runs independently through Vite, `/api` and `/h2-console` are proxied to `http://localhost:8080`.
