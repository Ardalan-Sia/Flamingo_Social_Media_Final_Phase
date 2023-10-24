package models;

import java.util.LinkedList;

public class CommentDemo {

    private final LinkedList<Integer> likes;
    private final int writerId;
    private final int commentId;
    private final int parentId;
    private final String time;
    private final String content;
    private final String encodedProfilePhoto;
    private final String writerUsername;
    private final boolean isLikedByOwner;

    public CommentDemo(LinkedList<Integer> likes, int writerId, int commentId, int parentId, String time, String content, String encodedProfilePhoto, String writerUsername, boolean isLikedByOwner) {
        this.likes = likes;
        this.writerId = writerId;
        this.commentId = commentId;
        this.parentId = parentId;
        this.time = time;
        this.content = content;
        this.encodedProfilePhoto = encodedProfilePhoto;
        this.writerUsername = writerUsername;
        this.isLikedByOwner = isLikedByOwner;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public int getWriterId() {
        return writerId;
    }

    public LinkedList<Integer> getLikes() {
        return likes;
    }

    public String getEncodedProfilePhoto() {
        return encodedProfilePhoto;
    }

    public boolean isLikedByOwner() {
        return isLikedByOwner;
    }

    public String getWriterUsername() {
        return writerUsername;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getParentId() {
        return parentId;
    }
}
