package event.parsing.model;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventStats {
    private long eventCount;
    private ConcurrentSkipListSet<String> data;

    public EventStats() {
        data = new ConcurrentSkipListSet<>();
    }

    public EventStats(String eventData) {
        this();
        addEventData(eventData);
    }

    public void addEventData(String eventData) {
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
