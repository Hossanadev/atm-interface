package view;

import Utils.GreetingHandler;
import view.auth.LandingPage;

import java.util.Scanner;

public class Dashboard {
    static Scanner sc = new Scanner(System.in);

    public static void show(String userName) {
        System.out.println("Hi " + userName + ", " + GreetingHandler.getGreeting());
        System.out.println("App Menu:");
        System.out.println("1: -- Transaction History");
        System.out.println("2: -- Withdraw");
        System.out.println("3: -- Deposit");
        System.out.println("4: -- Transfer");
        System.out.println("5: -- Logout");

        System.out.println("Enter preferred option: ");
        String selectedMenu = sc.nextLine();

        switch (selectedMenu) {
            case "1":
                System.out.println("1. View Transaction History");
                Dashboard.show(userName);
                break;
             case "5":
                 LandingPage.show();
                 break;
            default: {
                System.out.println("ERROR! -- Invalid option!");
                Dashboard.show(userName);
            }
        }
    }
}