package models;

import java.util.LinkedList;

public class TweetDemo {

private final LinkedList<Integer> likes;
private final int writerId;
private final int tweetId;
private final String time;
private final String content;
private final String encodedImage;
private final String encodedProfilePhoto;
private final String writerUsername;
private final boolean isMyTweet;
private final boolean isLikedByOwner;

    public TweetDemo(LinkedList<Integer> likes, int writerId, int tweetId, String time, String content, String encodedImage, String encodedProfilePhoto, String writerUsername, boolean isMyTweet, boolean isLikedByOwner) {
        this.likes = likes;
        this.writerId = writerId;
        this.tweetId = tweetId;
        this.time = time;
        this.content = content;
        this.encodedImage = encodedImage;
        this.encodedProfilePhoto = encodedProfilePhoto;
        this.writerUsername = writerUsername;
        this.isMyTweet = isMyTweet;
        this.isLikedByOwner = isLikedByOwner;
    }

    public String getEncodedImage() {
        return encodedImage;
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

    public boolean isMyTweet() {
        return isMyTweet;
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

    public int getTweetId() {
        return tweetId;
    }
}
