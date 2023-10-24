package request;

import models.*;
import response.Response;

public interface RequestVisitor {

    Response signUp(SignUpForm signUpForm);
    Response signIn(SignInForm signInForm);
    Response goToPrivacyPage();
    Response editPrivacySettings(boolean isPrivate, LastSeenType lastSeenType, String password);
    Response goToInfoPage();
    Response editInfoPage(SignUpForm form);
    Response goToMyTweets();
    Response creatTweet(TweetDemo tweetDemo);
    Response likeTweet(int tweetId , boolean liked);
    Response getComments(int tweetId);
    Response newComment(CommentDemo commentDemo);
    Response likeComment(int parentId , int commentId , boolean liked);
    Response getUser(String username);
    Response logout(boolean exit);
    Response userPanelActionRequest(int userId,String request);
    Response gotoTimeLine();
    Response gotoExplorerTweets();
    Response followAnswer(int userId,boolean accepted);
    Response getSystemNotifications();
    Response getSentRequests();
    Response getRequests();
    Response getFollowers();
    Response getFollowings();
    Response getBlackList();



}
