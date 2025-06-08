# 🗂️ Task & Category Management REST API

A robust and reliable Spring Boot-based RESTful API designed for managing tasks and their associated categories. This system supports full CRUD operations, partial updates via PATCH, and comprehensive exception handling, making it ideal for integration with frontend applications or mobile clients.

## 🚀 Features

- **Task Management**: Create, retrieve, update, and delete tasks with attributes like title, description, status, and due dates.
- **Category Management**: Organize tasks into categories for better structure and retrieval.
- **Partial Updates**: Utilize PATCH requests to update specific fields of tasks or categories.
- **Timestamp Automation**: Automatically manage timestamps for task creation, start, and completion events.
- **Exception Handling**: Consistent and informative error responses using a global exception handler.
- **Bulk Operations**: Efficiently create multiple tasks or categories in a single request.

## 📦 Tech Stack

- **Backend**: Spring Boot, Spring Web, Spring Data JPA
- **Testing**: Swagger UI,Postman
- **Database**: MySQL
- **Language**: Java 21
- **Build Tool**: Maven

## 📁 Project Structure

- `controller/` – REST controllers for tasks and categories.
- `service/` – Business logic and service layer.
- `dto/` – Data Transfer Objects for API communication.
- `entity/` – JPA entities representing database tables.
- `repository/` – Spring Data JPA repositories.
- `mapper/` – Utilities for converting between entities and DTOs.
- `exception/` – Custom exceptions and global exception handler.
- `utils/` – Utility classes for timestamp management.

