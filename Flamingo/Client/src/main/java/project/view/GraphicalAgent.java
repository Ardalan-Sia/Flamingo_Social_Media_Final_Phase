package project.view;

import models.*;
import project.listener.RequestListener;
import project.listener.StringListener;
import request.*;
import response.FollowRequests;
import tools.Loop;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class GraphicalAgent {

    private final RequestListener listener;
    private  MainFrame frame;
    private  MainPanel mainPanel;
    private Stack<JPanel> stack ;
    private JPanel currentPage;
    private Loop loop;

    public GraphicalAgent(RequestListener listener) {
        this.listener = listener;
    }

    public void initialize() {
        if (frame!=null) {
            frame.dispose();
            System.out.println(frame!=null);
        }
        this.frame = new MainFrame(listener);
        this.mainPanel = new MainPanel();
        frame.setVisible(true);
        stack = new Stack<>();

        launch();
    }

    public void launch() {
        mainPanel.initialize(this::toolBarListener);
        frame.add(mainPanel);
        frame.pack();
        System.out.println("launched");
    }

    public void toolBarListener(String command) {
        switch (command) {
            case "homePage" -> {
                stack.clear();
                gotoHomePage();
            }
            case "back" -> {
                if (stack.size() <= 1)
                    break;
                stack.pop();
                currentPage = stack.peek();
                mainPanel.setCenterPanel(currentPage);
                mainPanel.repaint();
                mainPanel.revalidate();
                System.out.println("back");

            }
            case "registration" -> {
                gotoRegistrationPage();

            }
            case "login" -> {
                gotoLoginPage();
            }
        }
    }


    public void gotoRegistrationPage() {
        System.out.println("register");
        RegistrationPage panel = new RegistrationPage(listener);
        mainPanel.setCenterPanel(panel);
        currentPage = panel;

    }

    public void gotoLoginPage() {
        LoginPage panel = new LoginPage(listener);
        mainPanel.setCenterPanel(panel);
        currentPage = panel;
    }

    public void gotoHomePage() {
        mainPanel.getCenterPage().setLayout(new BorderLayout());
        mainPanel.getToolBar().updateToolbar();
        HomePage homePage = new HomePage();
        stack.push(homePage);
        currentPage = homePage;
        mainPanel.setCenterPanel(homePage);
        homePage.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                switch (string) {
                    case "settings" -> {
                        gotoSettingsPage();
                    }
                    case "personalPage" -> {
                        gotoPersonalPage();
                    }
                    case "explorer" -> {
                        gotoExplorerPage();
                    }case "timeLine"->{
                        listener.listen(new GotoTimeLine());
                    }
                }
            }
        });
    }

    public void gotoSettingsPage() {
        SettingsPage settingsPage = new SettingsPage();
        stack.push(settingsPage);
        currentPage = settingsPage;
        mainPanel.setCenterPanel(settingsPage);
        settingsPage.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                switch (string) {
                    case "logout" -> {
                        listener.listen(new LogoutRequest(false));
                    }
                    case "deleteAccount" -> {

                    }
                    case "privacy" -> {
                        System.out.println("privacy");
                        listener.listen(new GotoPrivacyPageRequest());
                    }
                }
            }
        });
    }

    public void gotoPrivacy(LastSeenType lastSeenType, boolean isPrivate, String password) {
        if (!(currentPage instanceof PrivacyPage)) {
            PrivacyPage privacyPage = new PrivacyPage(listener);
            stack.push(privacyPage);
            currentPage = privacyPage;
            mainPanel.setCenterPanel(privacyPage);
        }
        PrivacyPage privacyPage = (PrivacyPage) currentPage;
        privacyPage.initialize(lastSeenType, isPrivate, password);
    }

    public void gotoPersonalPage() {
        PersonalPage personalPage = new PersonalPage();
        stack.push(personalPage);
        currentPage = personalPage;
        mainPanel.setCenterPanel(personalPage);
        personalPage.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                switch (string) {
                    case "info" -> listener.listen(new GotoInfoPageRequest());
                    case "newTweet"->listener.listen(new GoToMyTweets());
                    case "notifications"->gotoNotificationPage();
                }
            }
        });
    }

    public void gotoInfoPage(SignUpForm form) throws IOException {
        if (!(currentPage instanceof PrivacyPage)) {
            InfoPage infoPage = new InfoPage(listener);
            stack.push(infoPage);
            currentPage = infoPage;
            mainPanel.setCenterPanel(infoPage);
        }
        InfoPage infoPage = (InfoPage) currentPage;
        infoPage.initialize(form);
    }

    public void gotoExplorerPage() {
        ExplorerPage explorerPage = new ExplorerPage();
        stack.push(explorerPage);
        currentPage = explorerPage;
        mainPanel.setCenterPanel(explorerPage);
        explorerPage.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                switch (string) {
                    case "search"->{
                        gotoUserSearchingPanel();
                    }
                    case "explore"->{
                        listener.listen(new GotoExplorer());
                    }

                }
            }
        });
    }

    public void gotoExploreTweets(LinkedList<TweetDemo> list) throws IOException {
        if (!(currentPage instanceof ExplorePage)) {
            ExplorePage explorePage = new ExplorePage(listener);
            stack.push(explorePage);
            currentPage = explorePage;
            mainPanel.setCenterPanel(explorePage);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateExplorer);
            loop.stop();;
        }
        ExplorePage explorePage = (ExplorePage) currentPage;
        explorePage.initialize(list);
    }
    public void gotoTimeLine(LinkedList<TweetDemo> list) throws IOException{
        if (!(currentPage instanceof TimeLinePage)) {
            TimeLinePage timeLinePage = new TimeLinePage(listener);
            stack.push(timeLinePage);
            currentPage = timeLinePage;
            mainPanel.setCenterPanel(timeLinePage);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateTimeLine);
            loop.stop();
        }
        TimeLinePage timeLinePage = (TimeLinePage) currentPage;
        timeLinePage.initialize(list);
    }

    public void gotoUserPanel(UserDemo demo) throws IOException {
        if (!(currentPage instanceof TimeLinePage)) {
            UserPanel userPanel = new UserPanel(listener);
            stack.push(userPanel);
            currentPage = userPanel;
            mainPanel.setCenterPanel(userPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateUserPanel);
            loop.stop();
        }
        UserPanel userPanel = (UserPanel) currentPage;
        userPanel.initialize(demo);
    }

    public void gotoMyTweets(LinkedList<TweetDemo> list) throws IOException {

        if (!(currentPage instanceof MyTweets)) {
            MyTweets myTweets = new MyTweets(listener);
            stack.push(myTweets);
            currentPage = myTweets;
            mainPanel.setCenterPanel(myTweets);
            System.out.println("MyTweet");
        }
        MyTweets myTweets = (MyTweets)currentPage;
        myTweets.initialize(list);
    }

    public void gotoComments(LinkedList<CommentDemo> list , int parentId) throws IOException {
        if (!(currentPage instanceof CommentsPage)) {
            CommentsPage commentsPage = new CommentsPage(listener, parentId);
            stack.push(commentsPage);
            currentPage = commentsPage;
            mainPanel.setCenterPanel(commentsPage);
        }
        CommentsPage commentsPage = (CommentsPage) currentPage;
        commentsPage.initialize(list);
    }

    public void gotoUserSearchingPanel(){
        if (!(currentPage instanceof SearchingPage)) {
            SearchingPage searchingPage = new SearchingPage(listener);
            stack.push(searchingPage);
            currentPage = searchingPage;
            mainPanel.setCenterPanel(searchingPage);
            updateUserPanel();
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1 , this::updateUserPanel);
            loop.start();
        }
        SearchingPage searchingPage = (SearchingPage) currentPage;

    }

    public void gotoNotificationPage(){
            NotificationsPanel notificationsPanel = new NotificationsPanel();
            stack.push(notificationsPanel);
            currentPage = notificationsPanel;
            mainPanel.setCenterPanel(notificationsPanel);
        notificationsPanel.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                switch (string){
                    case "requests"->{listener.listen(new GetRequests());}
                    case "systemNotifications"->{listener.listen(new GetSystemNotifications());}
                    case "sentRequests"->{listener.listen(new GetSentRequests());}
                }
            }
        });
    }

    public void gotoRequestsPage(LinkedList<UserDemo> demos){
            if (!(currentPage instanceof SearchingPage)) {
                NotificationListingPanel listingPanel = new NotificationListingPanel(listener);
                stack.push(listingPanel);
                currentPage = listingPanel;
                mainPanel.setCenterPanel(listingPanel);
                if (loop!=null)
                    loop.stop();
                loop = new Loop(0.1,this::updateRequests);
                loop.stop();
            }
        NotificationListingPanel listingPanel = (NotificationListingPanel) currentPage;
        listingPanel.addRequests(demos);
    }

    public void gotoSentRequestsPage(LinkedHashMap<String ,SentRequestStatue > sentRequests){
        if (!(currentPage instanceof SearchingPage)) {
            NotificationListingPanel listingPanel = new NotificationListingPanel(listener);
            stack.push(listingPanel);
            currentPage = listingPanel;
            mainPanel.setCenterPanel(listingPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateSentNotif);
            loop.stop();
        }
        NotificationListingPanel listingPanel = (NotificationListingPanel) currentPage;
        System.out.println(sentRequests.size());
        listingPanel.addSentRequest(sentRequests);
    }

    public void gotoSystemNotificationsPage(LinkedHashSet<SystemNotification> systemNotifications){
        if (!(currentPage instanceof SearchingPage)) {
            NotificationListingPanel listingPanel = new NotificationListingPanel(listener);
            stack.push(listingPanel);
            currentPage = listingPanel;
            mainPanel.setCenterPanel(listingPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateSystemNotif);
            loop.stop();
        }
        System.out.println(systemNotifications.size());
        NotificationListingPanel listingPanel = (NotificationListingPanel) currentPage;
        listingPanel.addSystemNotifications(systemNotifications);
    }

    public JPanel getCurrentPage() {
        return currentPage;
    }

    public void updateUserPanel(){
        if (currentPage instanceof SearchingPage){
            if (((SearchingPage)currentPage).getUsername() != null){
                System.out.println();
                listener.listen(new GetUserRequest(((SearchingPage)currentPage).getUsername()));
            }
        }

    }

    public void gotoFollowings(LinkedList<UserDemo> demos) throws IOException {
        if (!(currentPage instanceof FollowersPanel)) {
            FollowersPanel listingPanel = new FollowersPanel(listener);
            stack.push(listingPanel);
            currentPage = listingPanel;
            mainPanel.setCenterPanel(listingPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateFollowings);
            loop.stop();
        }
        FollowersPanel listingPanel = (FollowersPanel) currentPage;
        listingPanel.setFollowers(demos);

    }
    public void gotoBlackList(LinkedList<UserDemo> demos) throws IOException {
        if (!(currentPage instanceof FollowersPanel)) {
            FollowersPanel listingPanel = new FollowersPanel(listener);
            stack.push(listingPanel);
            currentPage = listingPanel;
            mainPanel.setCenterPanel(listingPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateBlackList);
            loop.stop();
        }
        FollowersPanel listingPanel = (FollowersPanel) currentPage;
        listingPanel.setFollowers(demos);

    }
    public void gotoFollowers(LinkedList<UserDemo> demos) throws IOException {
        if (!(currentPage instanceof FollowersPanel)) {
            FollowersPanel listingPanel = new FollowersPanel(listener);
            stack.push(listingPanel);
            currentPage = listingPanel;
            mainPanel.setCenterPanel(listingPanel);
            if (loop!=null)
                loop.stop();
            loop = new Loop(0.1,this::updateFollowers);
            loop.stop();
        }
        FollowersPanel listingPanel = (FollowersPanel) currentPage;
        listingPanel.setFollowers(demos);

    }
    public void updateFollowers(){
        listener.listen(new GetFollowers());
    }
    public void updateFollowings(){
        listener.listen(new GetFollowings());
    }
    public void updateBlackList(){
        listener.listen(new GetBlackList());
    }


    public void updateTimeLine(){
        listener.listen(new GotoTimeLine());
    }
    public void updateExplorer(){
        listener.listen(new GotoExplorer());
    }
    public void updateRequests(){
        listener.listen(new GetRequests());
    }
    public void updateSentNotif(){
        listener.listen(new GetSentRequests());
    }
    public void updateSystemNotif(){
        listener.listen(new GetSystemNotifications());
    }



}
