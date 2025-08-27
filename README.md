# Hello World REST API

A simple Spring Boot REST API that returns "Hello World" in JSON format.

## Overview

This project demonstrates how to create a basic REST API using Spring Boot following the Spring.io tutorial. It provides a simple endpoint that returns a JSON response with a "Hello World" message.

## Features

- Spring Boot 2.7.18
- Java 11
- Maven build system
- REST API endpoint at `GET /`
- JSON response format

## API Endpoints

- `GET /` - Returns a JSON object with "Hello World" message

Example response:
```json
{
  "message": "Hello World"
}
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

Or using Maven directly:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Testing the API

You can test the API using curl:

```bash
curl http://localhost:8080/
```

Or visit `http://localhost:8080/` in your web browser.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/hello_world_api/
│   │       ├── HelloWorldApiApplication.java
│   │       └── controller/
│   │           └── HelloWorldController.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/hello_world_api/
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework
- [Maven](https://maven.apache.org/) - Dependency management
- [Java](https://www.oracle.com/java/) - Programming language

## References

This project follows the [Spring REST Tutorial](https://spring.io/guides/tutorials/rest) guidelines.
