package view.auth;

import java.util.Scanner;

public class LandingPage {
    private static final String greeting = "-- WELCOME TO HOSSANA ATM INTERFACE --";
    private static final Scanner sc = new Scanner(System.in);

    public static void show() {
        System.out.println(greeting);
        System.out.println("Option: ");
        System.out.println("1 -- Login");
        System.out.println("2 -- Register");
        System.out.println("3 -- Exit");

        switch (sc.nextLine()) {
            case "1":
                LoginService.start();
                break;
            case "2":
                CreateAccount.newAccount();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                LandingPage.show();
        }
    }
}
