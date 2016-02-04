package event.parsing.model;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventStats {
    private long eventCount;
    private ConcurrentSkipListSet<String> words;

    public EventStats() {
        words = new ConcurrentSkipListSet<>();
    }

    public void addEventData(String eventData) {
        eventCount++;
        words.add(eventData);
    }

    public long getEventCount() {
        return eventCount;
    }

    public long getWordCount() {
        return words.size();
    }
}
