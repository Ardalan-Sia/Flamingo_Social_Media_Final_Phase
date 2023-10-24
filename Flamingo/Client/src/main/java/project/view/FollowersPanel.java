package project.view;


import models.Commons;
import models.UserDemo;
import project.listener.RequestListener;
import project.tools.ImageResizer;
import request.GetUserRequest;
import tools.ImageConvertor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;

public class FollowersPanel extends JPanel implements ActionListener , Commons {


    private LinkedList<JLabel> followers;
    private ScrollableListView<JLabel> scrollbar;
    private Container container;
    private CardLayout cardLayout;
    private RequestListener requestListener;


    public FollowersPanel(RequestListener requestListener) {
        this.requestListener = requestListener;

        followers = new LinkedList<>();
        scrollbar = new ScrollableListView<>(followers);
        add(scrollbar);
        this.setLayout(new GridLayout());
        scrollbar.setBackground(Color.YELLOW);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void setFollowers(LinkedList<UserDemo> users) throws IOException {

        if (users == null)
            return;

        LinkedList<JLabel> labels = new LinkedList<>();
        for (UserDemo user : users) {
            JLabel userLabel = new JLabel();
            userLabel.setHorizontalTextPosition(JLabel.RIGHT);
            userLabel.setVerticalTextPosition(JLabel.CENTER);
            userLabel.setText(user.getUsername());
            userLabel.setForeground(Color.WHITE);

            userLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FollowersPanel.this.mouseClicked(user);
                }
            });
            userLabel.setIcon(ImageResizer.reSizeImage(SMALL_PROFILE_PICTURE_DIMENSION.width,
                    SMALL_PROFILE_PICTURE_DIMENSION.height,
                    ImageConvertor.toBufferedImage(user.getEncodedImage())));
            labels.add(userLabel);
        }
        scrollbar.update(labels);
        repaint();
        revalidate();

    }

    public void mouseClicked(UserDemo user){
        requestListener.listen(new GetUserRequest(user.getUsername()));
    }

}
