package project.view;


import models.Commons;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ScrollableListView<T> extends JPanel implements Commons {
    private JPanel panel = new JPanel();
    private Color bg;

    public ScrollableListView(LinkedList<T> list){

        bg = DEFAULT_BACKGROUND_COLOR;
        BoxLayout boxLayout = new BoxLayout(panel , BoxLayout.Y_AXIS);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        for (T t: list) {
            JPanel panel1 = new JPanel();
            panel1.setBackground(DEFAULT_BACKGROUND_COLOR);
            panel1.add((Component) t);
            panel.add(panel1);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        panel.setBackground(DEFAULT_BACKGROUND_COLOR);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel contentPane = new JPanel();;
        contentPane.add(scrollPane);
        contentPane.setLayout(new GridLayout());
        contentPane.setPreferredSize(new Dimension(500, 400));
        this.setBackground(DEFAULT_BACKGROUND_COLOR);
        this.add(contentPane);
    }
    public ScrollableListView(LinkedList<T> list , Color backGround){

        bg = backGround;
        BoxLayout boxLayout = new BoxLayout(panel , BoxLayout.Y_AXIS);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        for (T t: list) {
            JPanel panel1 = new JPanel();
            panel1.setBackground(backGround);

            panel1.add((Component) t);
            panel.add(panel1);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        panel.setBackground(DEFAULT_BACKGROUND_COLOR);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel contentPane = new JPanel();;
        contentPane.add(scrollPane);
        contentPane.setLayout(new GridLayout());
        contentPane.setPreferredSize(new Dimension(500, 400));
        this.setBackground(DEFAULT_BACKGROUND_COLOR);
        this.add(contentPane);
    }
    public void update(LinkedList<T> views){
        panel.removeAll();
        for (T tweet: views) {
            JPanel panel1 = new JPanel();
            panel1.setBackground(bg);

            panel1.add((Component) tweet);
            panel.add(panel1);
        }
    }


    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }
}
