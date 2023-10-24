package project.view;

import models.UserDemo;
import project.listener.RequestListener;
import request.FollowAnswerRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestBar extends JPanel implements ActionListener {

    private JButton decline;
    private JButton accept;
    private JLabel req;
    private UserDemo user;
    private RequestListener requestListener;

    public RequestBar(UserDemo requestedUser , RequestListener requestListener){
        this.user = requestedUser;
        this.requestListener = requestListener;
        req = new JLabel();

        setLayout(new BorderLayout());
        accept = new JButton("accept");
        accept.setBackground(Color.GREEN);
        decline = new JButton("decline");
        decline.setBackground(Color.red);
        decline.addActionListener(this);
        accept.addActionListener(this);

        req.setText("@"+requestedUser.getUsername() + " has requested to follow you");


        add(req,BorderLayout.WEST);
        add(accept,BorderLayout.CENTER);
        add(decline,BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == accept){
            requestListener.listen(new FollowAnswerRequest(true ,user.getId() ));
        }else if (e.getSource() == decline){
            requestListener.listen(new FollowAnswerRequest(false ,user.getId() ));
        }
    }
}
