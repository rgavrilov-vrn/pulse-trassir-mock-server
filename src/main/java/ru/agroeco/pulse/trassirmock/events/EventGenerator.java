package ru.agroeco.pulse.trassirmock.events;

import org.springframework.stereotype.Service;
import ru.agroeco.pulse.trassirmock.catalog.ObjectCatalog;
import ru.agroeco.pulse.trassirmock.catalog.TrassirObject;
import ru.agroeco.pulse.trassirmock.config.MockProperties;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
public class EventGenerator {

    private static final String SERVER_GUID = "tkcbkSr1";
    private static final String DEFAULT_CAMERA_GUID = "QhSW4hj2";

    private final EventStore eventStore;
    private final MockProperties properties;
    private final ObjectCatalog objectCatalog;
    private final RandomPicker randomPicker;
    private final Clock clock;

    public EventGenerator(
        EventStore eventStore,
        MockProperties properties,
        ObjectCatalog objectCatalog,
        RandomPicker randomPicker,
        Clock clock
    ) {
        this.eventStore = eventStore;
        this.properties = properties;
        this.objectCatalog = objectCatalog;
        this.randomPicker = randomPicker;
        this.clock = clock;
    }

    public TrassirEvent generate() {
        if (!properties.isGenerateEvents()) {
            return null;
        }

        Instant now = clock.instant();
        MockProperties.MockEventType type = randomPicker.pick(properties.getEventTypes());

        TrassirEvent event = switch (type) {
            case CONNECTION_LOST -> TrassirEvent.connectionLost(now, randomDeviceGuid());
            case CONNECTION_ESTABLISHED -> TrassirEvent.connectionEstablished(now, randomDeviceGuid());
            case LOGIN_SUCCESS -> TrassirEvent.loginSuccess(
                now,
                SERVER_GUID,
                randomPicker.pick(properties.getLoginUsers()),
                randomPicker.pick(properties.getLoginIps())
            );
            case LOGIN_FAILED -> TrassirEvent.loginFailed(
                now,
                SERVER_GUID,
                randomPicker.pick(properties.getLoginUsers()),
                randomPicker.pick(properties.getLoginIps())
            );
        };

        eventStore.append(event);
        return event;
    }

    private String randomDeviceGuid() {
        List<TrassirObject> devices = objectCatalog.findAll().stream()
            .filter(object -> "IP Device".equals(object.className())
                || "Channel".equals(object.className()))
            .toList();

        return devices.isEmpty()
            ? DEFAULT_CAMERA_GUID
            : randomPicker.pick(devices).guid();
    }
}
