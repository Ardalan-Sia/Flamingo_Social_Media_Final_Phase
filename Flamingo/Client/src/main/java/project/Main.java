package project;

import project.controller.MainController;
import project.network.SocketRequestSender;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5050);
        MainController controller = new MainController(new SocketRequestSender(socket));
        controller.start();
    }
}
