package ui;

import exceptions.InvalidNumberException;
import model.Goods;
import model.User;
import model.Warehouse;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends JFrame {
    User user;
    Warehouse warehouse;
    Goods goodsToSell;
    private static final String JSON_STORE = "./data/user.json";

    JPanel panelCont; // list of panels for cards
    JPanel beginningPanel;
    JPanel logInPanel;
    JPanel mainMenuPanel;
    JPanel goodsMenuPanel;
    JPanel transactionsPanel;
    JPanel goodsDetailPanel;
    JPanel sellGoodsPanel;
    JPanel purchaseGoodsPanel;
    JPanel createUserPanel;

    CardLayout cards = new CardLayout();

    public GUI() {
        super("InventoryManagement");
        initializeFields();
        initializeFrame();
        initializeBeginningPanel();
        initializeLogInPanel();
        initializeMainMenuPanel();
        initializeGoodsMenuPanel();
        initializeTransactionPanel();
        initializeGoodsDetailPanel();
        initializeSellGoodsPanel();
        initializePurchaseGoodsPanel();
        initializeCreateUserPanel();

        // Cards Layout
        panelCont.add(beginningPanel, "1");
        panelCont.add(logInPanel, "2");
        panelCont.add(mainMenuPanel, "3");
        panelCont.add(goodsMenuPanel, "4");
        panelCont.add(transactionsPanel, "5");
        panelCont.add(goodsDetailPanel, "6");
        panelCont.add(sellGoodsPanel, "7");
        panelCont.add(purchaseGoodsPanel, "8");
        panelCont.add(createUserPanel,"9");

        cards.show(panelCont, "1"); // show beginning panel initially
    }




    private void initializeFields() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            user = jsonReader.read();
            warehouse = user.getWareHouses().get(0);
            goodsToSell = warehouse.getGoodsInWarehouseMenu().get(0);
        } catch (IOException e) {
            //will not happen
        }
    }

    private void initializeFrame() {
        panelCont = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);

        // add Icon
        ImageIcon image = new ImageIcon("data/label_warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        panelCont.setLayout(cards);
        this.add(panelCont);

        addClosingListener();
    }

    // ask if the user wna tot save when closing
    private void addClosingListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to save the current user?",
                        "Saved?", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
                    try {
                        jsonWriter.open();
                        jsonWriter.write(user);
                        jsonWriter.close();
                        System.out.println("Saved " + user.getName() + " to " + JSON_STORE);
                    } catch (FileNotFoundException e1) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                }
            }
        });

    }

    private void initializeBeginningPanel() {
        beginningPanel = new JPanel();
        updateBeginningPanel();
    }

    // paint the beginning panel again
    private void updateBeginningPanel() {
        beginningPanel.removeAll();
        beginningPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 70));

        JLabel label = new JLabel("Manage all your inventory with ease!", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 41));
        beginningPanel.add(label);

        // Button to load from file
        JButton buttonToLoad = new JButton();
        buttonToLoad.setText("             I am " + user.getName() + "            ");
        buttonToLoad.setFont(new Font("Comic Sans", Font.BOLD, 25));
        buttonToLoad.setBackground(Color.WHITE);
        buttonToLoad.addActionListener(e -> cards.show(panelCont, "2"));

        // Button to create a new user
        JButton buttonToCreate = new JButton();
        buttonToCreate.setText("Create a new user account");
        buttonToCreate.setFont(new Font("Comic Sans", Font.BOLD, 25));
        buttonToCreate.setBackground(Color.WHITE);
        buttonToCreate.addActionListener(e -> cards.show(panelCont,"9"));

        beginningPanel.add(buttonToLoad);
        beginningPanel.add(buttonToCreate);
    }

    // initialize log in panel
    private void initializeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(null);
        addComponentsToLogInPanel();

        final JPasswordField passwordField = new JPasswordField(user.getPassword());
        passwordField.setBounds(500, 145, 100, 30);
        passwordField.setFont(new Font("MV Boli", Font.BOLD, 20));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 220, 200, 40);
        loginButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        loginButton.setBackground(Color.WHITE);
        // listener for login
        char[] input = passwordField.getPassword();
        char[] correctPass = user.getPassword().toCharArray();
        loginButton.addActionListener(e -> {
            if (Arrays.equals(correctPass, input)) {
                cards.show(panelCont, "3");
            } else {
                JOptionPane.showMessageDialog(logInPanel, "wrong password!");
            }
        });

        logInPanel.add(passwordField);
        logInPanel.add(loginButton);

    }

    // add JLabels and JButtons to login Panel
    private void addComponentsToLogInPanel() {
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(300, 80, 150, 30);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("MV Boli", Font.BOLD, 20));

        JLabel name = new JLabel(user.getName());
        name.setBounds(500, 80, 100, 30);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("MV Boli", Font.BOLD, 20));

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(300, 145, 150, 30);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("MV Boli", Font.BOLD, 20));

        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 300, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "1"));

        logInPanel.add(l1);
        logInPanel.add(l2);
        logInPanel.add(name);
        logInPanel.add(backButton);
    }

    // EFFECTS: initialize the main menu
    private void initializeMainMenuPanel() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 30));

        JLabel label = new JLabel("Main menu", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 41));
        mainMenuPanel.add(label);

        addButtonsToMainMenu();
    }

    // EFFECTS: add JButtons to main menu
    private void addButtonsToMainMenu() {
        // Button to purchase
        JButton buttonToBuy = getButtonToBuy();

        JButton buttonToSell = new JButton();
        buttonToSell.setText("           Sell           ");
        buttonToSell.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buttonToSell.setBackground(Color.WHITE);
        buttonToSell.addActionListener(e -> cards.show(panelCont, "7"));

        JButton buttonCheckInventory = new JButton();
        buttonCheckInventory.setText("     View goods     ");
        buttonCheckInventory.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buttonCheckInventory.setBackground(Color.WHITE);
        buttonCheckInventory.addActionListener(e -> cards.show(panelCont, "4"));

        JButton buttonCheckTransactions = new JButton();
        buttonCheckTransactions.setText("View transactions");
        buttonCheckTransactions.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buttonCheckTransactions.setBackground(Color.WHITE);
        buttonCheckTransactions.addActionListener(e -> cards.show(panelCont, "5"));

        mainMenuPanel.add(buttonToBuy);
        mainMenuPanel.add(buttonToSell);
        mainMenuPanel.add(buttonCheckInventory);
        mainMenuPanel.add(buttonCheckTransactions);
    }

    // EFFECTS: add helpers to get button to buy for main menu
    private JButton getButtonToBuy() {
        JButton buttonToBuy = new JButton();
        buttonToBuy.setText("      Purchase      ");
        buttonToBuy.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buttonToBuy.setBackground(Color.WHITE);
        buttonToBuy.addActionListener(e -> cards.show(panelCont, "8"));
        return buttonToBuy;
    }

    // EFFECTS: initialize goods menu
    private void initializeGoodsMenuPanel() {
        goodsMenuPanel = new JPanel();
        updateGoodsMenu();
    }

    // EFFECTS: add action listener to JTable
    private void addTableListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if (row >= 0 && column >= 0) {
                        updateGoodsDetailPanel(row);
                        cards.show(panelCont, "6");
                        System.out.println(row);
                    }
                }
            }
        });
    }

    // EFFECTS: repaint the good details panel
    private void updateGoodsDetailPanel(int row) {
        goodsDetailPanel.removeAll();

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 170, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "4"));

        Goods goods = warehouse.getGoodsInWarehouseMenu().get(row);
        JLabel label = new JLabel("Name: " + goods.getName());
        label.setForeground(Color.BLACK);
        label.setFont(new Font("MV Boli", Font.BOLD, 41));
        JLabel label1 = new JLabel("Quantity: " + goods.getQuantityInStock());
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("MV Boli", Font.BOLD, 41));
        JLabel label2 = new JLabel("Average cost: " + goods.getAverageCost());
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("MV Boli", Font.BOLD, 41));
        JLabel label3 = new JLabel("Total value: " + goods.getTotalCostInStock());
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("MV Boli", Font.BOLD, 41));
        goodsDetailPanel.add(label);
        goodsDetailPanel.add(label1);
        goodsDetailPanel.add(label2);
        goodsDetailPanel.add(label3);
        goodsDetailPanel.add(backButton);
    }

    // EFFECTS: initialize goods detail panel
    private void initializeGoodsDetailPanel() {
        goodsDetailPanel = new JPanel();
        goodsDetailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
    }

    // EFFECTS: initialize transaction panel
    private void initializeTransactionPanel() {
        transactionsPanel = new JPanel();
        updateTransactions();
    }

    // EFFECTS: initialize sell goods panel
    private void initializeSellGoodsPanel() {
        sellGoodsPanel = new JPanel();
        updateSellGoodsPanel();
    }

    // EFFECTS: update sell goods panel
    private void updateSellGoodsPanel() {
        if ((warehouse.getGoodsInWarehouseMenu().isEmpty())) {
            goodsToSell = null;

        } else {
            goodsToSell = warehouse.getGoodsInWarehouseMenu().get(0);
        }


        sellGoodsPanel.removeAll();
        addComponentsToSellGoodsPanel();

        JLabel quantityLeft = getJLabel();
        addComboBox(quantityLeft);

        JTextField quantityToSell = new JTextField();
        quantityToSell.setBounds(500, 180, 100, 30);
        quantityToSell.setForeground(Color.BLACK);
        quantityToSell.setFont(new Font("Serif", Font.BOLD, 20));
        sellGoodsPanel.add(quantityToSell);

        JTextField priceSell = new JTextField();
        priceSell.setBounds(500, 230, 100, 30);
        priceSell.setForeground(Color.BLACK);
        priceSell.setFont(new Font("Serif", Font.BOLD, 20));
        sellGoodsPanel.add(priceSell);

        addConfirmButton(quantityLeft, quantityToSell, priceSell);
    }

    // EFFECTS: add confirm button to sell panel
    private void addConfirmButton(JLabel quantityLeft, JTextField quantityToSell, JTextField priceSell) {
        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(370, 300, 200, 30);
        confirmButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            doSell(quantityToSell, priceSell, quantityLeft);
            updateGoodsMenu();
            updateTransactions();

        });
        sellGoodsPanel.add(confirmButton);
    }

    // EFFECTS: repaint the transaction panel
    private void updateTransactions() {
        transactionsPanel.removeAll();
        transactionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

        JLabel label = new JLabel("Transactions", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 41));

        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Details"}, 0));
        table.setFont(new Font("Serif", Font.BOLD, 20));
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < this.warehouse.getTransactionRecords().size(); i++) {
            String records = this.warehouse.getTransactionRecords().get(i);
            model.addRow(new Object[]{
                    records});
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 170, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "3"));

        transactionsPanel.add(label);
        transactionsPanel.add(scrollPane);
        transactionsPanel.add(backButton);
    }

    // EFFECTS: repaint the goods menu
    private void updateGoodsMenu() {
        goodsMenuPanel.removeAll();

        goodsMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

        JTable table = new JTable(new DefaultTableModel(new Object[]{"#", "Name", "Quantity"}, 0));
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 15));
        table.setFont(new Font("Serif", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
            Goods goods = this.warehouse.getGoodsInWarehouseMenu().get(i);
            model.addRow(new Object[]{
                    i + 1, goods.getName(), goods.getQuantityInStock()});
        }
        addTableListener(table);

        JLabel label = new JLabel("Goods in stock", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 41));
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 170, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "3"));

        goodsMenuPanel.add(label);
        goodsMenuPanel.add(scrollPane);
        goodsMenuPanel.add(backButton);
    }

    // EFFECTS: initialize purchase panel
    private void initializePurchaseGoodsPanel() {
        purchaseGoodsPanel = new JPanel();
        purchaseGoodsPanel.setLayout(null);

        addTitleAndBackToPurchasePanel();

        JTextField quantityToPurchase = new JTextField();
        quantityToPurchase.setBounds(500, 180, 100, 30);
        quantityToPurchase.setForeground(Color.BLACK);
        quantityToPurchase.setFont(new Font("Serif", Font.BOLD, 20));
        purchaseGoodsPanel.add(quantityToPurchase);

        JTextField cost = new JTextField();
        cost.setBounds(500, 230, 100, 30);
        cost.setForeground(Color.BLACK);
        cost.setFont(new Font("Serif", Font.BOLD, 20));
        purchaseGoodsPanel.add(cost);

        JTextField name = new JTextField();
        name.setBounds(500, 130, 100, 30);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Serif", Font.BOLD, 20));
        purchaseGoodsPanel.add(name);

        addLabelsToPurchasePanel();

        addConfirmButtonToPurchasePanel(quantityToPurchase, cost, name);
    }

    // EFFECTS: add confirm button to purchase panel
    private void addConfirmButtonToPurchasePanel(JTextField quantityToPurchase, JTextField cost, JTextField name) {
        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(370, 300, 200, 30);
        confirmButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(e -> {

            try {
                String s1 = name.getText();
                name.setText("");

                String s2 = quantityToPurchase.getText();
                int quantity = Integer.parseInt(s2);
                quantityToPurchase.setText("");

                String s3 = cost.getText();
                double price = Double.parseDouble(s3);
                cost.setText("");

                warehouse.purchaseGoods(s1,quantity,price);
                updateTransactions();
                updateGoodsMenu();
                updateSellGoodsPanel();
            } catch (InvalidNumberException ex) {
                JOptionPane.showMessageDialog(sellGoodsPanel, "bad input!");
            }
        });
        purchaseGoodsPanel.add(confirmButton);
    }

    // EFFECTS: add labels to purchase panel
    private void addLabelsToPurchasePanel() {
        JLabel l1 = new JLabel("Enter quantity: ");
        l1.setBounds(300, 180, 200, 30);
        l1.setFont(new Font("MV Boli", Font.BOLD, 20));
        purchaseGoodsPanel.add(l1);

        JLabel l2 = new JLabel("Enter cost:");
        l2.setBounds(300, 230, 200, 30);
        l2.setFont(new Font("MV Boli", Font.BOLD, 20));
        purchaseGoodsPanel.add(l2);

        JLabel l3 = new JLabel("Enter name:");
        l3.setBounds(300, 130, 200, 30);
        l3.setFont(new Font("MV Boli", Font.BOLD, 20));
        purchaseGoodsPanel.add(l3);
    }

    // EFFECTS: add title and buttons to purchase panel
    private void addTitleAndBackToPurchasePanel() {
        JLabel label = new JLabel("Purchase");
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 40));
        label.setBounds(390, 50, 300, 50);
        purchaseGoodsPanel.add(label);

        JButton backButton = new JButton("Back");
        backButton.setBounds(420, 400, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "3"));
        purchaseGoodsPanel.add(backButton);
    }

    // EFFECTS: add Combo box to sell panel
    private void addComboBox(JLabel quantityLeft) {
        JComboBox comboBox = new JComboBox(getGoodsName());
        comboBox.setFont(new Font("Serif", Font.BOLD, 20));
        comboBox.setBounds(500, 130, 100, 40);
        sellGoodsPanel.add(comboBox);
        comboBox.addActionListener(e -> {
            int index = comboBox.getSelectedIndex();
            this.goodsToSell = warehouse.getGoodsInWarehouseMenu().get(index);
            quantityLeft.setText(goodsToSell.getQuantityInStock() + " left");

        });
    }

    // EFFECTS: get the label for left quantity
    private JLabel getJLabel() {
        if (goodsToSell != null) {
            JLabel quantityLeft = new JLabel(goodsToSell.getQuantityInStock() + " left");
            quantityLeft.setFont(new Font("MV Boli", Font.BOLD, 20));
            quantityLeft.setBounds(650, 180, 100, 30);
            sellGoodsPanel.add(quantityLeft);
            return quantityLeft;
        } else {
            return null;
        }

    }

    // EFFECTS: do sell after clicking the sell button
    private void doSell(JTextField quantityToSell, JTextField priceSell, JLabel quantityLeft) {
        String s1 = quantityToSell.getText();
        quantityToSell.setText("");
        int quantity = Integer.parseInt(s1);
        String s2 = priceSell.getText();
        priceSell.setText("");
        double price = Double.parseDouble(s2);
        try {
            warehouse.sellGoods(goodsToSell.getName(), quantity, price);
        } catch (InvalidNumberException e) {
            JOptionPane.showMessageDialog(sellGoodsPanel, "bad input!");
        }
        quantityLeft.setText(goodsToSell.getQuantityInStock() + " left");
    }

    // EFFECTS: add buttons and labels to sell goods panel
    private void addComponentsToSellGoodsPanel() {

        sellGoodsPanel.setLayout(null);

        JLabel label = new JLabel(" Sell ");
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 40));
        label.setBounds(400, 50, 300, 50);
        sellGoodsPanel.add(label);

        JLabel l1 = new JLabel("Enter quantity: ");
        l1.setBounds(300, 180, 200, 30);
        l1.setFont(new Font("MV Boli", Font.BOLD, 20));
        sellGoodsPanel.add(l1);

        JLabel l2 = new JLabel("Enter price:");
        l2.setBounds(300, 230, 200, 30);
        l2.setFont(new Font("MV Boli", Font.BOLD, 20));
        sellGoodsPanel.add(l2);

        JLabel l3 = new JLabel("Select from: ");
        l3.setBounds(300, 130, 200, 30);
        l3.setFont(new Font("MV Boli", Font.BOLD, 20));
        sellGoodsPanel.add(l3);

        JButton backButton = new JButton("Back");
        backButton.setBounds(420, 400, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "3"));
        sellGoodsPanel.add(backButton);
    }

    // EFFECTS: get goods name as string
    private String[] getGoodsName() {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
            Goods goods = this.warehouse.getGoodsInWarehouseMenu().get(i);
            String name = goods.getName();
            listOfNames.add(name);
        }
        return listOfNames.toArray(new String[0]);
    }

    //EFFECTS: initialize create user panel
    private void initializeCreateUserPanel() {
        createUserPanel = new JPanel();
        createUserPanel.setLayout(null);

        addTitleAndBackToCreateUserPanel();

        JTextField userName = new JTextField();
        userName.setBounds(500, 130, 100, 30);
        userName.setForeground(Color.BLACK);
        userName.setFont(new Font("Serif", Font.BOLD, 20));
        createUserPanel.add(userName);

        JTextField userPassword = new JTextField();
        userPassword.setBounds(500, 180, 100, 30);
        userPassword.setForeground(Color.BLACK);
        userPassword.setFont(new Font("Serif", Font.BOLD, 20));
        createUserPanel.add(userPassword);

        JTextField userEmail = new JTextField();
        userEmail.setBounds(500, 230, 100, 30);
        userEmail.setForeground(Color.BLACK);
        userEmail.setFont(new Font("Serif", Font.BOLD, 20));
        createUserPanel.add(userEmail);

        addLabelToCreateUserPanel();

        addConfirmButtonToCreateUserPanel(userName, userPassword, userEmail);
    }

    // EFFECTS: add title and back button to create user panel
    private void addTitleAndBackToCreateUserPanel() {
        JLabel label = new JLabel("Create user");
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli", Font.BOLD, 40));
        label.setBounds(390, 50, 300, 50);
        createUserPanel.add(label);

        JButton backButton = new JButton("Back");
        backButton.setBounds(420, 400, 80, 30);
        backButton.addActionListener(e -> cards.show(panelCont, "1"));
        createUserPanel.add(backButton);
    }

    // EFFECTS: add confirm button to create user panel
    private void addConfirmButtonToCreateUserPanel(JTextField userName,
                                                   JTextField userPassword, JTextField userEmail) {
        JButton confirmButton = new JButton("create");
        confirmButton.setBounds(370, 300, 200, 30);
        confirmButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            String name = userName.getText();
            userName.setText("");
            String password = userPassword.getText();
            userPassword.setText("");
            String email = userEmail.getText();
            userEmail.setText("");
            User newUser = new User(name,email,password);
            Warehouse newWarehouse = new Warehouse("newWarehouse");
            newUser.addWarehouse(newWarehouse);
            user = newUser;
            warehouse = newWarehouse;

            updateAfterCreateNewUser();
        });

        createUserPanel.add(confirmButton);
    }

    // EFFECTS: update the panels after create a new user
    private void updateAfterCreateNewUser() {
        updateGoodsMenu();
        updateTransactions();
        updateSellGoodsPanel();
        updateBeginningPanel();
        cards.show(panelCont, "3");
    }

    // EFFECTS: add labels to create user panel
    private void addLabelToCreateUserPanel() {
        JLabel l1 = new JLabel("Enter username: ");
        l1.setBounds(300, 130, 200, 30);
        l1.setFont(new Font("MV Boli", Font.BOLD, 20));
        createUserPanel.add(l1);

        JLabel l2 = new JLabel("Enter password:");
        l2.setBounds(300, 180, 200, 30);
        l2.setFont(new Font("MV Boli", Font.BOLD, 20));
        createUserPanel.add(l2);

        JLabel l3 = new JLabel("Enter email:");
        l3.setBounds(300, 230, 200, 30);
        l3.setFont(new Font("MV Boli", Font.BOLD, 20));
        createUserPanel.add(l3);
    }

}

