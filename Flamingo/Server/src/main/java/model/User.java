package model;

import models.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class User implements Commons {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private String bio;
    private boolean isPrivate;
    private LastSeenType lastSeenType;
    private String lastSeen = "";
    private String profilePhotoPath;
    private UserStatues statues;
    private LinkedHashSet<Integer> contacts = new LinkedHashSet<>();
    private LinkedHashSet<Integer> followings= new LinkedHashSet<>();
    private LinkedHashSet<Integer> followers= new LinkedHashSet<>();
    private LinkedHashSet<Integer> blackList= new LinkedHashSet<>();
    private LinkedHashSet<Integer> muteList= new LinkedHashSet<>();
    private LinkedHashSet<Integer> receivedRequests = new LinkedHashSet<>();
    private LinkedHashMap<Integer, SentRequestStatue> sentRequests = new LinkedHashMap<>();
    private LinkedHashSet<SystemNotification> SystemNotification = new LinkedHashSet<>();
    private LinkedList<Integer> groups = new LinkedList<>();
    private LinkedHashSet<Integer> chats = new LinkedHashSet<>();


    public User() {

    }

    public User(String username, String password, String name, String email, String phoneNumber, Date birthday, String bio) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.bio = bio;
        this.profilePhotoPath = Commons.NO_IMAGE_ICON.getDescription();
        this.lastSeenType = LastSeenType.EVERYONE;

    }



    public LinkedHashSet<Integer> getBlackList() {
        return blackList;
    }

    public void setBlackList(LinkedHashSet<Integer> blackList) {
        this.blackList = blackList;
    }

    public LinkedHashSet<Integer> getMuteList() {
        return muteList;
    }

    public void setMuteList(LinkedHashSet<Integer> muteList) {
        this.muteList = muteList;
    }

    public LinkedHashSet<Integer> getReceivedRequests() {
        return receivedRequests;
    }

    public void setReceivedRequests(LinkedHashSet<Integer> receivedRequests) {
        this.receivedRequests = receivedRequests;
    }

    public LinkedHashMap<Integer, SentRequestStatue> getSentRequests() {
        return sentRequests;
    }

    public void setSentRequests(LinkedHashMap<Integer, SentRequestStatue> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public LinkedHashSet<SystemNotification> getSystemNotification() {
        return SystemNotification;
    }

    public void setSystemNotification(LinkedHashSet<SystemNotification> systemNotification) {
        SystemNotification = systemNotification;
    }

    public LinkedList<Integer> getGroups() {
        return groups;
    }

    public void setGroups(LinkedList<Integer> groups) {
        this.groups = groups;
    }

    public LastSeenType getLastSeenType() {
        return lastSeenType;
    }

    public void setLastSeenType(LastSeenType lastSeenType) {
        this.lastSeenType = lastSeenType;
    }

    public LinkedList<Integer> getMyTweets() {
        return myTweets;
    }

    public void setMyTweets(LinkedList<Integer> myTweets) {
        this.myTweets = myTweets;
    }

    private LinkedList<Integer> myTweets = new LinkedList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getLastSeen() {
        if (statues.equals(UserStatues.ONLINE))
            return UserStatues.ONLINE.toString();
        else
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public LinkedHashSet<Integer> getContacts() {
        return contacts;
    }

    public void setContacts(LinkedHashSet<Integer> contacts) {
        this.contacts = contacts;
    }

    public LinkedHashSet<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedHashSet<Integer> followings) {
        this.followings = followings;
    }

    public LinkedHashSet<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedHashSet<Integer> followers) {
        this.followers = followers;
    }

    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }

    public UserStatues getStatues() {
        return statues;
    }

    public void setStatues(UserStatues statues) {
        this.statues = statues;
    }

    public LinkedHashSet<Integer> getChats() {
        return chats;
    }

    public void setChats(LinkedHashSet<Integer> chats) {
        this.chats = chats;
    }

    public void addContact(int id){
        contacts.add(id);


    }
}
