package project.view;

import models.CommentDemo;
import models.Commons;
import project.listener.RequestListener;
import project.listener.StringListener;
import project.tools.ImageResizer;
import request.LikeCommentRequest;
import tools.ImageConvertor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CommentView extends JPanel implements ActionListener , Commons {

    private JTextArea body = new JTextArea(2,22);
    private JLabel writer = new JLabel();
    private JLabel time  = new JLabel("time");
    private JCheckBox likeCheck =  new JCheckBox();
    private StringListener stringListener;
    private RequestListener requestListener;
    private int parentId;
    private int commentId;

    public CommentView(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createEmptyBorder(5,5,5,5);

        Border outerBoarder = BorderFactory.createLineBorder(Color.darkGray,5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        this.setBackground(COMMENTS_BACKGROUND);

        likeCheck.setBackground(COMMENTS_BACKGROUND);
        likeCheck.setFocusable(false);
        likeCheck.setIcon(DISLIKE);
        likeCheck.setSelectedIcon(LIKE);
        likeCheck.addActionListener(this);
        likeCheck.setForeground(COMMENTS_FOREGROUND);


        body.setEditable(false);
//        body.setForeground(COMMENTS_FOREGROUND);


        writer.setHorizontalTextPosition(JLabel.RIGHT);
        writer.setVerticalTextPosition(JLabel.CENTER);
        writer.setForeground(COMMENTS_FOREGROUND);


        time.setForeground(Color.GRAY);



        gc.weightx = 1;
        gc.weighty = 0.1;
        /////////////// 0

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        this.add(writer, gc);

        ///////////////1

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0,40,0,0);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(body, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0,0,0,0);

        gc.anchor = GridBagConstraints.LINE_END;

        this.add(likeCheck , gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.anchor = GridBagConstraints.LAST_LINE_END;

        this.add(time, gc);
    }

    public void initialize(CommentDemo demo) throws IOException {
        likeCheck.setText(String.valueOf(demo.getLikes().size()));
        likeCheck.setSelected(demo.isLikedByOwner());


        body.setText(demo.getContent());

        writer.setText(demo.getWriterUsername());
        writer.setIcon(ImageResizer.reSizeImage(SMALL_PROFILE_PICTURE_DIMENSION.width,
                SMALL_PROFILE_PICTURE_DIMENSION.height,
                ImageConvertor.toBufferedImage(demo.getEncodedProfilePhoto())));


    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == likeCheck){
            if (likeCheck.isSelected()) {
                like(true);
                likeCheck.setText(String.valueOf(Integer.parseInt(likeCheck.getText()) + 1));
            }
            else if (!likeCheck.isSelected()) {
                like(false);
                likeCheck.setText(String.valueOf(Integer.parseInt(likeCheck.getText()) - 1));

            }
        }
    }

    public void like(boolean liked){
        requestListener.listen(new LikeCommentRequest(parentId,commentId,liked));
    }

}


