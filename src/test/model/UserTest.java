package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private User testUser;

    @BeforeEach
    void runBefore() {
        testUser = new User("Hanze WANG", "hanzewang123@outlook.com", "mypassword");
    }

    @Test
    void testConstructor() {
        assertEquals("Hanze WANG", testUser.getName());
        assertEquals("hanzewang123@outlook.com", testUser.getEmail());
        assertEquals("mypassword", testUser.getPassword());
        assertTrue(testUser.getWareHouses().isEmpty());

    }

    @Test
    void testAddWarehouse() {
        testUser.addWarehouse("Warehouse1");
        assertEquals(1, testUser.getWareHouses().size());
        assertEquals("Warehouse1", testUser.getWareHouses().get(0));
        System.out.println(testUser.getWareHouses());

        testUser.addWarehouse("Warehouse2");
        assertEquals(2, testUser.getWareHouses().size());
        assertEquals("Warehouse2", testUser.getWareHouses().get(1));
    }
}
