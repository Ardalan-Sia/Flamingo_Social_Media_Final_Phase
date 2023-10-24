package project.view;


import models.UserDemo;
import project.listener.RequestListener;
import project.listener.SearchListener;
import project.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SearchingPage extends JPanel implements ActionListener {
    private SearchingBar searchingBar;
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private UserPanel userPanel;
    private RequestListener requestListener;
    private SearchListener searchListener;
    private StringListener stringListener;
    private String username;


    public SearchingPage(RequestListener requestListener) {
        searchListener = new SearchListener(requestListener);
        searchingBar = new SearchingBar(searchListener);
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());


        northPanel.add(searchingBar);
        northPanel.setBackground(Color.cyan);

        this.add(northPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public SearchingBar getSearchUserPanel() {
        return searchingBar;
    }

    public void setSearchUserPanel(SearchingBar searchingBar) {
        this.searchingBar = searchingBar;
    }


    public void setUserPanel(UserDemo userDemo) throws IOException {
        centerPanel.removeAll();
        this.username = userDemo.getUsername();
        UserPanel userPanel = new UserPanel(requestListener);
        userPanel.initialize(userDemo);
        this.userPanel = userPanel;
        centerPanel.setLayout(new GridLayout());


        centerPanel.add(userPanel);
        this.add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public String getUsername() {
        return username;
    }

    public void userNotFound() {
        JOptionPane.showMessageDialog(null, "user not found", null, JOptionPane.ERROR_MESSAGE);

    }


    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
