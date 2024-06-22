package model;

public class AccountModel {
    private int userId, accountNumber, accountBalance;
    String transactionHistory = "";

    public AccountModel(int userId, int accountNumber, int accountBalance, String transactionHistory) {
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

    public String getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(String transactionHistory) {
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