package persistence;

import exceptions.InvalidNumberException;
import model.User;
import model.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    // cited from example
    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("test","test","test");
            JsonWriter writer = new JsonWriter("./data/my/illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            User user = new User("empty","em","pw");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            User user1 = reader.read();
            assertEquals("empty", user1.getName());
            assertEquals("em",user1.getEmail());
            assertEquals("pw",user1.getPassword());
            assertTrue(user1.getWareHouses().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("hz","em","pw");
            Warehouse wh1 = new Warehouse("wh1");
            try {
                wh1.purchaseGoods("apple",10,1);
            } catch (InvalidNumberException e) {
                fail("throw exception incorrectly");
            }
            Warehouse wh2 = new Warehouse("wh2");


            user.addWarehouse(wh1);
            user.addWarehouse(wh2);


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            User user1 = reader.read();
            assertEquals("hz", user1.getName());
            List<Warehouse> warehouses = user1.getWareHouses();
            assertEquals(2, warehouses.size());

            assertEquals(1,user1.getWareHouses().get(0).getTransactionRecords().size());
            assertEquals(0,user1.getWareHouses().get(1).getTransactionRecords().size());
            assertEquals(1,user1.getWareHouses().get(0).getGoodsInWarehouseMenu().size());
            assertEquals(0,user1.getWareHouses().get(1).getGoodsInWarehouseMenu().size());
            assertEquals(10,user1.getWareHouses().get(0).getTotalCostInWarehouse());
            assertEquals(0,user1.getWareHouses().get(1).getTotalCostInWarehouse());
            assertEquals("wh1",user1.getWareHouses().get(0).getName());
            assertEquals("wh2",user1.getWareHouses().get(1).getName());

            checkGoods("apple",10,1,10,
                    user1.getWareHouses().get(0).getGoodsInWarehouseMenu().get(0));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
