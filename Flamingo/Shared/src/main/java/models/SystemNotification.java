package models;

import java.time.LocalDateTime;

public class SystemNotification extends Model {
    private String message;
    private String time;

    public SystemNotification(){

    }
    public SystemNotification(String message) {
        this.message = message;
        this.time = timeToString(LocalDateTime.now());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return message + '\'' +time + '\'';
    }
}
