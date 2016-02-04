package event.parsing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by bharel on 2/4/2016.
 */
@RestController
public class eventStatsRestControllerImpl implements eventStatsRestController {

    private static final String ALL_EVENTS = "";

    @RequestMapping("/eventCount")
    public Map<String, Long> getEventCount(@RequestParam(value = "name", defaultValue = ALL_EVENTS) String name) {
        return Collections.singletonMap("Test", 123L);
    }

    @RequestMapping("/wordCount")
    public Map<String, Long> getWordCount(@RequestParam(value = "name", defaultValue = ALL_EVENTS) String name) {
        return null;
    }
}
