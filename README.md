# TRASSIR Mock Server

Endpoints:

- GET /login?password=mock-sdk-password -> SID
- GET /objects/?sid=<SID> -> objects
- GET /events/?sid=<SID> -> new events only

Run:
mvn spring-boot:run
