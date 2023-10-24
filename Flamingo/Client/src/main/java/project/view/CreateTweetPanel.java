package project.view;

import models.Commons;
import models.TweetDemo;
import org.apache.commons.io.FilenameUtils;
import project.listener.RequestListener;
import project.listener.StringListener;
import project.tools.ImageFilter;
import project.tools.ImageResizer;
import request.CreatTweetRequest;
import tools.ImageConvertor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class CreateTweetPanel extends JPanel implements ActionListener, Commons {

    private JButton choosePhotoBtn = new JButton("choose image");
    private JButton tweetBtn = new JButton("Tweet");
    private JLabel photoLabel = new JLabel(NO_IMAGE_ICON);
    private JTextArea body = new JTextArea(5, 22);
    private String photoPath = "";
    private StringListener stringListener;
    private RequestListener requestListener;


    public CreateTweetPanel(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "New Tweet", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 20), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        this.setBackground(Commons.TWEETS_BACKGROUND);

        tweetBtn.setBackground(Color.CYAN);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 0.1;
        ///////////////1
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("Tweet's text"), gc);

        gc.gridy = 0;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        body.setLineWrap(true);
        body.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(body);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, gc);

        //////////////2
        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(choosePhotoBtn, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(5, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(photoLabel, gc);
        //////////////////3
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 8;
        gc.insets = new Insets(10, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(tweetBtn, gc);
        tweetBtn.setFocusable(false);
        tweetBtn.addActionListener(this);

        choosePhotoBtn.setFocusable(false);
        choosePhotoBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choosePhotoBtn) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
            int ans = fileChooser.showOpenDialog(this);
            if (ans == JFileChooser.APPROVE_OPTION) {
                photoPath = fileChooser.getSelectedFile().getPath();

                try {
                    BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
                    bi = ImageIO.read(new File(photoPath));

                    photoLabel.setIcon(ImageResizer.reSizeImage(300, 300, bi));
                    revalidate();
                    repaint();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
        if (e.getSource() == tweetBtn) {
            if (!body.getText().isEmpty()) {
                File imageFile = photoPath.equals("") ? null: new File(photoPath) ;
                String encodedImage = "";
                try {
                    if (imageFile!= null)
                   encodedImage = ImageConvertor.toString(ImageIO.read(imageFile),FilenameUtils.getExtension(imageFile.getPath()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                    requestListener.listen(new CreatTweetRequest(new TweetDemo(null,
                            -1,
                            -1,
                            "",
                            body.getText(),
                           encodedImage,
                            "",
                            "",
                            false,
                            false)));

                JOptionPane.showMessageDialog(null, "Done :)", "error", JOptionPane.INFORMATION_MESSAGE, CHECK_IMAGE_ICON);
                stringListener.stringEventOccurred("newTweet");

            }
            else {
                JOptionPane.showMessageDialog(null, "empty tweet", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getBody() {
        return body.getText();
    }


    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}