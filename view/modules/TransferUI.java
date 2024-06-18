package view.modules;

import controller.AccountController;
import model.AccountModel;
import model.UserModel;
import service.Transfer;
import view.Dashboard;

import java.util.Scanner;

public class TransferUI {
    public static UserModel user;
    static AccountController accountController = new AccountController();

    public static void show() {
        System.out.println("Enter the account number you want to transfer to: ");
        Scanner sc = new Scanner(System.in);
        String account_number = sc.nextLine();

        System.out.println("Enter the amount you want to transfer: ");
        String amount = sc.nextLine();

        int parsedAccountNumber = 0, parsedAmount = 0;

        try {
            parsedAccountNumber = Integer.parseInt(account_number);
            parsedAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.err.println("-- Invalid account number or amount! --");
        }

        AccountModel receivers_account = accountController.getAccountByAccountNumber(parsedAccountNumber);
        if (receivers_account == null) {
            System.err.println("-- Account not found! --");
            TransferUI.show();
        }

        int user_account_balance = accountController.getAccountByUserId(user.getId()).getAccountBalance();
        if (user_account_balance < parsedAmount) {
            System.err.println("-- Insufficient balance, your current balance is: " + user_account_balance + " --");
            TransferUI.show();
        }

        assert receivers_account != null;
        Transfer.transferMoney(accountController, user, receivers_account, user_account_balance, parsedAmount);

        Dashboard.show(user);
    }
}
