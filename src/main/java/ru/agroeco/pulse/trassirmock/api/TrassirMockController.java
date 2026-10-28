package ru.agroeco.pulse.trassirmock.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.agroeco.pulse.trassirmock.application.TrassirMockFacade;

import java.util.List;

@Tag(name = "TRASSIR Mock API", description = "Mock TRASSIR SDK endpoints")
@RestController
@RequestMapping("/api/v1/trassirmock")
public class TrassirMockController {
    private final TrassirMockFacade facade;

    public TrassirMockController(TrassirMockFacade facade) {
        this.facade = facade;
    }

    @Operation(summary = "Create a mock TRASSIR session")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Session SID"),
        @ApiResponse(responseCode = "401", description = "Invalid password")
    })
    @GetMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(
        @Parameter(description = "TRASSIR SDK password")
        @RequestParam(required = false) String password
    ) {
        return ResponseEntity.ok(facade.login(password).value());
    }

    @Operation(summary = "Get the mock TRASSIR object tree")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "TRASSIR objects"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired SID")
    })
    @GetMapping(value = "/objects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ObjectResponse> objects(
        @Parameter(description = "Active session SID")
        @RequestParam(required = false) String sid
    ) {
        return facade.objects(sid).stream()
            .map(ObjectResponse::from)
            .toList();
    }

    @Operation(summary = "Poll new mock TRASSIR events")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "New events; repeated calls do not return the same events"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired SID")
    })
    @GetMapping(value = "/events/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventResponse> events(
        @Parameter(description = "Active session SID")
        @RequestParam(required = false) String sid
    ) {
        return facade.events(sid).stream()
            .map(EventResponse::from)
            .toList();
    }
}
