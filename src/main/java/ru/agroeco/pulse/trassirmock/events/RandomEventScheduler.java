package ru.agroeco.pulse.trassirmock.events;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class RandomEventScheduler {

    private final EventGenerator eventGenerator;
    private final MockProperties properties;
    private final RandomPicker randomPicker;
    private final TaskScheduler taskScheduler;

    public RandomEventScheduler(
        EventGenerator eventGenerator,
        MockProperties properties,
        RandomPicker randomPicker,
        TaskScheduler taskScheduler
    ) {
        this.eventGenerator = eventGenerator;
        this.properties = properties;
        this.randomPicker = randomPicker;
        this.taskScheduler = taskScheduler;
    }

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
