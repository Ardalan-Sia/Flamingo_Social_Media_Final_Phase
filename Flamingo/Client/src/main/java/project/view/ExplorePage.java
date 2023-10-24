package project.view;

import project.listener.RequestListener;


import java.awt.*;

public class ExplorePage extends MyTweets{

    public ExplorePage(RequestListener requestListener) {
        super(requestListener);
        scrollableListView = new ScrollableListView<>(tweets);

        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
    }
}
