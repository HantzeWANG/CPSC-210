package persistence;

import model.Goods;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGoods(String name, int quantityInStock,
                              double averageCost, double totalCostInStock, Goods goods) {
        assertEquals(name, goods.getName());
        assertEquals(quantityInStock, goods.getQuantityInStock());
        assertEquals(averageCost, goods.getAverageCost());
        assertEquals(totalCostInStock, goods.getTotalCostInStock());
    }
}
