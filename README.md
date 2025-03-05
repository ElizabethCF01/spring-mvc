# Spring Boot Email Subscription Service

A Spring Boot application that allows users to subscribe to a newsletter by submitting their email address. The system validates emails, tracks IP addresses, and stores user data in a MySQL database.

## Project Overview

This application demonstrates a Spring Boot architecture with the following features:
- **REST API for email subscription**
- **Email validation** to ensure correct format and prevent duplicates
- **IP address tracking** for subscribers
- **Persistent storage** using MySQL and JPA
- **MVC with Thymeleaf** for web-based form submission
- **Spring Data JPA** for database interactions

## Technology Stack

- Java 17
- Spring Boot 3.4.3
- Spring MVC & REST
- Spring Data JPA (Hibernate)
- MySQL
- Thymeleaf
- Maven

## Folder Structure
```
└── elizabethcf01-spring-mvc/
    ├── src/
    │   ├── main/java/com/example/spring_boot/
    │   │   │   ├── Application.java
    │   │   │   ├── controllers/  # Contains both REST and MVC controllers
    │   │   │   ├── dto/           # Data Transfer Objects (UserRequest, etc.)
    │   │   │   ├── model/         # JPA Entities (UserModel.java)
    │   │   │   ├── repositories/  # JPA Repositories (UserRepository.java)
    │   │   │   ├── services/      # Business logic (UserManager.java)
    │   │   │   ├── util/          # Utility classes (IPAddressUtils.java)
    │   │   └── resources/
    │   │       ├── templates/     # Thymeleaf views
    │   │       ├── application.properties
    │   └── test/
    └── .mvn/
```

## API Endpoints

### 1️⃣ **Subscribe via REST API**
**Endpoint:** `POST /api/subscribe`

**Request Body (JSON):**
```json
{
  "email": "user@example.com"
}
```
**Response:**
- `200 OK` → Subscription successful
- `400 Bad Request` → Invalid email format or duplicate email

### 2️⃣ **Subscribe via Web Form (Thymeleaf + MVC)**
Users can also subscribe via the form at:
```
http://localhost:8080
```

## Database Configuration (MySQL)
Ensure MySQL is running and update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/email_subscription_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mypassword

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## How to Run

### Prerequisites
- Java 17 or higher
- MySQL installed and running
- Maven (or use the included Maven wrapper)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/ElizabethCF01/spring-mvc.git
   cd spring-mvc
   ```
2. Configure your MySQL database (`application.properties`)
3. Build the project:
   ```bash
   ./mvnw clean package
   ```
   Or on Windows:
   ```bash
   mvnw.cmd clean package
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```
5. Access the application:
   - **Web form:** `http://localhost:8080`
   - **REST API:** Use Postman or Curl to call `POST http://localhost:8080/api/subscribe`

## Future Improvements
- Enhance error handling and logging 😅

---
Developed with ❤️ using Spring Boot 🚀

