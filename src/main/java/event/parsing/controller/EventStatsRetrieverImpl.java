package event.parsing.controller;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
@Service
public class EventStatsRetrieverImpl implements EventStatsRetriever {

    public Map<String, Long> getEventCount(String eventType) {
        return null;
    }

    public Map<String, Long> getWordCount(String eventType) {
        return null;
    }

    public Set<String> getEventTypes() {
        return null;
    }
}
