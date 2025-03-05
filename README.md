# Spring Boot Email Subscription Service

A simple Spring Boot MVC application that allows users to subscribe to a newsletter by submitting their email address.

## Project Overview

This application demonstrates the implementation of a Spring MVC architecture with the following features:
- Email subscription form with validation
- IP address tracking for subscribers
- In-memory storage of user data
- Thymeleaf templating for the frontend

## Technology Stack

- Java 17
- Spring Boot 3.4.3
- Spring MVC
- Thymeleaf
- Maven

## Folder Structure
```
└── elizabethcf01-spring-mvc/
    ├── src/
    │   ├── main/java/com/example/spring_boot/
    │   │   │   ├── Application.java
    │   │   │   ├── controllers/
    │   │   │   ├── dto/
    │   │   │   ├── model/
    │   │   │   ├── repositories/
    │   │   │   ├── services/
    │   │   │   └── util/
    │   │   └── resources/
    │   │       └── templates/
    │   └── test/
    └── .mvn/
```


## How to Run

### Prerequisites
- Java 17 or higher
- Maven (or use the included Maven wrapper)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/ElizabethCF01/spring-mvc.git
   cd spring-mvc
   ```
2. Build the project:
   ```./mvnw clean package```
   Or on Windows:
   ```mvnw.cmd clean package```
3. Run the application:
   ```./mvnw spring-boot:run```
   Or on Windows:
   ```mvnw.cmd spring-boot:run```
4. Access the application:
   Open your browser and navigate to `http://localhost:8080`
