package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoodsTest {
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
        try {
            testGoods.purchase(5,10);
        } catch (InvalidNumberException e) {
            fail("there should not be an exception thrown");
        }

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

        try {
            testGoods.purchase(5,20);
        } catch (InvalidNumberException e) {
            fail("there should not be an exception thrown");
        }

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

        try {
            testGoods.purchase(-10,10);
            fail("exception did not thrown");
        } catch (InvalidNumberException e) {
            //expected
        }

        try {
            testGoods.purchase(10,-10);
            fail("exception did not thrown");
        } catch (InvalidNumberException e) {
            //expected
        }

    }

    @Test
    void testSell() {
        try {
            testGoods.purchase(5,10);
        } catch (InvalidNumberException e) {
            fail();
        }

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

        try {
            testGoods.purchase(5,20);
        } catch (InvalidNumberException e) {
            fail();
        }

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

        try {
            testGoods.sell(5,30);
        } catch (InvalidNumberException e) {
            fail();
        }

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

        try {
            testGoods.sell(5,50);
        } catch (InvalidNumberException e) {
            fail();
        }

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

        try {
            testGoods.sell(1,10);
            fail("no stock");
        } catch (InvalidNumberException e) {
            // expected
        }

        try {
            testGoods.sell(-10,10);
            fail("negative amount");
        } catch (InvalidNumberException e) {
            // expected
        }
    }

    @Test
    void testStatusToString() {
        assertEquals("Name of goods: MacBook Pro,\n" +
                " Quantity in stock: 0,\n" +
                " Average cost: 0.0,\n" +
                " Total cost of goods in stock: 0.0,\n" +
                " historicalTotalCost: 0.0,\n" +
                " historicalTotalRevenue: 0.0,\n" +
                " historicalTotalProfit: 0.0,\n" +
                " historicalAverageCost: 0.0,\n" +
                " historicalAverageRevenue: 0.0,\n" +
                " historicalTotalPurchaseAmount: 0,\n" +
                " historicalTotalSellAmount: 0", testGoods.statusToString());

        try {
            testGoods.purchase(5,10);
        } catch (InvalidNumberException e) {
            fail();
        }

        assertEquals("Name of goods: MacBook Pro,\n" +
                " Quantity in stock: 5,\n" +
                " Average cost: 10.0,\n" +
                " Total cost of goods in stock: 50.0,\n" +
                " historicalTotalCost: 50.0,\n" +
                " historicalTotalRevenue: 0.0,\n" +
                " historicalTotalProfit: 0.0,\n" +
                " historicalAverageCost: 10.0,\n" +
                " historicalAverageRevenue: 0.0,\n" +
                " historicalTotalPurchaseAmount: 5,\n" +
                " historicalTotalSellAmount: 0", testGoods.statusToString());
    }

}