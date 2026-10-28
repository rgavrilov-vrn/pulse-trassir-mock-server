package ru.agroeco.pulse.trassirmock.events;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Component
public class InMemoryEventStore implements EventStore {
    static final int MAX_EVENTS = 1_000;

    private final Deque<TrassirEvent> events = new ArrayDeque<>();

    @Override
    public synchronized void append(TrassirEvent event) {
        if (events.size() >= MAX_EVENTS) {
            events.pollFirst();
        }
        events.addLast(event);
    }

    @Override
    public synchronized List<TrassirEvent> poll(int limit) {
        if (limit <= 0) {
            return List.of();
        }

        int count = Math.min(limit, events.size());
        List<TrassirEvent> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(events.pollFirst());
        }
        return result;
    }

    @Override
    public synchronized int size() {
        return events.size();
    }
}
