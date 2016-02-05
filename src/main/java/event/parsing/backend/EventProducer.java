package event.parsing.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import event.parsing.model.Event;
import event.parsing.model.SimpleEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bharel on 2/5/2016.
 */
@Service
public class EventProducer {

    private BufferedReader bufferedReader;
    private SynchronousQueue<Event> events;

    @PostConstruct
    private void setup(){
        events = new SynchronousQueue<>();
    }

    public void setStream(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void startProducing(){
        while (true){
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    continue;
                }

                addEvent(line);
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public SynchronousQueue<Event> getEvents(){
        return events;
    }

    private void addEvent(String line)
                throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleEvent simpleEvent = objectMapper.readValue(line, SimpleEvent.class);
        events.put(simpleEvent);
    }

}
