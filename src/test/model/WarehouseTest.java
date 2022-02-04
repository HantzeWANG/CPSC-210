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
    }

    @Test
    void testPurchaseGoods() {
        testWarehouse.purchaseGoods("AirPods Pro", 10, 200);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(2000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(1, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());

        testWarehouse.purchaseGoods("iPhone 13", 2, 1500);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(5000, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());
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

        testWarehouse.sellGoods("iPhone 13", 2, 5000);
        assertEquals("w1", testWarehouse.getName());
        assertEquals(0, testWarehouse.getTotalCostInWarehouse());
        assertEquals(2, testWarehouse.getGoodsInWarehouseMenu().size());
        assertEquals("AirPods Pro", testWarehouse.getGoodsInWarehouseMenu().get(0).getName());
        assertEquals("iPhone 13", testWarehouse.getGoodsInWarehouseMenu().get(1).getName());

    }
}
