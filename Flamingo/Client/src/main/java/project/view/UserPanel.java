package project.view;

import models.Commons;
import models.UserDemo;
import project.listener.RequestListener;
import project.tools.ImageResizer;
import request.UserPanelRequest;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserPanel extends JPanel implements ActionListener, Commons {
    private JLabel userLabel = new JLabel("user");
    private JCheckBox followCheck = new JCheckBox();
    private JCheckBox muteCheck = new JCheckBox();
    private JCheckBox blockCheck = new JCheckBox();
    private JButton messageBtn = new JButton("Message");
    private JButton showTweetsBtn = new JButton("ShowTweets");
    private JButton reportBtn = new JButton("Report");
    private JPanel infoPanel = new JPanel();
    private JTextField nameField = new JTextField(10);
    private JTextArea bioField = new JTextArea(2,10);
    private JLabel isPrivateLabel = new JLabel();
    private JLabel lastSeenField = new JLabel();
    JPanel panel = new JPanel();
    private RequestListener requestListener;

    public int getUserId() {
        return userId;
    }

    private int userId;
//    private StringListener stringListener;


    public UserPanel(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Profile", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 20), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());


        userLabel.setHorizontalTextPosition(JLabel.RIGHT);
        userLabel.setVerticalTextPosition(JLabel.CENTER);
        userLabel.setIcon(NO_IMAGE_ICON);

        userLabel.setVerticalTextPosition(JLabel.BOTTOM);
        userLabel.setHorizontalTextPosition(JLabel.CENTER);
        userLabel.setFont(new Font(null,Font.PLAIN,40));

        followCheck.setIcon(FOLLOW_IMAGE_ICON);
        followCheck.setSelectedIcon(UNFOLLOW_IMAGE_ICON);
        followCheck.addActionListener(this);

        muteCheck.setIcon(MUTE_IMAGE_ICON);
        muteCheck.setSelectedIcon(UNMUTE_IMAGE_ICON);
        muteCheck.addActionListener(this);

        blockCheck.setIcon(BLOCK_IMAGE_ICON);
        blockCheck.setSelectedIcon(UNBLOCK_IMAGE_ICON);
        blockCheck.addActionListener(this);

        reportBtn.setFocusable(false);
        reportBtn.addActionListener(this);

        showTweetsBtn.setFocusable(false);
        showTweetsBtn.addActionListener(this);

        messageBtn.setFocusable(false);
        messageBtn.addActionListener(this);





        gc.weightx = 1;
        gc.weighty = 0.1;
        ///////////////1
        gc.gridx = 0;
        gc.gridy = 0;

        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(userLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(0, 10, 0, 0);
        panel.add(followCheck , gc);

        gc.gridx = 1;
        gc.gridy = 0;

        gc.insets = new Insets(60, 10, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        panel.add(blockCheck ,gc);


        gc.gridx = 2;
        gc.gridy = 0;

        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        panel.add(muteCheck, gc);

        gc.gridx = 0;
        gc.gridy = 1;

        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(infoPanel, gc);

        gc.gridx = 2;
        gc.gridy = 1;

        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        panel.add(showTweetsBtn, gc);

        gc.gridx = 2;
        gc.gridy = 1;

        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        panel.add(messageBtn, gc);


        gc.gridx = 2;
        gc.gridy = 1;

        gc.insets = new Insets(0, 0, 5, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        panel.add(reportBtn, gc);





//        setInfoPanel(true ,"name" , "sdfsd" , "recently");

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);
        this.setLayout(new GridLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == followCheck) {
            if (followCheck.isSelected())
                requestListener.listen(new UserPanelRequest("follow",userId));
//                stringListener.stringEventOccurred("follow");
            if (!followCheck.isSelected()) {
                requestListener.listen(new UserPanelRequest("unFollow",userId));
//                stringListener.stringEventOccurred("unFollow");
            }
        }

        if (e.getSource() == blockCheck) {
            if (blockCheck.isSelected()){
                requestListener.listen(new UserPanelRequest("block",userId));
//                stringListener.stringEventOccurred("block");
            }
            if (!blockCheck.isSelected()){
                requestListener.listen(new UserPanelRequest("unBlock",userId));
//                stringListener.stringEventOccurred("unBlock");
            }
        }
        if (e.getSource() == messageBtn) {

//            stringListener.stringEventOccurred("message");
        }
        if (e.getSource() == muteCheck)
            if (muteCheck.isSelected()){
                requestListener.listen(new UserPanelRequest("mute",userId));
            }
        if (!muteCheck.isSelected())
            requestListener.listen(new UserPanelRequest("unMute",userId));
//            stringListener.stringEventOccurred("mute");
//            else stringListener.stringEventOccurred("unMute");

//        stringListener.stringEventOccurred("openPage");
//        MainFrame.refreshFrame();


    }


    public void setUser(String username, String profilePhotoPath) {

    }

    public void followCheck(boolean isFollowed) {
        if (isFollowed){
            messageBtn.setEnabled(true);
            messageBtn.setVisible(true);
        }else {
            messageBtn.setVisible(false);
            messageBtn.setEnabled(false);
        }
    }

    public void isPrivate(boolean isPrivate){

    }
    public void isOpenToMe(){}
    public void blockCheck(boolean isBlocked) {
        if (isBlocked) {
            followCheck.setVisible(false);
            followCheck.setEnabled(false);
            muteCheck.setEnabled(false);
            muteCheck.setVisible(false);
            blockCheck.setSelected(true);
            messageBtn.setEnabled(false);
            messageBtn.setVisible(false);

        }
        else{
            followCheck.setVisible(true);
            followCheck.setEnabled(true);
            muteCheck.setEnabled(true);
            muteCheck.setVisible(true);
            blockCheck.setSelected(false);
            messageBtn.setVisible(true);
            messageBtn.setEnabled(true);
        }
    }
    public void muteCheck(boolean isMuted){
            muteCheck.setSelected(isMuted);

    }

//    public void setStringListener(StringListener stringListener) {
//        this.stringListener = stringListener;
//    }

    public void initialize(UserDemo e) throws IOException {
        userId = e.getId();

        infoPanel.removeAll();
        setInfoPanel(e.isPrivate() , e.getName() , e.getBio() , e.getLastSeen());

        userLabel.setText(e.getUsername());
        userLabel.setIcon(ImageResizer.reSizeImage(LARGE_PROFILE_PICTURE_DIMENSION.width,
                LARGE_PROFILE_PICTURE_DIMENSION.height,
                ImageConvertor.toBufferedImage(e.getEncodedImage())));



        if (e.isAlreadyBlocked()) {
            followCheck.setVisible(false);
            followCheck.setEnabled(false);

            muteCheck.setEnabled(false);
            muteCheck.setVisible(false);

            blockCheck.setSelected(true);

            messageBtn.setEnabled(false);
            messageBtn.setVisible(false);

            showTweetsBtn.setEnabled(false);
            showTweetsBtn.setVisible(false);
            System.out.println("clear");
        }
        else{
            muteCheck.setSelected(e.isMuted());
            followCheck.setVisible(true);
            followCheck.setEnabled(true);

            muteCheck.setEnabled(true);
            muteCheck.setVisible(true);

            messageBtn.setEnabled(true);
            messageBtn.setVisible(true);

            showTweetsBtn.setEnabled(true);
            showTweetsBtn.setVisible(true);
            if (e.isAlreadyFollowed()){
                followCheck.setSelected(true);

                messageBtn.setEnabled(true);
                messageBtn.setVisible(true);

                showTweetsBtn.setVisible(true);
                showTweetsBtn.setEnabled(true);

            }else {
                followCheck.setSelected(false);

                messageBtn.setVisible(false);
                messageBtn.setEnabled(false);
                if (!e.isPrivate()){
                    showTweetsBtn.setEnabled(true);
                    showTweetsBtn.setVisible(true);
                }else {
                    showTweetsBtn.setVisible(false);
                    showTweetsBtn.setEnabled(false);
                }
            }
        }

    }

    public void setInfoPanel(boolean isPrivate , String name , String bio , String lastSeen){
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        nameField.setText(name);
        nameField.setEditable(false);

        bioField.setText(bio);
        bioField.setEditable(false);
        bioField.setFocusable(false);

        lastSeenField.setText(lastSeen);

        gc.weightx = 1;
        gc.weighty = 0.1;
        //////////////////1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10, 0, 20, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(new JLabel("name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 20, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(nameField, gc);

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 20, 7);
        gc.anchor = GridBagConstraints.LINE_END;

        infoPanel.add(new JLabel("bio: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        JScrollPane scrollPane = new JScrollPane(bioField);
        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        infoPanel.add(scrollPane, gc);

        /////////////// 3
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 20, 7);
        gc.anchor = GridBagConstraints.LINE_END;

        if (isPrivate){
            isPrivateLabel.setText("Private");
            isPrivateLabel.setForeground(Color.red);
        }else {
            isPrivateLabel.setText("Public");
            isPrivateLabel.setForeground(Color.GREEN);
        }
        infoPanel.add(isPrivateLabel, gc);
        ////////////////////4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 10, 20, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(new JLabel("LastSeen :"), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 20, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(lastSeenField, gc);

    }

}
