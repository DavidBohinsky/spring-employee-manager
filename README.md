# Employee management app

This is a simple Java web application developed as part of a **Java Junior Developer course**. It demonstrates how to build a CRUD-based system using:

- Spring Boot (backend framework)
- Spring Data JPA (data access layer)
- Thymeleaf (template engine for HTML views)
- MySQL (relational database)

---

## Features

- View a list of all employees
- Add a new employee or company using form input
- Edit existing employee records
- Delete employees from the database
- Upload employee profile photos (with file size validation)
- Display data in a user-friendly table layout

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Thymeleaf
- MySQL
- Maven
- Bootstrap (for simple styling)

---

##  Setup Instructions

Clone the repository

Set up MySQL database:
- Create a database named employee_db
- Set your DB username/password in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=yourpassword


Open in browser: http://localhost:8080
