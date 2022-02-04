package ui;

import model.Goods;
import model.User;
import model.Warehouse;

public class Main {
    public static void main(String[] args) {
        Goods good1 = new Goods("AirPods Pro");
        System.out.println(good1.statusToString());

        good1.purchase(10,200);
        System.out.println(good1.statusToString());

        good1.sell(5,300);
        System.out.println(good1.statusToString());

        Warehouse w1 = new Warehouse("warehouse");
        User user1 = new User("Hanze","xxx","asd");
        user1.addWarehouse("w1");
        System.out.println(user1.getWareHouses().get(0));
    }
}


