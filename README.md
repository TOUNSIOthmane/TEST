# Spring Boot 4 Application with Java 25 - DDD Architecture

A modern Spring Boot application built with Java 25 and Spring Boot 4.0.2, implementing **Domain-Driven Design (DDD)** architecture.

## 🏗️ Architecture

This application follows a comprehensive **Domain-Driven Design** architecture with clear separation of concerns across four main layers:

- **Domain Layer**: Core business logic and entities
- **Application Layer**: Use cases and orchestration
- **Infrastructure Layer**: Technical implementations
- **Presentation Layer**: REST API controllers

**📖 For detailed architecture documentation, see [DDD_ARCHITECTURE.md](DDD_ARCHITECTURE.md)**

## Requirements

- **Java 25** or higher
- **Maven 3.9+**

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.test.springapp
│   │   │       ├── SpringAppApplication.java
│   │   │       ├── domain/              # Domain Layer
│   │   │       │   ├── common/          # Base interfaces
│   │   │       │   ├── model/           # Entities & Aggregates
│   │   │       │   ├── service/         # Domain services
│   │   │       │   └── event/           # Domain events
│   │   │       ├── application/         # Application Layer
│   │   │       │   ├── service/         # Application services
│   │   │       │   ├── dto/             # Data Transfer Objects
│   │   │       │   ├── command/         # Commands & handlers
│   │   │       │   ├── query/           # Queries & handlers
│   │   │       │   └── usecase/         # Use cases
│   │   │       ├── infrastructure/      # Infrastructure Layer
│   │   │       │   ├── persistence/     # JPA entities
│   │   │       │   ├── repository/      # Repository implementations
│   │   │       │   ├── config/          # Configuration
│   │   │       │   └── adapter/         # External adapters
│   │   │       ├── presentation/        # Presentation Layer
│   │   │       │   ├── rest/            # REST controllers
│   │   │       │   ├── exception/       # Exception handlers
│   │   │       │   └── mapper/          # Mappers
│   │   │       └── shared/              # Shared utilities
│   │   │           ├── exception/       # Common exceptions
│   │   │           ├── validation/      # Validation
│   │   │           └── util/            # Utilities
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
├── pom.xml
├── README.md
└── DDD_ARCHITECTURE.md              # Complete architecture guide
```

## Features

- **Spring Boot 4.0.2**: Latest version with first-class Java 25 support
- **Java 25**: Taking advantage of the latest JVM features
- **Domain-Driven Design**: Clean architecture with separated layers
- **REST API**: Comprehensive REST endpoints
- **JPA & H2**: In-memory database for persistence
- **Exception Handling**: Global exception handling
- **Example Domain**: Complete Product domain implementation
- **Maven**: Build and dependency management

## Building the Application

To build the application, run:

```bash
mvn clean package
```

This will create an executable JAR file in the `target` directory.

## Running the Application

To run the application:

```bash
java -jar target/springapp-1.0.0-SNAPSHOT.jar
```

Or using Maven:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## Testing

To run the tests:

```bash
mvn test
```

## API Endpoints

### System Endpoints

#### GET /api/hello

Returns a simple greeting message.

**Request:**
```bash
curl http://localhost:8080/api/hello
```

**Response:**
```
Hello from Spring Boot 4 with Java 25!
```

#### GET /api/info

Returns version information about Spring Boot and Java.

**Request:**
```bash
curl http://localhost:8080/api/info
```

**Response:**
```
Spring Boot Version: 4.0.2, Java Version: 25.0.1
```

### Product Management Endpoints

#### POST /api/products
Creates a new product.

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 1299.99,
    "stockQuantity": 10
  }'
```

#### GET /api/products
Lists all products.

```bash
curl http://localhost:8080/api/products
```

#### GET /api/products/{id}
Gets a specific product by ID.

```bash
curl http://localhost:8080/api/products/1
```

#### PUT /api/products/{id}
Updates a product.

```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Laptop",
    "description": "Updated description",
    "price": 1199.99
  }'
```

#### DELETE /api/products/{id}
Deletes a product.

```bash
curl -X DELETE http://localhost:8080/api/products/1
```

#### GET /api/products/search?keyword={keyword}
Searches products by name.

```bash
curl http://localhost:8080/api/products/search?keyword=laptop
```

#### GET /api/products/in-stock
Gets all products in stock.

```bash
curl http://localhost:8080/api/products/in-stock
```

#### PATCH /api/products/{id}/stock?quantity={quantity}
Adjusts product stock (positive or negative).

```bash
curl -X PATCH http://localhost:8080/api/products/1/stock?quantity=-2
```

## Configuration

The application can be configured through `src/main/resources/application.properties`:

- `spring.application.name`: Application name
- `server.port`: Server port (default: 8080)
- `spring.datasource.url`: Database URL (H2 in-memory by default)
- `spring.jpa.hibernate.ddl-auto`: DDL mode (create-drop for development)
- `spring.h2.console.enabled`: Enable H2 web console (default: true)
- `logging.level.*`: Logging levels

### H2 Database Console

The H2 console is available at: http://localhost:8080/h2-console

**Connection settings:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## Technology Stack

- **Java**: 25.0.1
- **Spring Boot**: 4.0.2
- **Spring Framework**: 7.0.3
- **Spring Data JPA**: For data persistence
- **H2 Database**: In-memory database
- **Lombok**: Reduce boilerplate code
- **MapStruct**: DTO mapping
- **Maven**: 3.9.12
- **JUnit**: 5.10.2 (via Spring Boot Starter Test)

## License

This project is open source and available under the MIT License.

## DDD Architecture Guide

For a comprehensive guide to the Domain-Driven Design architecture used in this project, including:
- Layer descriptions and responsibilities
- Package structure and organization
- DDD patterns and best practices
- Example implementations
- Testing strategies

**See the [DDD Architecture Documentation](DDD_ARCHITECTURE.md)**