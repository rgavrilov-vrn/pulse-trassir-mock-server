package ru.agroeco.pulse.trassirmock.events;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEventGenerator {
    private final EventGenerator generator;

    public ScheduledEventGenerator(EventGenerator generator) {
        this.generator = generator;
    }

    @Scheduled(fixedDelayString = "${trassir.mock.event-generation-interval}")
    public void generate() {
        generator.generate();
    }
}
