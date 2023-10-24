package controller.network;

import Tools.RandomString;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.Deserializer;
import gson.Serializer;
import request.Request;
import response.LoginResponse;
import response.Response;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketResponseSender implements ResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private String authToken;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Request.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();

    }

    @Override
    public Request getRequest() {
        String eventString = scanner.nextLine();
        Request request = gson.fromJson(eventString, Request.class);
        if (authToken != null && !request.getAuthToken().equals(authToken)) {
            System.out.println(request.getAuthToken());
                throw new Error("unknown authToken");
        }
        return request;
    }


    @Override
    public void sendResponse(Response response) {
        if (response instanceof LoginResponse && ((LoginResponse) response).isSuccess()) {
            System.out.println("Login");
            authToken = new RandomString().nextString();
            response.setAuthToken(authToken);
        }
        printStream.println(gson.toJson(response, Response.class));
    }

    @Override
    public void expireToken() {
        authToken = null;

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
