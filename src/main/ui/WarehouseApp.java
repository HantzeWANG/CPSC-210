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
        } else if (command.equals("n")) {
            getWarehouse();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes warehouse
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        createUser();
        getWarehouse();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\t\nViewing: " + this.warehouse.getName());
        System.out.println("Select from:");
        System.out.println("\tenter p for -> purchase");
        System.out.println("\tenter s for -> sell");
        System.out.println("\tenter t for -> view transaction records");
        System.out.println("\tenter g for -> view goods menu in warehouse");
        System.out.println("\tenter n for -> change warehouse or create a new one");
        System.out.println("\tenter q for -> quit");
    }

    // MODIFIES: this
    // EFFECTS: purchase the goods at given cost for given quantity
    private void doPurchase() {
        System.out.print("Enter name of goods purchased: ");
        String goodsName = input.next();
        System.out.print("Enter number of goods purchased: ");
        int amount = input.nextInt();
        System.out.print("Enter cost of each goods: ");
        double cost = input.nextDouble();

        if (amount > 0.0 && cost >= 0) {
            warehouse.purchaseGoods(goodsName,amount,cost);
            System.out.println("\nThe goods have been added successfully!");
        } else {
            System.out.println("Your input is invalid...\n");
        }
    }



    // MODIFIES: this
    // EFFECTS: sell the goods with given name at given price for given quantity
    private void doSell() {
        if (warehouse.getGoodsInWarehouseMenu().isEmpty()) {
            System.out.println("There is no goods on menu");
        } else {
            System.out.println("enter: ");
            for (int i = 0; i < warehouse.getGoodsInWarehouseMenu().size(); i++) {
                System.out.println(i + 1 + " for " + warehouse.getGoodsInWarehouseMenu().get(i).getName());
            }
            int index = input.nextInt() - 1;
            Goods goodsForSell = warehouse.getGoodsInWarehouseMenu().get(index);
            System.out.println("Inventory: " + goodsForSell.getQuantityInStock());
            System.out.println("Average cost: " + goodsForSell.getAverageCost());
            System.out.println("Enter amount to sell: ");
            int amount = input.nextInt();
            if (goodsForSell.getQuantityInStock() < amount) {
                System.out.println("there is not enough stock, only " + goodsForSell.getQuantityInStock() + " left...");
            } else {
                System.out.print("Enter selling price of each goods : ");
                double price = input.nextDouble();
                warehouse.sellGoods(goodsForSell.getName(), amount, price);
                System.out.println("\nThe goods have left warehouse successfully!");
            }
        }
    }


    // EFFECTS: view transaction records in the warehouse
    private void doViewTransactionRecords() {
        for (int i = 0; i < warehouse.getTransactionRecords().size(); i++) {
            System.out.println(warehouse.getTransactionRecords().get(i));
        }
        System.out.println(warehouse.getTransactionRecords().size() + " records found");
    }

    // EFFECTS: view goods menu in the warehouse,
    //          then view goods detail
    private void doViewGoods() {
        for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
            System.out.println(i + 1 + " -> " + this.warehouse.getGoodsInWarehouseMenu().get(i).getName());
        }

        System.out.println("\nTotal cost of goods in warehouse currently: " + warehouse.getTotalCostInWarehouse());
        System.out.println("Enter the correspondent number to view goods details: ");
        int index = input.nextInt();
        System.out.println(warehouse.getGoodsInWarehouseMenu().get(index - 1).statusToString());
    }

    //EFFECTS : create a user with given email and password
    private void createUser() {
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter email: ");
        String email = input.next();
        System.out.print("Enter password: ");
        String pw = input.next();
        this.user = new User(username, email, pw);
        System.out.println("Welcome! " + username);
    }


    // EFFECTS: get the warehouse user want to use
    private void getWarehouse() {
        System.out.println("\nPlease select warehouse: ");
        for (int i = 0; i < this.user.getWareHouses().size(); i++) {
            System.out.println("enter " + i + 1 + " for -> " + this.user.getWareHouses().get(i).getName());
        }
        System.out.println("enter 0 to create a new warehouse");
        int index = input.nextInt();
        if (index == 0) {
            System.out.println("Enter name of new warehouse: ");
            String newName = input.next();
            this.warehouse = new Warehouse(newName);
            this.user.getWareHouses().add(warehouse);
        } else {
            this.warehouse = this.user.getWareHouses().get(index - 1);
        }
    }
}









