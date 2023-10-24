package project.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.Deserializer;
import gson.Serializer;
import project.listener.RequestSender;
import request.LogoutRequest;
import request.Request;
import response.Response;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketRequestSender implements RequestSender {
    private final Socket socket;
    private String authToken;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketRequestSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .create();
    }

    @Override
    public Response send(Request request) {
        request.setAuthToken(authToken);
        String eventString = gson.toJson(request, Request.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        System.out.println(responseString);
        Response response = gson.fromJson(responseString, Response.class);
        if (authToken == null)
        authToken = response.getAuthToken();
        System.out.println(authToken);
        if ((request instanceof LogoutRequest))
            authToken = null;
        return response;
    }



    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
