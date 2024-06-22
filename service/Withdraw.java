package service;

import Utils.GetCurrentDateTime;
import controller.AccountController;
import model.UserModel;

public class Withdraw {
    public static void withdrawMoney(int current_account_balance, int parsedAmount, UserModel user, AccountController accountController) {
        int new_balance = current_account_balance - parsedAmount;

        boolean isNotificationHistoryEmpty = (accountController.getTransactionHistory(user.getId()).equalsIgnoreCase("") || !(accountController.getTransactionHistory(user.getId()).length() > 2));
        String notification = isNotificationHistoryEmpty ? GetCurrentDateTime.get() + " ~ You withdrew N" + parsedAmount :
                accountController.getTransactionHistory(user.getId()).concat(","+ GetCurrentDateTime.get() +" ~ You withdrew N" + parsedAmount);
        accountController.updateAccount(accountController.getAccountByUserId(user.getId()).getAccountNumber(), new_balance, notification);

        System.out.println("-- Withdraw Successful! your new balance is: " + new_balance + " --");
    }
}