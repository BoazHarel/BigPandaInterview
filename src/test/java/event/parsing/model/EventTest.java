package event.parsing.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;

/**
 * Created by bharel on 2/4/2016.
 */
public class EventTest {

    @Test
    public void testParseEvent()
                throws Exception {
        String type = "baz";
        String data = "amet";
        String timeStamp = "1454616855";
        String json = MessageFormat.format("'{' \"event_type\": \"{0}\", \"data\": \"{1}\", \"timestamp\": {2} '}'"
                    , type
                    , data
                    , timeStamp);
        ObjectMapper objectMapper = new ObjectMapper();

        Event event = objectMapper.readValue(json, SimpleEvent.class);

        assertEquals(type, event.getType());
        assertEquals(data, event.getData());
        assertEquals(timeStamp, String.valueOf(event.getTimeStamp()));
    }
}
