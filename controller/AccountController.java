package controller;

import Utils.ErrorHandler;
import model.AccountModel;

import java.io.*;
import java.util.ArrayList;

public class AccountController {
    ArrayList<AccountModel> accounts = new ArrayList<>();


    public void createAccount(AccountModel account) {
        persistAccount(account);
    }

    public AccountModel getAccountByUserId(int userId) {
        AccountModel userAccount = null;
        for (AccountModel account : accounts) {
            if (account.getUserId() == userId) {
                userAccount = new AccountModel(account.getUserId(), account.getAccountNumber(),
                        account.getAccountBalance(), account.getTransactionHistory());
            }
        }
       return userAccount;
    }

    public ArrayList<AccountModel> getAccounts() {
        loadAccountsFromDatabase();
        return accounts;
    }

    public void loadAccountsFromDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader("database/accounts/accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                AccountModel account = parseAccountFromDatabase(line);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistAccount(AccountModel account) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("database/accounts/accounts.txt", true));
            bw.write(account.toString());
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.err.println("An error occurred while trying to write account to the file: " + e.getMessage());
        }
    }

    private AccountModel parseAccountFromDatabase(String line) {
        try {
            line = line.replace("AccountModel{", "").replace("}", "");
            String[] parts = line.split(", ");

            int user_id = Integer.parseInt(parts[0].split("=")[1].replace("'", ""));
            int account_number = Integer.parseInt(parts[1].split("=")[1]);
            int account_balance = Integer.parseInt(parts[2].split("=")[1]);
            ArrayList<String> transaction_history = new ArrayList<>();
            for (int i = 0; i < parts.length - 3; i++) {
                transaction_history.add(parts[i + 3].split("=")[1]);
            }
            return new AccountModel(user_id, account_number, account_balance, transaction_history);
        } catch (Exception e) {
            ErrorHandler.parseDataFromDatabase(1, e);
            return null;
        }
    }
}
