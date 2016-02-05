package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by bharel on 2/5/2016.
 */
@Service
public class EventConsumerImpl implements EventConsumer {

    private static Logger logger = Logger.getLogger(EventConsumerImpl.class);

    @Autowired
    private EventProducer eventProducer;
    private SynchronousQueue<Event> events;
    private ConcurrentSkipListMap<String, EventStats> eventTypeToStats;

    @PostConstruct
    private void setup() {
        events = eventProducer.getEvents();
        eventTypeToStats = new ConcurrentSkipListMap<>();
        consume();
    }

    @Override
    public Map<String, EventStats> getEventTypeToStatsMap() {
        return eventTypeToStats;
    }

    private void consume() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Event event = events.take();
                    consume(event);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
        }, EventConsumerImpl.class.getSimpleName());
        thread.start();
    }

    private void consume(Event event) {
        String eventType = event.getType();
        String eventData = event.getData();

        if (!eventTypeToStats.containsKey(eventType)) {
            addNewEventType(eventType, eventData);
        }

        updateEventType(eventType, eventData);
    }

    private void updateEventType(String eventType, String eventData) {
        EventStats eventStats = eventTypeToStats.get(eventType);
        eventStats.addEventData(eventData);
        logger.trace(MessageFormat.format("existing eventType: ''{0}'', eventStats: ''{1}''", eventType, eventStats));
    }

    private void addNewEventType(String eventType, String eventData) {
        EventStats eventStats = new EventStats(eventData);
        logger.info(MessageFormat.format("new eventType: ''{0}'', eventStats: ''{1}''", eventType, eventStats));
        eventTypeToStats.put(eventType, eventStats);
    }
}
