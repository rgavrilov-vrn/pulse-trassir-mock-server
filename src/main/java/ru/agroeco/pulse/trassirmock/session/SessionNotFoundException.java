package ru.agroeco.pulse.trassirmock.session;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(SessionId id) {
        super("Session not found or expired: " + id.value());
    }
}
