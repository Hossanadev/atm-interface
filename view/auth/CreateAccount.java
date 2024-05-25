package view.auth;

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
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("Enter account pin: ");
        int pin = Integer.parseInt(sc.nextLine());


        for (UserModel user : users) {
            if (name.equals(user.getName())) {
                System.out.println("-- Error: User with this name already exists, enter unique name --");
                LandingPage.show();
            }
            if (id == user.getId()) {
                System.out.println("-- Error: User with this id already exists, enter unique id --");
                LandingPage.show();
            }
        }

        UserModel newUser = new UserModel(name, id, pin);
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
