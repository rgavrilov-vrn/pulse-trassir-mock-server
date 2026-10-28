package ru.agroeco.pulse.trassirmock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "trassir.mock")
public class MockProperties {
    private String sdkPassword = "mock-sdk-password";
    private Duration sessionTtl = Duration.ofMinutes(30);
    private int maxEventsPerResponse = 100;
    private boolean generateEvents = true;
    private Duration eventGenerationInterval = Duration.ofSeconds(1);
    private Duration eventGenerationJitter = Duration.ofSeconds(5);
    private List<MockEventType> eventTypes = List.of(
        MockEventType.CONNECTION_LOST, MockEventType.CONNECTION_ESTABLISHED,
        MockEventType.LOGIN_SUCCESS, MockEventType.LOGIN_FAILED
    );
    private List<String> loginUsers = List.of("admin", "КРО", "Administrator");
    private List<String> loginIps = List.of("10.50.5.174", "10.10.11.185", "10.10.13.144");

    public enum MockEventType {
        CONNECTION_LOST, CONNECTION_ESTABLISHED, LOGIN_SUCCESS, LOGIN_FAILED
    }
}
