package project.view;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import models.Commons;
import models.SignUpForm;
import project.listener.RequestListener;
import project.tools.JNumberTextField;
import project.tools.WarningLabel;
import request.SignUpRequest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RegistrationPage extends JPanel implements ActionListener, Commons {
    private JTextField nameField = new JTextField(10);
    private JTextField usernameFiled = new JTextField(10);
    private JTextField emailField = new JTextField(10);
    private JPasswordField password1Field = new JPasswordField(10);
    private JPasswordField password2Field = new JPasswordField(10);
    private JNumberTextField phoneNumberField = new JNumberTextField(10);
    private JTextArea bioField = new JTextArea(5, 10);
    private JDateChooser birthDay = new JDateChooser();


    WarningLabel nameRequiredW = new WarningLabel("required field!");
    WarningLabel password1RequiredW = new WarningLabel("required field!");
    WarningLabel passwordConfirmW = new WarningLabel("Your password and confirmation password do not match");
    WarningLabel password2RequiredW = new WarningLabel("required field!");
    WarningLabel emailRequiredW = new WarningLabel("required field!");
    WarningLabel duplicateEmailW = new WarningLabel("this email has already been taken");
    WarningLabel duplicatePhoneNumberW = new WarningLabel("this phone number has already been taken");
    WarningLabel usernameRequiredW = new WarningLabel("required field!");
    WarningLabel duplicateUsernameW = new WarningLabel("this username has already been taken");


    private JButton registerBtn = new JButton("register");

    private RequestListener requestListener;

    public RegistrationPage(RequestListener requestListener) {
        this.requestListener = requestListener;
        nameRequiredW.setVisible(false);
        usernameRequiredW.setVisible(false);
        password1RequiredW.setVisible(false);
        password2RequiredW.setVisible(false);
        emailRequiredW.setVisible(false);
        passwordConfirmW.setVisible(false);
        duplicatePhoneNumberW.setVisible(false);
        duplicateEmailW.setVisible(false);
        duplicateUsernameW.setVisible(false);


        this.setBackground(REGISTRATION_BACKGROUND_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder("Registration form");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        registerBtn.setFocusable(false);


        gc.weightx = 1;
        gc.weighty = 0.1;
        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(new JLabel("<html><font color=red>*</font> name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(nameField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(nameRequiredW, gc);
        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(new JLabel("<html><font color=red>*</font> username: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameFiled, gc);
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameRequiredW, gc);
        this.add(duplicateUsernameW, gc);


        /////////////// 3
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red>*</font> email: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(emailField, gc);
        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(emailRequiredW, gc);
        this.add(duplicateEmailW, gc);

        /////////////// 4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red>*</font> password: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password1Field, gc);
        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password1RequiredW, gc);

        /////////////// 5
        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red>*</font> confirm password: "), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password2Field, gc);
        gc.gridx = 2;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password2RequiredW, gc);
        this.add(passwordConfirmW, gc);
        /////////////// 6
        gc.gridx = 0;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("birthday: "), gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        birthDay.setPreferredSize(new Dimension(100, 20));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) birthDay.getDateEditor();
        editor.setEditable(false);
        this.add(birthDay, gc);

        /////////////// 7
        gc.gridx = 0;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("phone number: "), gc);

        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(phoneNumberField, gc);
        gc.gridx = 2;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        this.add(duplicatePhoneNumberW, gc);
        ////////////////8
        gc.gridx = 0;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("bio: "), gc);

        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(bioField);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scroll, gc);
        ////////////////9
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 8;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(registerBtn, gc);

        registerBtn.addActionListener(this);

    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getUsernameFiled() {
        return usernameFiled.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getPassword1Field() {
        return password1Field.getText();
    }

    public String getPassword2Field() {
        return password2Field.getText();
    }

    public Date getBirthDay() {
        try {
            return birthDay.getDate();
        } catch (NullPointerException e) {
            return null;
        }

    }

    public String getPhoneNumberField() {
        return phoneNumberField.getText();
    }

    public String getBioField() {
        return bioField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (registerBtn == (JButton) e.getSource()) {
            resetWarningLabels();

             requestListener.listen(new SignUpRequest(new SignUpForm(getNameField(),
                     getUsernameFiled(),
                     getEmailField(),
                     getPassword1Field(),
                     getPassword2Field(),
                     getBirthDay(),
                     getPhoneNumberField(),
                     getBioField(),
                     null)));


        }
    }

    public void setError(String exception){
        if (exception.equals("nameRequired"))
            nameRequiredW.setVisible(true);
        if (exception.equals("duplicateUsername"))
            duplicateUsernameW.setVisible(true);
        if (exception.equals("usernameRequired"))
            usernameRequiredW.setVisible(true);
        if (exception.equals("password1Required"))
            password1RequiredW.setVisible(true);
        if (exception.equals("password2Required"))
            password2RequiredW.setVisible(true);
        if (exception.equals("emailRequired"))
            emailRequiredW.setVisible(true);
        if (exception.equals("duplicateEmail"))
            duplicateEmailW.setVisible(true);
        if (exception.equals("duplicatePhoneNumber"))
            duplicatePhoneNumberW.setVisible(true);
        if (exception.equals("passwordConfirm"))
            passwordConfirmW.setVisible(true);

    }
    private void resetWarningLabels() {
        nameRequiredW.setVisible(false);
        usernameRequiredW.setVisible(false);
        emailRequiredW.setVisible(false);
        password1RequiredW.setVisible(false);
        password2RequiredW.setVisible(false);
        duplicatePhoneNumberW.setVisible(false);
        passwordConfirmW.setVisible(false);
        duplicateUsernameW.setVisible(false);
        duplicateEmailW.setVisible(false);
    }

}