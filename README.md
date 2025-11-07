# Spring Basics — Demo API

A small Spring Boot 3 application demonstrating:
- REST controllers for People, Software Engineers, and Books
- JPA/Hibernate entities with relationships
- DTOs to control JSON payloads and prevent infinite recursion
- Simple database seeding on startup (idempotent)

## Tech Stack
- Java 21
- Spring Boot 3 (Web, Data JPA)
- PostgreSQL (via Docker Compose)
- Maven

## Domain Overview
- People (entity)
  - One-to-one with SoftwareEngineer
  - One-to-many with Book
- SoftwareEngineer (entity)
  - Back-reference to People
- Book (entity)
  - Many-to-one to People

To keep API responses compact and avoid circular JSON, controllers use DTOs and Jackson managed/back references where appropriate.

## Prerequisites
- Java 21+
- Maven 3.9+
- Docker and Docker Compose

## Quick Start — Run Postgres with Docker, then run the app locally
This is the recommended way to run the app during development.

1) Start PostgreSQL using Docker Compose
```
# from the project root
docker compose up -d
```
The compose file exposes Postgres on port 5332 and creates database `amigos` with user `amigoscode`/`password`.

2) Build and run the app
```
# build
./mvnw clean package

# run (option A)
./mvnw spring-boot:run

# or run the built jar (option B)
java -jar target/spring-basics-0.0.1-SNAPSHOT.jar
```

The app uses the following datasource (already configured in `src/main/resources/application.properties`):
```
spring.datasource.url=jdbc:postgresql://localhost:5332/amigos
spring.datasource.username=amigoscode
spring.datasource.password=password
```

3) Try the endpoints
You can use the provided `requests.http` file or curl:
```
# List people (DTO response)
curl http://localhost:8080/people

# List software engineers (DTO response)
curl http://localhost:8080/api/v1/software-engineers

# List books
curl http://localhost:8080/books
```

Seed data: On first run, the app inserts one `People` with one `SoftwareEngineer` and one `Book`. Seeding is idempotent (guarded by repository counts).

## Stop and clean the database
```
# stop containers
docker compose down

# remove containers and persisted volume (DATA LOSS)
docker compose down -v
```

## Troubleshooting
- Error: `Unable to determine Dialect without JDBC metadata...`
  - Cause: App could not connect to the database to fetch metadata. Ensure Postgres is running: `docker compose up -d`.
  - Verify connectivity: `psql -h localhost -p 5332 -U amigoscode -d amigos` (password: `password`).
  - Check that the datasource URL points to `jdbc:postgresql://localhost:5332/amigos`.

- Tables not created / schema issues
  - For dev, `spring.jpa.hibernate.ddl-auto=create-drop` is set. You can change it to `update` if you want to preserve data between runs.

## Optional: Run the app itself in Docker (containerize)
This repository does not include a `Dockerfile` yet. If you want to containerize the application:
1) Create a `Dockerfile` (example multi-stage):
```
# syntax=docker/dockerfile:1
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/spring-basics-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
```
2) Build and run it (with the Postgres container from docker compose):
```
docker build -t spring-basics:local .
# ensure DB is up
docker compose up -d
# run app container, network with DB via host port mapping
docker run --rm -p 8080:8080 --name spring-basics \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5332/amigos \
  -e SPRING_DATASOURCE_USERNAME=amigoscode \
  -e SPRING_DATASOURCE_PASSWORD=password \
  spring-basics:local
```

Note: On Linux, replace `host.docker.internal` with your host IP (e.g., `172.17.0.1`) or attach the app container to the same Docker network as the DB and use the service name `db` as the host.

## Project layout
- `src/main/java/com/amigoscode/` — application, services, controllers, entities
- `src/main/java/com/amigoscode/book/` — book domain
- `src/main/java/com/amigoscode/peope/` — people domain
- `src/main/resources/application.properties` — datasource and JPA config
- `docker-compose.yml` — Postgres database service
- `requests.http` — handy HTTP requests for quick testing

## License
This project is for educational/demo purposes.