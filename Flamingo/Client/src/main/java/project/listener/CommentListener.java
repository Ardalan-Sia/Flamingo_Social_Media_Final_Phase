//package project.listener;
//import a.controller.Controller;
//import a.controller.TweetController;
//import a.model.Comment;
//import a.view.pagesView.CommentsPage;
//
//public class CommentListener implements StringListener {
//    private TweetController tweetController = new TweetController();
//    private Comment comment;
//    private CommentsPage previousCommentsPage;
//
//    public CommentListener(Comment comment , CommentsPage previousCommentsPage) {
//        this.comment = comment;
//        this.previousCommentsPage = previousCommentsPage;
//    }
//
//    @Override
//    public void stringEventOccurred(String string) {
//        switch (string) {
//            case "like" -> comment.getLikes().add(Controller.getOwner().getId());
//
//            case "disLike" -> comment.getLikes().remove(Integer.valueOf(Controller.getOwner().getId()));
//
//            case "comments" -> {
//                CommentsPageListener<Comment> commentsPageListener = new CommentsPageListener<>(previousCommentsPage, comment);
//                commentsPageListener.stringEventOccurred("comments");
//                CommentsPage commentsPage1 = new CommentsPage(previousCommentsPage.getContainer(), previousCommentsPage.getCardLayout());
//                CommentsPageListener<Comment> commentsPageListener1 = new CommentsPageListener<>(commentsPage1, comment);
//                previousCommentsPage.getContainer().add(commentsPage1);
//                previousCommentsPage.getCardLayout().next(previousCommentsPage.getContainer());
//                commentsPage1.addStringListeners(commentsPageListener1);
//                commentsPage1.setEventListener(commentsPageListener);
//                commentsPageListener1.stringEventOccurred("openPage");
//            }
//        }
//
//    }
//
//
//}
