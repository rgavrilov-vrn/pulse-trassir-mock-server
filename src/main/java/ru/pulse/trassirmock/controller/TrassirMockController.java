package ru.pulse.trassirmock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pulse.trassirmock.config.MockProperties;
import ru.pulse.trassirmock.model.MockEvent;
import ru.pulse.trassirmock.model.MockObject;
import ru.pulse.trassirmock.service.MockTrassirState;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrassirMockController {

    private final MockTrassirState state;
    private final MockProperties properties;

    @GetMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestParam(required = false) String password) {
        if (!properties.getSdkPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }
        return ResponseEntity.ok(state.createSession());
    }

    @GetMapping(value = "/objects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MockObject> objects(@RequestParam(required = false) String sid) {
        requireSession(sid);
        return List.of(
            new MockObject("video81", "tkcbkSr1", "Server", "tkcbkSr1"),
            new MockObject("Каналы", "tkcbkSr1C", "Folder", "tkcbkSr1"),
            new MockObject("IP-устройства", "tkcbkSr1I", "Folder", "tkcbkSr1"),
            new MockObject("TR-D2123ZCL6 15", "QhSW4hj2", "IP Device", "tkcbkSr1I"),
            new MockObject("СК Гаврильский тех.зона обработка рук", "A09M5rCx", "Channel", "tkcbkSr1C"),
            new MockObject("СК Гаврильский сер.зона бункер 9,10", "B4k6o1EE", "Channel", "tkcbkSr1C")
        );
    }

    @GetMapping(value = "/events/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MockEvent> events(@RequestParam(required = false) String sid) {
        requireSession(sid);
        return state.pollEvents(properties.getMaxEventsPerResponse());
    }

    private void requireSession(String sid) {
        if (!state.isValidSession(sid)) throw new InvalidSessionException();
    }

    public static class InvalidSessionException extends RuntimeException {
        public InvalidSessionException() {
            super("Недействительный или отсутствующий SID");
        }
    }
}
