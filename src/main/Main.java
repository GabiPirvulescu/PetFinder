package main;

import controller.HomePageController;
import controller.LoginController;
import view.HomePageView;
import view.LoginView;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {

        System.out.println("Hello world!");
        LoginController controller = new LoginController();
        LoginView view=new LoginView(controller);
        controller.setLoginView(view);

        view.setVisible(true);
//        HomePageController controller=new HomePageController();
//        HomePageView view=new HomePageView(controller);
    }
}