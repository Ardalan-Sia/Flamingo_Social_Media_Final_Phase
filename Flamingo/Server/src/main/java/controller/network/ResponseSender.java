package controller.network;


import request.Request;
import response.Response;

public interface ResponseSender {
    Request getRequest();


    void sendResponse(Response response);
    void expireToken();

    void close();
}
