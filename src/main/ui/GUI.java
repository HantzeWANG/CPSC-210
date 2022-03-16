package ui;

import model.Goods;
import model.User;
import model.Warehouse;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;



public class GUI extends JFrame {
    User user;
    Warehouse warehouse;
    private static final String JSON_STORE = "./data/user.json";

    JPanel panelCont; // list of panels for cards
    JPanel beginningPanel;
    JPanel logInPanel;
    JPanel mainMenuPanel;
    JPanel goodsMenuPanel;
    JPanel transactionsPanel;


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

        // Cards Layout
        panelCont.add(beginningPanel,"1");
        panelCont.add(logInPanel,"2");
        panelCont.add(mainMenuPanel,"3");
        panelCont.add(goodsMenuPanel,"4");
        panelCont.add(transactionsPanel,"5");

        cards.show(panelCont,"1");


    }



    private void initializeFields() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            user = jsonReader.read();
            warehouse = user.getWareHouses().get(0);
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
    }

    private void initializeBeginningPanel() {
        beginningPanel = new JPanel();
        beginningPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1000,70));

        JLabel label = new JLabel("Manage all your inventory with ease!", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli",Font.BOLD,41));
        beginningPanel.add(label);

        // Button to load from file
        JButton buttonToLoad = new JButton();
        buttonToLoad.setText("             I am " + user.getName() + "            ");
        buttonToLoad.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToLoad.setBackground(Color.WHITE);
        buttonToLoad.addActionListener(e -> cards.show(panelCont,"2"));

        // Button to create a new user
        JButton buttonToCreate = new JButton();
        buttonToCreate.setText("Create a new user account");
        buttonToCreate.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToCreate.setBackground(Color.WHITE);
        //buttonToCreate.addActionListener(e -> cards.show(panelCont,"2"));

        beginningPanel.add(buttonToLoad);
        beginningPanel.add(buttonToCreate);
    }

    private void initializeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(null);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(300,80, 150,30);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("MV Boli",Font.BOLD,20));

        final JLabel name = new JLabel(user.getName());
        name.setBounds(500,80, 100,30);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("MV Boli",Font.BOLD,20));

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(300,145, 150,30);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("MV Boli",Font.BOLD,20));

        final JPasswordField passwordField = new JPasswordField(user.getPassword());
        passwordField.setBounds(500,145,100,30);
        passwordField.setFont(new Font("MV Boli",Font.BOLD,20));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350,220,200,40);
        loginButton.setFont(new Font("Comic Sans", Font.BOLD,20));
        loginButton.setBackground(Color.WHITE);
        // listener for login
        char[] input = passwordField.getPassword();
        char[] correctPass = new char[] {'1', '2', '3', '4', '5', '6'};
        loginButton.addActionListener(e -> {
            if (Arrays.equals(input, correctPass)) {
                cards.show(panelCont,"3");
            } else {
                JOptionPane.showMessageDialog(logInPanel,"wrong password!");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(400,300, 80,30);
        backButton.addActionListener(e -> cards.show(panelCont,"1"));

        logInPanel.add(passwordField);
        logInPanel.add(l1);
        logInPanel.add(l2);
        logInPanel.add(name);
        logInPanel.add(loginButton);
        logInPanel.add(backButton);
    }

    private void initializeMainMenuPanel() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1000,30));

        JLabel label = new JLabel("Main menu", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli",Font.BOLD,41));
        mainMenuPanel.add(label);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 6;

        // Button to purchase
        JButton buttonToBuy = new JButton();
        buttonToBuy.setText("      Purchase      ");
        buttonToBuy.setFont(new Font("Comic Sans", Font.BOLD,20));
        buttonToBuy.setBackground(Color.WHITE);

        JButton buttonToSell = new JButton();
        buttonToSell.setText("           Sell           ");
        buttonToSell.setFont(new Font("Comic Sans", Font.BOLD,20));
        buttonToSell.setBackground(Color.WHITE);

        JButton buttonCheckInventory = new JButton();
        buttonCheckInventory.setText("     View goods     ");
        buttonCheckInventory.setFont(new Font("Comic Sans", Font.BOLD,20));
        buttonCheckInventory.setBackground(Color.WHITE);
        buttonCheckInventory.addActionListener(e -> cards.show(panelCont,"4"));

        JButton buttonCheckTransactions = new JButton();
        buttonCheckTransactions.setText("View transactions");
        buttonCheckTransactions.setBounds(x,700,300,50);
        buttonCheckTransactions.setFont(new Font("Comic Sans", Font.BOLD,20));
        buttonCheckTransactions.setBackground(Color.WHITE);
        buttonCheckTransactions.addActionListener(e -> cards.show(panelCont,"5"));

        JButton backButton = new JButton("Back");
        backButton.setBounds(100,170, 80,30);
        backButton.addActionListener(e -> cards.show(panelCont,"2"));


        mainMenuPanel.add(buttonToBuy);
        mainMenuPanel.add(buttonToSell);
        mainMenuPanel.add(buttonCheckInventory);
        mainMenuPanel.add(buttonCheckTransactions);
        mainMenuPanel.add(backButton);
    }

    private void initializeGoodsMenuPanel() {
        goodsMenuPanel = new JPanel();
        goodsMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1000,10));

        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Name", "Quantity", "Average Cost", "Total Cost"}, 0));
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        table.setFont(new Font("Serif", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,300));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < this.warehouse.getGoodsInWarehouseMenu().size(); i++) {
            Goods goods = this.warehouse.getGoodsInWarehouseMenu().get(i);
            model.addRow(new Object[]{
                    goods.getName(), goods.getQuantityInStock(), goods.getAverageCost(), goods.getTotalCostInStock()});
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(100,170, 80,30);
        backButton.addActionListener(e -> cards.show(panelCont,"3"));

        JLabel label = new JLabel("Goods in stock", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli",Font.BOLD,41));

        goodsMenuPanel.add(label);
        goodsMenuPanel.add(scrollPane);
        goodsMenuPanel.add(backButton);
    }

    private void initializeTransactionPanel() {
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,1000,10));

        JLabel label = new JLabel("Transactions", JLabel.LEFT);
        label.setForeground(Color.RED);
        label.setFont(new Font("MV Boli",Font.BOLD,41));

        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Details"}, 0));
        table.setFont(new Font("Serif", Font.BOLD, 20));
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,300));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < this.warehouse.getTransactionRecords().size(); i++) {
            String records = this.warehouse.getTransactionRecords().get(i);
            model.addRow(new Object[]{
                    records});
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(100,170, 80,30);
        backButton.addActionListener(e -> cards.show(panelCont,"3"));

        transactionsPanel.add(label);
        transactionsPanel.add(scrollPane);
        transactionsPanel.add(backButton);
    }
}

