package model;

import exceptions.InvalidNumberException;

// represents a kind of goods with given name, quantity in stock
// and many relative statistical data (see corresponding comments)
public class Goods {
    private final String name;
    private int quantityInStock;
    private double averageCost;    //average cost for purchasing goods in stock
    private double totalCostInStock; // total cost for goods in stock
    private double historicalTotalCost; // money spent on purchasing goods historically
    private double historicalTotalRevenue; // revenue on goods historically
    private double historicalTotalProfit; // profit made on goods historically
    private double historicalAverageCost; // average cost for goods historically
    private double historicalAverageRevenue; //average revenue for goods historically
    private int historicalTotalPurchaseAmount; // total purchased amount historically
    private int historicalTotalSellAmount; // total sold amount historically

    // EFFECTS: constructs a goods with given String name
    // and 0 in stock, with other values as 0
    public Goods(String name) {
        this.name = name;
        this.quantityInStock = 0;
        this.averageCost = 0;
        this.totalCostInStock = 0;

        this.historicalTotalCost = 0;
        this.historicalTotalRevenue = 0;
        this.historicalTotalProfit = 0;
        this.historicalAverageCost = 0;
        this.historicalAverageRevenue = 0;
        this.historicalTotalPurchaseAmount = 0;
        this.historicalTotalSellAmount = 0;
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

            this.historicalTotalCost = this.historicalTotalCost + cost * amount;
            this.historicalTotalPurchaseAmount = this.historicalTotalPurchaseAmount + amount;
            this.historicalAverageCost = this.historicalTotalCost / this.historicalTotalPurchaseAmount;
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
            this.historicalTotalRevenue = this.historicalTotalRevenue + price * amount;
            this.historicalTotalSellAmount = this.historicalTotalSellAmount + amount;
            this.historicalAverageRevenue =
                    this.historicalTotalRevenue / this.historicalTotalSellAmount;
            this.historicalTotalProfit =
                    this.historicalTotalRevenue - this.historicalTotalCost + this.totalCostInStock;
        }
    }

    // EFFECTS: give a brief report of statistics figure about goods
    public String statusToString() {
        return "Name of goods: " + this.getName()
                + ",\n Quantity in stock: " + this.getQuantityInStock()
                + ",\n Average cost: " + this.getAverageCost()
                + ",\n Total cost of goods in stock: " + this.getTotalCostInStock()
                + ",\n historicalTotalCost: " + this.historicalTotalCost
                + ",\n historicalTotalRevenue: " + this.historicalTotalRevenue
                + ",\n historicalTotalProfit: " + this.historicalTotalProfit
                + ",\n historicalAverageCost: " + this.historicalAverageCost
                + ",\n historicalAverageRevenue: " + this.historicalAverageRevenue
                + ",\n historicalTotalPurchaseAmount: " + this.historicalTotalPurchaseAmount
                + ",\n historicalTotalSellAmount: " + this.historicalTotalSellAmount;
    }

    // getter
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

    public double getHistoricalTotalCost() {
        return this.historicalTotalCost;
    }

    public double getHistoricalTotalRevenue() {
        return this.historicalTotalRevenue;
    }

    public double getHistoricalTotalProfit() {
        return this.historicalTotalProfit;
    }

    public int getHistoricalTotalPurchaseAmount() {
        return this.historicalTotalPurchaseAmount;
    }

    public int getHistoricalTotalSellAmount() {
        return this.historicalTotalSellAmount;
    }

    public double getHistoricalAverageCost() {
        return historicalAverageCost;
    }

    public double getHistoricalAverageRevenue() {
        return historicalAverageRevenue;
    }
}