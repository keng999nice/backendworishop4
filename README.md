# Hello World REST API with JWT Authentication

A Spring Boot REST API with user authentication, JWT tokens, and Swagger documentation.

## Overview

This project demonstrates how to create a complete REST API using Spring Boot with user registration, login, JWT authentication, and database integration following the Spring.io tutorial.

## Features

- Spring Boot 2.7.18
- Java 11
- Maven build system
- User Registration & Authentication
- JWT Token-based Security
- SQLite Database with JPA/Hibernate
- Swagger/OpenAPI Documentation
- Password Encryption with BCrypt

## API Endpoints

### Public Endpoints
- `GET /` - Returns a JSON object with "Hello World" message
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

### Protected Endpoints (Requires JWT Token)
- `GET /api/auth/me` - Get current user profile

### Documentation
- `GET /swagger-ui.html` - Swagger UI Documentation
- `GET /api-docs` - OpenAPI JSON specification

## API Examples

### 1. Register a new user
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "firstname": "John",
    "lastname": "Doe",
    "phoneNumber": "0812345678",
    "birthday": "1990-01-01"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### 3. Get user profile (with JWT token)
```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## Getting Started

### Prerequisites

- Java 11 or later
- Maven 3.6+ (or use the included Maven wrapper)

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Database

The application uses SQLite database (`database.db` file) which will be created automatically in the project root directory.

### Swagger Documentation

Visit `http://localhost:8080/swagger-ui.html` to access the interactive API documentation.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/hello_world_api/
│   │       ├── HelloWorldApiApplication.java
│   │       ├── config/
│   │       │   ├── OpenApiConfig.java
│   │       │   └── WebSecurityConfig.java
│   │       ├── controller/
│   │       │   ├── AuthController.java
│   │       │   └── HelloWorldController.java
│   │       ├── dto/
│   │       │   ├── JwtResponse.java
│   │       │   ├── LoginRequest.java
│   │       │   ├── RegisterRequest.java
│   │       │   └── UserResponse.java
│   │       ├── entity/
│   │       │   └── User.java
│   │       ├── repository/
│   │       │   └── UserRepository.java
│   │       ├── security/
│   │       │   └── AuthTokenFilter.java
│   │       ├── service/
│   │       │   ├── UserDetailsServiceImpl.java
│   │       │   └── UserService.java
│   │       └── util/
│   │           └── JwtUtils.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/hello_world_api/
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework
- [Spring Security](https://spring.io/projects/spring-security) - Authentication and security
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Data persistence
- [SQLite](https://www.sqlite.org/) - Database
- [JWT](https://jwt.io/) - JSON Web Tokens for authentication
- [Swagger/OpenAPI](https://swagger.io/) - API documentation
- [Maven](https://maven.apache.org/) - Dependency management
- [Java](https://www.oracle.com/java/) - Programming language

## Security Configuration

- JWT tokens expire after 24 hours
- Passwords are encrypted using BCrypt
- Public endpoints: `/`, `/api/auth/**`, `/swagger-ui/**`, `/api-docs/**`
- All other endpoints require valid JWT token in Authorization header

## References

This project follows the [Spring REST Tutorial](https://spring.io/guides/tutorials/rest) guidelines with additional security and documentation features.
