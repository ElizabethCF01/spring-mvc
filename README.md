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
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── example/
    │   │   │           └── spring_boot/
    │   │   │               ├── Application.java
    │   │   │               ├── controllers/
    │   │   │               │   └── UserController.java
    │   │   │               ├── dto/
    │   │   │               │   ├── MessageResponse.java
    │   │   │               │   └── UserRequest.java
    │   │   │               ├── model/
    │   │   │               │   └── UserModel.java
    │   │   │               ├── repositories/
    │   │   │               │   ├── IUserRepository.java
    │   │   │               │   └── UserRepository.java
    │   │   │               ├── services/
    │   │   │               │   └── UserManager.java
    │   │   │               └── util/
    │   │   │                   └── IPAddressUtils.java
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       └── templates/
    │   │           └── index.html
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── example/
    │                   └── spring_boot/
    │                       └── ApplicationTests.java
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties
```


## How to Run

### Prerequisites
- Java 17 or higher
- Maven (or use the included Maven wrapper)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/elizabethcf01-spring-mvc.git
   cd elizabethcf01-spring-mvc
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
