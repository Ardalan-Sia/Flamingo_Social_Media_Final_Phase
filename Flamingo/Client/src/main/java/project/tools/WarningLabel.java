package project.tools;


import models.Commons;

import javax.swing.*;

public class WarningLabel extends JLabel implements Commons {
    public WarningLabel(String title){
        super(title);
        this.setFont(WARNING_FONT);
        this.setForeground(WARNING_FONT_COLOR);
    }

}
