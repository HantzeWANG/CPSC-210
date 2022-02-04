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
        testUser.addWarehouse("w1");
        assertEquals(1, testUser.getWareHouses().size());
        assertEquals("w1", testUser.getWareHouses().get(0).getName());

        testUser.addWarehouse("w2");
        assertEquals(2, testUser.getWareHouses().size());
        assertEquals("w2", testUser.getWareHouses().get(1).getName());

    }
}
