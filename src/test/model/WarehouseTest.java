package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        testWarehouse.purchaseGoods("AirPods Pro", 10, 200);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(2000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(1, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals(1, testWarehouse.getTransactionRecords().size());
        assertEquals("2022-02-04: Purchased 10 AirPods Pro at cost of 200.0"
                ,testWarehouse.getTransactionRecords().get(0));

        testWarehouse.purchaseGoods("iPhone 13", 2, 1500);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(5000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());
        assertEquals(2, testWarehouse.getTransactionRecords().size());
        assertEquals("2022-02-04: Purchased 2 iPhone 13 at cost of 1500.0"
                ,testWarehouse.getTransactionRecords().get(1));
    }

    @Test
    void testSellGoods() {
        testWarehouse.purchaseGoods("AirPods Pro", 10, 200);
        testWarehouse.purchaseGoods("iPhone 13", 2, 1500);

        testWarehouse.sellGoods("AirPods Pro", 10, 300);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(3000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals(3, testWarehouse.getTransactionRecords().size());
        assertEquals("2022-02-04: Sold 10 AirPods Pro at price of 300.0"
                ,testWarehouse.getTransactionRecords().get(2));

        testWarehouse.sellGoods("iPhone 13", 2, 5000);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(0, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());
        assertEquals(4, testWarehouse.getTransactionRecords().size());
        assertEquals("2022-02-04: Sold 2 iPhone 13 at price of 5000.0"
                ,testWarehouse.getTransactionRecords().get(3));
    }

    @Test
    void testEditWarehouseName() {
        testWarehouse.editWarehouseName("newName");
        assertEquals("newName", testWarehouse.getName());
    }


}
