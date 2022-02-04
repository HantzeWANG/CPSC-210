package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoodTest {
    private Goods testGoods;


    @BeforeEach
    void runBefore(){
        testGoods = new Goods("MacBook Pro");
    }

    @Test
    void testConstructor(){
        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(0, testGoods.getAverageCost());
        assertEquals(0, testGoods.getQuantityInStock());
        assertEquals(0, testGoods.getTotalCostInStock());

        assertEquals(0, testGoods.getHistoricalAverageCost());
        assertEquals(0, testGoods.getHistoricalAverageRevenue());
        assertEquals(0, testGoods.getHistoricalTotalCost());
        assertEquals(0, testGoods.getHistoricalTotalProfit());
        assertEquals(0, testGoods.getHistoricalTotalRevenue());
        assertEquals(0, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(0, testGoods.getHistoricalTotalSellAmount());
    }

    @Test
    void testPurchase() {
        testGoods.purchase(5,10);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(10, testGoods.getAverageCost());
        assertEquals(5, testGoods.getQuantityInStock());
        assertEquals(50, testGoods.getTotalCostInStock());

        assertEquals(10, testGoods.getHistoricalAverageCost());
        assertEquals(0, testGoods.getHistoricalAverageRevenue());
        assertEquals(50, testGoods.getHistoricalTotalCost());
        assertEquals(0, testGoods.getHistoricalTotalProfit());
        assertEquals(0, testGoods.getHistoricalTotalRevenue());
        assertEquals(5, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(0, testGoods.getHistoricalTotalSellAmount());

        testGoods.purchase(5,20);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(15, testGoods.getAverageCost());
        assertEquals(10, testGoods.getQuantityInStock());
        assertEquals(150, testGoods.getTotalCostInStock());

        assertEquals(15, testGoods.getHistoricalAverageCost());
        assertEquals(0, testGoods.getHistoricalAverageRevenue());
        assertEquals(150, testGoods.getHistoricalTotalCost());
        assertEquals(0, testGoods.getHistoricalTotalProfit());
        assertEquals(0, testGoods.getHistoricalTotalRevenue());
        assertEquals(10, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(0, testGoods.getHistoricalTotalSellAmount());

    }

    @Test
    void testSell() {
        testGoods.purchase(5,10);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(10, testGoods.getAverageCost());
        assertEquals(5, testGoods.getQuantityInStock());
        assertEquals(50, testGoods.getTotalCostInStock());

        assertEquals(10, testGoods.getHistoricalAverageCost());
        assertEquals(0, testGoods.getHistoricalAverageRevenue());
        assertEquals(50, testGoods.getHistoricalTotalCost());
        assertEquals(0, testGoods.getHistoricalTotalProfit());
        assertEquals(0, testGoods.getHistoricalTotalRevenue());
        assertEquals(5, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(0, testGoods.getHistoricalTotalSellAmount());

        testGoods.purchase(5,20);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(15, testGoods.getAverageCost());
        assertEquals(10, testGoods.getQuantityInStock());
        assertEquals(150, testGoods.getTotalCostInStock());

        assertEquals(15, testGoods.getHistoricalAverageCost());
        assertEquals(0, testGoods.getHistoricalAverageRevenue());
        assertEquals(150, testGoods.getHistoricalTotalCost());
        assertEquals(0, testGoods.getHistoricalTotalProfit());
        assertEquals(0, testGoods.getHistoricalTotalRevenue());
        assertEquals(10, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(0, testGoods.getHistoricalTotalSellAmount());

        testGoods.sell(5,30);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(15, testGoods.getAverageCost());
        assertEquals(5, testGoods.getQuantityInStock());
        assertEquals(75, testGoods.getTotalCostInStock());

        assertEquals(15, testGoods.getHistoricalAverageCost());
        assertEquals(30, testGoods.getHistoricalAverageRevenue());
        assertEquals(150, testGoods.getHistoricalTotalCost());
        assertEquals(75, testGoods.getHistoricalTotalProfit());
        assertEquals(150, testGoods.getHistoricalTotalRevenue());
        assertEquals(10, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(5, testGoods.getHistoricalTotalSellAmount());

        testGoods.sell(5,50);

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(0, testGoods.getAverageCost());
        assertEquals(0, testGoods.getQuantityInStock());
        assertEquals(0, testGoods.getTotalCostInStock());

        assertEquals(15, testGoods.getHistoricalAverageCost());
        assertEquals(40, testGoods.getHistoricalAverageRevenue());
        assertEquals(150, testGoods.getHistoricalTotalCost());
        assertEquals(250, testGoods.getHistoricalTotalProfit());
        assertEquals(400, testGoods.getHistoricalTotalRevenue());
        assertEquals(10, testGoods.getHistoricalTotalPurchaseAmount());
        assertEquals(10, testGoods.getHistoricalTotalSellAmount());

    }

    @Test
    void testStatusToString() {
        assertEquals("Name of goods: MacBook Pro, Quantity in stock: 0, Average cost: 0.0, " +
                "Total cost of goods in stock: 0.0, historicalTotalCost: 0.0, " +
                "historicalTotalRevenue: 0.0, historicalTotalProfit: 0.0, " +
                "historicalAverageCost: 0.0, historicalAverageRevenue: 0.0, " +
                "historicalTotalPurchaseAmount: 0, historicalTotalSellAmount: 0", testGoods.statusToString());

        testGoods.purchase(5,10);

        assertEquals("Name of goods: MacBook Pro, Quantity in stock: 5, Average cost: 10.0, " +
                "Total cost of goods in stock: 50.0, historicalTotalCost: 50.0, " +
                "historicalTotalRevenue: 0.0, historicalTotalProfit: 0.0, " +
                "historicalAverageCost: 10.0, historicalAverageRevenue: 0.0, " +
                "historicalTotalPurchaseAmount: 5, historicalTotalSellAmount: 0", testGoods.statusToString());
    }






}