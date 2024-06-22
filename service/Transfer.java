package service;

import Utils.GetCurrentDateTime;
import controller.AccountController;
import model.AccountModel;
import model.UserModel;

public class Transfer {

    public static void transferMoney(AccountController accountController, UserModel user, AccountModel receivers_account, int user_account_balance, int parsedAmount) {
        int receivers_new_accountBalance = receivers_account.getAccountBalance() + parsedAmount;
        int senders_new_accountBalance = user_account_balance - parsedAmount;
        UserModel receiver_user = accountController.getUserByAccountNumber(receivers_account.getAccountNumber());

        boolean isReceiversNotificationEmpty = (accountController.getTransactionHistory(receiver_user.getId()).equalsIgnoreCase("") || !(accountController.getTransactionHistory(receiver_user.getId()).length() > 2));;
        String receivers_notification = isReceiversNotificationEmpty ? GetCurrentDateTime.get() + " ~ " + user.getName() + " transferred N" + parsedAmount + " to you":
                accountController.getTransactionHistory(receiver_user.getId()).concat("," + GetCurrentDateTime.get() + " ~ " + user.getName() + " transferred N" + parsedAmount + " to you");
        accountController.updateAccount(receivers_account.getAccountNumber(), receivers_new_accountBalance, receivers_notification);

        boolean isNotificationHistoryEmpty = (accountController.getTransactionHistory(user.getId()).equalsIgnoreCase("") || !(accountController.getTransactionHistory(user.getId()).length() > 2));
        String notification = isNotificationHistoryEmpty ? GetCurrentDateTime.get() + " ~ You transferred N" + parsedAmount + " to " + receiver_user.getName() :
                accountController.getTransactionHistory(user.getId()).concat("," + GetCurrentDateTime.get() + " ~ You transferred N" + parsedAmount + " to " + receiver_user.getName());
        accountController.updateAccount(accountController.getAccountByUserId(user.getId()).getAccountNumber(), senders_new_accountBalance, notification);

        System.out.println("-- Transfer successful! " + user.getName() + ", you transferred " + parsedAmount + " to " + receiver_user.getName() + "! --");
        System.out.println("-- New account balance: " + accountController.getAccountByUserId(user.getId()).getAccountBalance() + "!");
    }
}