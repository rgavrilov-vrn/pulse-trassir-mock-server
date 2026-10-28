package ru.agroeco.pulse.trassirmock.events;

import java.time.Instant;

public record TrassirEvent(Instant occurredAt, String type, String origin, String username, String ipAddress) {
    public TrassirEvent {
        if (occurredAt == null) throw new IllegalArgumentException("Event time is required");
        if (type == null || type.isBlank()) throw new IllegalArgumentException("Event type is required");
        if (origin == null || origin.isBlank()) throw new IllegalArgumentException("Event origin is required");
    }

    public static TrassirEvent loginSuccess(Instant occurredAt, String origin, String username, String ipAddress) {
        return new TrassirEvent(occurredAt, "Login Successful, %1 from %2", origin, username, ipAddress);
    }

    public static TrassirEvent loginFailed(Instant occurredAt, String origin, String username, String ipAddress) {
        return new TrassirEvent(occurredAt, "Login Failed, %1 from %2", origin, username, ipAddress);
    }

    public static TrassirEvent connectionLost(Instant occurredAt, String origin) {
        return new TrassirEvent(occurredAt, "Connection Lost", origin, null, null);
    }

    public static TrassirEvent connectionEstablished(Instant occurredAt, String origin) {
        return new TrassirEvent(occurredAt, "Connection Established", origin, null, null);
    }
}
