package ru.agroeco.pulse.trassirmock.events;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import ru.agroeco.pulse.trassirmock.config.MockProperties;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class RandomEventScheduler {
    private final EventGenerator eventGenerator;
    private final MockProperties properties;
    private final RandomPicker randomPicker;
    private final TaskScheduler taskScheduler;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        scheduleNext();
    }

    private void scheduleNext() {
        if (!properties.isGenerateEvents()) {
            return;
        }
        Duration delay = randomPicker.nextDelay(
            properties.getEventGenerationInterval(),
            properties.getEventGenerationJitter()
        );
        taskScheduler.schedule(
            () -> {
                try {
                    eventGenerator.generate();
                } finally {
                    scheduleNext();
                }
            },
            Instant.now().plus(delay)
        );
    }
}
