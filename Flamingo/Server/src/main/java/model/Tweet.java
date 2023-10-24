package model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Tweet extends Model {
    private String body = "";
    private int ownerId;
    private String photoPath = "";
    private LinkedList<Comment> comments = new LinkedList<>();
    private LinkedList<Integer> likes = new LinkedList<>();
    private String time;
    private int reportedCount;
    private boolean notAllowed;

    public Tweet() {

    }

    public Tweet(int ownerId) {
        this.ownerId = ownerId;

    }

    public Tweet(String body, int ownerId) {
        this.body = body;
        this.ownerId = ownerId;
        time = timeToString(LocalDateTime.now());

    }


    public Tweet(int ownerId, String photoPath) {
        this.photoPath = photoPath;
        this.ownerId = ownerId;
        time = timeToString(LocalDateTime.now());

    }

    public void report() {
        reportedCount++;
        if (reportedCount > 3)
            notAllowed = true;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LinkedList<Comment> getComments() {
        return comments;
    }

    public void setComments(LinkedList<Comment> comments) {
        this.comments = comments;
    }

    public LinkedList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(LinkedList<Integer> likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isNotAllowed() {
        return notAllowed;
    }

    public void setNotAllowed(boolean notAllowed) {
        this.notAllowed = notAllowed;
    }

    public void addComment(Comment comment){
        if (comments.isEmpty())
            comment.setId(0);
        else comment.setId(comments.getLast().getId()+1);

        comments.add(comment);
    }
}
