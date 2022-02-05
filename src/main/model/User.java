package model;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;


    // EFFECTS: constructs a user with username,
    //          email address and password, his/her list of warehouses
    //          is initially set to be 0
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }




    // getter
    public String getName() {
        return name;
    }

    // getter
    public String getEmail() {
        return email;
    }

    // getter
    public String getPassword() {
        return password;
    }

}
