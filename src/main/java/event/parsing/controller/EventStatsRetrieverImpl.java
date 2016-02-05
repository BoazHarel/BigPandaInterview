package event.parsing.controller;

import event.parsing.backend.EventConsumer;
import event.parsing.model.EventStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
@Service
public class EventStatsRetrieverImpl implements EventStatsRetriever {

    @Autowired
    private EventConsumer eventConsumer;

    @Override
    public Map.Entry<String, Long> getEventCount(String eventType) {
        EventStats eventStats = getEventStats(eventType);
        long eventCount = eventStats.getEventCount();
        return new AbstractMap.SimpleEntry<>(eventType, eventCount);
    }

    private EventStats getEventStats(String eventType) {
        return eventConsumer.getEventTypeToStatsMap().get(eventType);
    }

    @Override
    public Map.Entry<String, Long> getWordCount(String eventType) {
        EventStats eventStats = getEventStats(eventType);
        long dataCount = eventStats.getDataCount();
        return new AbstractMap.SimpleEntry<>(eventType, dataCount);
    }

    @Override
    public Set<String> getEventTypes() {
        return eventConsumer.getEventTypeToStatsMap().keySet();
    }
}
