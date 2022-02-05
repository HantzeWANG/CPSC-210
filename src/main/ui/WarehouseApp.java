package ui;

import model.Goods;
import model.User;
import model.Warehouse;

import java.util.Scanner;


// Inventory management application
public class WarehouseApp {
    private User user;
    private Warehouse warehouse;
    private Scanner input;


    // EFFECTS: runs the warehouse application
    public WarehouseApp() {
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("p")) {
            doPurchase();
        } else if (command.equals("s")) {
            doSell();
        } else if (command.equals("t")) {
            doViewTransactionRecords();
        } else if (command.equals("g")) {
            doViewGoods();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user
    private void init() {
        //System.out.println("Enter username: ");
        //String username = input.next();
        // ADD email and password
        //this.user = new User(username,"email000","pw000");
        this.warehouse = new Warehouse("wareHouseName000");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> purchase");
        System.out.println("\ts -> sell");
        System.out.println("\tt -> view transaction records");
        System.out.println("\tg -> view goods menu in warehouse");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: purchase the goods at given cost of given quantity
    private void doPurchase() {
        System.out.print("Enter name of goods purchased: ");
        String goodsName = input.next();
        System.out.print("Enter number of goods purchased: ");
        int amount = input.nextInt();
        System.out.print("Enter cost of each goods : ");
        double cost = input.nextDouble();

        if (amount >= 0.0 && cost >= 0) {
            warehouse.purchaseGoods(goodsName,amount,cost);
            System.out.println("\nThe goods has been added successfully!");
        } else {
            System.out.println("please do not give negative number...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: sell the goods with given name at given price of given quantity
    private void doSell() {
        Goods goods1 = null;
        System.out.print("Enter name of goods for sell: ");
        String goodsName = input.next();
        for (int i = 0; i < warehouse.getGoodsInWarehouseMenu().size(); i++) {
            if (warehouse.getGoodsInWarehouseMenu().get(i).getName().equals(goodsName)) {
                goods1 = warehouse.getGoodsInWarehouseMenu().get(i);
                break;
            }
        }
        if (goods1 == null) {
            System.out.println("There is no such goods in warehouse...");
        } else {
            System.out.print("Enter number of goods for sell: ");
            int amount = input.nextInt();
            if (goods1.getQuantityInStock() < amount) {
                System.out.println("there is not enough stock, only " + goods1.getQuantityInStock() + " left...");
            } else {
                System.out.print("Enter selling price of each goods : ");
                double price = input.nextDouble();
                warehouse.sellGoods(goodsName, amount, price);
                System.out.println("\nThe goods have left warehouse successfully!");
            }
        }
    }

    // EFFECTS: view transaction records in the warehouse
    private void doViewTransactionRecords() {
        for (int i = 0; i < warehouse.getTransactionRecords().size(); i++) {
            System.out.println(warehouse.getTransactionRecords().get(i));
        }
        System.out.println(warehouse.getTransactionRecords().size() + " records in total");
    }

    // EFFECTS: view goods menu in the warehouse,
    //          then view goods detail
    private void doViewGoods() {
        for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
            System.out.println(i + 1 + ": " + this.warehouse.getGoodsInWarehouseMenu().get(i).getName());
        }

        System.out.println("\nTotal cost of goods in warehouse currently: " + warehouse.getTotalCostInWarehouse());
        System.out.println("Enter the correspondent number to view goods details: ");
        int index = input.nextInt();
        System.out.println(warehouse.getGoodsInWarehouseMenu().get(index - 1).statusToString());
    }

}









