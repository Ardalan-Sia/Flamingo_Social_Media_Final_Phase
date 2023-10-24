package db;


import model.Chat;
import model.Message;
import model.Tweet;
import model.User;

public class Context {
    public DBSet<User> Users = new UserDB();
    public DBSet<Tweet> Tweets = new TweetDB();
    public DBSet<Chat> Chats = new ChatDB();
    public DBSet<Message> Messages = new MessageDB();
    public DBSet<Group> Groups = new GroupDB();

}
