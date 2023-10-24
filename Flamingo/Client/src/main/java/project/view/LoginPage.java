package project.view;


import models.SignInForm;
import project.listener.RequestListener;
import project.tools.WarningLabel;
import request.SignInRequest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {
    JTextField usernameField = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);

    JButton loginBtn = new JButton("login");

    private WarningLabel error = new WarningLabel("incorrect username/password");
    private RequestListener requestListener;

    public LoginPage(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder("login");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);
        error.setVisible(false);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 0.1;

        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(passwordField, gc);

        /////////////// 3
        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;


        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(error, gc);

        /////////////// 4
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(loginBtn, gc);
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (loginBtn == (JButton) e.getSource()) {
            requestListener.listen(new SignInRequest(new SignInForm(getUsernameField(),
                    getPasswordField())));

        }
    }
    public void setError(){
        error.setVisible(true);
    }

}

