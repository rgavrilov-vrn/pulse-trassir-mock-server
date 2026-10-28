package ru.agroeco.pulse.trassirmock.session;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemorySessionRepository implements SessionRepository {
    private final Map<SessionId, Session> sessions = new ConcurrentHashMap<>();

    @Override
    public void save(Session session) {
        sessions.put(session.id(), session);
    }

    @Override
    public Optional<Session> findById(SessionId id) {
        return Optional.ofNullable(sessions.get(id));
    }

    @Override
    public void deleteExpired(Instant now) {
        sessions.entrySet().removeIf(entry -> !entry.getValue().isActive(now));
    }
}
