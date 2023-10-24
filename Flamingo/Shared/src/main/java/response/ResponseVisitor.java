package response;

import models.*;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public interface ResponseVisitor {

    void signIn(boolean success, String message);

    void signUp(boolean success, String message);

    void privacyInfo(LastSeenType lastSeenType, boolean isPrivate , String password);

    void personalInfo(SignUpForm form);

    void editInfo(boolean success, String message);

    void myTweetsList(LinkedList<TweetDemo> list);

    void tweetComments(LinkedList<CommentDemo> list , int parentId);

    void userInfo(UserDemo userDemo);

    void showMessage(String message);

    void logout();

    void timeLineTweets(LinkedList<TweetDemo> list);

    void explorerTweets(LinkedList<TweetDemo> list);

    void systemNotifications(LinkedHashSet<SystemNotification> systemNotifications);

    void sentNotifications(LinkedHashMap<String ,SentRequestStatue > sentRequests);

    void requests(LinkedList<UserDemo> demos);

    void followings(LinkedList<UserDemo> demos);
    void followers(LinkedList<UserDemo> demos);
    void blackList(LinkedList<UserDemo> demos);


}