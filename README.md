# Task 1: Java Backend & REST API for Task Management

## Project Overview
This project is a Spring Boot application that provides a REST API to manage **Task** objects. Each task represents a shell command that can be executed. Task data is stored in **MongoDB**.

---

## **Tech Stack**
- Java 17
- Spring Boot 3.5.6
- Maven
- MongoDB
- VS Code (IDE)
- Postman / Curl (for API testing)

---

## **MongoDB Configuration**
Add these properties in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.database=tasksdb
spring.data.mongodb.port=27017
spring.data.mongodb.host=localhost

## **Running application**
mvn spring-boot:run


## REST API Endpoints

- **Create Task**: `PUT /tasks`  
  Request JSON body:  
  ```json
  {
    "id": "123",
    "name": "Print Hello",
    "owner": "John Smith",
    "command": "echo Hello World"
  }
