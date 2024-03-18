# GitHub Repository API

This Spring Boot application provides an API to fetch popular GitHub repositories based on certain criteria. The implementation was done using the official GitHub API documentation (https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28). Below is a brief overview of the classes and their functionalities within the application:

## Classes Overview

### `AppConfig`
- Configuration class responsible for creating a `RestTemplate` bean.

### `RepositoryController`
- REST controller class handling HTTP requests for fetching popular repositories.
- Endpoint: `/api/v1/repositories`
- Accepts parameters:
    - `count`: Number of repositories to fetch (default: 10).
    - `language`: Optional filter for repositories by programming language.
    - `since`: Optional filter for repositories created since a certain date.

### `ExceptionResponse`
- Data class representing an exception response with a code and message.

### `License`
- Data class representing the license details of a repository.

### `Owner`
- Data class representing the owner details of a repository.

### `Repository`
- Data class representing a GitHub repository with numerous attributes including ID, name, owner details, URLs, etc.

### `RepositoryList`
- Data class representing a list of repositories with total count and items.

### `CustomExceptionHandler`
- Global exception handler class to handle custom exceptions.
- Handles `IllegalArgumentException` and `ThirdpartyServiceException` with appropriate responses.

### `ThirdpartyServiceException`
- Custom runtime exception class for third-party service exceptions.

### `RepositoryService`
- Service class responsible for fetching popular repositories from the GitHub API.
- Uses `RestTemplate` for making HTTP requests.
- Constructs the GitHub API URL based on input parameters like count, language, and since date.

## Usage

To use this application, you can follow these steps:

1. Ensure you have Java 17 and Maven installed on your system.
2. Clone the repository.
3. Navigate to the project directory.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.
6. Access the API endpoints as described above.

## Swagger

You can hit below link to get swagger doc.

- **http://localhost:8080/swagger-ui/index.html**

## Dependencies

- Spring Boot
- RestTemplate
- Lombok
- Jackson (for JSON parsing)

## API Documentation

- Endpoint: `/api/v1/repositories`
- Method: `GET`
- Parameters:
    - `count`: Number of repositories to fetch (default: 10).
    - `language`: Optional filter for repositories by programming language.
    - `since`: Optional filter for repositories created since a certain date.
  Example: `http://localhost:8080/api/v1/repositories?count=10&language=Java&since=2023-01-15`
- Responses:
    - `200 OK`: Returns a list of repositories based on the provided criteria.
    - `400 Bad Request`: Returned when an invalid request is made.
    - `503 Service Unavailable`: Returned when there's an issue with the third-party service (GitHub API).

## Contributing

Contributions are welcome! Feel free to open issues or contact me. :) 