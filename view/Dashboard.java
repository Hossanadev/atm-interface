package view;

import Utils.GreetingHandler;
import model.UserModel;
import view.auth.LandingPage;
import view.modules.DepositUI;
import view.modules.TransactionHistoryUI;
import view.modules.TransferUI;
import view.modules.WithdrawUI;

import java.util.Scanner;

public class Dashboard {
    static Scanner sc = new Scanner(System.in);

    public static void show(UserModel user) {
        System.out.println("Hi " + user.getName() + ", " + GreetingHandler.getGreeting());
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
                TransactionHistoryUI.user = new UserModel(user.getName(), user.getId(), user.getPin());
                TransactionHistoryUI.show();
            case "2":
                WithdrawUI.user = new UserModel(user.getName(), user.getId(), user.getPin());
                WithdrawUI.show();
                break;
            case "3":
                DepositUI.user = new UserModel(user.getName(), user.getId(), user.getPin());
                DepositUI.show();
                break;
            case "4":
                TransferUI.user = new UserModel(user.getName(), user.getId(), user.getPin());
                TransferUI.show();
                break;
            case "5":
                LandingPage.show();
                break;
            default: {
                System.out.println("ERROR! -- Invalid option!");
                Dashboard.show(user);
            }
        }
    }
}