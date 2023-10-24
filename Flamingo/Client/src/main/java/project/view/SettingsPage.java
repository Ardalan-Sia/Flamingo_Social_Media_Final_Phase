package project.view;


import models.Commons;
import project.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage extends JPanel implements Commons, ActionListener {


    private JButton privacyBtn;
    private JButton deleteAccountBtn;
    private JButton logoutBtn;

    private StringListener stringListener;

    public SettingsPage() {
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Settings", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));


        privacyBtn = new JButton("Privacy");
        privacyBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        privacyBtn.setBackground(GRAY_BTN_COLOR);
        privacyBtn.setFocusable(false);
        privacyBtn.addActionListener(this);

        deleteAccountBtn = new JButton("Delete account");
        deleteAccountBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        deleteAccountBtn.setBackground(GRAY_BTN_COLOR);
        deleteAccountBtn.setFocusable(false);
        deleteAccountBtn.addActionListener(this);


        logoutBtn = new JButton("Logout");
        logoutBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        logoutBtn.setBackground(GRAY_BTN_COLOR);
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        this.add(privacyBtn);
        this.add(deleteAccountBtn);
        this.add(logoutBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteAccountBtn)
            stringListener.stringEventOccurred("deleteAccount");
        if (e.getSource() == privacyBtn)
            stringListener.stringEventOccurred("privacy");
        if (e.getSource() == logoutBtn)
            stringListener.stringEventOccurred("logout");


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No2.getImage(), 0, 0, null);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

}
