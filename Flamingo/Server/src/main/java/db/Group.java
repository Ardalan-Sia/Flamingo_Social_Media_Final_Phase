package db;

import model.Chat;
import model.Message;
import model.User;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Group extends Chat {
    private int id;
    private String groupName;
    private int ownerId;
    private LinkedList<Integer> users = new LinkedList<>();
    private LinkedList<Integer> messages = new LinkedList<>();
    private LinkedHashMap<Integer , Integer> unreadCounter = new LinkedHashMap<>();

    public Group() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public void setMessages(LinkedList<Integer> messages) {
        this.messages = messages;
    }


    @Override
    public LinkedHashMap<Integer, Integer> getUnreadCounter() {
        return unreadCounter;
    }

    @Override
    public void setUnreadCounter(LinkedHashMap<Integer, Integer> unreadCounter) {
        this.unreadCounter = unreadCounter;
    }

    public Group(String groupName , int ownerId) {
        this.ownerId = ownerId;
        users.add(ownerId);
        unreadCounter.put(ownerId,0);
        this.groupName = groupName;
    }

    public void addMember(User user){
        users.add(user.getId());
        unreadCounter.put(user.getId(), messages.size());
        System.out.println(unreadCounter.get(id));

    }

    @Override
    public void addMessage(Message message) {
        messages.add(message.getId());
        for (Integer id:unreadCounter.keySet()) {
            if (id != message.getOwnerId())
                unreadCounter.put(id,unreadCounter.get(id)+1);
        }
    }

    @Override
    public void seen(int user) {
        unreadCounter.put(user,0);
        System.out.println(unreadCounter.get(user)+"sd");
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LinkedList<Integer> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<Integer> users) {
        this.users = users;
    }


    public LinkedList<Integer> getMessages() {
        return messages;
    }
}
