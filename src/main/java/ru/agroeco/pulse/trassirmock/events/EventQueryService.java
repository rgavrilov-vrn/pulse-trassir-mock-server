package ru.agroeco.pulse.trassirmock.events;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventQueryService {
    private final EventStore eventStore;

    public EventQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<TrassirEvent> poll(int limit) {
        return eventStore.poll(limit);
    }
}
