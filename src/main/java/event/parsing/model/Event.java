package event.parsing.model;

/**
 * Created by bharel on 2/5/2016.
 */
public interface Event<T> {
    String getType();

    void setType(String type);

    T getData();

    void setData(T data);

    long getTimeStamp();

    void setTimeStamp(long timeStamp);
}
