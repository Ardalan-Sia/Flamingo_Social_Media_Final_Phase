package project.view;

import models.Commons;
import models.TweetDemo;
import project.listener.RequestListener;
import project.listener.StringListener;
import request.GetComments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class MyTweets extends JPanel implements ActionListener, Commons {

    protected LinkedList<TweetsView> tweets = new LinkedList<>();
    protected JPanel centerPanel = new JPanel();
    protected ScrollableListView<TweetsView> scrollableListView;
    private JPanel bottomPanel = new JPanel();
    private JButton newTweetBtn = new JButton("new tweet");
    private LinkedList<StringListener> stringListener = new LinkedList<>();
    private RequestListener requestListener;

    public MyTweets(RequestListener requestListener) {
        this.requestListener = requestListener;


        scrollableListView = new ScrollableListView<>(tweets);

        this.setLayout(new BorderLayout());
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);


        centerPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        bottomPanel.setBackground(DEFAULT_BACKGROUND_COLOR);

        newTweetBtn.addActionListener(this);
        newTweetBtn.setFocusable(false);

        bottomPanel.setPreferredSize(new Dimension(0, 100));
        bottomPanel.add(newTweetBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newTweetBtn) {
            for (StringListener s : stringListener) {
                s.stringEventOccurred("newTweet");
            }
            newTweet();
        }
    }

    private void newTweet() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        CreateTweetPanel createTweetPanel = new CreateTweetPanel(requestListener);


        frame.setVisible(true);
        frame.add(createTweetPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        createTweetPanel.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                if (string.equals("newTweet")) {
                    frame.dispose();
                }
            }
        });

    }

    public LinkedList<TweetsView> getTweets() {
        return tweets;
    }

    public void initialize(LinkedList<TweetDemo> tweetDemos) throws IOException {
        centerPanel.removeAll();
        LinkedList<TweetsView> views = new LinkedList<>();
        for (TweetDemo tweetDemo:tweetDemos) {
            TweetsView tweetsView = new TweetsView(requestListener);
            tweetsView.initialize(tweetDemo);
            views.add(tweetsView);
        }
        this.tweets = views;
        centerPanel.add(scrollableListView);
        scrollableListView.update(views);
        revalidate();
        repaint();
    }


    public void addStringListener(StringListener stringListener) {
        this.stringListener.add(stringListener);
    }
}
