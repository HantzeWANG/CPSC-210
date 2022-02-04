package model;

import java.util.ArrayList;

public class Warehouse {
    private String name;
    private double totalCostInWarehouse;
    private ArrayList<Goods> goodsInWarehouseMenu;

    // EFFECTS : constructs a warehouse with given name,
    //           set total value in stock as 0,
    //           and set goods menu list as empty
    public Warehouse(String name) {
        this.name = name;
        this.totalCostInWarehouse = 0;
        this.goodsInWarehouseMenu = new ArrayList<>();
    }

    // REQUIRES: amount and cost should be non-negative
    // MODIFIES: this, goods(nameOfGoods)
    // EFFECTS: if the goods has already been created,
    // use purchase methods to do purchase, if not, create
    // a new goods and add it into list of goods menu, then add the value
    // total costs of goods in warehouse
    public void purchaseGoods(String nameOfGoods, int amount, double cost) {
        Goods good = new Goods(nameOfGoods);
        if (goodsInWarehouseMenu.contains(good)) {
            good.purchase(amount, cost);
            totalCostInWarehouse += (amount * cost);
        } else {
            good.purchase(amount, cost);
            this.goodsInWarehouseMenu.add(good);
            totalCostInWarehouse += (amount * cost);
        }
    }

    // REQUIRES: amount and price should be non-negative
    //           amount <= the stock number of this goods in warehouse
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
    public ArrayList<Goods> getGoodsInStock() {
        return goodsInWarehouseMenu;
    }
}
