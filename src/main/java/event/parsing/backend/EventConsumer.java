package event.parsing.backend;

import event.parsing.model.Event;
import event.parsing.model.EventStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bharel on 2/5/2016.
 */
@Service
public class EventConsumer {

    @Autowired
    private EventProducer eventProducer;
    private SynchronousQueue<Event> events;
    private ConcurrentSkipListMap<String, EventStats> eventTypeToStats;

    @PostConstruct
    private void setup(){
        events = eventProducer.getEvents();
        eventTypeToStats = new ConcurrentSkipListMap<>();
        consume();
    }

    public Map<String, EventStats> getEventTypeToStatsMap() {
        return eventTypeToStats;
    }

    private void consume(){
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    Event event = events.poll();
                    if(event == null){
                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                        continue;
                    }

                    consume(event);
                } catch (InterruptedException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }, EventConsumer.class.getSimpleName());
        thread.start();
    }

    private void consume(Event event) {
        String eventType = event.getType();
        String eventData = event.getData();

        if(!eventTypeToStats.containsKey(eventType)){
            eventTypeToStats.put(eventType, new EventStats(eventData));
        }

        eventTypeToStats.get(eventType).addEventData(eventData);
    }
}
