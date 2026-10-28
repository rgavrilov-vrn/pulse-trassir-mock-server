package ru.agroeco.pulse.trassirmock.events;

import org.springframework.stereotype.Component;

@Component
public class MockPropertiesHolder {
    private final ru.agroeco.pulse.trassirmock.config.MockProperties properties;

    public MockPropertiesHolder(ru.agroeco.pulse.trassirmock.config.MockProperties properties) {
        this.properties = properties;
    }

    public ru.agroeco.pulse.trassirmock.config.MockProperties get() {
        return properties;
    }
}
