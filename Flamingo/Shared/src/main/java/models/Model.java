package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Model {
//    protected LocalDateTime createAt;
    protected int id;


    public String timeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        return formatter.format(dateTime);
    }
}
