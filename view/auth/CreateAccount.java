package view.auth;

import Utils.ErrorHandler;
import controller.UserController;
import model.UserModel;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateAccount {
    private static final Scanner sc = new Scanner(System.in);

    public static void newAccount() {
        UserController userController = new UserController();
        ArrayList<UserModel> users = userController.getUsers();

        System.out.println("-- CREATE ACCOUNT --");

        System.out.println("Enter account name: ");
        String name = sc.nextLine();

        System.out.println("Enter account id: ");
        String id = sc.nextLine();

        System.out.println("Enter account pin: ");
        String pin = sc.nextLine();

        if (name.isEmpty()) {
            ErrorHandler.createAccount(1);
            CreateAccount.newAccount();
        }

        if (id.isEmpty()) {
            ErrorHandler.createAccount(2);
            CreateAccount.newAccount();
        }

        if (pin.isEmpty()) {
            ErrorHandler.createAccount(3);
            CreateAccount.newAccount();
        }

        int parsedId = 0, parsedPin = 0;

        try {
            parsedId = Integer.parseInt(id);
            parsedPin = Integer.parseInt(pin);
        } catch (NumberFormatException e) {
            ErrorHandler.createAccount(4);
            CreateAccount.newAccount();
        }

        for (UserModel user : users) {
            if (name.equalsIgnoreCase(user.getName())) {
                ErrorHandler.createAccount(5);
                LandingPage.show();
            }
            if (parsedId == user.getId()) {
                ErrorHandler.createAccount(6);
                LandingPage.show();
            }
        }

        UserModel newUser = new UserModel(name, parsedId, parsedPin);
        userController.addUser(newUser);
        System.out.println("-- " + name + ", your account is created successfully... 100% --");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            LandingPage.show();
        }
        LandingPage.show();
    }
}