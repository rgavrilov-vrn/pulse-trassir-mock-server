package ru.agroeco.pulse.trassirmock.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.agroeco.pulse.trassirmock.application.TrassirMockFacade;

import java.util.List;

@RestController
public class TrassirMockController {
    private final TrassirMockFacade facade;

    public TrassirMockController(TrassirMockFacade facade) {
        this.facade = facade;
    }

    @GetMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestParam(required = false) String password) {
        return ResponseEntity.ok(facade.login(password).value());
    }

    @GetMapping(value = "/objects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ObjectResponse> objects(@RequestParam(required = false) String sid) {
        return facade.objects(sid).stream()
            .map(ObjectResponse::from)
            .toList();
    }

    @GetMapping(value = "/events/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventResponse> events(@RequestParam(required = false) String sid) {
        return facade.events(sid).stream()
            .map(EventResponse::from)
            .toList();
    }
}
