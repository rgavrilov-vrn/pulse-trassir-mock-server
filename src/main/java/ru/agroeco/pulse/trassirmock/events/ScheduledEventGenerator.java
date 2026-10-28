package ru.agroeco.pulse.trassirmock.events;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.agroeco.pulse.trassirmock.config.MockProperties;

@Component
public class ScheduledEventGenerator {
    private final EventGenerator generator;
    private final MockProperties properties;

    public ScheduledEventGenerator(EventGenerator generator, MockProperties properties) {
        this.generator = generator;
        this.properties = properties;
    }

    @Scheduled(fixedDelayString = "#{@mockProperties.eventGenerationInterval.toMillis()}")
    public void generate() {
        generator.generate();
    }
}
