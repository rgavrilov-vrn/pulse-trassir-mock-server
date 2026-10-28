package ru.agroeco.pulse.trassirmock.events;

import ru.agroeco.pulse.trassirmock.config.MockProperties;

import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class EventGenerator {
    private static final String CAMERA_GUID = "QhSW4hj2";
    private static final String SERVER_GUID = "tkcbkSr1";

    private final EventStore eventStore;
    private final MockProperties properties;
    private final Clock clock;

    public EventGenerator(EventStore eventStore, MockProperties properties, Clock clock) {
        this.eventStore = eventStore;
        this.properties = properties;
        this.clock = clock;
    }

    public void generate() {
        if (!properties.isGenerateEvents()) {
            return;
        }

        int variant = ThreadLocalRandom.current().nextInt(0, 10);
        Instant now = clock.instant();
        TrassirEvent event = switch (variant) {
            case 0 -> TrassirEvent.loginSuccess(now, SERVER_GUID, "admin", "10.50.5.174");
            case 1 -> TrassirEvent.loginSuccess(now, SERVER_GUID, "КРО", "10.10.11.185");
            case 2 -> TrassirEvent.loginFailed(now, SERVER_GUID, "Administrator", "10.10.13.144");
            default -> variant % 2 == 0
                    ? TrassirEvent.connectionLost(now, CAMERA_GUID)
                    : TrassirEvent.connectionEstablished(now, CAMERA_GUID);
        };

        eventStore.append(event);
    }
}
