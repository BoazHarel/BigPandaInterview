package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventDigesterImplTest {

    EventDigesterImpl eventDigester;

    @Before
    public void setup(){
        eventDigester = new EventDigesterImpl();
    }

    @Test
    public void testEventStats(){
        Event a = new Event("typeA", "word1", 123);
        Event b = new Event("typeB", "word1", 123);
        Event anotherB = new Event("typeB", "word2", 123);

        List<Event> events = Arrays.asList(a, b, anotherB);

        for (Event event : events) {
            eventDigester.digest(event);
        }

        EventStats aStats = eventDigester.getEventStats("typeA");
        assertEquals(1, aStats.getEventCount());
        assertEquals(1, aStats.getWordCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEventTypeMissingThrows(){
        eventDigester.getEventStats("No such event");
    }

}
