package project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import models.Commons;
import project.listener.RequestListener;
import request.LogoutRequest;
import tools.Loop;

public class MainFrame extends JFrame implements Commons {

    public static MainFrame instance = null;
    private Loop loop;
    private RequestListener requestListener;

    public MainFrame(RequestListener requestListener)  {
        super(TITLE);
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(APP_ICON.getImage());
        this.setVisible(true);

        instance = this;

        loop = new Loop(FPS,this::update);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
//                Controller.logger.info("user loggedOut");
                requestListener.listen(new LogoutRequest(true));
            }
        });



    }

    public  void refreshFrame(){
//        SwingUtilities.updateComponentTreeUI(instance);
        repaint();
        revalidate();
    }


    public void logout(){
//        AuthController.updateCurrentUserLastSeen();
////        Controller.logger.info("user loggedOut");
//        this.dispose();
//        Project.Main.mainFrame = new MainFrame();
    }
    public void update() {
        this.repaint();
        this.revalidate();
    }



}
