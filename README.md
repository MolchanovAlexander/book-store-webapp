# Book Store Web Application

## Overview
This is a web application for managing a book store, written in Java. The application supports various functionalities like listing books, adding new books, updating existing book details, and deleting books.

## Technologies Used
- **Java**: The primary programming language used for the backend development.
- **Spring Boot**: Framework used to create stand-alone, production-grade Spring-based applications.
- **Hibernate**: ORM tool for database interaction.
- **MySQL**: Database for storing book records and related data.
- **Maven**: Build automation tool used for managing dependencies and building the project.
- **Docker**: Used for containerizing the application to ensure consistent environments across development and production.
- **Docker Compose**: Tool for defining and running multi-container Docker applications.
- **GitHub Actions**: For CI/CD to automate the build and deployment process.
- **Checkstyle**: Code analysis tool to ensure code quality and adherence to coding standards.

## Project Structure
 - src/: Contains the source code of the application.
 - Dockerfile: Instructions for building the Docker image.
 - docker-compose.yml: Configuration for Docker Compose to run the application and its dependencies.
 - pom.xml: Maven configuration file.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- Docker
- Docker Compose 

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/MolchanovAlexander/book-store-webapp.git
   cd book-store-webapp

2. Create .env file in root directory with test data
      ```plaintext
    SPRING_LOCAL_PORT=8088
    SPRING_DOCKER_PORT=8080
    DEBUG_PORT=5005
    
    MYSQL_LOCAL_PORT=3333
    MYSQL_DOCKER_PORT=3306
    
    DB_LOGIN=root
    MYSQL_PASSWORD=---ABCtest11
    
    MYSQL_DATABASE=bookstore
    JWT_EXPIRATION_TIME=3000000
    JWT_SECRET=ttttttdfdfc1213123ttttt1234567890RRRRwwqq252525cccH


3. Build the application:

    ```sh
    mvn clean install
   
4. Run the application using Docker Compose:
    ```sh
    docker-compose up --build

5. Access the application at http://localhost:8088 via Postman or use [swagger](http://localhost:8088/api/swagger-ui/index.html#)

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.