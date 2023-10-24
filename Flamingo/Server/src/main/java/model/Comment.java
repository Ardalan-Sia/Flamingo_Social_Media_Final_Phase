package model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Comment extends Model {
    private int ownerId;
    private String body;
    private String time;
    private LinkedList<Integer> likes = new LinkedList<>();

    public Comment() {
    }

    public Comment(String body , int ownerId){
    this.body = body;
    this.ownerId = ownerId;
    this.time = timeToString(LocalDateTime.now());
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LinkedList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(LinkedList<Integer> likes) {
        this.likes = likes;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}
