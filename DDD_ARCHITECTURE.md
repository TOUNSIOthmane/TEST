# Domain-Driven Design (DDD) Architecture

This document describes the Domain-Driven Design architecture implemented in this project.

## Architecture Overview

The application follows a layered DDD architecture with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│       Presentation Layer                │  <- REST Controllers, Exception Handlers
├─────────────────────────────────────────┤
│       Application Layer                 │  <- Use Cases, DTOs, Commands, Queries
├─────────────────────────────────────────┤
│       Domain Layer                      │  <- Entities, Aggregates, Domain Services
├─────────────────────────────────────────┤
│       Infrastructure Layer              │  <- Repositories, External Services
└─────────────────────────────────────────┘
```

## Layer Descriptions

### 1. Domain Layer (`domain`)

The **Domain Layer** is the heart of the application containing business logic and rules.

#### Components:

- **`domain.common`**: Base interfaces and classes
  - `Entity<ID>`: Base interface for entities with identity
  - `AggregateRoot<ID>`: Marker for aggregate roots
  - `ValueObject`: Marker for immutable value objects
  - `DomainEvent`: Interface for domain events
  - `Repository<T, ID>`: Base repository interface
  - `DomainService`: Marker for domain services
  - `BaseEntity<ID>`: Base implementation for entities
  - `BaseDomainEvent`: Base implementation for events

- **`domain.model`**: Domain entities and aggregates
  - Example: `Product` aggregate root

- **`domain.service`**: Domain services (stateless business logic)

- **`domain.event`**: Domain events

#### Key Principles:
- **Technology Independent**: No framework dependencies
- **Business Logic**: Contains all business rules
- **Aggregates**: Consistency boundaries around entities
- **Value Objects**: Immutable objects defined by attributes

### 2. Application Layer (`application`)

The **Application Layer** orchestrates use cases and coordinates domain objects.

#### Components:

- **`application.service`**: Application services
  - `ApplicationService`: Marker interface
  - Example: `ProductService` orchestrates product use cases

- **`application.dto`**: Data Transfer Objects
  - `DTO`: Marker interface
  - Request/Response objects for API

- **`application.command`**: Commands (write operations)
  - `Command`: Marker interface
  - `CommandHandler<C, R>`: Handler interface

- **`application.query`**: Queries (read operations)
  - `Query`: Marker interface
  - `QueryHandler<Q, R>`: Handler interface

- **`application.usecase`**: Specific use case implementations

#### Key Principles:
- **Transaction Boundaries**: Application services are transactional
- **Orchestration**: Coordinates domain objects
- **DTO Transformation**: Converts between domain and presentation models
- **Thin Layer**: No business logic, only orchestration

### 3. Infrastructure Layer (`infrastructure`)

The **Infrastructure Layer** provides technical implementations for domain interfaces.

#### Components:

- **`infrastructure.persistence`**: JPA entities and mappings
  - Example: `ProductEntity` (persistence model)
  - `JpaProductRepository` (Spring Data repository)

- **`infrastructure.repository`**: Repository implementations
  - Example: `ProductRepositoryImpl` adapts JPA to domain

- **`infrastructure.config`**: Configuration classes
  - Spring configuration
  - Database configuration

- **`infrastructure.adapter`**: External service adapters
  - Third-party integrations
  - External APIs

#### Key Principles:
- **Adapter Pattern**: Adapts external technologies to domain interfaces
- **Separation**: Persistence model separate from domain model
- **Implementation Details**: Contains framework-specific code

### 4. Presentation Layer (`presentation`)

The **Presentation Layer** handles HTTP requests and responses.

#### Components:

- **`presentation.rest`**: REST controllers
  - Example: `ProductController`, `HelloController`

- **`presentation.exception`**: Exception handlers
  - `GlobalExceptionHandler`: Translates exceptions to HTTP responses

- **`presentation.mapper`**: Request/Response mappers

#### Key Principles:
- **Thin Controllers**: Delegate to application services
- **HTTP Concerns**: Handle only HTTP-specific logic
- **Exception Translation**: Convert domain exceptions to HTTP responses

### 5. Shared Layer (`shared`)

The **Shared Layer** contains cross-cutting concerns.

#### Components:

- **`shared.exception`**: Common exceptions
  - `DomainException`: Base domain exception
  - `ResourceNotFoundException`: Resource not found
  - `ValidationException`: Validation failures
  - `BusinessRuleViolationException`: Business rule violations

- **`shared.validation`**: Validation utilities

- **`shared.util`**: Common utilities

## Package Structure

```
com.test.springapp
├── SpringAppApplication.java
├── domain/
│   ├── common/          # Base interfaces & classes
│   ├── model/           # Domain entities & aggregates
│   ├── service/         # Domain services
│   └── event/           # Domain events
├── application/
│   ├── service/         # Application services
│   ├── dto/             # Data Transfer Objects
│   ├── command/         # Commands & handlers
│   ├── query/           # Queries & handlers
│   └── usecase/         # Use case implementations
├── infrastructure/
│   ├── persistence/     # JPA entities & repositories
│   ├── repository/      # Repository implementations
│   ├── config/          # Configuration
│   └── adapter/         # External service adapters
├── presentation/
│   ├── rest/            # REST controllers
│   ├── exception/       # Exception handlers
│   └── mapper/          # Request/Response mappers
└── shared/
    ├── exception/       # Common exceptions
    ├── validation/      # Validation utilities
    └── util/            # Common utilities
```

## Example: Product Domain

The application includes a complete example of the Product domain:

### Domain Layer
- **`Product`**: Aggregate root with business logic
- **`ProductRepository`**: Repository interface

### Infrastructure Layer
- **`ProductEntity`**: JPA entity
- **`JpaProductRepository`**: Spring Data repository
- **`ProductRepositoryImpl`**: Repository implementation

### Application Layer
- **`ProductService`**: Application service
- **`CreateProductDTO`**, **`UpdateProductDTO`**, **`ProductDTO`**: DTOs

### Presentation Layer
- **`ProductController`**: REST controller with CRUD operations

## API Endpoints

### Product Management

```
POST   /api/products              # Create product
GET    /api/products              # List all products
GET    /api/products/{id}         # Get product by ID
PUT    /api/products/{id}         # Update product
DELETE /api/products/{id}         # Delete product
GET    /api/products/search?keyword={keyword}  # Search products
GET    /api/products/in-stock     # Get in-stock products
PATCH  /api/products/{id}/stock?quantity={quantity}  # Adjust stock
```

### Example Requests

**Create Product:**
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

**Get All Products:**
```bash
curl http://localhost:8080/api/products
```

## DDD Patterns Used

### 1. Aggregate Pattern
- **Product** is an aggregate root that ensures consistency
- All modifications go through the aggregate root

### 2. Repository Pattern
- Abstract data access behind repository interfaces
- Domain defines interfaces, infrastructure implements them

### 3. Value Object Pattern
- Immutable objects with no identity
- Defined by their attributes

### 4. Domain Event Pattern
- Events represent domain occurrences
- Can be used for event sourcing or messaging

### 5. Application Service Pattern
- Orchestrate use cases
- Transaction boundaries
- DTO transformation

### 6. Layered Architecture
- Clear separation of concerns
- Dependencies point inward toward domain

## Dependency Direction

```
Presentation Layer  ────▶  Application Layer  ────▶  Domain Layer
                                                          ▲
Infrastructure Layer ──────────────────────────────────┘
```

**Key Rule**: Dependencies only point toward the domain layer. The domain has no dependencies on other layers.

## Best Practices

1. **Keep Domain Pure**: No framework dependencies in domain layer
2. **Use Aggregates**: Define clear consistency boundaries
3. **Immutable Value Objects**: Value objects should be immutable
4. **Rich Domain Models**: Put business logic in entities
5. **Thin Controllers**: Controllers should only handle HTTP concerns
6. **Application Services**: Orchestrate domain objects, don't contain business logic
7. **Repository Abstraction**: Domain defines repository interfaces
8. **Separate Persistence Model**: JPA entities separate from domain entities
9. **Domain Events**: Use events for decoupling
10. **Exception Translation**: Translate domain exceptions at boundaries

## Testing Strategy

- **Domain Layer**: Unit tests for entities and domain services
- **Application Layer**: Integration tests for application services
- **Infrastructure Layer**: Repository tests with test database
- **Presentation Layer**: Controller tests with MockMvc

## Technologies

- **Spring Boot 3.2.2**: Application framework
- **Java 17**: Programming language
- **Spring Data JPA**: Data access
- **H2 Database**: In-memory database
- **Lombok**: Reduce boilerplate
- **Maven**: Build tool

## Getting Started

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the API:**
   - API: http://localhost:8080/api
   - H2 Console: http://localhost:8080/h2-console

4. **Test the endpoints:**
   ```bash
   # Create a product
   curl -X POST http://localhost:8080/api/products \
     -H "Content-Type: application/json" \
     -d '{"name":"Test Product","description":"Test","price":99.99,"stockQuantity":5}'
   
   # List products
   curl http://localhost:8080/api/products
   ```

## Next Steps

To extend this architecture:

1. **Add More Domains**: Create new aggregate roots following the Product example
2. **Implement CQRS**: Separate command and query models
3. **Add Domain Events**: Implement event publishing and handling
4. **Add Validation**: Use Bean Validation in DTOs
5. **Add Security**: Implement authentication and authorization
6. **Add Tests**: Write comprehensive tests for all layers
7. **Add API Documentation**: Use SpringDoc/OpenAPI

## References

- **Domain-Driven Design** by Eric Evans
- **Implementing Domain-Driven Design** by Vaughn Vernon
- **Clean Architecture** by Robert C. Martin
