package ui;

import exceptions.InvalidNumberException;
import model.Goods;
import model.User;
import model.Warehouse;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Inventory management application
public class WarehouseApp {
    private static final String JSON_STORE = "./data/test.json";
    private User user;
    private Warehouse warehouse;
    private Scanner input;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    // EFFECTS: runs the warehouse application
    public WarehouseApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command;

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
        } else if (command.equals("1")) {
            saveUser();
        } else if (command.equals("2")) {
            loadUser();
        }
        System.out.println("--Press any key to continue--");
        input.next();
    }

    // MODIFIES: this
    // EFFECTS: initializes warehouse
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("\nSelect from:");
        System.out.println("\tenter l for -> load from file");
        System.out.println("\tenter n for -> new user");
        String command = input.next();
        if (command.equals("l")) {
            loadUser();
            System.out.println("enter password for " + user.getName());
            if (!input.next().equals(user.getPassword())) {
                System.out.println("bad password!");
                init();
            }
            warehouse = user.getWareHouses().get(0);
        } else if (command.equals("n")) {
            createUser();
            getWarehouse();
        }

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\t\nUser: " + this.user.getName());
        System.out.println("----Warehouse: " + this.warehouse.getName());
        System.out.println("\nSelect from:");
        System.out.println("\tenter p for -> purchase");
        System.out.println("\tenter s for -> sell");
        System.out.println("\tenter t for -> view transaction records");
        System.out.println("\tenter g for -> view goods menu in warehouse");
        System.out.println("\tenter n for -> switch warehouse or create a new one");
        System.out.println("\tenter q for -> quit");
        System.out.println("\t1 -> save work room to file");  ///
        //System.out.println("\t2 -> load work room from file");
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

        try {
            warehouse.purchaseGoods(goodsName,amount,cost);
            System.out.println("\nThe goods have been added successfully!");
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }
    }


    // MODIFIES: this
    // EFFECTS: sell the goods with given name at given price for given quantity
    private void doSell() {
        if (warehouse.getGoodsInWarehouseMenu().isEmpty()) {
            System.out.println("There is no goods on menu");
            return;
        }
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
        System.out.print("Enter selling price of each goods : ");
        double price = input.nextDouble();
        try {
            warehouse.sellGoods(goodsForSell.getName(), amount, price);
            System.out.println("\nThe goods have left warehouse successfully!");
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
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
        if (this.warehouse.getGoodsInWarehouseMenu().isEmpty()) {
            System.out.println("There is no goods on menu");
        } else {
            System.out.println("\tSelect from:");
            for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
                System.out.println(i + 1 + " -> " + this.warehouse.getGoodsInWarehouseMenu().get(i).getName());
            }
            System.out.println("\nTotal cost of goods in warehouse currently: " + warehouse.getTotalCostInWarehouse());
            System.out.println("Enter the correspondent number to view goods details: ");
            int index = input.nextInt();
            System.out.println(warehouse.getGoodsInWarehouseMenu().get(index - 1).statusToString());
        }
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
        if (user.getWareHouses().isEmpty()) {
            System.out.println("Currently you don't have any warehouse");
        } else {
            for (int i = 0; i < this.user.getWareHouses().size(); i++) {
                System.out.println("enter " + (i + 1) + " for -> " + this.user.getWareHouses().get(i).getName());
            }
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

    // EFFECTS: saves the user to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved " + user.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user from file
    private void loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}









