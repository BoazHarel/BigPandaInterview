package event.parsing.model;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventStats<T> {
    private long eventCount;
    private ConcurrentSkipListSet<T> data;

    public EventStats() {
        data = new ConcurrentSkipListSet<>();
    }

    public void addEventData(T eventData) {
        eventCount++;
        data.add(eventData);
    }

    public long getEventCount() {
        return eventCount;
    }

    public long getDataCount() {
        return data.size();
    }
}
