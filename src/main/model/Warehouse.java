package model;

import exceptions.InvalidNumberException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

// Represents a warehouse that has a name, total cost of goods in warehouse
// list of goods on menu, and list of transaction records.
public class Warehouse implements Writable {
    private String name;
    private double totalCostInWarehouse; // total costs of goods currently in warehouse
    private ArrayList<Goods> goodsInWarehouseMenu; // the list of goods that has been added to warehouse
    private ArrayList<String> transactionRecords; // records of purchase and sells

    // EFFECTS : constructs a warehouse with given name,
    //           set total value in stock as 0,
    //           and set goods menu list as empty
    public Warehouse(String name) {
        this.name = name;
        this.totalCostInWarehouse = 0;
        this.goodsInWarehouseMenu = new ArrayList<>();
        this.transactionRecords = new ArrayList<>();
    }

    // REQUIRES: amount and cost should be non-negative
    // MODIFIES: this
    // EFFECTS: if the goods has already been created, use purchase methods
    //          to do purchase, if not, create a new goods and do purchase
    //          add it into the list of goods menu
    public void purchaseGoods(String nameOfGoods, int amount, double cost) throws InvalidNumberException {
        Goods good = new Goods(nameOfGoods);
        for (Goods g : goodsInWarehouseMenu) {

            if (nameOfGoods.equals(g.getName())) {
                good = g;
            }
        }

        good.purchase(amount, cost);
        if (!goodsInWarehouseMenu.contains(good)) {
            this.goodsInWarehouseMenu.add(good);
        }
        totalCostInWarehouse += (amount * cost);

        LocalDate date = LocalDate.now();
        String s = date + ": Purchased " + amount + " " + nameOfGoods + " at cost of " + cost;
        transactionRecords.add(s);
    }

    // REQUIRES: amount > 0, price >= 0
    //           amount <= the stock number of this goods in warehouse
    //           the goods with given name should exist in the menu
    // MODIFIES: this
    // EFFECTS: use sell methods to do selling, and reduce the total
    //          costs of goods in warehouse correspondingly
    public void sellGoods(String nameOfGoods, int amount, double price) throws InvalidNumberException {
        for (int i = 0; i < this.goodsInWarehouseMenu.size(); i++) {
            if (this.goodsInWarehouseMenu.get(i).getName().equals(nameOfGoods)) {
                totalCostInWarehouse -=
                        (amount * this.goodsInWarehouseMenu.get(i).getAverageCost());

                this.goodsInWarehouseMenu.get(i).sell(amount, price);
            }
        }
        LocalDate date = LocalDate.now();
        String s = date + ": Sold " + amount + " " + nameOfGoods + " at price of " + price;
        transactionRecords.add(s);
    }

    // MODIFIES: this
    // EFFECTS: modify warehouse name
    public void editWarehouseName(String newName) {
        this.name = newName;
    }

    // EFFECTS: turn the file into Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("totalCostInWarehouse", totalCostInWarehouse);
        json.put("goodsInWarehouseMenu", goodsToJason());
        json.put("transactionRecords", transactionRecordsToJason());
        return json;
    }

    // EFFECTS: returns goods in warehouse as a JSON array
    private JSONArray goodsToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Goods g : goodsInWarehouseMenu) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns transaction records in warehouse as a JSON array
    private JSONArray transactionRecordsToJason() {
        JSONArray jsonArray = new JSONArray();

        for (String s : transactionRecords) {
            jsonArray.put(s);
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: add a transaction records to warehouse
    public void addTransactionRecords(String s) {
        this.transactionRecords.add(s);
    }

    // MODIFIES: this
    // EFFECTS: add a goods to warehouse
    public void addGoods(Goods g) {
        this.goodsInWarehouseMenu.add(g);
    }

    // getters
    public String getName() {
        return name;
    }

    public double getTotalCostInWarehouse() {
        return this.totalCostInWarehouse;
    }

    public ArrayList<Goods> getGoodsInWarehouseMenu() {
        return goodsInWarehouseMenu;
    }

    public ArrayList<String> getTransactionRecords() {
        return transactionRecords;
    }

    // setters
    public void setTotalCostInWarehouse(double number) {
        this.totalCostInWarehouse = number;
    }
}
