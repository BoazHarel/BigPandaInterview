package event.parsing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharel on 2/4/2016.
 */
public class Event<T> {
    @JsonProperty("event_type")
    private String type;
    @JsonProperty("data")
    private T data;
    @JsonProperty("timestamp")
    private long timeStamp;

    public Event(String type, T data, long timeStamp) {
        this.type = type;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
