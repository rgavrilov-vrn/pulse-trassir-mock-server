package ru.agroeco.pulse.trassirmock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.agroeco.pulse.trassirmock.config.MockProperties;
import ru.agroeco.pulse.trassirmock.model.MockEvent;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockEventGenerator {
    private static final String CAMERA_GUID = "QhSW4hj2";
    private static final String SERVER_GUID = "tkcbkSr1";

    private final MockTrassirState state;
    private final MockProperties properties;

    @Scheduled(fixedDelayString = "#{@mockProperties.eventGenerationInterval.toMillis()}")
    public void generateEvent() {
        if (!properties.isGenerateEvents()) return;

        int variant = ThreadLocalRandom.current().nextInt(0, 10);
        MockEvent event = switch (variant) {
            case 0 -> loginEvent("Login Successful, %1 from %2", "admin", "10.50.5.174");
            case 1 -> loginEvent("Login Successful, %1 from %2", "КРО", "10.10.11.185");
            case 2 -> loginEvent("Login Failed, %1 from %2", "Administrator", "10.10.13.144");
            default -> cameraEvent(variant % 2 == 0 ? "Connection Lost" : "Connection Established");
        };

        state.addEvent(event);
    }

    private MockEvent cameraEvent(String type) {
        return new MockEvent(Instant.now().toEpochMilli() * 1000, type, CAMERA_GUID, null, null);
    }

    private MockEvent loginEvent(String type, String username, String ip) {
        return new MockEvent(Instant.now().toEpochMilli() * 1000, type, SERVER_GUID, username, ip);
    }
}
