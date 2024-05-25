package controller;

import model.UserModel;

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