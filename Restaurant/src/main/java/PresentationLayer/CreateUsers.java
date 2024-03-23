package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUsers extends JFrame {
    private AdminManager adminManager;

    private JTextField nameField;
    private JTextField usernameField;
    private JTextField passwordField;

    public CreateUsers(RestaurantDAO restaurantDAO) {
        super("Users");

        this.adminManager = new AdminManager(restaurantDAO);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField = new JTextField();

        JButton createUserButton = new JButton("Create User");
        createUserButton.setBackground(Color.BLUE);
        createUserButton.setForeground(Color.WHITE);
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                adminManager.createUser(name, username, password);
                JOptionPane.showMessageDialog(CreateUsers.this, "User created!");
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(createUserButton);

        add(panel);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

//    public static void main(String[] args) {
//        RestaurantDAO restaurantDAO = null;
//        try {
//            restaurantDAO = new RestaurantDAO("jdbc:mysql://localhost:330/restaurant", "root", "MySQL_151*");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        new CreateUsers(restaurantDAO);
//    }
}
