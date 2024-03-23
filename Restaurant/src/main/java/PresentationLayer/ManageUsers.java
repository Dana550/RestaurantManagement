package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManageUsers extends JFrame {
    private AdminManager adminManager;

    public ManageUsers(RestaurantDAO restaurantDAO) {
        super("Manage users");

        AdminManager adminManager = new AdminManager(restaurantDAO);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton createUserButton = new JButton("Create user");
        createUserButton.setBackground(Color.BLUE);
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateUsers(restaurantDAO);
            }
        });
        panel.add(createUserButton);

        JButton editUserButton = new JButton("Edit user");
        editUserButton.setBackground(Color.BLUE);
        editUserButton.setForeground(Color.WHITE);
        editUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditUsers(restaurantDAO);
            }
        });
        panel.add(editUserButton);

        JButton deleteUserButton = new JButton("Delete user");
        deleteUserButton.setBackground(Color.BLUE);
        deleteUserButton.setForeground(Color.WHITE);
        deleteUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteUser(restaurantDAO);
            }
        });
        panel.add(deleteUserButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*    public static void main(String[] args) {

        RestaurantDAO restaurantDAO = null;
        try {
            restaurantDAO = new RestaurantDAO("jdbc:mysql://localhost:330/restaurant", "root", "MySQL_151*");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        new ManageUsers(restaurantDAO);
    }*/
}
