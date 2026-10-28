package ru.agroeco.pulse.trassirmock.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventResponse(
    long timestamp,
    String type,
    String origin,
    String username,
    @JsonProperty("ip_address") String ipAddress
) {
    public static EventResponse from(ru.agroeco.pulse.trassirmock.events.TrassirEvent event) {
        return new EventResponse(
            event.occurredAt().toEpochMilli() * 1000,
            event.type(),
            event.origin(),
            event.username(),
            event.ipAddress()
        );
    }
}
