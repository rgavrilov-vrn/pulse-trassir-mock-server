package ru.agroeco.pulse.trassirmock.session;

import java.time.Instant;
import java.util.Optional;

public interface SessionRepository {
    void save(Session session);

    Optional<Session> findById(SessionId id);

    void deleteExpired(Instant now);
}
