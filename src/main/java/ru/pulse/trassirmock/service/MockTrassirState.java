package ru.pulse.trassirmock.service;

import org.springframework.stereotype.Component;
import ru.pulse.trassirmock.model.MockEvent;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MockTrassirState {
    private final Map<String, Instant> sessions = new ConcurrentHashMap<>();
    private final Deque<MockEvent> pendingEvents = new ArrayDeque<>();

    public String createSession() {
        String sid = UUID.randomUUID().toString();
        sessions.put(sid, Instant.now());
        return sid;
    }

    public boolean isValidSession(String sid) {
        return sid != null && sessions.containsKey(sid);
    }

    public synchronized List<MockEvent> pollEvents(int limit) {
        int size = Math.min(limit, pendingEvents.size());
        List<MockEvent> events = new ArrayList<>(size);
        for (int i = 0; i < size; i++) events.add(pendingEvents.pollFirst());
        return events;
    }

    public synchronized void addEvent(MockEvent event) {
        pendingEvents.addLast(event);
    }

    public synchronized int pendingEventCount() {
        return pendingEvents.size();
    }
}
