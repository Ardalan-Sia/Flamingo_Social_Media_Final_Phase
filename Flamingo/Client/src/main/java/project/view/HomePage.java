package project.view;


import models.Commons;
import project.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel implements Commons, ActionListener {
    private JButton personalPage ;
    private JButton settings ;
    private JButton timeLine ;
    private JButton explorer ;
    private JButton messenger ;

    private StringListener stringListener;


    public HomePage() {
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Main page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null,Font.BOLD,40), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        this.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
        settings = new JButton("settings");
        settings.addActionListener(this);
        settings.setPreferredSize(LARGE_BTN_DIMENSION);
        settings.setBackground(GREEN_BTN_COLOR);
        settings.setFocusable(false);

        personalPage = new JButton("personal page");
        personalPage.addActionListener(this);
        personalPage.setPreferredSize(LARGE_BTN_DIMENSION);
        personalPage.setBackground(GREEN_BTN_COLOR);
        personalPage.setFocusable(false);

        timeLine = new JButton("timeline");
        timeLine.setPreferredSize(LARGE_BTN_DIMENSION);
        timeLine.setBackground(GREEN_BTN_COLOR);
        timeLine.addActionListener(this);
        timeLine.setFocusable(false);

        explorer = new JButton("explorer");
        explorer.setPreferredSize(LARGE_BTN_DIMENSION);
        explorer.setBackground(GREEN_BTN_COLOR);
        explorer.setFocusable(false);
        explorer.addActionListener(this);

        messenger = new JButton("messenger");
        messenger.setPreferredSize(LARGE_BTN_DIMENSION);
        messenger.setBackground(GREEN_BTN_COLOR);
        messenger.setFocusable(false);
        messenger.addActionListener(this);

        add(settings);
        add(personalPage);
        add(timeLine);
        add(explorer);
        add(messenger);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settings){
            stringListener.stringEventOccurred("settings");
        }
        if (e.getSource() == personalPage){
            stringListener.stringEventOccurred("personalPage");
        }
        if (e.getSource() == timeLine){
            stringListener.stringEventOccurred("timeLine");
        }
        if (e.getSource() == explorer){
            stringListener.stringEventOccurred("explorer");
        }
        if (e.getSource() == messenger){
            stringListener.stringEventOccurred("messenger");
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No1.getImage(),0,0,null);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
