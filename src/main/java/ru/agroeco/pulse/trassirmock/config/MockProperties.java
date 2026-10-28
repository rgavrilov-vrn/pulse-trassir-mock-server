package ru.agroeco.pulse.trassirmock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "trassir.mock")
public class MockProperties {
    private String sdkPassword = "mock-sdk-password";
    private Duration sessionTtl = Duration.ofMinutes(10);
    private int maxEventsPerResponse = 100;
    private boolean generateEvents = true;
    private Duration eventGenerationInterval = Duration.ofSeconds(1);
}
