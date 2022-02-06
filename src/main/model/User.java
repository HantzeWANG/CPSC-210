package model;

import java.util.ArrayList;

public class User {
    private String name;   // username
    private String email;
    private String password;
    private ArrayList<Warehouse> warehouses; // a user can have different warehouses


    // EFFECTS: constructs a user with username,
    //          email address and password, his/her list of warehouses
    //          is initially set to be 0
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.warehouses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a warehouse to a user's warehouses list
    public void addWarehouse(String wareHouseName) {
        Warehouse warehouse = new Warehouse(wareHouseName);
        this.warehouses.add(warehouse);
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

    // getter
    public ArrayList<Warehouse> getWareHouses() {
        return this.warehouses;
    }

}
