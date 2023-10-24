package model;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Chat {

    private int id;
    private LinkedList<Integer> messages;
    private LinkedList<Integer> users;
    private LinkedHashMap<Integer,Integer> unreadCounter = new LinkedHashMap<>();


    public Chat() {
    }

    public Chat(int user1 , int user2) {
        messages = new LinkedList<>();
        users = new LinkedList<>();
        unreadCounter.put(user1,0);
        unreadCounter.put(user2,0);
        users.add(user1);
        users.add(user2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Integer> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Integer> messages) {
        this.messages = messages;
    }
    public void addMessage(Message message){
        messages.add(message.getId());
        int target = users.get(0) == message.getOwnerId()?users.get(1):users.get(0);
            unreadCounter.put(target, unreadCounter.get(target)+1);
    }

    public LinkedHashMap<Integer, Integer> getUnreadCounter() {
        return unreadCounter;
    }

    public void setUnreadCounter(LinkedHashMap<Integer, Integer> unreadCounter) {
        this.unreadCounter = unreadCounter;
    }

    public void seen(int user){
        unreadCounter.put(user , 0);
        System.out.println(unreadCounter.get(user));

    }

    public LinkedList<Integer> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<Integer> users) {
        this.users = users;
    }
    public Integer getOther(int user){
        if (!users.contains(user)) {
            return null;
        }

        return users.get(0) == user ? users.get(1) : users.get(0);

    }

//    public LinkedHashMap<User, Integer> getUnreadCounter() {
//        return unreadCounter;
//    }

//    public void setUnreadCounter(LinkedHashMap<User, Integer> unreadCounter) {
//        this.unreadCounter = unreadCounter;
//    }
}
