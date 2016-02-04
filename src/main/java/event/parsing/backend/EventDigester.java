package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;

/**
 * Created by bharel on 2/4/2016.
 */
public interface EventDigester {
    void digest(Event event);

    EventStats getEventStats(String eventType);
}
