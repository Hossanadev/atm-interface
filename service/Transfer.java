package service;

import controller.AccountController;
import model.AccountModel;
import model.UserModel;

import java.util.ArrayList;

public class Transfer {

    public static void transferMoney(AccountController accountController, UserModel user, AccountModel receivers_account, int user_account_balance, int parsedAmount) {
        int  receivers_new_accountBalance = receivers_account.getAccountBalance() + parsedAmount;
        accountController.updateAccount(receivers_account.getAccountNumber(), receivers_new_accountBalance, new ArrayList<>());

        int senders_new_accountBalance = user_account_balance - parsedAmount;
        accountController.updateAccount(accountController.getAccountByUserId(user.getId()).getAccountNumber(), senders_new_accountBalance, new ArrayList<>());

        System.out.println("-- Transfer successful! " + user.getName() + ", you transferred " + parsedAmount + " to " + accountController.getUserByAccountNumber(receivers_account.getAccountNumber()).getName() + "! --");
        System.out.println("-- New account balance: " + accountController.getAccountByUserId(user.getId()).getAccountBalance() + "!");
    }
}
