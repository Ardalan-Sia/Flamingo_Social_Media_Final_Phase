package project.view;

import models.Commons;
import project.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationsPanel extends JPanel implements Commons, ActionListener {
    private StringListener stringListener;
    private JButton systemNotifications;
    private JButton requests;
    private JButton sentRequests;

    public NotificationsPanel() {
        sentRequests = new JButton("sent requests");
        requests = new JButton("requests");
        systemNotifications = new JButton("system notifications");
        setBackground(Color.ORANGE);
        requests.setPreferredSize(LARGE_BTN_DIMENSION);
        requests.setFocusable(false);
        requests.setBackground(BLUE_BTN_COLOR);
        requests.addActionListener(this);

        systemNotifications.setPreferredSize(LARGE_BTN_DIMENSION);
        systemNotifications.setFocusable(false);
        systemNotifications.setBackground(BLUE_BTN_COLOR);
        systemNotifications.addActionListener(this);

        sentRequests.setPreferredSize(LARGE_BTN_DIMENSION);
        sentRequests.setFocusable(false);
        sentRequests.setBackground(BLUE_BTN_COLOR);
        sentRequests.addActionListener(this);

        this.add(sentRequests);
        this.add(systemNotifications);
        this.add(requests);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == requests)
            stringListener.stringEventOccurred("requests");
        if (e.getSource() == systemNotifications)
            stringListener.stringEventOccurred("systemNotifications");
        if (e.getSource() == sentRequests)
            stringListener.stringEventOccurred("sentRequests");

    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
