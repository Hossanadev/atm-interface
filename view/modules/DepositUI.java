package view.modules;

import controller.AccountController;
import model.UserModel;
import service.Deposit;

import java.util.Scanner;

public class DepositUI {
    static public UserModel user;
    static AccountController accountController = new AccountController();

    public static void show() {
        System.out.println("-- DEPOSIT MONEY --");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number you want to deposit into: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter amount you want to deposit: ");
        String  amount = sc.nextLine();

        int parsedAccountNumber = 0;
        int parsedAmount = 0;

        try {
            parsedAccountNumber = Integer.parseInt(accountNumber);
            parsedAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.err.println("-- Account number and amount must be valid! --");
            DepositUI.show();
        }

        if (accountController.getAccountByAccountNumber(parsedAccountNumber) == null) {
            System.err.println("-- Account not found! --");
            DepositUI.show();
        }

        new Deposit().depositMoney(user.getId(), parsedAccountNumber, parsedAmount);
    }
}
