package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a user has a name, an email, a password,
// and a list of warehouses he/she has created
public class User implements Writable {
    private final String name;   // username
    private final String email;
    private final String password;
    private final ArrayList<Warehouse> warehouses; // a user can have different warehouses


    // EFFECTS: constructs a user with username,
    //          email address and password, his/her list of warehouses
    //          is initially set to be empty
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.warehouses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a warehouse to a user's warehouses list
    public void addWarehouse(Warehouse wh) {
        this.warehouses.add(wh);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("password", password);
        json.put("warehouses",warehousesToJson());
        return json;
    }

    // EFFECTS: returns warehouses of users as a JSON array
    private JSONArray warehousesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Warehouse w : warehouses) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Warehouse> getWareHouses() {
        return this.warehouses;
    }

}
