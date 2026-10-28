package ru.agroeco.pulse.trassirmock.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository repository;
    private final Clock clock;

    public SessionId create() {
        Instant now = clock.instant();
        Session session = new Session(SessionId.newId(), now, now.plusSeconds(30 * 60));
        repository.save(session);
        return session.id();
    }

    public Session requireActive(SessionId id) {
        repository.deleteExpired(clock.instant());
        return repository.findById(id)
            .filter(session -> session.isActive(clock.instant()))
            .orElseThrow(() -> new SessionNotFoundException(id));
    }
}
