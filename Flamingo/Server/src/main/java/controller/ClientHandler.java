package controller;

import Tools.RegistrationCheck;
import controller.network.ResponseSender;

import db.Context;
import model.Comment;
import model.Tweet;
import model.User;
import models.*;

import org.apache.commons.io.FilenameUtils;
import request.RequestVisitor;
import response.*;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ClientHandler extends Thread implements RequestVisitor {


    private ResponseSender sender;
    private Context context;
    private User owner;
    private ControllerManager controllerManager;

    public ClientHandler(ResponseSender sender, Context context) {
        this.sender = sender;
        this.context = context;
        controllerManager = new ControllerManager(this);
    }

    public void run() {
        while (true) {
            try {
                sender.sendResponse(sender.getRequest().visit(this));
            } catch (NoSuchElementException e1) {
                context.Users.update(owner);
                break;

            }
        }
    }

    @Override
    public Response signUp(SignUpForm signUpForm) {
        try {

            System.out.println(signUpForm.getEmail());
            owner = controllerManager.authController.signUp(signUpForm);
        } catch (Exception exception) {
            return new SignUpResponse(false, exception.getMessage());
        }

        return new SignUpResponse(true, "");

    }

    @Override
    public Response signIn(SignInForm signInForm) {
        try {
            owner = controllerManager.authController.signIn(signInForm);
        } catch (Exception e) {
            return new SignInResponse(false, e.getMessage());
        }
        return new SignInResponse(true, "");
    }

    @Override
    public Response goToPrivacyPage() {
        return new PrivacyInfoResponse(owner.getLastSeenType(), owner.isPrivate(), owner.getPassword());
    }

    @Override
    public Response editPrivacySettings(boolean isPrivate, LastSeenType lastSeenType, String password) {
        owner.setPrivate(isPrivate);
        owner.setLastSeenType(lastSeenType);
        owner.setPassword(password);
        context.Users.update(owner);
        controllerManager.userController.logger.info("@"+owner.getUsername()+" edited profile");
        return goToPrivacyPage();
    }

    @Override
    public Response goToInfoPage() {

        File imageFile = new File(owner.getProfilePhotoPath());
        String format = FilenameUtils.getExtension(owner.getProfilePhotoPath());

        try {
            BufferedImage bi = null;
            bi = new BufferedImage(10, 10,
                    BufferedImage.TYPE_INT_ARGB);
            bi = ImageIO.read(imageFile);
            return new PersonalInfoResponse(new SignUpForm(owner.getName(),
                    owner.getUsername(),
                    owner.getEmail(),
                    "",
                    "",
                    owner.getBirthday(),
                    owner.getPhoneNumber(),
                    owner.getBio(),
                    ImageConvertor.toString(bi, format)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response editInfoPage(SignUpForm signUpForm) {
        System.out.println("edit");
        try {
            RegistrationCheck registrationCheck = new RegistrationCheck();
            owner.setName(registrationCheck.checkName(signUpForm.getName()));
            if (!signUpForm.getUsername().equals(owner.getUsername()))
                owner.setUsername(registrationCheck.checkUsername(signUpForm.getUsername()));
            if (!signUpForm.getEmail().equals(owner.getEmail()))
                owner.setEmail(registrationCheck.checkEmail(signUpForm.getEmail()));
            if (!signUpForm.getPhoneNumber().equals(owner.getPhoneNumber()))
                owner.setPhoneNumber(registrationCheck.checkPhoneNumber(signUpForm.getPhoneNumber()));
            if (!signUpForm.getEncodedImage().isEmpty()) {
                context.Users.setPhoto(owner, ImageConvertor.toBufferedImage(signUpForm.getEncodedImage()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new EditInfoResponse(false, exception.getMessage());
        }
        return new EditInfoResponse(true, "");
    }

    @Override
    public Response goToMyTweets() {
        System.out.println("MyTweets");

        return new MyTweets(controllerManager.tweetsController.demos(owner.getMyTweets()));
    }

    @Override
    public Response creatTweet(TweetDemo tweetDemo) {
        System.out.println("new Tweet");
        Tweet tweet = new Tweet(tweetDemo.getContent(), owner.getId());
        context.Tweets.add(tweet);
        owner.getMyTweets().add(tweet.getId());
        if (tweetDemo.getEncodedImage() != null) {
            try {
                if (ImageConvertor.toBufferedImage(tweetDemo.getEncodedProfilePhoto()) != null)
                    context.Tweets.setPhoto(tweet, ImageConvertor.toBufferedImage(tweetDemo.getEncodedProfilePhoto()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        controllerManager.userController.logger.info("@"+owner.getUsername()+"  added new tweet");
        context.Users.update(owner);
        context.Tweets.update(tweet);
        return goToMyTweets();
    }

    @Override
    public Response likeTweet(int tweetId, boolean liked) {
        Tweet tweet = context.Tweets.get(tweetId);
        if (liked) {
            controllerManager.userController.logger.info("@"+owner.getUsername()+" Liked a tweet");
            System.out.println("liked");
            tweet.getLikes().add(owner.getId());
        } else
            tweet.getLikes().remove(owner.getId());

        context.Tweets.update(tweet);
        return null;
    }

    @Override
    public Response getComments(int parentId) {
        Tweet tweet = context.Tweets.get(parentId);
        LinkedList<CommentDemo> comments = new LinkedList<>();
        String ownerImage = null;
        try {
            ownerImage = ImageConvertor.toString(ImageIO.read(new File(owner.getProfilePhotoPath())), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Comment comment : tweet.getComments()) {
            comments.add(new CommentDemo(comment.getLikes(),
                    comment.getOwnerId(),
                    comment.getId(),
                    tweet.getId(),
                    comment.getTime(),
                    comment.getBody(),
                    ownerImage,
                    owner.getUsername(),
                    comment.getLikes().contains(owner.getId())));
        }
        return new TweetCommentsResponse(comments,tweet.getId());
    }

    @Override
    public Response newComment(CommentDemo commentDemo) {
        controllerManager.userController.logger.info("@"+owner.getUsername()+"added comment");
        Comment comment = new Comment(commentDemo.getContent(), owner.getId());
        Tweet tweet = context.Tweets.get(commentDemo.getParentId());
        tweet.addComment(comment);
        context.Tweets.update(tweet);
        return null;
    }

    @Override
    public Response likeComment(int parentId, int commentId, boolean liked) {
        System.out.println(liked);
        Tweet parent = context.Tweets.get(parentId);
        parent.getComments().forEach(comment1 -> {
            if (comment1.getId() == commentId) {
                if (liked)
                    comment1.getLikes().add(owner.getId());
                else comment1.getLikes().remove(owner.getId());
            }
        });
        context.Tweets.update(parent);
        return null;
    }

    @Override
    public Response getUser(String s) {
        UserDemo demo = controllerManager.userController.getUserDemo(s);
        if (demo == null)
            return new StringResponse("userNotFound");
        return new UserInfoResponse(demo);
    }

    @Override
    public Response logout(boolean exited) {
        controllerManager.authController.logout();
        sender.expireToken();
        return new LogoutResponse();
    }

    @Override
    public Response userPanelActionRequest(int userId, String request) {
        return controllerManager.userController.listenToUserPanel(userId,request);
    }

    @Override
    public Response gotoTimeLine() {
        return new TimeLineTweets(controllerManager.tweetsController.demos(controllerManager.timeLineController.timeLine()));
    }

    @Override
    public Response gotoExplorerTweets() {
        return new ExplorerTweets(controllerManager.tweetsController.demos(controllerManager.explorerController.explorerTweets(owner)));
    }

    @Override
    public Response followAnswer(int userId, boolean accepted) {

        if (accepted)
            controllerManager.userController.accept(context.Users.get(userId));
        else controllerManager.userController.decline(context.Users.get(userId));
        return getRequests() ;
    }

    @Override
    public Response getSystemNotifications() {
        System.out.println(owner.getSystemNotification().size() );
        return new SystemRequestResponse(owner.getSystemNotification());
    }

    @Override
    public Response getSentRequests() {
        LinkedHashMap<String , SentRequestStatue> temp = new LinkedHashMap<>();
        for (Integer id:owner.getSentRequests().keySet()) {
            temp.put(new Context().Users.get(id).getUsername() , owner.getSentRequests().get(id));
        }
        System.out.println(temp.size());
        return new SentRequests(temp);
    }

    @Override
    public Response getRequests() {
        LinkedList<UserDemo> requests = new LinkedList<>();
        for (Integer i : owner.getReceivedRequests()) {
            requests.add(controllerManager.userController.getUserDemo(new Context().Users.get(i).getUsername()));
        }
        return new FollowRequests(requests);
    }

    @Override
    public Response getFollowers() {
        LinkedList<UserDemo> demos = new LinkedList<>();
        for (Integer id:owner.getFollowers()) {
            User user = context.Users.get(id);
            demos.add(controllerManager.userController.getUserDemo(user.getUsername()));

        }
        return new Followers(demos);
    }

    @Override
    public Response getFollowings() {
        LinkedList<UserDemo> demos = new LinkedList<>();
        for (Integer id:owner.getFollowings()) {
            User user = context.Users.get(id);
            demos.add(controllerManager.userController.getUserDemo(user.getUsername()));

        }
        return new Followings(demos);
    }

    @Override
    public Response getBlackList() {
        LinkedList<UserDemo> demos = new LinkedList<>();
        for (Integer id:owner.getBlackList()) {
            User user = context.Users.get(id);
            demos.add(controllerManager.userController.getUserDemo(user.getUsername()));

        }
        return new BlackList(demos);
    }


    public User getOwner() {
        return owner;
    }
}

