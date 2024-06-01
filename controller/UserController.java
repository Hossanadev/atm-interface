package controller;

import model.UserModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    private final ArrayList<UserModel> users = new ArrayList<>();

    public UserController() {
        this.users.add(new UserModel("Hossana", 1, 1234));
        this.users.add(new UserModel("Mirabel", 2,9325));
        this.users.add(new UserModel("John", 3, 8016));
    }

    public void addUser(UserModel user) {
        users.add(user);
        persistUser(user);
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public UserModel getUserById(int id) {
        UserModel user_ = null;
        for (UserModel user : users) {
            if (user.getId() == id) {
                user_ = new UserModel(user.getName(), user.getId(), user.getPin());
            }
        }
        return user_;
    }

    private void persistUser(UserModel user) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("database/users/users.txt", true));
            bw.write(user.toString());
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.err.println("An error occurred while trying to write to the file: " + e.getMessage());
        }
    }

    public UserModel authenticateUser(int id, int pin) {
        UserModel user_ = null;
        for (UserModel user : users) {
            if (user.getId() == id && user.getPin() == pin) {
               user_ = new UserModel(user.getName(), user.getId(), user.getPin());
            }
        }
        return user_;
    }
}