package project.listener;


import models.UserDemo;
import project.view.SearchingPage;
import project.view.UserPanel;
import request.GetUserRequest;

public class SearchListener {
    private SearchingPage searchView;
    private RequestListener requestListener;
    private UserDemo foundUser;

    public SearchListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }

    public void searchUser(String username){
        requestListener.listen(new GetUserRequest(username));
    }





}
