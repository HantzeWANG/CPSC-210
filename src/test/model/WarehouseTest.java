package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class WarehouseTest {
    private Warehouse testWarehouse;

    @BeforeEach
    void runBefore() {
        testWarehouse = new Warehouse("w1");
    }

    @Test
    void testConstructor() {
        assertEquals("w1", testWarehouse.getName());
        assertEquals(0, testWarehouse.getTotalCostInWarehouse());
        assertTrue(testWarehouse.getGoodsInWarehouseMenu().isEmpty());
        assertTrue(testWarehouse.getTransactionRecords().isEmpty());
    }

    @Test
    void testPurchaseGoods() {
        LocalDate date = LocalDate.now();

        try {
            testWarehouse.purchaseGoods("AirPods Pro", 10, 200);
        } catch (InvalidNumberException e) {
            fail();
        }
        assertEquals("w1", testWarehouse.getName());
        assertEquals(2000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(1, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals(1, testWarehouse.getTransactionRecords().size());
        assertEquals(date + ": Purchased 10 AirPods Pro at cost of 200.0"
                ,testWarehouse.getTransactionRecords().get(0));

        try {
            testWarehouse.purchaseGoods("iPhone 13", 2, 1500);
        } catch (InvalidNumberException e) {
            fail();
        }
        assertEquals("w1", testWarehouse.getName());
        assertEquals(5000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());
        assertEquals(2, testWarehouse.getTransactionRecords().size());
        assertEquals(date + ": Purchased 2 iPhone 13 at cost of 1500.0"
                ,testWarehouse.getTransactionRecords().get(1));

        try {
            testWarehouse.purchaseGoods("AirPods Pro", 20, 200);
        } catch (InvalidNumberException e) {
            fail();
        }
        assertEquals("w1", testWarehouse.getName());
        assertEquals(9000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals(3, testWarehouse.getTransactionRecords().size());
        assertEquals(date + ": Purchased 20 AirPods Pro at cost of 200.0"
                ,testWarehouse.getTransactionRecords().get(2));
    }

    @Test
    void testSellGoods() {
        LocalDate date = LocalDate.now();
        try {
            testWarehouse.purchaseGoods("AirPods Pro", 10, 200);
        } catch (InvalidNumberException e) {
            fail();
        }
        try {
            testWarehouse.purchaseGoods("iPhone 13", 2, 1500);
        } catch (InvalidNumberException e) {
            fail();
        }

        try {
            testWarehouse.sellGoods("AirPods Pro", 10, 300);
        } catch (InvalidNumberException e) {
            fail();
        }
        assertEquals("w1", testWarehouse.getName());
        assertEquals(3000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals(3, testWarehouse.getTransactionRecords().size());
        assertEquals(date + ": Sold 10 AirPods Pro at price of 300.0"
                ,testWarehouse.getTransactionRecords().get(2));

        try {
            testWarehouse.sellGoods("iPhone 13", 2, 5000);
        } catch (InvalidNumberException e) {
            fail();
        }
        assertEquals("w1", testWarehouse.getName());
        assertEquals(0, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());
        assertEquals(4, testWarehouse.getTransactionRecords().size());
        assertEquals(date + ": Sold 2 iPhone 13 at price of 5000.0"
                ,testWarehouse.getTransactionRecords().get(3));
    }

    @Test
    void testEditWarehouseName() {
        testWarehouse.editWarehouseName("newName");
        assertEquals("newName", testWarehouse.getName());
    }

    @Test
    void testAddTransactionRecords() {
        testWarehouse.addTransactionRecords("test");
        assertEquals(1,testWarehouse.getTransactionRecords().size());
        assertEquals("test",testWarehouse.getTransactionRecords().get(0));

        testWarehouse.addTransactionRecords("test1");
        assertEquals(2,testWarehouse.getTransactionRecords().size());
        assertEquals("test1",testWarehouse.getTransactionRecords().get(1));
    }

    @Test
    void testAddGoods() {
        Goods g1 = new Goods("g1");
        Goods g2 = new Goods("g2");
        testWarehouse.addGoods(g1);
        assertEquals(1,testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals(g1,testWarehouse.getGoodsInWarehouseMenu().get(0));

        testWarehouse.addGoods(g2);
        assertEquals(2,testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals(g2,testWarehouse.getGoodsInWarehouseMenu().get(1));
    }

    @Test
    void testSetTotalCostInWarehouse() {
        testWarehouse.setTotalCostInWarehouse(100);
        assertEquals(100,testWarehouse.getTotalCostInWarehouse());
        testWarehouse.setTotalCostInWarehouse(200);
        assertEquals(200,testWarehouse.getTotalCostInWarehouse());
    }


}
