package service;

import Utils.GetCurrentDateTime;
import controller.AccountController;
import controller.UserController;
import model.AccountModel;
import model.UserModel;
import view.Dashboard;
import view.modules.DepositUI;

public class Deposit {
    UserController userController = new UserController();
    AccountController accountController = new AccountController();

    public void depositMoney(int depositorId, int accountNumber, int amount) {
        String depositorName = userController.getUserById(depositorId).getName();
        AccountModel receiver_account = accountController.getAccountByAccountNumber(accountNumber);

        UserModel receiver_user = userController.getUserById(receiver_account.getUserId());

        boolean isNotificationHistoryEmpty = (accountController.getTransactionHistory(receiver_user.getId()).equalsIgnoreCase("") || !(accountController.getTransactionHistory(receiver_user.getId()).length() > 2));
        String notification = isNotificationHistoryEmpty ? GetCurrentDateTime.get() + " ~ " + depositorName + " deposited N" + amount + " into your account" :
                accountController.getTransactionHistory(receiver_user.getId()).concat("," + GetCurrentDateTime.get() + " ~ " + depositorName + " deposited N" + amount + " into your account");

        int newAccountBalance = receiver_account.getAccountBalance() + amount;
        accountController.updateAccount(accountNumber, newAccountBalance, notification);

        System.out.println(depositorName + ", you have successfully deposited N" + amount + " to " + receiver_user.getName() + "-(" + (accountNumber) +")");

        Dashboard.show(DepositUI.user);
    }
}