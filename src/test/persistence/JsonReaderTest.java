package persistence;

import model.User;
import model.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    // cited from the example file
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals("empty", user.getName());
            assertEquals("em",user.getEmail());
            assertEquals("pw",user.getPassword());
            assertTrue(user.getWareHouses().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("Hanze", user.getName());
            assertEquals("email",user.getEmail());
            assertEquals("pw",user.getPassword());

            List<Warehouse> warehouses = user.getWareHouses();
            assertEquals(2,warehouses.size());

            Warehouse wh1 = warehouses.get(0);
            Warehouse wh2 = warehouses.get(1);
            assertEquals("wh1", wh1.getName() );
            assertEquals("wh2", wh2.getName() );

            assertEquals(60,wh1.getTotalCostInWarehouse());
            assertEquals(2,wh1.getGoodsInWarehouseMenu().size());
            assertEquals(3,wh1.getTransactionRecords().size());

            assertEquals(1,wh2.getTotalCostInWarehouse());
            assertEquals(1,wh2.getGoodsInWarehouseMenu().size());
            assertEquals(1,wh2.getTransactionRecords().size());

            checkGoods("apple",30,1.6666666666666667,
                    50, wh1.getGoodsInWarehouseMenu().get(0));
            checkGoods("pear",10,1,
                    10, wh1.getGoodsInWarehouseMenu().get(1));
            checkGoods("apple",1,1,1,wh2.getGoodsInWarehouseMenu().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
