package project.view;

import models.Commons;
import project.listener.StringListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ToolBar extends JPanel implements ActionListener , Commons {
    private JButton registrationBtn;
    private JButton loginBtn;
    private JButton homePageBtn;
    private JButton backBtn;
    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    ToolBar() {

        this.setBackground(DEFAULT_BACKGROUND_COLOR);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        registrationBtn = new JButton("Register");
        registrationBtn.setPreferredSize(new Dimension(85, 35));
        registrationBtn.setFocusable(false);
        registrationBtn.setBackground(Color.yellow);
        registrationBtn.addActionListener(this);


        loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(85, 35));
        loginBtn.setFocusable(false);
        loginBtn.setBackground(Color.yellow);
        loginBtn.addActionListener(this);

        homePageBtn = new JButton("home");
        homePageBtn.setPreferredSize(new Dimension(85, 35));
        homePageBtn.setFocusable(false);
        homePageBtn.setBackground(Color.yellow);
        homePageBtn.addActionListener(this);
        homePageBtn.setVisible(false);
        homePageBtn.setEnabled(false);

        backBtn = new JButton("back");
        backBtn.setPreferredSize(new Dimension(85, 35));
        backBtn.setFocusable(false);
        backBtn.setBackground(Color.yellow);
        backBtn.addActionListener(this);
        backBtn.setVisible(false);
        backBtn.setEnabled(false);

        this.add(registrationBtn);
        this.add(loginBtn);
        this.add(backBtn);
        this.add(homePageBtn);
    }

    public void addListener(StringListener stringListener) {
        stringListeners.add(stringListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (registrationBtn == (JButton)e.getSource()){
                stringListener.stringEventOccurred("registration");
            }
            if (loginBtn == (JButton)e.getSource()) {
                stringListener.stringEventOccurred("login");
            }
            if (homePageBtn == e.getSource()) {
                stringListener.stringEventOccurred("homePage");
            }
            if (backBtn == e.getSource()) {
                stringListener.stringEventOccurred("back");
            }
        }
    }

    public void updateToolbar(){
        loginBtn.setVisible(false);
        loginBtn.setEnabled(false);

        registrationBtn.setVisible(false);
        registrationBtn.setEnabled(false);

        backBtn.setVisible(true);
        backBtn.setEnabled(true);

        homePageBtn.setVisible(true);
        homePageBtn.setEnabled(true);
    }
}