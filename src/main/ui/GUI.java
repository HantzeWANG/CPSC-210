package ui;

import model.User;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GUI extends JFrame {
    User user;
    private static final String JSON_STORE = "./data/user.json";

    JPanel panelCont; // list of panels for cards
    JPanel beginningPanel;
    JPanel logInPanel;


    CardLayout cards = new CardLayout();

    public GUI() {
        super("InventoryManagement");
        initializeFields();
        initializeFrame();
        initializeBeginningPanel();
        initializeLogInPanel();

        // Cards Layout
        panelCont.add(beginningPanel,"1");
        panelCont.add(logInPanel,"2");

        cards.show(panelCont,"1");


    }

    private void initializeFields() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            user = jsonReader.read();
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 6;

        // Button to load from file
        JButton buttonToLoad = new JButton();
        buttonToLoad.setText("         Log in as " + user.getName() + "         ");
        buttonToLoad.setBounds(x,700,300,50);
        buttonToLoad.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToLoad.setBackground(Color.WHITE);
        buttonToLoad.addActionListener(e -> cards.show(panelCont,"2"));

        // Button to create a new user
        JButton buttonToCreate = new JButton();
        buttonToCreate.setText("Create a new user account");
        buttonToCreate.setBounds(x,200,300,50);
        buttonToCreate.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToCreate.setBackground(Color.WHITE);
        buttonToCreate.addActionListener(e -> cards.show(panelCont,"2"));

        beginningPanel.add(buttonToLoad);
        beginningPanel.add(buttonToCreate);

    }

    private void initializeLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(null);
//        JPasswordField password = new JPasswordField(100);
//        password.setBounds(80,100,400,50);
//        JTextField text = new JTextField();
//
//        JLabel label = new JLabel("Password for " + user.getName());
//        label.setForeground(Color.RED);
//        label.setFont(new Font("MV Boli",Font.BOLD,20));
//        label.setLabelFor(password);
//
//        JButton logInButton = new JButton("Login");
//
//        JButton backButton = new JButton("Back");
//        backButton.addActionListener(e -> cards.show(panelCont,"1"));
//
//        logInPanel.add(label);
//        logInPanel.add(password);
//        logInPanel.add(logInButton);
//        logInPanel.add(backButton);

        final JPasswordField value = new JPasswordField();
        value.setBounds(100,75,100,30);
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(20,20, 80,30);
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(20,75, 80,30);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100,120, 80,30);
        final JLabel name = new JLabel(user.getName());
        name.setBounds(100,20, 100,30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100,170, 80,30);
        backButton.addActionListener(e -> cards.show(panelCont,"1"));

        logInPanel.add(value);
        logInPanel.add(l1);
        logInPanel.add(l2);
        logInPanel.add(name);
        logInPanel.add(loginButton);
        logInPanel.add(backButton);

    }
}

