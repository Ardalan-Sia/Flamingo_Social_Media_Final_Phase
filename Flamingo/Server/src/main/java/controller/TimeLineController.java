package controller;

import java.util.LinkedList;

public class TimeLineController extends Controller {


    private ClientHandler clientHandler;

    public TimeLineController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public LinkedList<Integer> timeLine() {
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer id : clientHandler.getOwner().getFollowings()) {
            list.addAll(context.Users.get(id).getMyTweets());

        }

        return list;

    }


}
