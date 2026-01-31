# Spring Boot 4 Application with Java 25

A modern Spring Boot application built with Java 25 and Spring Boot 4.0.2.

## Requirements

- **Java 25** or higher
- **Maven 3.9+**

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── test
│   │   │           └── springapp
│   │   │               ├── SpringAppApplication.java
│   │   │               └── controller
│   │   │                   └── HelloController.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── test
│                   └── springapp
│                       └── SpringAppApplicationTests.java
├── pom.xml
└── README.md
```

## Features

- **Spring Boot 4.0.2**: Latest version with first-class Java 25 support
- **Java 25**: Taking advantage of the latest JVM features
- **REST API**: Simple REST endpoints for demonstration
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

### GET /api/hello

Returns a simple greeting message.

**Request:**
```bash
curl http://localhost:8080/api/hello
```

**Response:**
```
Hello from Spring Boot 4 with Java 25!
```

### GET /api/info

Returns version information about Spring Boot and Java.

**Request:**
```bash
curl http://localhost:8080/api/info
```

**Response:**
```
Spring Boot Version: 4.0.2, Java Version: 25.0.1
```

## Configuration

The application can be configured through `src/main/resources/application.properties`:

- `spring.application.name`: Application name
- `server.port`: Server port (default: 8080)
- `logging.level.*`: Logging levels

## Technology Stack

- **Java**: 25.0.1
- **Spring Boot**: 4.0.2
- **Spring Framework**: 7.0.3
- **Maven**: 3.9.12
- **JUnit**: 5.10.2 (via Spring Boot Starter Test)

## License

This project is open source and available under the MIT License.