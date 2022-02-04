package model;

public class Goods {
    private String name;
    private int quantityInStock;
    private double averageCost;
    private double totalCostInStock;
    private double historicalTotalCost;
    private double historicalTotalRevenue;
    private double historicalTotalProfit;
    private double historicalAverageCost;
    private double historicalAverageRevenue;
    private int historicalTotalPurchaseAmount;
    private int historicalTotalSellAmount;

    // EFFECTS: constructs a goods with given String name
    // and no quantity in stock, with other values as 0
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

    public void purchase(int amount, double cost) {
        this.quantityInStock = this.quantityInStock + amount;
        this.totalCostInStock = this.totalCostInStock + cost * amount;
        this.averageCost = this.totalCostInStock / this.quantityInStock;

        this.historicalTotalCost = this.historicalTotalCost + cost * amount;
        this.historicalTotalPurchaseAmount = this.historicalTotalPurchaseAmount + amount;
        this.historicalAverageCost = this.historicalTotalCost / this.historicalTotalPurchaseAmount;
    }

    // REQUIRES: amount and cost should be non-negative number
    // MODIFIES: this
    // EFFECTS: decrease amount of goods from the stock, and
    //          update other statistic values

    public void sell(int amount, double price) {
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

    // EFFECTS: give a brief report of many statistics figure of goods
    public String statusToString() {
        return "Name of goods: " + this.getName()
                + ", Quantity in stock: " + this.getQuantityInStock()
                + ", Average cost: " + this.getAverageCost()
                + ", Total cost of goods in stock: " + this.getTotalCostInStock()
                + ", historicalTotalCost: " + this.historicalTotalCost
                + ", historicalTotalRevenue: " + this.historicalTotalRevenue
                + ", historicalTotalProfit: " + this.historicalTotalProfit
                + ", historicalAverageCost: " + this.historicalAverageCost
                + ", historicalAverageRevenue: " + this.historicalAverageRevenue
                + ", historicalTotalPurchaseAmount: " + this.historicalTotalPurchaseAmount
                + ", historicalTotalSellAmount: " + this.historicalTotalSellAmount;
    }
























    // getter
    public String getName() {
        return this.name;
    }

    // getter
    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    // getter
    public double getAverageCost() {
        return this.averageCost;
    }

    // getter
    public double getTotalCostInStock() {
        return this.totalCostInStock;
    }

    // getter
    public double getHistoricalTotalCost() {
        return this.historicalTotalCost;
    }

    // getter
    public double getHistoricalTotalRevenue() {
        return this.historicalTotalRevenue;
    }

    // getter
    public double getHistoricalTotalProfit() {
        return this.historicalTotalProfit;
    }

    // getter
    public int getHistoricalTotalPurchaseAmount() {
        return this.historicalTotalPurchaseAmount;
    }

    // getter
    public int getHistoricalTotalSellAmount() {
        return this.historicalTotalSellAmount;
    }

    // getter
    public double getHistoricalAverageCost() {
        return historicalAverageCost;
    }

    // getter
    public double getHistoricalAverageRevenue() {
        return historicalAverageRevenue;
    }
}