package project.view;

import models.CommentDemo;
import models.Commons;
import models.TweetDemo;
import project.listener.RequestListener;
import project.listener.StringListener;
import request.GetComments;
import request.NewCommentRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class CommentsPage extends JPanel implements ActionListener, Commons {

    private LinkedList<CommentView> comments = new LinkedList<>();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private ScrollableListView<CommentView> scrollableListView;
    private JButton newCommentBtn = new JButton("new comment");
    private int commentId;
    private int parentId;

    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    private RequestListener requestListener;

    public CommentsPage(RequestListener requestListener , int parentId) {
        this.requestListener = requestListener;
        this.parentId = parentId;

        scrollableListView = new ScrollableListView<>(comments);
        this.setLayout(new BorderLayout());
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        newCommentBtn.addActionListener(this);
        newCommentBtn.setFocusable(false);


        centerPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        bottomPanel.setBackground(DEFAULT_BACKGROUND_COLOR);

        bottomPanel.setPreferredSize(new Dimension(0, 100));
        bottomPanel.add(newCommentBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() == newCommentBtn) {
                newComment();
            }

    }


    public void initialize(LinkedList<CommentDemo> comments) throws IOException {
        centerPanel.removeAll();
        LinkedList<CommentView> views = new LinkedList<>();
        for (CommentDemo commentDemo:comments) {
            CommentView tweetsView = new CommentView(requestListener);
            tweetsView.initialize(commentDemo);
            views.add(tweetsView);
        }
        this.comments = views;
        centerPanel.add(scrollableListView);
        scrollableListView.update(views);
        revalidate();
        repaint();
    }

    public void newComment() {
        JTextArea commment = new JTextArea(2, 22);
        JScrollPane scrollPane = new JScrollPane(commment);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        int ans = JOptionPane.showConfirmDialog(null, scrollPane,
                "Add Comment", JOptionPane.OK_CANCEL_OPTION);

        if (ans == JOptionPane.OK_OPTION) {
            if (!commment.getText().isEmpty()) {
                try {
                    requestListener.listen(new NewCommentRequest(new CommentDemo(
                            null,
                            -1,
                            commentId,
                            parentId,
                            "",
                            commment.getText(),
                            "",
                            "",
                            false)));
                    requestListener.listen(new GetComments(parentId));
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else JOptionPane.showMessageDialog(null,
                    "empty comment",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

}
