# TaskManager 🗂️ – Spring Boot Project with JWT Authentication

TaskManager is a robust backend application built using **Spring Boot**, designed to manage tasks, users, and task types with full **CRUD operations**. The application includes **JWT-based authentication**, cleanly separated layers (DTOs, services, mappers, exceptions), and supports **Swagger/OpenAPI** for API documentation.

---

## ✅ Features

- 🔐 JWT-based login authentication system
- 👥 Role-based User management
- ✅ Task CRUD with status tracking
- 🏷️ Task Types for category management
- 💡 Layered architecture (DTOs, Services, Mappers)
- ⚠️ Global exception handling
- 📖 Swagger UI support for API testing

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- JWT (JSON Web Tokens)
- H2 Database (can be swapped with MySQL/PostgreSQL)
- Swagger / OpenAPI
- Maven

---

## 🧱 Project Structure


src/
└── main/
├── java/com/taskmanager/
│ ├── config/
│ │ ├── SecurityConfig.java
│ │ └── OpenApiConfig.java
│ ├── controller/
│ │ ├── AuthController.java
│ │ ├── UserController.java
│ │ ├── TaskController.java
│ │ └── TaskTypeController.java
│ ├── dto/
│ │ ├── UserRequest.java / UserResponse.java
│ │ ├── TaskRequest.java / TaskResponse.java
│ │ └── TaskTypeRequest.java / TaskTypeResponse.java
│ ├── exception/
│ │ ├── GlobalExceptionHandler.java
│ │ └── ResourceNotFoundException.java
│ ├── mapper/
│ │ ├── UserMapper.java
│ │ ├── TaskMapper.java
│ │ └── TaskTypeMapper.java
│ ├── model/
│ │ ├── User.java
│ │ ├── Task.java
│ │ ├── TaskType.java
│ │ └── TaskStatus.java
│ ├── repository/
│ │ ├── UserRepository.java
│ │ ├── TaskRepository.java
│ │ └── TaskTypeRepository.java
│ ├── security/
│ │ ├── JwtUtil.java
│ │ ├── JwtFilter.java
│ │ └── UserDetailsServiceImpl.java
│ ├── service/
│ │ ├── AuthService.java
│ │ ├── UserService.java
│ │ ├── TaskService.java
│ │ ├── TaskTypeService.java
│ │ └── UserDetailsImpl.java
│ └── TaskManager.java
└── resources/
└── application.properties

---

## 🔐 Authentication Flow

- `POST /api/auth/login` – Accepts `{ username, password }`, returns JWT token.
- JWT is passed in the `Authorization` header as:  
  `Authorization: Bearer <token>`

### Sample Header:
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...


Protected endpoints require valid token. Spring Security filters requests via `JwtFilter`.

---

## 📬 Sample API Endpoints

| Function             | Method | Endpoint                  |
|----------------------|--------|---------------------------|
| Register/Login       | POST   | `/api/auth/login`         |
| Get All Users        | GET    | `/api/users`              |
| Create Task          | POST   | `/api/tasks`              |
| Get Tasks by User    | GET    | `/api/tasks/user/{id}`    |
| Create Task Type     | POST   | `/api/task-types`         |
| Get All Task Types   | GET    | `/api/task-types`         |

> (Note: Postman Collection can be added to your repo if needed.)

---

## 🚀 Running the Project

1. Clone the repo:
git clone https://github.com/<your-username>/task-manager.git
cd task-manager

2. Open in IntelliJ / VS Code  
3. Configure `application.properties` (default H2 or custom DB)  
4. Run `TaskManager.java` as Spring Boot application

---

## 📑 API Documentation

After running the app, Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html


Use this to test all endpoints interactively.

---

## ✨ Future Enhancements (Optional)

- Admin roles and permissions
- Email notifications for tasks
- Pagination and filtering
- Unit & Integration tests

---

## 📄 License

This project is open-source and available for educational and demonstration purposes.

---

## 🙋‍♀️ Author

**Shruti Tiwari**  
Email: [tiwarishruti0001@gmail.com](mailto:tiwarishruti0001@gmail.com)

---
