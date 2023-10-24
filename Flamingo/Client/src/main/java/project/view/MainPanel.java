package project.view;

import models.Commons;
import project.listener.StringListener;

import javax.swing.*;

import java.awt.*;

public class MainPanel extends JPanel implements Commons {

    private ToolBar toolBar;
    private CenterPanel centerPanel;

    public MainPanel() {

        centerPanel = new CenterPanel();

        toolBar = new ToolBar();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        this.add(centerPanel, BorderLayout.CENTER);


        this.add(toolBar, BorderLayout.NORTH);

    }

    public void setCenterPanel(JPanel panel){
        centerPanel.removeAll();
        centerPanel.add(panel);

        repaint();
        revalidate();

    }

    public void initialize(StringListener listener){
        this.toolBar.addListener(listener);

    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public JPanel getCenterPage() {
        return centerPanel;
    }
}
