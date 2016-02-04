package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventDigesterImpl implements EventDigester {

    ConcurrentNavigableMap<String, EventStats> eventToStats = new ConcurrentSkipListMap<>();

    public void digest(Event event) {
        String eventType = event.getType();

        if (!eventToStats.containsKey(eventType)) {
            eventToStats.put(eventType, new EventStats(event));
            return;
        }

        eventToStats.get(eventType).digest(event);
    }

    public EventStats getEventStats(String eventType) {
        EventStats result = eventToStats.get(eventType);
        if (result == null) {
            throw new IllegalArgumentException(MessageFormat.format("eventType: '{0}' does not exist", eventType));
        }

        return result;
    }
}
