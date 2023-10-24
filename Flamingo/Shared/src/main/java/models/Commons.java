package models;

import javax.swing.*;
import java.awt.*;

public interface Commons {

    int WIDTH = 1000;
    int HEIGHT = 600;

    public static final int FPS = 60;
    String TITLE = "Flamingo";

    Color REGISTRATION_BACKGROUND_COLOR = new Color(188, 212, 230);
    Color DEFAULT_BACKGROUND_COLOR = new Color(2,23,52,255);
    Color WARNING_FONT_COLOR = Color.red;
    Color GREEN_BTN_COLOR = Color.decode("#20ff5e");
    Color GRAY_BTN_COLOR = Color.decode("#bcb8b6");
    Color BLUE_BTN_COLOR = Color.decode("#05E9FF");
    Color TWEETS_BACKGROUND = Color.decode("#92A8D1");
    Color COMMENTS_BACKGROUND = Color.decode("#192734");
    Color COMMENTS_FOREGROUND = Color.decode("#F5F5F5");


    ImageIcon BACKGROUND_IMAGE_ICON_No1 = new ImageIcon("src\\main\\resources\\image\\blueBackground.png");
    ImageIcon BACKGROUND_IMAGE_ICON_No2 = new ImageIcon("src\\main\\resources\\image\\grayBackground.png");
    ImageIcon BACKGROUND_IMAGE_ICON_No3 = new ImageIcon("src\\main\\resources\\image\\personalPageBackGround.png");
    ImageIcon BACKGROUND_IMAGE_ICON_No4 = new ImageIcon("src\\main\\resources\\image\\backGround4.jpg");
    ImageIcon BLUE_BACKGROUND_IMAGE_ICON_2 = new ImageIcon("src\\main\\resources\\image\\backGround3.png");


    ImageIcon NO_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\no-image-icon.png");
    ImageIcon CHECK_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\check.png");
    ImageIcon CROSS_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\cross.png");
    ImageIcon APP_ICON = new ImageIcon("src\\main\\resources\\image\\Flamingo-icon.png");
    ImageIcon LIKE = new ImageIcon("src\\main\\resources\\image\\like.png");
    ImageIcon DISLIKE = new ImageIcon("src\\main\\resources\\image\\dislike.png");
    ImageIcon FOLLOW_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\Follow.png");
    ImageIcon UNFOLLOW_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\UnFollow.png");
    ImageIcon MUTE_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\mute.png");
    ImageIcon UNMUTE_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\unMute.png");
    ImageIcon BLOCK_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\block.jpg");
    ImageIcon UNBLOCK_IMAGE_ICON = new ImageIcon("src\\main\\resources\\image\\unBlock.jpg");

    Font WARNING_FONT = new Font("TimesRoman", Font.PLAIN,10);

    String USERS_DIRECTORY = "src\\main\\resources\\Users";
    String TWEETS_DIRECTORY = "src\\main\\resources\\Tweets";
    String CHATS_DIRECTORY = "src\\main\\resources\\Chats";
    String GROUP_DIRECTORY = "src\\main\\resources\\Groups";
    String MESSAGES_DIRECTORY = "src\\main\\resources\\Messages";


    Dimension LARGE_BTN_DIMENSION = new Dimension(200,100);
    Dimension LARGE_PROFILE_PICTURE_DIMENSION = new Dimension(300,300);
    Dimension SMALL_PROFILE_PICTURE_DIMENSION = new Dimension(20,20);
    Dimension TWEET_PICTURE_DIMENSION = new Dimension(200,200);

}
