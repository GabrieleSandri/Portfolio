# ServerSpringbootApplication

This is a Spring Boot application that manages movies, actors, crews, genres, countries, and Oscar awards data.  
It uses PostgreSQL as the database and Spring Data JPA for data persistence.

---

## Features

- Manage movies with details such as name, release date, tagline, description, and duration.
- Manage related entities: actors, crews, genres, countries, and Oscar awards.
- Provides repository interfaces for CRUD operations on all entities.
- Supports searching movies by actor name, genre, or movie name with pagination.
- Handles relationships between movies and other entities using JPA mappings.
- Swagger UI documentation available at `/api-docs`

---

## Technologies Used

- Java 
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jakarta Persistence API (JPA)

---

## Requirements

- Java
- PostgreSQL installed and running
- Gradle for building the project

---

## Database Setup (PostgreSQL)

1. Read readme.md of "/Datas"

--- 

## Installation

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd <repository-folder>

2. Run

```bash
    ./gradlew clean build
    ./gradlew bootRun
    
Alternatively, you can run the project using intellij IDE 


