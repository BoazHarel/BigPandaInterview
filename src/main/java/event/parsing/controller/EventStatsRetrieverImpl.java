package event.parsing.controller;

import event.parsing.backend.EventConsumer;
import event.parsing.model.EventStats;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
@Service
public class EventStatsRetrieverImpl implements EventStatsRetriever {
    private static Logger logger = Logger.getLogger(EventStatsRetrieverImpl.class);

    @Autowired
    private EventConsumer eventConsumer;

    @Override
    public Map.Entry<String, Long> getEventCount(String eventType) {
        EventStats eventStats = getEventStats(eventType);
        long eventCount = eventStats.getEventCount();
        logger.trace(MessageFormat.format("Event type: ''{0}'', event count: {1}", eventType, eventCount));
        return new AbstractMap.SimpleEntry<>(eventType, eventCount);
    }

    private EventStats getEventStats(String eventType) {
        return eventConsumer.getEventTypeToStatsMap().get(eventType);
    }

    @Override
    public Map.Entry<String, Long> getWordCount(String eventType) {
        EventStats eventStats = getEventStats(eventType);
        long dataCount = eventStats.getDataCount();
        logger.trace(MessageFormat.format("Event type: ''{0}'', word count: {1}", eventType, dataCount));
        return new AbstractMap.SimpleEntry<>(eventType, dataCount);
    }

    @Override
    public Set<String> getEventTypes() {
        Set<String> eventTypes = eventConsumer.getEventTypeToStatsMap().keySet();
        logger.trace(MessageFormat.format("Event types: ''{0}''", eventTypes));
        return eventTypes;
    }
}
