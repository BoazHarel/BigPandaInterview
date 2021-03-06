package event.parsing.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import event.parsing.model.Event;
import event.parsing.model.SimpleEvent;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bharel on 2/5/2016.
 */
@Service
public class EventProducerImpl implements EventProducer {
    private static Logger logger = Logger.getLogger(EventProducerImpl.class);

    private BufferedReader bufferedReader;
    private SynchronousQueue<Event> events;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void setup(){
        events = new SynchronousQueue<>();
    }

    @Override
    public void setStream(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void startProducing(){
        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                        continue;
                    }

                    addEvent(line);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }, EventProducerImpl.class.getSimpleName());

        thread.start();
    }

    @Override
    public SynchronousQueue<Event> getEvents(){
        return events;
    }

    private void addEvent(String line)
                throws InterruptedException {
        try {
            SimpleEvent simpleEvent = objectMapper.readValue(line, SimpleEvent.class);
            logger.info("Added event:" + simpleEvent);
            events.put(simpleEvent);
        } catch (IOException e) {
            logger.warn(MessageFormat.format("failed to read event: ''{0}''", line));
        }
    }
}
