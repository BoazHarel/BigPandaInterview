package event.parsing.backend;

import event.parsing.model.Event;

import java.io.InputStream;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by bharel on 2/5/2016.
 */
public interface EventProducer {
    void setStream(InputStream inputStream);

    void startProducing();

    SynchronousQueue<Event> getEvents();
}
