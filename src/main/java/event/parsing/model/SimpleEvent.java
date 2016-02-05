package event.parsing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharel on 2/4/2016.
 */
public class SimpleEvent<T> implements Event<T> {
    @JsonProperty("event_type")
    private String type;
    @JsonProperty("data")
    private T data;

    public SimpleEvent() {
    }

    public SimpleEvent(String type, T data, long timeStamp) {
        this.type = type;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    @JsonProperty("timestamp")
    private long timeStamp;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
