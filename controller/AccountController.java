package controller;

import Utils.ErrorHandler;
import model.AccountModel;
import model.UserModel;

import java.io.*;
import java.util.ArrayList;

public class AccountController {
    ArrayList<AccountModel> accounts = new ArrayList<>();

    public AccountController() {
        loadAccountsFromDatabase();
    }

    public void createAccount(AccountModel account) {
        persistAccount(account);
    }

    public AccountModel getAccountByUserId(int userId) {
        for (AccountModel account : accounts) {
            if (account.getUserId() == userId) {
                return new AccountModel(account.getUserId(), account.getAccountNumber(),
                        account.getAccountBalance(), account.getTransactionHistory());
            }
        }
       return null;
    }

    public ArrayList<AccountModel> getAccounts() {
        return accounts;
    }

    public AccountModel getAccountByAccountNumber(int accountNumber) {
        for (AccountModel account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public UserModel getUserByAccountNumber(int accountNumber) {
        ArrayList<UserModel> users = new UserController().getUsers();
        AccountModel account = getAccountByAccountNumber(accountNumber);
        for (UserModel user : users) {
            if (user.getId() == account.getUserId()) {
                return user;
            }
        }
        return null;
    }

    public String getTransactionHistory(int userId) {
        for (AccountModel account : accounts) {
            if (account.getUserId() == userId) {
                return account.getTransactionHistory();
            }
        }
        return "";
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

    public void updateAccount(int accountNumber, int newAccountBalance, String transactionHistory) {
        for (AccountModel account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setAccountBalance(newAccountBalance);
                account.setTransactionHistory(transactionHistory);
                persistAccountUpdate(account);
            }
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

    public void persistAccountUpdate(AccountModel updatedAccount) {
        try {
            File inputFile = new File("database/accounts/accounts.txt");
            File tempFile = new File("database/accounts/accounts_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                AccountModel account = parseAccountFromDatabase(currentLine);
                if (account != null && account.getAccountNumber() == updatedAccount.getAccountNumber()) {
                    writer.write(updatedAccount.toString());
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.err.println("Could not delete the original file");
            }
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename the temporary file");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while trying to update the account in the file: " + e.getMessage());
        }
    }

    private AccountModel parseAccountFromDatabase(String line) {
        try {
            line = line.replace("AccountModel{", "").replace("}", "");
            String[] parts = line.split(", ");

            int user_id = Integer.parseInt(parts[0].split("=")[1].replace("'", ""));
            int account_number = Integer.parseInt(parts[1].split("=")[1]);
            int account_balance = Integer.parseInt(parts[2].split("=")[1]);
            String transaction_history = parts[3].split("=")[1];

            return new AccountModel(user_id, account_number, account_balance, transaction_history);
        } catch (Exception e) {
            ErrorHandler.parseDataFromDatabase(1, e);
            return null;
        }
    }
}