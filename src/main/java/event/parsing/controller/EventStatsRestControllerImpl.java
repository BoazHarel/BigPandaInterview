package event.parsing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
@RestController
public class EventStatsRestControllerImpl implements EventStatsRestController {

    @Autowired
    private EventStatsRetriever eventStatsRetriever;

    @Override
    @RequestMapping("/events")
    public Set<String> getEventTypes(){
        return eventStatsRetriever.getEventTypes();
    }

    @Override
    @RequestMapping("/events/{eventType}/eventCount")
    public Map<String, Long> getEventCount(@PathVariable("eventType") String eventType) {
        return eventStatsRetriever.getEventCount(eventType);
    }

    @Override
    @RequestMapping("/events/{eventType}/wordCount")
    public Map<String, Long> getWordCount(@PathVariable("eventType") String eventType) {
        return eventStatsRetriever.getWordCount(eventType);
    }
}
