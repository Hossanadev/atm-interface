package app;

import controller.UserController;
import view.auth.LandingPage;

public class Main {
    public static void main(String[] args) {
        new UserController().getUsers();
        LandingPage.show();
    }
}