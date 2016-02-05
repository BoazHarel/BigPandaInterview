package event.parsing.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Set;

/**
 * Created by bharel on 2/4/2016.
 */
public interface EventStatsRestController {
    Set<String> getEventTypes();

    Map.Entry<String, Long> getEventCount(String eventType);

    Map.Entry<String, Long> getWordCount(String eventType);
}
