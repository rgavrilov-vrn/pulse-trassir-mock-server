package ru.agroeco.pulse.trassirmock.session;

public record SessionId(String value) {
    public SessionId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Session id must not be blank");
    }

    public static SessionId newId() {
        return new SessionId(java.util.UUID.randomUUID().toString());
    }
}
