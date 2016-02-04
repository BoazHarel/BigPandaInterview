package event.parsing.controller;

import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
public interface EventStatsRetriever {

    Map<String,Long> getEventCount(String eventType);

    Map<String,Long> getWordCount(String eventType);

    Set<String> getEventTypes();
}
