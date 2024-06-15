package service;

import controller.AccountController;
import controller.UserController;
import model.AccountModel;
import view.Dashboard;
import view.modules.DepositUI;

import java.util.ArrayList;

public class Deposit {
    UserController userController = new UserController();
    AccountController accountController = new AccountController();

    public void depositMoney(int depositorId, int accountNumber, int amount) {
        String depositorName = userController.getUserById(depositorId).getName();
        AccountModel account = accountController.getAccountByAccountNumber(accountNumber);

        int newAccountBalance = account.getAccountBalance() + amount;
        accountController.updateAccount(accountNumber, newAccountBalance, new ArrayList<>());

        String recipientName = userController.getUserById(account.getUserId()).getName();
        System.out.println(depositorName + ", you have successfully deposited N" + amount + " to " + recipientName + "-(" + (accountNumber) +")");

        Dashboard.show(DepositUI.user);
    }
}