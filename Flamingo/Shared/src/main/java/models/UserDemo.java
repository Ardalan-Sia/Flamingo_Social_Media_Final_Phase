package models;

public class UserDemo {

    private final int id;
    private final String name;
    private final String username;
    private final String encodedImage;
    private final boolean isPrivate;
    private final String lastSeen;
    private final String bio;
    private final boolean alreadyFollowed;
    private final boolean alreadyBlocked;
    private final boolean isMuted;

    public UserDemo(int id, String name, String username, String encodedImage, boolean isPrivate, String lastSeen, String bio, boolean alreadyFollowed, boolean alreadyBlocked, boolean isMuted) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.encodedImage = encodedImage;
        this.isPrivate = isPrivate;
        this.lastSeen = lastSeen;
        this.bio = bio;
        this.alreadyFollowed = alreadyFollowed;
        this.alreadyBlocked = alreadyBlocked;
        this.isMuted = isMuted;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public int getId() {
        return id;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getBio() {
        return bio;
    }

    public boolean isAlreadyFollowed() {
        return alreadyFollowed;
    }

    public boolean isAlreadyBlocked() {
        return alreadyBlocked;
    }

    public boolean isMuted() {
        return isMuted;
    }
}
