package project.view;

import project.listener.SearchListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchingBar extends JPanel implements ActionListener {

    private JTextField searchField = new JTextField(10);
    private JButton searchBtn = new JButton("Search");
    private SearchListener searchListener;

    public SearchingBar(SearchListener searchListener) {
        this.searchListener = searchListener;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        searchBtn.setFocusable(false);
        searchBtn.addActionListener(this);

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(searchField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(searchBtn, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBtn) {
            System.out.println("search");
            searchListener.searchUser(searchField.getText());
        }
    }
}
