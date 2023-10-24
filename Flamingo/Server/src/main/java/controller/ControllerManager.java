package controller;

import javax.sound.sampled.Control;

public class ControllerManager {
    private  ClientHandler clientHandler;

    public final UserController userController;
    public final TweetsController tweetsController;
    public final TimeLineController timeLineController;
    public final ExplorerController explorerController;

    public ControllerManager(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        userController = new UserController(clientHandler);
        tweetsController = new TweetsController();
        timeLineController = new TimeLineController(clientHandler);
        explorerController = new ExplorerController();
    }
    public final  AuthController authController = new AuthController();

}
