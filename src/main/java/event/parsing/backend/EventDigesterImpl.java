package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventDigesterImpl<T> implements EventDigester<T> {

    private ConcurrentNavigableMap<String, EventStats<T>> eventToStats = new ConcurrentSkipListMap<>();

    public void digest(Event<T> event) {
        String eventType = event.getType();

        if (!eventToStats.containsKey(eventType)) {
            EventStats<T> eventStats = new EventStats<>();
            eventStats.addEventData(event.getData());
            eventToStats.put(eventType, eventStats);
            return;
        }

        eventToStats.get(eventType).addEventData(event.getData());
    }

    public EventStats<T> getEventStats(String eventType) {
        EventStats<T> result = eventToStats.get(eventType);
        if (result == null) {
            throw new IllegalArgumentException(MessageFormat.format("eventType: '{0}' does not exist", eventType));
        }

        return result;
    }
}
