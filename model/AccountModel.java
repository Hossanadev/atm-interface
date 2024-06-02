package model;

import java.util.ArrayList;

public class AccountModel {
    private int userId, accountNumber, accountBalance;
    ArrayList<String> transactionHistory;

    public AccountModel(int userId, int accountNumber, int accountBalance, ArrayList<String> transactionHistory) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.transactionHistory = transactionHistory;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "userId=" + userId +
                ", accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}