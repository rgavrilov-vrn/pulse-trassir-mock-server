package ru.agroeco.pulse.trassirmock.session;

import java.time.Instant;

public record Session(SessionId id, Instant createdAt, Instant expiresAt) {
    public Session {
        if (id == null) throw new IllegalArgumentException("Session id is required");
        if (createdAt == null || expiresAt == null) throw new IllegalArgumentException("Session timestamps are required");
        if (expiresAt.isBefore(createdAt)) throw new IllegalArgumentException("Session expiration must be after creation");
    }

    public boolean isActive(Instant now) {
        return now.isBefore(expiresAt);
    }
}
