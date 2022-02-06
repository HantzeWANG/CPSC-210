package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Warehouse {
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
    public void purchaseGoods(String nameOfGoods, int amount, double cost) {
        Goods good = new Goods(nameOfGoods);
        for (int i = 0; i < this.goodsInWarehouseMenu.size(); i++) {
            Goods goods = this.goodsInWarehouseMenu.get(i);
            if (nameOfGoods.equals(goods.getName())) {
                good = goods;
            }
        }
        if (goodsInWarehouseMenu.contains(good)) {
            good.purchase(amount, cost);
            totalCostInWarehouse += (amount * cost);
        } else {
            good.purchase(amount, cost);
            this.goodsInWarehouseMenu.add(good);
            totalCostInWarehouse += (amount * cost);
        }
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
    public void sellGoods(String nameOfGoods, int amount, double price) {
        for (int i = 0; i < this.goodsInWarehouseMenu.size(); i++) {
            if (this.goodsInWarehouseMenu.get(i).getName().equals(nameOfGoods)) {
                totalCostInWarehouse -=
                        (amount * this.goodsInWarehouseMenu.get(i).getAverageCost());
                this.goodsInWarehouseMenu.get(i).sell(amount,price);
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

    // getters
    public String getName() {
        return name;
    }

    // getters
    public double getTotalCostInWarehouse() {
        return this.totalCostInWarehouse;
    }

    // getters
    public ArrayList<Goods> getGoodsInWarehouseMenu() {
        return goodsInWarehouseMenu;
    }

    // getters
    public ArrayList<String> getTransactionRecords() {
        return transactionRecords;
    }
}
