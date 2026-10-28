# TRASSIR Mock Server

Spring Boot mock server for the TRASSIR SDK HTTP API.

## Requirements

- JDK 27
- Maven 3.9+

## Run

```bash
mvn clean verify
mvn spring-boot:run
```

The application uses context path `/pulse-trassirmock` and listens on port `8080` by default.

## Swagger / OpenAPI

- Swagger UI: http://localhost:8080/pulse-trassirmock/swagger-ui.html
- OpenAPI 3.0 JSON: http://localhost:8080/pulse-trassirmock/v3/api-docs
- OpenAPI 3.0 YAML: http://localhost:8080/pulse-trassirmock/v3/api-docs.yaml

## API

Base path: `/pulse-trassirmock/api/v1/trassirmock`

| Method | Path | Description |
|---|---|---|
| `GET` | `/login?password=mock-sdk-password` | Creates a session and returns its SID |
| `GET` | `/objects/?sid=<SID>` | Returns the mock TRASSIR object tree |
| `GET` | `/events/?sid=<SID>` | Returns and consumes new mock events |

## Example

```bash
# 1. Create a session
SID=$(curl -s 'http://localhost:8080/pulse-trassirmock/api/v1/trassirmock/login?password=mock-sdk-password')

# 2. Get objects
curl -s "http://localhost:8080/pulse-trassirmock/api/v1/trassirmock/objects/?sid=$SID"

# 3. Poll events
curl -s "http://localhost:8080/pulse-trassirmock/api/v1/trassirmock/events/?sid=$SID"
```
