package project.controller;


import models.*;
import project.listener.RequestSender;
import request.Request;
import response.Response;
import response.ResponseVisitor;
import tools.Loop;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import project.view.*;


public class MainController implements ResponseVisitor {


    private RequestSender requestSender;
    private final List<Request> requests;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;

    public MainController(RequestSender requestSender) {
        this.requestSender = requestSender;
        this.requests = new LinkedList<>();
        this.loop = new Loop(10, this::sendRequest);
        this.graphicalAgent = new GraphicalAgent(this::addRequest);
    }


    private void addRequest(Request request) {
        synchronized (requests) {
            requests.add(request);
        }
    }


    private void sendRequest() {
        List<Request> temp;
        synchronized (requests) {
            temp = new LinkedList<>(requests);
            requests.clear();
        }
        for (Request request : temp) {
            Response response = requestSender.send(request);
            try {

                response.visit(this);
            } catch (NullPointerException e) {

            }

        }
    }

    public void start() {
        loop.start();
        graphicalAgent.initialize();
//        graphicalAgent.launch();

    }

    @Override
    public void signIn(boolean success, String message) {
        if (!(graphicalAgent.getCurrentPage() instanceof LoginPage))
            return;
        if (success)
            graphicalAgent.gotoHomePage();
        else {
            ((LoginPage) graphicalAgent.getCurrentPage()).setError();
        }
    }

    @Override
    public void signUp(boolean success, String message) {
        if (!(graphicalAgent.getCurrentPage() instanceof RegistrationPage))
            return;
        if (success)
            graphicalAgent.gotoHomePage();
        else {
            ((RegistrationPage) graphicalAgent.getCurrentPage()).setError(message);
        }
    }

    @Override
    public void privacyInfo(LastSeenType lastSeenType, boolean isPrivate, String password) {
        graphicalAgent.gotoPrivacy(lastSeenType, isPrivate, password);
    }

    @Override
    public void personalInfo(SignUpForm form) {
        try {
            graphicalAgent.gotoInfoPage(form);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editInfo(boolean success, String error) {
        if (!success)
            ((InfoPage) (graphicalAgent.getCurrentPage())).setError(error);

    }

    @Override
    public void myTweetsList(LinkedList<TweetDemo> list) {

        try {
            System.out.println("My");
            graphicalAgent.gotoMyTweets(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tweetComments(LinkedList<CommentDemo> list, int parentId) {
        try {
            graphicalAgent.gotoComments(list, parentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userInfo(UserDemo userDemo) {
        if (graphicalAgent.getCurrentPage() instanceof SearchingPage){
            try {
                ((SearchingPage)(graphicalAgent.getCurrentPage())).setUserPanel(userDemo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            try {
                graphicalAgent.gotoUserPanel(userDemo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void showMessage(String s) {
        switch (s){
            case "userNotFound"->{
                if (graphicalAgent.getCurrentPage() instanceof SearchingPage)
                    ((SearchingPage)(graphicalAgent.getCurrentPage())).userNotFound();
            }
        }
    }

    @Override
    public void logout() {
        System.out.println("Logout");
        graphicalAgent.initialize();
    }

    @Override
    public void timeLineTweets(LinkedList<TweetDemo> linkedList) {
        try {
            graphicalAgent.gotoTimeLine(linkedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void explorerTweets(LinkedList<TweetDemo> linkedList) {
        try {
            graphicalAgent.gotoExploreTweets(linkedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void systemNotifications(LinkedHashSet<SystemNotification> linkedHashSet) {
        graphicalAgent.gotoSystemNotificationsPage(linkedHashSet);
    }

    @Override
    public void sentNotifications(LinkedHashMap<String, SentRequestStatue> linkedHashMap) {
        graphicalAgent.gotoSentRequestsPage(linkedHashMap);
    }

    @Override
    public void requests(LinkedList<UserDemo> linkedList) {
        graphicalAgent.gotoRequestsPage(linkedList);
    }

    @Override
    public void followings(LinkedList<UserDemo> linkedList) {
        try {
            graphicalAgent.gotoFollowings(linkedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void followers(LinkedList<UserDemo> linkedList) {
        try {
            graphicalAgent.gotoFollowers(linkedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void blackList(LinkedList<UserDemo> linkedList) {
        try {
            graphicalAgent.gotoBlackList(linkedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
