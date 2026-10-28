package ru.pulse.trassirmock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MockEvent(
    long timestamp,
    String type,
    String origin,
    String username,
    @JsonProperty("ip_address") String ipAddress
) {}
