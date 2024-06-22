package view.modules;

import controller.AccountController;
import model.UserModel;
import view.Dashboard;

public class TransactionHistoryUI {
    public static UserModel user;
    static AccountController accountController = new AccountController();

    public static void show() {
        System.out.println("-- Transaction History --");
        if (accountController.getTransactionHistory(user.getId()).isEmpty() || !(accountController.getTransactionHistory(user.getId()).length() > 2)) {
            System.out.println(user.getName() + ", your transaction history is empty.");
            Dashboard.show(user);
        }

        String[] transactionHistory = accountController.getTransactionHistory(user.getId()).split(",");
        for (String history : transactionHistory) {
            System.out.println(history);
        }

        try {
            Thread.sleep(3000);
            Dashboard.show(user);
        } catch (InterruptedException e) {
            System.err.println("-- Error occurred at Transaction History UI --");
            Dashboard.show(user);
        }
    }
}
