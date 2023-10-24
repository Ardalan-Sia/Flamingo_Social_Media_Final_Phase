package project.view;

import models.Commons;
import models.LastSeenType;
import project.listener.RequestListener;
import project.listener.StringListener;
import request.EditPrivacyRequest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PrivacyPage extends JPanel implements ActionListener, Commons {
    //    private UpdateSettingsView updateSettingsView;
    private JButton changePasswordBtn = new JButton("change password");
    private JCheckBox privateCheckBox = new JCheckBox("Private");
    private JComboBox lastSeenBox;
    private JPanel changePassPanel = new JPanel();
    private JPanel lastSeenPanel = new JPanel();
    private JPanel privateCheckPanel = new JPanel();
    private StringListener stringListener;
    //    private SettingsListener settingsListener;
    public static int LAST_SEEN_NOBODY = 0;
    public static int LAST_SEEN_EVERYONE = 1;
    public static int LAST_SEEN_FOLLOWINGS = 2;
    private RequestListener requestListener;
    private LastSeenType lastSeenType;
    private boolean isPrivate;
    private String password;


    public PrivacyPage(RequestListener requestListener) {
        this.requestListener = requestListener;


        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Privacy", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(40, 40, 40, 40);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        GridBagConstraints gc = new GridBagConstraints();

        String[] lastSeenTypes = {"NOBODY", "EVERYONE", "FOLLOWINGS"};

        gc.weightx = 1;
        gc.weighty = 0.1;
        lastSeenBox = new JComboBox(lastSeenTypes);

        lastSeenPanel.add(new JLabel());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;

        JLabel lastSeenLabel = new JLabel("last seen");
        lastSeenLabel.setFont(new Font(null, Font.PLAIN, 25));
        lastSeenPanel.add(lastSeenLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        lastSeenPanel.add(lastSeenBox, gc);

        this.setLayout(new FlowLayout());

        lastSeenBox.setFont(new Font(null, Font.PLAIN, 10));

        lastSeenBox.setBackground(Color.cyan);
        lastSeenBox.setFocusable(false);
        lastSeenPanel.setPreferredSize(new Dimension(210, 110));
        lastSeenBox.addActionListener(this);

        privateCheckBox.setFont(new Font("", Font.PLAIN, 50));
        privateCheckBox.setFocusable(false);
        privateCheckBox.setIcon(CROSS_IMAGE_ICON);
        privateCheckBox.setSelectedIcon(CHECK_IMAGE_ICON);
        privateCheckBox.addActionListener(this);

        privateCheckPanel.add(privateCheckBox);
        privateCheckBox.setPreferredSize(new Dimension(250, 100));
        privateCheckBox.setForeground(Color.cyan);

        changePassPanel.setPreferredSize(new Dimension(210, 110));
        changePasswordBtn.setPreferredSize(LARGE_BTN_DIMENSION);
        changePasswordBtn.setBackground(Color.cyan);
        changePasswordBtn.addActionListener(this);
        changePassPanel.add(changePasswordBtn);

        this.add(lastSeenPanel);
        this.add(privateCheckPanel);
        this.add(changePassPanel);
    }

    public void initialize(LastSeenType lastSeenType, boolean isPrivate, String password) {
        this.lastSeenType = lastSeenType;
        this.isPrivate = isPrivate;
        this.password = password;
        this.getPrivateCheckBox().setSelected(isPrivate);
        setLastSeen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No2.getImage(), 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == changePasswordBtn) {
            if(changePassword())
                requestListener.listen(new EditPrivacyRequest(lastSeenType, isPrivate, password));

            System.out.println(changePassPanel);
        }

        if (e.getSource() == lastSeenBox) {
            switch (Objects.requireNonNull(lastSeenBox.getSelectedItem()).toString()) {
                case "NOBODY" -> {
                    if (lastSeenType == LastSeenType.NOBODY)
                        break;
                    lastSeenType = LastSeenType.NOBODY;
                    requestListener.listen(new EditPrivacyRequest(lastSeenType, isPrivate, password));

                }
                case "EVERYONE" -> {
                    if (lastSeenType == LastSeenType.EVERYONE)
                        break;
                    lastSeenType = LastSeenType.EVERYONE;
                    requestListener.listen(new EditPrivacyRequest(lastSeenType, isPrivate, password));

                }
                case "FOLLOWINGS" -> {
                    if (lastSeenType == LastSeenType.FOLLOWINGS)
                        break;
                    lastSeenType = LastSeenType.FOLLOWINGS;
                    requestListener.listen(new EditPrivacyRequest(lastSeenType, isPrivate, password));

                }
            }
        }

        if (e.getSource() == privateCheckBox) {
            isPrivate = privateCheckBox.isSelected();
            requestListener.listen(new EditPrivacyRequest(lastSeenType, isPrivate, password));

        }
    }


    public void setLastSeen() {
        switch (lastSeenType) {
            case NOBODY -> lastSeenBox.setSelectedIndex(0);
            case EVERYONE -> lastSeenBox.setSelectedIndex(1);
            case FOLLOWINGS -> lastSeenBox.setSelectedIndex(2);
        }
    }

    public void setStringListener(StringListener stringListener) {

        this.stringListener = stringListener;

    }

    public JCheckBox getPrivateCheckBox() {
        return privateCheckBox;
    }

    public boolean changePassword() {
        JPasswordField oldPass = new JPasswordField();
        JPasswordField pass1 = new JPasswordField();
        JPasswordField pass2 = new JPasswordField();

        oldPass.setPreferredSize(new Dimension(150, 20));
        pass1.setPreferredSize(new Dimension(150, 20));
        pass2.setPreferredSize(new Dimension(150, 20));

        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 0.1;
        panel.add(new JLabel());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;

        panel.add(new JLabel("old password: "), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(oldPass, gc);
        /////////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;

        panel.add(new JLabel("new password: "), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(pass1, gc);
        //////////////////////
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;

        panel.add(new JLabel("confirm new password: "), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(pass2, gc);
        ///////////////////////
        int ans = JOptionPane.showConfirmDialog(null,
                panel,
                "JOptionPane Example : ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (ans == JOptionPane.OK_OPTION) {
            if (!pass1.getText().equals(pass2.getText())) {
                JOptionPane.showMessageDialog(null, "confirm failed", "", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (oldPass.getText().equals(password)) {
                password = pass2.getText();
                return true;


            } else {
                JOptionPane.showMessageDialog(null, "incorrect password!", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else return false;
    }
}
