package controller;


import db.Context;


import model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Controller {
    public Context context;


    public Controller(){

        context = new Context();

    }
    public volatile  Logger logger = LogManager.getLogger(Controller.class);

}