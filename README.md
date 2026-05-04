# EMT Lab Group B

## Prerequisites

Make sure these are installed locally:

- Java 21
- Docker
- Docker Compose

## Run the database with Docker Compose

The project uses PostgreSQL through `docker-compose.yaml`.

Start the database:

```bash
docker compose up -d
```

This starts PostgreSQL with the following settings:

- Host: `localhost`
- Port: `5433`
- Database: `accommodations`
- Username: `emt`
- Password: `emt`

Stop the database:

```bash
docker compose down
```

If you also want to remove the persisted database volume:

```bash
docker compose down -v
```

## Run the Spring Boot application

The application starts with the `dev` profile by default and connects to the PostgreSQL instance from Docker Compose.

Run the app from the project root:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bash
mvnw.cmd spring-boot:run
```

By default, the app runs on:

```text
http://localhost:8080
```

## Open Swagger UI

After the application is running, open Swagger UI in the browser:

```text
http://localhost:8080/swagger-ui/index.html#
```

Swagger UI lets you inspect and test the REST API directly from the browser.

The OpenAPI JSON is available at:

```text
http://localhost:8080/v3/api-docs
```

## Typical workflow

1. Start PostgreSQL with `docker compose up -d`
2. Start the Spring Boot app with `./mvnw spring-boot:run`
3. Open `http://localhost:8080/swagger-ui/index.html#`
4. Test the endpoints from the Swagger UI page

## Notes

- The API controller is currently exposed under `/api/accommodatios` in the code, so that same path will appear in Swagger UI.
- Flyway migrations run automatically on startup.
