package ui;

import model.User;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GUI extends JFrame {
    User user;
    private static final String JSON_STORE = "./data/user.json";

    JPanel panelCont;
    JPanel beginningPanel;
    JPanel panel2 = new JPanel();


    JButton button2 = new JButton("back");
    CardLayout cards = new CardLayout();

    public GUI() {
        super("InventoryManagement");
        initializeFields();
        initializeFrame();
        initializeBeginningPanel();



        panel2.add(button2);

        panelCont.add(beginningPanel,"1");
        panelCont.add(panel2,"2");
        cards.show(panelCont,"1");



        button2.addActionListener(e -> cards.show(panelCont,"1"));
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

        JLabel lab1 = new JLabel("Manage all your inventory with ease!", JLabel.LEFT);
        lab1.setForeground(Color.RED);
        lab1.setFont(new Font("MV Boli",Font.BOLD,41));
        beginningPanel.add(lab1);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 6;

        // Button to load from file
        JButton buttonToLoad = new JButton();
        buttonToLoad.setText("Log in as " + user.getName());
        buttonToLoad.setBounds(x,700,300,50);
        buttonToLoad.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToLoad.setBackground(Color.WHITE);
        buttonToLoad.addActionListener(e -> cards.show(panelCont,"2"));

        // Button to create a new user
        JButton buttonToCreate = new JButton();
        buttonToCreate.setText("create a new user account");
        buttonToCreate.setBounds(x,200,300,50);
        buttonToCreate.setFont(new Font("Comic Sans", Font.BOLD,25));
        buttonToCreate.setBackground(Color.WHITE);
        buttonToCreate.addActionListener(e -> cards.show(panelCont,"2"));

        beginningPanel.add(buttonToLoad);
        beginningPanel.add(buttonToCreate);

    }
}

