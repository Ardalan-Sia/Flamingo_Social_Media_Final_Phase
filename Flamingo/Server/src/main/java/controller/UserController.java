package controller;

import model.Chat;
import model.Tweet;
import model.User;
import models.SentRequestStatue;
import models.SystemNotification;
import models.TweetDemo;
import models.UserDemo;
import response.Response;
import response.UserInfoResponse;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class UserController extends Controller {
    private ClientHandler clientHandler;

    public UserController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Response listenToUserPanel(int foundUserId , String request){
        User foundUser = context.Users.get(foundUserId);

        switch (request){
            case "follow"-> follow(foundUser);
            case "unFollow"->unFollow(foundUser);
            case "mute"->mute(foundUser);
            case "unMute"->unMute(foundUser);
            case "block"->block(foundUser);
            case "unBlock"->unBlock(foundUser);

        }
        context.Users.update(foundUser);
        return new UserInfoResponse(getUserDemo(foundUser.getUsername()));

    }


    public String getEncodedProfilePhoto(User user) {
        String image = null;
        try {
            image = ImageConvertor.toString(ImageIO.read(new File(user.getProfilePhotoPath())), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public UserDemo getUserDemo(String username) {

        for (User foundUser : context.Users.all()) {
            String lastSeen = "recently";
            switch (foundUser.getLastSeenType()) {
                case EVERYONE:
                    lastSeen = foundUser.getLastSeen();
                    break;
                case NOBODY:
                    lastSeen = "recently";
                    break;
                case FOLLOWINGS:
                    if (foundUser.getFollowings().contains(clientHandler.getOwner().getId()))
                        lastSeen = foundUser.getLastSeen();
                    break;
            }
            if (foundUser.getUsername().equals(username)) {
                return new UserDemo(foundUser.getId(),
                        foundUser.getName(),
                        foundUser.getUsername(),
                        getEncodedProfilePhoto(foundUser),
                        foundUser.isPrivate(),
                        lastSeen,
                        foundUser.getBio(),
                        clientHandler.getOwner().getFollowings().contains(foundUser.getId()),
                        clientHandler.getOwner().getBlackList().contains(foundUser.getId()),
                        clientHandler.getOwner().getMuteList().contains(foundUser.getId()));
            }

        }
        return null;
    }

    public void accept(User user){
        if(!clientHandler.getOwner().getReceivedRequests().contains(user.getId())){
            return;
        }
        clientHandler.getOwner().getReceivedRequests().remove(user.getId());
        clientHandler.getOwner().getFollowers().add(user.getId());
        user.addContact(clientHandler.getOwner().getId());
        clientHandler.getOwner().addContact(user.getId());
        user.getFollowings().add(clientHandler.getOwner().getId());
        user.getSentRequests().put(clientHandler.getOwner().getId() , SentRequestStatue.ACCEPTED);

        context.Users.update(clientHandler.getOwner());
        context.Users.update(user);
    }
    public void decline(User user){
        if(!clientHandler.getOwner().getReceivedRequests().contains(user.getId())){
            return;
        }
        clientHandler.getOwner().getReceivedRequests().remove(user.getId());
        user.getSentRequests().put(clientHandler.getOwner().getId() , SentRequestStatue.DECLINED);
        context.Users.update(clientHandler.getOwner());
        context.Users.update(user);
    }

    public boolean follow(User foundUser) {

        if (foundUser.isPrivate()) {
            sendRequest(foundUser);
            foundUser.getSystemNotification().add(
                    new SystemNotification("@" + clientHandler.getOwner().getUsername() + " has requested to follow you"));

            context.Users.update(clientHandler.getOwner());
            context.Users.update(foundUser);
            return false;

        } else {
            clientHandler.getOwner().getFollowings().add(foundUser.getId());
            foundUser.getFollowers().add(clientHandler.getOwner().getId());
            foundUser.getSystemNotification().add(
                    new SystemNotification("@" + clientHandler.getOwner().getUsername() + " has followed you"));
            foundUser.addContact(clientHandler.getOwner().getId());
            clientHandler.getOwner().addContact(foundUser.getId());
            context.Users.update(clientHandler.getOwner());
            context.Users.update(foundUser);
            logger.info("@"+clientHandler.getOwner().getUsername()+" followed "+foundUser.getUsername());
            return true;

        }


    }

    public void unFollow(User foundUser) {

        System.out.println("unfollow");
        clientHandler.getOwner().getFollowings().remove(foundUser.getId());
        foundUser.getFollowers().remove(clientHandler.getOwner().getId());

        foundUser.getSystemNotification().add(
                new SystemNotification('@' + clientHandler.getOwner().getUsername() + " has unFollowed you"));
        logger.info("@"+clientHandler.getOwner().getUsername()+" unfollowed "+foundUser.getUsername());
        context.Users.update(clientHandler.getOwner());
        context.Users.update(foundUser);
    }

    private void sendRequest(User foundUser) {
        foundUser.getReceivedRequests().add(clientHandler.getOwner().getId());
        clientHandler.getOwner().getSentRequests().put(foundUser.getId(), SentRequestStatue.SENT);
        logger.info("@"+clientHandler.getOwner().getUsername()+" send request to "+foundUser.getUsername());
        context.Users.update(clientHandler.getOwner());
        context.Users.update(foundUser);
    }

    public void unBlock(User foundUser){
        logger.info("@"+clientHandler.getOwner().getUsername()+" unblocked "+foundUser.getUsername());
        clientHandler.getOwner().getBlackList().remove(foundUser.getId());
        context.Users.update(clientHandler.getOwner());

    }
    public void block(User foundUser){
        logger.info("@"+clientHandler.getOwner().getUsername()+" blocked "+foundUser.getUsername());
        unFollow(foundUser);
        clientHandler.getOwner().getBlackList().add(foundUser.getId());
        clientHandler.getOwner().getFollowers().remove(foundUser.getId());
        clientHandler.getOwner().getContacts().remove(foundUser.getId());
        foundUser.getChats().remove(clientHandler.getOwner().getId());
        foundUser.getFollowings().remove(clientHandler.getOwner().getId());

        for (Integer id:clientHandler.getOwner().getChats()) {
            Chat chat = context.Chats.get(id);
            if (chat.getUsers().contains(foundUser.getId()))
                context.Chats.remove(chat);
        }

        context.Users.update(clientHandler.getOwner());
        context.Users.update(foundUser);

    }

    public void mute(User foundUser) {
        logger.info("@"+clientHandler.getOwner().getUsername()+" muted "+foundUser.getUsername());
        clientHandler.getOwner().getMuteList().add(foundUser.getId());
        context.Users.update(clientHandler.getOwner());

    }

    public void unMute(User foundUser) {
        logger.info("@"+clientHandler.getOwner().getUsername()+" un muted "+foundUser.getUsername());
        clientHandler.getOwner().getMuteList().remove(foundUser.getId());
        context.Users.update(clientHandler.getOwner());
    }

//    LinkedList<UserDemo> demos(LinkedList<Integer> usersId){
//        LinkedList<UserDemo> users = new LinkedList<>();
//
//        for (Integer userId : usersId) {
//            User user = context.Users.get(userId);
//            String ownerImage = null;
//            try {
//                ownerImage = ImageConvertor.toString(ImageIO.read(new File(user.getProfilePhotoPath())), "png");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            UserDemo demo = new UserDemo(user.getLikes(),
//                    user.getOwnerId(),
//                    user.getId(),
//                    user.getTime(),
//                    user.getBody(),
//                    tweetImage,
//                    ownerImage,
//                    tweetOwner.getUsername(),
//                    tweetOwner.getMyTweets().contains(user.getId()),
//                    user.getLikes().contains(tweetOwner.getId()));
//            users.add(demo);
//
//        }
//        return users;
//    }



}
