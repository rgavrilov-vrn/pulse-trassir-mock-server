package ru.agroeco.pulse.trassirmock.events;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomPicker {

    public <T> T pick(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Cannot pick from an empty list");
        }
        return values.get(ThreadLocalRandom.current().nextInt(values.size()));
    }

    public Duration nextDelay(Duration base, Duration jitter) {
        Duration safeJitter = jitter == null || jitter.isNegative() ? Duration.ZERO : jitter;
        long min = base.toMillis();
        long max = min + safeJitter.toMillis();
        long millis = ThreadLocalRandom.current().nextLong(min, max + 1);
        return Duration.ofMillis(millis);
    }
}
