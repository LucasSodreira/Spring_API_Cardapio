# Cardápio API - AI Coding Instructions

## Architecture Overview
This is a Spring Boot REST API for a food menu (cardápio) system using:
- **Spring Boot 3.5.4** with Java 21
- **PostgreSQL** database with JPA/Hibernate
- **Lombok** for boilerplate reduction
- **Maven** for dependency management

## Project Structure & Patterns

### Entity-Repository-Controller Pattern
Follow the established 3-layer pattern in `src/main/java/com/example/cardapio/`:
```
food/
├── Food.java           # JPA Entity with Lombok annotations
├── FoodRepository.java # Spring Data JPA repository
├── FoodRequestDTO.java # Request payload (Java record)
└── FoodResponseDTO.java # Response payload with constructor from entity
controller/
└── FoodController.java # REST endpoints with @CrossOrigin
```

### Key Conventions
- **Entities**: Use `@Table(name = "foods")` and `@Entity(name = "foods")` for plural table names
- **DTOs**: Use Java records for request DTOs, classes with entity constructor for response DTOs
- **Controllers**: Place in `controller/` package, use `@CrossOrigin(origins = "*", allowedHeaders = "*")` for CORS
- **Repositories**: Extend `JpaRepository<Entity, IdType>` with no additional methods needed

### Database Configuration
- Database name: `food` (must be created manually in PostgreSQL)
- Connection configured in `application.properties`
- Uses `hibernate.ddl-auto=update` for automatic table creation/updates
- PostgreSQL dialect explicitly configured

## Critical Development Workflows

### Running the Application
```bash
# Using Maven wrapper (preferred)
./mvnw spring-boot:run

# Or compile and run
./mvnw clean compile
java -jar target/cardapio-0.0.1-SNAPSHOT.jar
```

### Database Setup
1. **Manual database creation required**: `CREATE DATABASE food;` in PostgreSQL
2. Tables auto-created by Hibernate on startup
3. **Common issue**: Price column type conflicts - drop/recreate database if schema changes

### API Endpoints
- **POST** `/food` - Create new food item
- **GET** `/food` - List all food items
- **Request format**: `{"title": "string", "image": "string", "price": integer}`
- **Response format**: `{"id": long, "title": "string", "image": "string", "price": integer}`

## Integration Points & Dependencies

### Key Dependencies (pom.xml)
- `spring-boot-starter-web` - REST API capabilities
- `spring-boot-starter-data-jpa` - Database ORM
- `postgresql` - Database driver
- `lombok` - Code generation

### CORS Configuration
All endpoints use wildcard CORS (`origins = "*"`) - suitable for development, review for production.

## Common Patterns & Gotchas

### Entity Construction
```java
// In controllers, always construct entities from DTOs
Food foodData = new Food(data);
repository.save(foodData);
```

### DTO Mapping
```java
// Response DTOs map via stream operations
List<FoodResponseDTO> foodList = repository.findAll()
    .stream().map(FoodResponseDTO::new).toList();
```

### Lombok Usage
- Entities use `@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of="id")`
- No setters needed due to constructor-based initialization

### Error Patterns to Avoid
- Don't create database manually in code - use external PostgreSQL tools
- Price field must be `Integer` type consistently across entity/DTOs
- Repository interfaces should be minimal - Spring Data handles implementation
