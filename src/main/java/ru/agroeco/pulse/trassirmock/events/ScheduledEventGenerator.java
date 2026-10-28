package ru.agroeco.pulse.trassirmock.events;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEventGenerator {
    private final EventGenerator generator;
    private final MockPropertiesHolder propertiesHolder;

    public ScheduledEventGenerator(EventGenerator generator, MockPropertiesHolder propertiesHolder) {
        this.generator = generator;
        this.propertiesHolder = propertiesHolder;
    }

    @Scheduled(fixedDelayString = "#{@mockProperties.eventGenerationInterval.toMillis()}")
    public void generate() {
        generator.generate();
    }
}
