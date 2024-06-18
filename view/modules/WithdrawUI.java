package view.modules;

import controller.AccountController;
import model.UserModel;
import service.Withdraw;
import view.Dashboard;

import java.util.Scanner;

public class WithdrawUI {
    static public UserModel user;
    static AccountController accountController = new AccountController();

    public static void show() {
        System.out.println("-- Withdraw Money --");

        System.out.println("Enter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        String amount = sc.nextLine();

        int parsedAmount = 0;

        try {
            parsedAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.err.println("-- Invalid amount entered! --");
            WithdrawUI.show();
        }

        if (parsedAmount <= 0) {
            System.err.println("-- Invalid amount entered! --");
            WithdrawUI.show();
        }

        int current_account_balance = accountController.getAccountByUserId(user.getId()).getAccountBalance();
        if (current_account_balance < parsedAmount) {
            System.err.println("-- Insufficient funds! your account balance is: " + current_account_balance + " --");
            WithdrawUI.show();
        }

        Withdraw.withdrawMoney(current_account_balance, parsedAmount, user, accountController);

        Dashboard.show(user);
    }
}