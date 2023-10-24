package project.view;

import models.Commons;
import models.TweetDemo;
import models.UserDemo;
import project.listener.RequestListener;
import project.listener.StringListener;
import project.tools.ImageResizer;
import request.GetComments;
import request.GetUserRequest;
import request.LikeTweetRequest;
import tools.ImageConvertor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TweetsView extends JPanel implements ActionListener, Commons {
    private JButton commentsBtn = new JButton("Comments");
    private JButton reTweetBtn = new JButton("ReTweet");
    private JButton deleteBtn = new JButton("Delete");
    private JButton saveTweetBtn = new JButton("save tweet");
    private JButton forwardBtn = new JButton("forward");
    private JButton reportBtn = new JButton("report");

    private JCheckBox likeCheck = new JCheckBox("Like");
    private JLabel image = new JLabel();
    private JLabel time = new JLabel("time");
    private JTextArea tweetsBody = new JTextArea(5, 22);
    private JLabel writer = new JLabel();

    private int tweetId;

    private StringListener stringListener;

    private RequestListener requestlistener;

    public TweetsView(RequestListener listener) {
        this.requestlistener = listener;
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "tweet", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 12), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        this.setBackground(TWEETS_BACKGROUND);

        likeCheck.setBackground(TWEETS_BACKGROUND);
        likeCheck.setFocusable(false);
        likeCheck.setIcon(DISLIKE);
        likeCheck.setSelectedIcon(LIKE);

        likeCheck.addActionListener(this);


        commentsBtn.setBackground(TWEETS_BACKGROUND);
        commentsBtn.setBorderPainted(false);
        commentsBtn.setFocusable(false);
        commentsBtn.addActionListener(this);

        deleteBtn.setBackground(TWEETS_BACKGROUND);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusable(false);
        deleteBtn.setEnabled(false);
        deleteBtn.setVisible(false);

        reTweetBtn.setBackground(TWEETS_BACKGROUND);
        reTweetBtn.setBorderPainted(false);
        reTweetBtn.setFocusable(false);
        reTweetBtn.addActionListener(this);

        saveTweetBtn.setBackground(TWEETS_BACKGROUND);
        saveTweetBtn.setBorderPainted(false);
        saveTweetBtn.setFocusable(false);

        forwardBtn.setBackground(TWEETS_BACKGROUND);
        forwardBtn.setBorderPainted(false);
        forwardBtn.setFocusable(false);

        reportBtn.setBackground(TWEETS_BACKGROUND);
        reportBtn.setBorderPainted(false);
        reportBtn.setFocusable(false);
        reportBtn.setVisible(false);
        reportBtn.setEnabled(false);
        reportBtn.addActionListener(this);


        tweetsBody.setEditable(false);


        writer.setHorizontalTextPosition(JLabel.RIGHT);
        writer.setVerticalTextPosition(JLabel.CENTER);


        gc.weightx = 1;
        gc.weighty = 0.1;
        /////////////// 0

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        this.add(writer, gc);
////////////////////1
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(image, gc);


        /////////////// 2
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.anchor = GridBagConstraints.LINE_END;

        tweetsBody.setWrapStyleWord(true);
        tweetsBody.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(tweetsBody);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(time, gc);


        /////////////// 3
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(5, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        this.add(likeCheck, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        this.add(commentsBtn, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        this.add(deleteBtn, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        this.add(reTweetBtn, gc);

        ///////////////////////////
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        this.add(saveTweetBtn, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        this.add(forwardBtn, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        this.add(reportBtn, gc);


    }

    public void initialize(TweetDemo tweet) throws IOException {
        this.tweetId = tweet.getTweetId();
        likeCheck.setText(String.valueOf(tweet.getLikes().size()));
        likeCheck.setSelected(tweet.isLikedByOwner());
        setLikes(tweet.getLikes().size());

        if (tweet.isMyTweet()) {
            deleteBtn.addActionListener(this);
            deleteBtn.setEnabled(true);
            deleteBtn.setVisible(true);
        }

        time.setText(tweet.getTime());

        tweetsBody.setText(tweet.getContent());

        if (tweet.getEncodedImage() != null)
            setImage(ImageConvertor.toBufferedImage(tweet.getEncodedImage()));

        writer.setText(tweet.getWriterUsername());
        writer.setIcon(ImageResizer.reSizeImage(SMALL_PROFILE_PICTURE_DIMENSION.width,
                SMALL_PROFILE_PICTURE_DIMENSION.height,
                ImageConvertor.toBufferedImage(tweet.getEncodedProfilePhoto())));

        if (tweet.isMyTweet()) {
            reportBtn.setVisible(true);
            reportBtn.setEnabled(true);
        }

        writer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestlistener.listen(new GetUserRequest(tweet.getWriterUsername()));
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == commentsBtn) {
            requestlistener.listen(new GetComments(tweetId));
        }
        if (e.getSource() == reTweetBtn) {
            stringListener.stringEventOccurred("reTweet");
        }
        if (e.getSource() == deleteBtn) {
            stringListener.stringEventOccurred("delete");
        }
        if (e.getSource() == reportBtn) {
            stringListener.stringEventOccurred("report");
        }
        if (e.getSource() == likeCheck) {

            if (likeCheck.isSelected()) {
                like(true);
                likeCheck.setSelected(true);
                likeCheck.setText(String.valueOf(Integer.parseInt(likeCheck.getText()) + 1));
            } else if (!likeCheck.isSelected()) {
                like(false);
                likeCheck.setSelected(false);
                likeCheck.setText(String.valueOf(Integer.parseInt(likeCheck.getText()) - 1));
            }
        }
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    public void setImage(BufferedImage bi) {
        image.setIcon(ImageResizer.reSizeImage(200, 200, bi));
    }

    public void like(boolean liked) {
        requestlistener.listen(new LikeTweetRequest(tweetId, liked));
    }


    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setLikes(int likes) {
        likeCheck.setText(String.valueOf(likes));
    }


    public int getTweetId() {
        return tweetId;
    }
}


