package project.view;

import models.SentRequestStatue;
import models.SystemNotification;
import models.UserDemo;
import project.listener.RequestListener;
import project.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class NotificationListingPanel extends JPanel {

    private LinkedList<Component> contents;
    private ScrollableListView<Component> scrollbar;
    private StringListener stringListener;
    private RequestListener requestListener;

    public NotificationListingPanel(RequestListener requestListener) {
        this.requestListener = requestListener;

        contents = new LinkedList<>();
        scrollbar = new ScrollableListView<>(contents);
        add(scrollbar);
        this.setLayout(new GridLayout());
        scrollbar.setBackground(Color.YELLOW);
    }
    public void addRequests(LinkedList<UserDemo> requestedUsers){
        LinkedList<Component> panels = new LinkedList<>();

        for (UserDemo user :requestedUsers) {
            RequestBar requestBar = new RequestBar(user , requestListener);
//            RequestBarListener listener = new RequestBarListener();
//            requestBar.setListener(listener);
            panels.add(requestBar);
        }
        scrollbar.update(panels);
        repaint();
        revalidate();
    }

    public void addSystemNotifications(LinkedHashSet<SystemNotification> systemNotifications){

        LinkedList<Component> temp = new LinkedList<>();
        for (SystemNotification s :systemNotifications) {
            JLabel label = new JLabel(s.toString());
            label.setForeground(Color.WHITE);
            temp.add(label);
        }
        scrollbar.update(temp);
        repaint();
        revalidate();
    }

    public void addSentRequest(LinkedHashMap<String, SentRequestStatue> sentRequests){
        LinkedList<Component> temp = new LinkedList<>();
        for (String username :sentRequests.keySet()) {
            JLabel label = new JLabel('@'+username+" "+sentRequests.get(username));
            switch (sentRequests.get(username)){
                case SENT -> label.setForeground(Color.WHITE);
                case DECLINED->label.setForeground(Color.red);
                case ACCEPTED -> label.setForeground(Color.green);
            }
            temp.add(label);
        }
        scrollbar.update(temp);
        repaint();
        revalidate();
    }

}
