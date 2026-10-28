package ru.agroeco.pulse.trassirmock.application;

import org.springframework.stereotype.Service;
import ru.agroeco.pulse.trassirmock.catalog.ObjectCatalogService;
import ru.agroeco.pulse.trassirmock.catalog.TrassirObject;
import ru.agroeco.pulse.trassirmock.config.MockProperties;
import ru.agroeco.pulse.trassirmock.events.EventQueryService;
import ru.agroeco.pulse.trassirmock.events.TrassirEvent;
import ru.agroeco.pulse.trassirmock.session.Session;
import ru.agroeco.pulse.trassirmock.session.SessionId;
import ru.agroeco.pulse.trassirmock.session.SessionService;

import java.util.List;

@Service
public class TrassirMockFacade {
    private final SessionService sessionService;
    private final ObjectCatalogService objectCatalogService;
    private final EventQueryService eventQueryService;
    private final MockProperties properties;

    public TrassirMockFacade(
            SessionService sessionService,
            ObjectCatalogService objectCatalogService,
            EventQueryService eventQueryService,
            MockProperties properties
    ) {
        this.sessionService = sessionService;
        this.objectCatalogService = objectCatalogService;
        this.eventQueryService = eventQueryService;
        this.properties = properties;
    }

    public SessionId login(String password) {
        if (!properties.getSdkPassword().equals(password)) {
            throw new InvalidPasswordException();
        }
        return sessionService.create();
    }

    public List<TrassirObject> objects(String sid) {
        requireSession(sid);
        return objectCatalogService.allObjects();
    }

    public List<TrassirEvent> events(String sid) {
        requireSession(sid);
        return eventQueryService.poll(properties.getMaxEventsPerResponse());
    }

    private Session requireSession(String sid) {
        if (sid == null || sid.isBlank()) {
            throw new ru.agroeco.pulse.trassirmock.session.SessionNotFoundException(new SessionId("missing"));
        }
        return sessionService.requireActive(new SessionId(sid));
    }

    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException() {
            super("Invalid password");
        }
    }
}
