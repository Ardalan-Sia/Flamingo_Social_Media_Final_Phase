package project.view;

import models.Commons;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel implements Commons {
    public CenterPanel() {

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(Commons.BACKGROUND_IMAGE_ICON_No1.getImage(), 0, 0, null);
    }
}

