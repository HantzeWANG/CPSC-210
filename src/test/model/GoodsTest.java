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


        try {
            testGoods.purchase(5,20);
        } catch (InvalidNumberException e) {
            fail("there should not be an exception thrown");
        }

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(15, testGoods.getAverageCost());
        assertEquals(10, testGoods.getQuantityInStock());
        assertEquals(150, testGoods.getTotalCostInStock());


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

        try {
            testGoods.purchase(5,20);
        } catch (InvalidNumberException e) {
            fail();
        }

        try {
            testGoods.sell(5,30);
        } catch (InvalidNumberException e) {
            fail();
        }

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(15, testGoods.getAverageCost());
        assertEquals(5, testGoods.getQuantityInStock());
        assertEquals(75, testGoods.getTotalCostInStock());


        try {
            testGoods.sell(5,50);
        } catch (InvalidNumberException e) {
            fail();
        }

        assertEquals("MacBook Pro", testGoods.getName());
        assertEquals(0, testGoods.getAverageCost());
        assertEquals(0, testGoods.getQuantityInStock());
        assertEquals(0, testGoods.getTotalCostInStock());


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

        try {
            testGoods.sell(10,-10);
            fail("exception is not thrown when price < 0");
        } catch (InvalidNumberException e){
            //expected

        }
    }

    @Test
    void testStatusToString() {
        assertEquals("Name of goods: MacBook Pro,\n" +
                " Quantity in stock: 0,\n" +
                " Average cost: 0.0,\n" +
                " Total cost of goods in stock: 0.0",
                testGoods.statusToString());

        try {
            testGoods.purchase(5,10);
        } catch (InvalidNumberException e) {
            fail();
        }

        assertEquals("Name of goods: MacBook Pro,\n" +
                " Quantity in stock: 5,\n" +
                " Average cost: 10.0,\n" +
                " Total cost of goods in stock: 50.0"
                , testGoods.statusToString());
    }

    @Test
    void testSetQuantityInStock() {
        testGoods.setQuantityInStock(100);
        assertEquals(100,testGoods.getQuantityInStock());
        testGoods.setQuantityInStock(200);
        assertEquals(200,testGoods.getQuantityInStock());
    }

    @Test
    void testSetAverageCost() {
        testGoods.setAverageCost(1);
        assertEquals(1,testGoods.getAverageCost());
        testGoods.setAverageCost(2);
        assertEquals(2,testGoods.getAverageCost());

    }

    @Test
    void testSetTotalCostInStock() {
        testGoods.setTotalCostInStock(100);
        assertEquals(100,testGoods.getTotalCostInStock());
        testGoods.setTotalCostInStock(200);
        assertEquals(200,testGoods.getTotalCostInStock());

    }

}