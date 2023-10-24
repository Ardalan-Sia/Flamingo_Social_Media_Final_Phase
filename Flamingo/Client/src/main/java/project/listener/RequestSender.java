package project.listener;


import request.Request;
import response.Response;

public interface RequestSender {
    Response send(Request request);

    void close();
}
