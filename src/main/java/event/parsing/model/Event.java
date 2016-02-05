package event.parsing.model;

/**
 * Created by bharel on 2/5/2016.
 */
public interface Event {
    String getType();

    void setType(String type);

    String getData();

    void setData(String data);

    long getTimeStamp();

    void setTimeStamp(long timeStamp);
}
