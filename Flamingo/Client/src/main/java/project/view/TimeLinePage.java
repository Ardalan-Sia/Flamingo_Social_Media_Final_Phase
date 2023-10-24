package project.view;

import project.listener.RequestListener;
import request.Request;

import java.awt.*;

public class TimeLinePage extends MyTweets {


    public TimeLinePage(RequestListener requestListener) {
        super(requestListener);
        scrollableListView = new ScrollableListView<>(tweets);

        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(DEFAULT_BACKGROUND_COLOR);

    }
}
