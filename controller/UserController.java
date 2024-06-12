package controller;

import Utils.ErrorHandler;
import model.UserModel;

import java.io.*;
import java.util.ArrayList;

public class UserController {
    private final ArrayList<UserModel> users = new ArrayList<>();

    public UserController() {
        loadUsersFromDatabase();
    }

    public void addUser(UserModel user) {
        persistUser(user);
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public UserModel getUserById(int id) {
        for (UserModel user : users) {
            if (user.getId() == id) {
                return new UserModel(user.getName(), user.getId(), user.getPin());
            }
        }
        return null;
    }

    private void persistUser(UserModel user) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("database/users/users.txt", true));
            bw.write(user.toString());
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.err.println("An error occurred while trying to write user to the file: " + e.getMessage());
        }
    }

    private void loadUsersFromDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader("database/users/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                UserModel user = parseUserFromDatabase(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel authenticateUser(int id, int pin) {
        for (UserModel user : users) {
            if (user.getId() == id && user.getPin() == pin) {
                return new UserModel(user.getName(), user.getId(), user.getPin());
            }
        }
        return null;
    }

    private UserModel parseUserFromDatabase(String line) {
        try {
            if (line == null || line.isEmpty()) {
                return null;
            }

            line = line.replace("UserModel{", "").replace("}", "");
            String[] parts = line.split(", ");

            String name = parts[0].split("=")[1].replace("'", "");
            int id = Integer.parseInt(parts[1].split("=")[1]);
            int pin = Integer.parseInt(parts[2].split("=")[1]);

            return new UserModel(name, id, pin);
        } catch (Exception e) {
            ErrorHandler.parseDataFromDatabase(1, e);
            return null;
        }
    }
}