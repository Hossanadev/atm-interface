package service;

import controller.AccountController;
import model.UserModel;

import java.util.ArrayList;

public class Withdraw {
    public static void withdrawMoney(int current_account_balance, int parsedAmount, UserModel user, AccountController accountController) {
        int new_balance = current_account_balance - parsedAmount;
        accountController.updateAccount(accountController.getAccountByUserId(user.getId()).getAccountNumber(), new_balance, new ArrayList<>());

        System.out.println("-- Withdraw Successful! your new balance is: " + new_balance + " --");
    }
}
