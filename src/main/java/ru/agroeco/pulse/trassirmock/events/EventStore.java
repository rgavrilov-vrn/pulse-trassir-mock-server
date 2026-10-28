package ru.agroeco.pulse.trassirmock.events;

import java.util.List;

public interface EventStore {
    void append(TrassirEvent event);

    List<TrassirEvent> poll(int limit);

    int size();
}
