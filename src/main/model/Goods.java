package model;

import exceptions.InvalidNumberException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Iterator;

// represents a kind of goods with given name, quantity in stock
// and many relative statistical data (see corresponding comments)
public class Goods implements Writable {
    private final String name;
    private int quantityInStock;
    private double averageCost;    //average cost for purchasing goods in stock
    private double totalCostInStock; // total cost for goods in stock

    // EFFECTS: constructs a goods with given String name
    // and 0 in stock, with other values as 0
    public Goods(String name) {
        this.name = name;
        this.quantityInStock = 0;
        this.averageCost = 0;
        this.totalCostInStock = 0;
    }

    // REQUIRES: amount and cost should be non-negative number
    // MODIFIES: this
    // EFFECTS: add amount to the stock of goods, and
    //          update other statistic values.
    public void purchase(int amount, double cost) throws InvalidNumberException {
        if (amount > 0 && cost > 0) {
            this.quantityInStock = this.quantityInStock + amount;
            this.totalCostInStock = this.totalCostInStock + cost * amount;
            this.averageCost = this.totalCostInStock / this.quantityInStock;

        } else {
            throw new InvalidNumberException("***The number is invalid***");
        }
    }

    // REQUIRES: amount and cost should be non-negative number
    // MODIFIES: this
    // EFFECTS: decrease amount of goods from the stock, and
    //          update other statistic values
    public void sell(int amount, double price) throws InvalidNumberException {
        if (amount <= 0 || price < 0) {
            throw new InvalidNumberException("***The number is invalid***");
        }
        if (amount > this.quantityInStock) {
            throw new InvalidNumberException("***Insufficient amount***");
        } else {
            this.quantityInStock = this.quantityInStock - amount;
            this.totalCostInStock = this.totalCostInStock - this.getAverageCost() * amount;
            if (this.quantityInStock == 0) {
                this.averageCost = 0;
            }
        }
    }

    // EFFECTS: give a brief report of statistics figure about goods
    public String statusToString() {
        return "Name of goods: " + this.getName()
                + ",\n Quantity in stock: " + this.getQuantityInStock()
                + ",\n Average cost: " + this.getAverageCost()
                + ",\n Total cost of goods in stock: " + this.getTotalCostInStock();
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public double getAverageCost() {
        return this.averageCost;
    }

    public double getTotalCostInStock() {
        return this.totalCostInStock;
    }

    // REQUIRES: number >= 0
    // MODIFIES: this
    // EFFECTS: set quantity in stock of goods to be the given number
    public void setQuantityInStock(int number) {
        this.quantityInStock = number;
    }

    // REQUIRES: number >= 0
    // MODIFIES: this
    // EFFECTS: set average costs of goods to be the given number
    public void setAverageCost(double number) {
        this.averageCost = number;
    }

    // REQUIRES: number >= 0
    // MODIFIES: this
    // EFFECTS: set total costs of goods in stock to be the given number
    public void setTotalCostInStock(double number) {
        this.totalCostInStock = number;
    }

    // EFFECTS: write the file into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantityInStock", quantityInStock);
        json.put("averageCost", averageCost);
        json.put("totalCostInStock", totalCostInStock);
        return json;


    }

}