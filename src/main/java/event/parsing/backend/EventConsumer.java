package event.parsing.backend;

import event.parsing.model.EventStats;

import java.util.Map;

/**
 * Created by bharel on 2/5/2016.
 */
public interface EventConsumer {
    Map<String, EventStats> getEventTypeToStatsMap();
}
