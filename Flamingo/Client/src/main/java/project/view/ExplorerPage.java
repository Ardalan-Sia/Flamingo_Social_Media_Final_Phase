package project.view;

import models.Commons;
import project.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExplorerPage extends JPanel implements ActionListener , Commons {
    private JButton exploreBtn = new JButton("Explore");
    private JButton searchBtn = new JButton("Search");

    private StringListener stringListener;

    public ExplorerPage() {
        exploreBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        exploreBtn.setFocusable(false);
        exploreBtn.setBackground(BLUE_BTN_COLOR);
        exploreBtn.addActionListener(this);

        searchBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        searchBtn.setFocusable(false);
        searchBtn.setBackground(BLUE_BTN_COLOR);
        searchBtn.addActionListener(this);

        this.add(searchBtn);
        this.add(exploreBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exploreBtn){
            stringListener.stringEventOccurred("explore");
        }
        if (e.getSource() == searchBtn){
            stringListener.stringEventOccurred("search");

        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No4.getImage(),0,0,null);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

}
