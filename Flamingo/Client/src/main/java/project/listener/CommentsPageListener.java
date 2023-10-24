//package project.listener;
//
//import project.view.CommentsPage;
//import request.NewCommentRequest;
//
//import java.util.LinkedList;
//
//public class CommentsPageListener<Parent> implements StringListener, EventListener {
//    CommentsPage commentsPage;
//    //    TweetController tweetController = new TweetController();
////    private static Tweet parentTweet;
//    private Parent parent;
//    private RequestListener requestListener;
////    private LinkedList<Comment> comments = new LinkedList<>();
//
//    public CommentsPageListener(RequestListener requestListener) {
//        this.requestListener = requestListener;
//
////        if (parent.getClass().equals(Comment.class)){
////            this.comments = ((Comment) parent).getComments();
////        }else if (parent.getClass().equals(Tweet.class)) {
////            this.comments = ((Tweet) parent).getComments();
////            parentTweet = (Tweet) parent;
//    }
//
//    @Override
//    public void stringEventOccurred(String string) {
//
//        if (string.equals("newComment")) {
//
//
//        }
//    }
//
//
////    private void openPage(){
////        tweetController.context.Tweets.update(parentTweet);
////        LinkedList<CommentView> commentViewsList = new LinkedList<>();
////        for (Comment comment : comments) {
////
////            CommentView commentView = new CommentView(new InitCommentViewEvent(this,
////                    comment.getLikes().contains(Controller.owner.getId())
////                    , comment.getBody()
////                    , comment.getLikes().size()
////                    , tweetController.context.Users.get(comment.getOwnerId())));
////
////            a.listener.CommentListener commentListener = new a.listener.CommentListener(comment , commentsPage);
////            commentListener.stringEventOccurred("openPage");
////            commentView.setStringListener(commentListener);
////            commentViewsList.add(commentView);
////        }
////
////        commentsPage.setComments(commentViewsList);
////    }
//
//    @Override
//    public <T> void eventOccurred(T event) throws Exception {
//        requestListener.listen(new NewCommentRequest())
//    }
//    public <T>void addComment(T t , NewCommentEvent e){
//        if (t.getClass().equals(Comment.class))
//            ((Comment) t).getComments().add(new Comment(e.getBody(), owner.getId()));
//
//        else if (t.getClass().equals(Tweet.class))
//            ((Tweet) t).getComments().add(new Comment(e.getBody() , owner.getId()));
//
//    }
//
//}
