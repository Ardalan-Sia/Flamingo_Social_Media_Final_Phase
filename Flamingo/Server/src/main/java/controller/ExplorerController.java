package controller;

import model.User;

import java.util.LinkedList;

public class ExplorerController extends Controller{

    public ExplorerController() {

    }

    public LinkedList<Integer> explorerTweets(User owner){
        LinkedList<Integer> list = new LinkedList<>();
        for (User user : context.Users.all()) {
            if (!owner.getBlackList().contains(user.getId())){
                for (Integer id: user.getMyTweets()) {
                    if (!context.Tweets.get(id).isNotAllowed())
                    list.add(id);
                }
            }
        }
        return list;
    }

}
