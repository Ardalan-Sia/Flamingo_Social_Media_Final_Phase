package controller;

import controller.network.SocketResponseSender;
import db.Context;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager extends Thread{

    public void run() {
        try {
             ServerSocket serverSocket = new ServerSocket(5050);
             Context context = new Context();
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(new SocketResponseSender(socket), context);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

