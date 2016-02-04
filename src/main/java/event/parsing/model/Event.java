package event.parsing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharel on 2/4/2016.
 */
public class Event {



    @JsonProperty("event_type")
    private String type;
    @JsonProperty("data")
    private String data;
    @JsonProperty("timestamp")
    private long timeStamp;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
