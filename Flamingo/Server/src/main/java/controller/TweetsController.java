package controller;

import model.Tweet;
import model.User;
import models.TweetDemo;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class TweetsController extends Controller{
    LinkedList<TweetDemo> demos(LinkedList<Integer> tweetIds){
        LinkedList<TweetDemo> tweets = new LinkedList<>();

        for (Integer tweetId : tweetIds) {
            Tweet tweet = context.Tweets.get(tweetId);
            User tweetOwner = context.Users.get(tweet.getOwnerId());
            String tweetImage = null;
            String ownerImage = null;
            try {
                ownerImage = ImageConvertor.toString(ImageIO.read(new File(tweetOwner.getProfilePhotoPath())), "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (!tweet.getPhotoPath().isEmpty())
                    tweetImage = ImageConvertor.toString(ImageIO.read(new File(tweet.getPhotoPath())), "png");

            } catch (IOException e) {
                e.printStackTrace();
            }

            TweetDemo demo = new TweetDemo(tweet.getLikes(),
                    tweet.getOwnerId(),
                    tweet.getId(),
                    tweet.getTime(),
                    tweet.getBody(),
                    tweetImage,
                    ownerImage,
                    tweetOwner.getUsername(),
                    tweetOwner.getMyTweets().contains(tweet.getId()),
                    tweet.getLikes().contains(tweetOwner.getId()));
            tweets.add(demo);

        }
        return tweets;
    }
}
