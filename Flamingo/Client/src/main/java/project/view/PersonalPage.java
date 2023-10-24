package project.view;

import models.Commons;
import project.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalPage extends JPanel implements ActionListener , Commons {


    private JButton newTweetBtn;
    private JButton followingsBtn;
    private JButton followersBtn;
    private JButton blackListBtn;
    private JButton infoBtn;
    private JButton notificationsBtn;

    private StringListener stringListener;

    public PersonalPage() {
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Personal Page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));


        newTweetBtn = new JButton("New Tweet");
        newTweetBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        newTweetBtn.setBackground(BLUE_BTN_COLOR);
        newTweetBtn.setFocusable(false);
        newTweetBtn.addActionListener(this);

        followingsBtn = new JButton("Followings");
        followingsBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        followingsBtn.setBackground(BLUE_BTN_COLOR);
        followingsBtn.setFocusable(false);
        followingsBtn.addActionListener(this);

        followersBtn = new JButton("Followers");
        followersBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        followersBtn.setBackground(BLUE_BTN_COLOR);
        followersBtn.setFocusable(false);
        followersBtn.addActionListener(this);

        blackListBtn = new JButton("BlackList");
        blackListBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        blackListBtn.setBackground(BLUE_BTN_COLOR);
        blackListBtn.setFocusable(false);
        blackListBtn.addActionListener(this);

        infoBtn = new JButton("Info & Edit");
        infoBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        infoBtn.setBackground(BLUE_BTN_COLOR);
        infoBtn.setFocusable(false);
        infoBtn.addActionListener(this);

        notificationsBtn = new JButton("Notifications");
        notificationsBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        notificationsBtn.setBackground(BLUE_BTN_COLOR);
        notificationsBtn.setFocusable(false);
        notificationsBtn.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        this.add(newTweetBtn);
        this.add(followingsBtn);
        this.add(followersBtn);
        this.add(blackListBtn);
        this.add(infoBtn);
        this.add(notificationsBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newTweetBtn)
            stringListener.stringEventOccurred("newTweet");
        if (e.getSource() == infoBtn)
            stringListener.stringEventOccurred("info");
        if (e.getSource() == followersBtn)
            stringListener.stringEventOccurred("followers");
        if (e.getSource() == followingsBtn)
            stringListener.stringEventOccurred("followings");
        if (e.getSource() == blackListBtn)
            stringListener.stringEventOccurred("blackList");
        if (e.getSource() == notificationsBtn)
            stringListener.stringEventOccurred("notifications");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No3.getImage(), 0, 0, null);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

}


