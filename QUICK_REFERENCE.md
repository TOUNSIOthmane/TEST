# DDD Architecture Quick Reference

## Package Structure Tree

```
com.test.springapp/
│
├── 📦 domain/                          # DOMAIN LAYER - Core business logic
│   ├── common/                         # Base abstractions
│   │   ├── Entity.java                 # Entity interface
│   │   ├── AggregateRoot.java         # Aggregate root marker
│   │   ├── ValueObject.java           # Value object marker
│   │   ├── DomainEvent.java           # Event interface
│   │   ├── Repository.java            # Repository interface
│   │   ├── DomainService.java         # Domain service marker
│   │   ├── BaseEntity.java            # Base entity implementation
│   │   └── BaseDomainEvent.java       # Base event implementation
│   ├── model/                          # Domain entities
│   │   ├── Product.java               # Product aggregate root (example)
│   │   └── ProductRepository.java     # Product repository interface
│   ├── service/                        # Domain services
│   └── event/                          # Domain events
│
├── 📦 application/                     # APPLICATION LAYER - Use cases
│   ├── service/                        # Application services
│   │   ├── ApplicationService.java    # Service marker
│   │   └── ProductService.java        # Product use cases (example)
│   ├── dto/                            # Data Transfer Objects
│   │   ├── DTO.java                   # DTO marker
│   │   ├── ProductDTO.java            # Product response DTO
│   │   ├── CreateProductDTO.java      # Create request DTO
│   │   └── UpdateProductDTO.java      # Update request DTO
│   ├── command/                        # Commands (write operations)
│   │   ├── Command.java               # Command marker
│   │   └── CommandHandler.java        # Command handler interface
│   ├── query/                          # Queries (read operations)
│   │   ├── Query.java                 # Query marker
│   │   └── QueryHandler.java          # Query handler interface
│   └── usecase/                        # Specific use cases
│
├── 📦 infrastructure/                  # INFRASTRUCTURE LAYER - Technical details
│   ├── persistence/                    # Database entities
│   │   ├── ProductEntity.java         # JPA product entity (example)
│   │   └── JpaProductRepository.java  # Spring Data repository
│   ├── repository/                     # Repository implementations
│   │   └── ProductRepositoryImpl.java # Domain repository implementation
│   ├── config/                         # Spring configuration
│   └── adapter/                        # External service adapters
│
├── 📦 presentation/                    # PRESENTATION LAYER - API/UI
│   ├── rest/                           # REST controllers
│   │   ├── ProductController.java     # Product API (example)
│   │   └── HelloController.java       # Demo endpoint
│   ├── exception/                      # Exception handlers
│   │   └── GlobalExceptionHandler.java # Global exception handler
│   └── mapper/                         # Request/response mappers
│
└── 📦 shared/                          # SHARED - Cross-cutting concerns
    ├── exception/                      # Common exceptions
    │   ├── DomainException.java        # Base domain exception
    │   ├── ResourceNotFoundException.java
    │   ├── ValidationException.java
    │   └── BusinessRuleViolationException.java
    ├── validation/                     # Validation utilities
    └── util/                           # Common utilities
```

## Layer Dependencies

```
┌─────────────────────────────────────────────┐
│    Presentation Layer (REST/UI)             │
│  • ProductController                        │
│  • GlobalExceptionHandler                   │
└──────────────────┬──────────────────────────┘
                   │ depends on ↓
┌──────────────────▼──────────────────────────┐
│    Application Layer (Use Cases)            │
│  • ProductService                           │
│  • DTOs, Commands, Queries                  │
└──────────────────┬──────────────────────────┘
                   │ depends on ↓
┌──────────────────▼──────────────────────────┐
│    Domain Layer (Business Logic)            │  ◄── CORE
│  • Product (Aggregate Root)                 │
│  • ProductRepository (Interface)            │
│  • Domain Services, Events                  │
└───────────────────────────────────────────┬─┘
                                            ▲
                                            │ implements
┌───────────────────────────────────────────┴─┐
│    Infrastructure Layer (Technical)         │
│  • ProductEntity (JPA)                      │
│  • ProductRepositoryImpl                    │
│  • Database, External APIs                  │
└─────────────────────────────────────────────┘
```

## Key Principles

### ✅ DO:
- Keep domain layer pure (no framework dependencies)
- Put business logic in domain entities
- Use aggregates to define consistency boundaries
- Make value objects immutable
- Use repositories to abstract persistence
- Use application services to orchestrate use cases
- Throw domain exceptions for business rule violations
- Separate persistence model (JPA entities) from domain model

### ❌ DON'T:
- Put business logic in controllers or services
- Access repositories directly from presentation layer
- Mix domain and persistence concerns
- Use anemic domain models (entities with only getters/setters)
- Expose domain entities directly through APIs
- Put infrastructure code in domain layer

## Example Flow: Create Product

```
1. HTTP POST /api/products
   ↓
2. ProductController receives CreateProductDTO
   ↓
3. ProductController calls ProductService.createProduct(dto)
   ↓
4. ProductService creates Product domain entity (validation happens here)
   ↓
5. ProductService calls ProductRepository.save(product)
   ↓
6. ProductRepositoryImpl converts Product to ProductEntity
   ↓
7. JpaProductRepository persists to database
   ↓
8. ProductRepositoryImpl converts back to Product
   ↓
9. ProductService converts Product to ProductDTO
   ↓
10. ProductController returns ProductDTO as JSON
```

## Quick Start Commands

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Test endpoints
curl http://localhost:8080/api/hello
curl http://localhost:8080/api/products

# Create a product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","description":"Gaming laptop","price":1299.99,"stockQuantity":10}'

# View H2 Console
open http://localhost:8080/h2-console
```

## Files to Extend

When adding a new domain:

1. **Domain Layer**
   - Create aggregate root: `domain/model/YourEntity.java`
   - Create repository interface: `domain/model/YourEntityRepository.java`

2. **Infrastructure Layer**
   - Create JPA entity: `infrastructure/persistence/YourEntityEntity.java`
   - Create Spring Data repo: `infrastructure/persistence/JpaYourEntityRepository.java`
   - Create repo implementation: `infrastructure/repository/YourEntityRepositoryImpl.java`

3. **Application Layer**
   - Create DTOs: `application/dto/YourEntityDTO.java`
   - Create service: `application/service/YourEntityService.java`

4. **Presentation Layer**
   - Create controller: `presentation/rest/YourEntityController.java`

Follow the `Product` example as a template!
