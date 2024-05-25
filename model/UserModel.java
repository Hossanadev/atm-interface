package model;

public class UserModel {
    private String name;
    private int id, pin;

    public UserModel(String name, int id, int pin) {
        this.name = name;
        this.id = id;
        this.pin = pin;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
