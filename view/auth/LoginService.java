package view.auth;

import Utils.ErrorHandler;
import controller.UserController;
import model.UserModel;
import view.Dashboard;

import java.util.Scanner;

public class LoginService {
    private static final Scanner sc = new Scanner(System.in);

    public static void start() {
        UserController userController = new UserController();
        System.out.println("-- LOGIN WITH USER ID & PIN --");

        System.out.println("Enter your id: ");
        String id = sc.nextLine();

        System.out.println("Enter your pin: ");
        String pin = sc.nextLine();

        if (id.isEmpty() || pin.isEmpty()) {
            ErrorHandler.login(1);
            LoginService.start();
        }

        int parsedId = 0, parsedPin = 0;

        try {
            parsedId = Integer.parseInt(id);
            parsedPin = Integer.parseInt(pin);
        } catch (NumberFormatException e) {
            ErrorHandler.login(3);
            LoginService.start();
        }

        UserModel user = userController.authenticateUser(parsedId, parsedPin);

        if (user != null) {
            System.out.println("-- Login Successful! --");
            Dashboard.show(user);
        } else {
            ErrorHandler.login(2);
            LandingPage.show();
        }
    }
}
