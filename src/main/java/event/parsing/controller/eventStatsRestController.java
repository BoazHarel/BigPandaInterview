package event.parsing.controller;

import java.util.Map;

/**
 * Created by bharel on 2/4/2016.
 */
public interface eventStatsRestController {
    String ALL_EVENTS = "";

    Map<String, Long> getEventCount(String name);

    Map<String, Long> getWordCount(String name);
}
