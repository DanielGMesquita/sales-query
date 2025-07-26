# Sales Query System

A Spring Boot application for querying and reporting sales data with efficient pagination and filtering.

## Features

- Paginated sales report and summary endpoints
- Filter by date range and seller name
- Efficient DTO projections for API responses
- H2 in-memory database for development
- Follows best practices for performance and security

## Endpoints

- `/sales/report`  
  Returns paginated sales details.  
  **Query params:** `minDate`, `maxDate`, `name`

- `/sales/summary`  
  Returns paginated sales summary by seller.  
  **Query params:** `minDate`, `maxDate`

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Setup

1. Clone the repository:
    ```bash
    git clone
    ```

2. Build and run the application:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
3. Access H2 console at:
    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:salesdb`

## Usage

Send GET requests to the endpoints with appropriate query parameters.  
Example:

```bash
curl "http://localhost:8080/sales/report?minDate=2023-01-01&maxDate=2023-12-31&name=John"
```

## Testing

Run the tests using Maven:

```bash
mvn test
```

## Project Structure

```
sales-query-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/
│   │   │       └── danielmesquita/salesquery
│   │   │           ├── SalesQueryApplication.java
│   │   │           ├── controllers/
│   │   │           │   └── SaleController.java
│   │   │           ├── dto/
│   │   │           │   ├── SaleMinDTO.java
│   │   │           │   └── SaleSummaryDTO.java
│   │   │           ├── entities/
│   │   │           │   └── Sale.java
│   │   │           ├── repositories/
│   │   │           │   └── SaleRepository.java
│   │   │           └── services/
│   │   │               └── SaleService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── import.sql
│   └── test/
│       └── java/
│           └── dev/
│               └── danielmesquita/salesquery
│                   ├── SalesQueryApplicationTests.java
│                   ├── controller/
│                   │   └── SaleControllerTest.java
│                   ├── repository/
│                   │   └── SaleRepositoryTest.java
│                   └── service/
│                       └── SaleServiceTest.java
├── pom.xml
└── README.md
```

## Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Maven

## License

This project is for educational purposes.
