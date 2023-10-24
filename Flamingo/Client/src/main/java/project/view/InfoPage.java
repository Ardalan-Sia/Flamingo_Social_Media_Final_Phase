package project.view;


import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import models.Commons;
import models.SignUpForm;
import org.apache.commons.io.FilenameUtils;
import project.listener.RequestListener;
import project.listener.StringListener;
import project.tools.ImageFilter;
import project.tools.ImageResizer;
import project.tools.JNumberTextField;
import project.tools.WarningLabel;
import request.EditInfo;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class InfoPage extends JPanel implements ActionListener, Commons {
    private JPanel panel = new JPanel();
    private JTextField nameField = new JTextField(10);
    private JTextField usernameFiled = new JTextField(10);
    private JTextField emailField = new JTextField(10);
    private JNumberTextField phoneNumberField = new JNumberTextField(10);
    private JTextArea bioField = new JTextArea(5, 10);
    private JDateChooser birthDay = new JDateChooser();
    private JLabel photoLabel = new JLabel();
    private File imageFile;

    private static BufferedImage profileImage;


    WarningLabel nameRequiredW = new WarningLabel("required field!");
    WarningLabel emailRequiredW = new WarningLabel("required field!");
    WarningLabel duplicateEmailW = new WarningLabel("this email has already been taken");
    WarningLabel duplicatePhoneNumberW = new WarningLabel("this phone number has already been taken");
    WarningLabel usernameRequiredW = new WarningLabel("required field!");
    WarningLabel duplicateUsernameW = new WarningLabel("this username has already been taken");


    private JButton saveBtn = new JButton("save");
    private JButton choosePhotoBtn = new JButton("choose photo");


    private StringListener stringListener;
    private RequestListener requestListener;

    public InfoPage(RequestListener requestListener) {
        this.requestListener = requestListener;
        photoLabel.setPreferredSize(LARGE_PROFILE_PICTURE_DIMENSION);
        photoLabel.setOpaque(true);
        photoLabel.setBackground(Color.BLACK);

        nameRequiredW.setVisible(false);
        usernameRequiredW.setVisible(false);
        emailRequiredW.setVisible(false);
        duplicatePhoneNumberW.setVisible(false);
        duplicateEmailW.setVisible(false);
        duplicateUsernameW.setVisible(false);


        panel.setBackground(REGISTRATION_BACKGROUND_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder("Info form");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        saveBtn.setFocusable(false);
        choosePhotoBtn.setFocusable(false);


        gc.weightx = 1;
        gc.weighty = 0.1;
        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);

        gc.anchor = GridBagConstraints.LINE_END;

        panel.add(new JLabel("<html><font color=red>*</font> name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameRequiredW, gc);
        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);

        gc.anchor = GridBagConstraints.LINE_END;

        panel.add(new JLabel("<html><font color=red>*</font> username: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameFiled, gc);
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameRequiredW, gc);
        panel.add(duplicateUsernameW, gc);


        /////////////// 3
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("<html><font color=red>*</font> email: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emailField, gc);
        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emailRequiredW, gc);
        panel.add(duplicateEmailW, gc);

        /////////////// 4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("birthday: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        birthDay.setPreferredSize(new Dimension(100, 20));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) birthDay.getDateEditor();
        editor.setEditable(false);
        panel.add(birthDay, gc);

        /////////////// 5
        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("phone number: "), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(phoneNumberField, gc);
        gc.gridx = 2;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        panel.add(duplicatePhoneNumberW, gc);
        ////////////////6
        gc.gridx = 0;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("bio: "), gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(bioField);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll, gc);
        ////////////////7
        gc.gridx = 0;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(choosePhotoBtn, gc);

        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);


        panel.add(photoLabel, gc);
        ///////////////8
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(saveBtn, gc);

        saveBtn.addActionListener(this);
        choosePhotoBtn.addActionListener(this);

        this.add(panel);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveBtn) {
            resetWarningLabels();
            try {
                String format = "";
                if (imageFile != null)
                    format = FilenameUtils.getExtension(imageFile.getPath());
                requestListener.listen(new EditInfo(new SignUpForm(getNameField(),
                        getUsernameFiled(),
                        getEmailField(),
                        "",
                        "",
                        getBirthDay(),
                        getPhoneNumberField(),
                        getBioField(),
                        ImageConvertor.toString(profileImage , format))));

            } catch (Exception exception) {
                if (exception.getMessage().equals("nameRequired"))
                    nameRequiredW.setVisible(true);
                if (exception.getMessage().equals("duplicateUsername"))
                    duplicateUsernameW.setVisible(true);
                if (exception.getMessage().equals("usernameRequired"))
                    usernameRequiredW.setVisible(true);
                if (exception.getMessage().equals("emailRequired"))
                    emailRequiredW.setVisible(true);
                if (exception.getMessage().equals("duplicateEmail"))
                    duplicateEmailW.setVisible(true);
                if (exception.getMessage().equals("duplicatePhoneNumber"))
                    duplicatePhoneNumberW.setVisible(true);
                exception.printStackTrace();
            }


        }
        if (e.getSource() == choosePhotoBtn) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
            int ans = fileChooser.showOpenDialog(this);

            if (ans == JFileChooser.APPROVE_OPTION) {
                imageFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
                    bi = ImageIO.read(imageFile);
                    profileImage = bi;
                    photoLabel.setIcon(new ImageIcon(bi));
                    revalidate();
                    repaint();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }


    public void setError(String exception){
        if (exception.equals("nameRequired"))
            nameRequiredW.setVisible(true);
        if (exception.equals("duplicateUsername"))
            duplicateUsernameW.setVisible(true);
        if (exception.equals("usernameRequired"))
            usernameRequiredW.setVisible(true);
        if (exception.equals("emailRequired"))
            emailRequiredW.setVisible(true);
        if (exception.equals("duplicateEmail"))
            duplicateEmailW.setVisible(true);
        if (exception.equals("duplicatePhoneNumber"))
            duplicatePhoneNumberW.setVisible(true);
    }
    public void initialize(SignUpForm form) throws IOException {
        nameField.setText(form.getName());
        usernameFiled.setText(form.getUsername());
        birthDay.setDate(form.getBirthDay());
        phoneNumberField.setText(form.getPhoneNumber());
        bioField.setText(form.getBio());
        emailField.setText(form.getEmail());
        setPhoto(ImageConvertor.toBufferedImage(form.getEncodedImage()));
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

    public void setName(String name) {
        this.nameField.setText(name);
    }

    public void setUsername(String username) {
        this.usernameFiled.setText(username);
    }

    public void setEmail(String email) {
        this.emailField.setText(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberField.setText(phoneNumber);
    }

    public void setBio(String bio) {
        this.bioField.setText(bio);
    }

    public void setBirthDay(Date birthDay) {
            if (birthDay != null)
            this.birthDay.setDate(birthDay);

    }

    public void setPhoto(BufferedImage photo) {
        this.photoLabel.setIcon(null);
        ImageResizer.reSizeImage(10,10,photo);
        this.photoLabel.setIcon(ImageResizer.reSizeImage(LARGE_PROFILE_PICTURE_DIMENSION.width,
                LARGE_PROFILE_PICTURE_DIMENSION.height
                ,photo));
    }

    private void resetWarningLabels() {
        nameRequiredW.setVisible(false);
        usernameRequiredW.setVisible(false);
        emailRequiredW.setVisible(false);
        duplicatePhoneNumberW.setVisible(false);
        duplicateUsernameW.setVisible(false);
        duplicateEmailW.setVisible(false);
    }

//    public void setFormListener(EventListener eventListener) {
//        this.eventListener = eventListener;
//    }
    public void setStringListener(StringListener stringListener){

        this.stringListener = stringListener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE_ICON_No3.getImage(),0,0,null);


    }
}