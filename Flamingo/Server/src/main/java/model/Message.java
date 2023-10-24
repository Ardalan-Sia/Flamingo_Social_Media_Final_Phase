package model;

import java.time.LocalDateTime;

public class Message extends Model{
    private String photoPath = "";
    private String content;
    private String time;
    private int ownerId;
    private int parentId;

    public Message(){

    }
    public Message(String content , int ownerId, int parentId) {
        this.parentId = parentId;
//        this.photoPath = photoPath;
        this.ownerId = ownerId;
        this.content = content;
        time = timeToString(LocalDateTime.now());
//        new Context().Messages.add(this);
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getContent() {
        return content;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
