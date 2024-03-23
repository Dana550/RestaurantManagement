package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

public class AdminLogin extends JFrame {
    private AdminManager adminManager;

    public AdminLogin(RestaurantDAO restaurantDAO) {
        super("Admin");

        this.adminManager = new AdminManager(restaurantDAO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(); // panel
        panel.setLayout(new GridLayout(4, 1));

        JButton manageUsersButton = new JButton("Users");
        manageUsersButton.setBackground(Color.BLUE);
        manageUsersButton.setForeground(Color.WHITE);
        manageUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageUsers(restaurantDAO);
                dispose();
            }
        });
        panel.add(manageUsersButton);

        JButton manageMenuButton = new JButton("Menu");
        manageMenuButton.setBackground(Color.BLUE);
        manageMenuButton.setForeground(Color.WHITE);
        manageMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageMenu(restaurantDAO);
            }
        });
        panel.add(manageMenuButton);

        JButton viewOrdersButton = new JButton("View orders between dates");
        viewOrdersButton.setBackground(Color.BLUE);
        viewOrdersButton.setForeground(Color.WHITE);
        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(viewOrdersButton);

        JButton viewMostOrderedButton = new JButton("View most ordered items");
        viewMostOrderedButton.setBackground(Color.BLUE);
        viewMostOrderedButton.setForeground(Color.WHITE);
        viewMostOrderedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<String, Integer> mostOrderedItems = adminManager.viewMostOrderedItems();

                StringBuilder message = new StringBuilder("Most ordered items:\n");
                for (Map.Entry<String, Integer> entry : mostOrderedItems.entrySet()) {
                    message.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                JOptionPane.showMessageDialog(AdminLogin.this, message.toString());
            }
        });
        panel.add(viewMostOrderedButton);

        add(panel);

        setLocationRelativeTo(null); // center window
        setVisible(true);
    }

    /*    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RestaurantDAO restaurantDAO = null;
                try {
                    restaurantDAO = new RestaurantDAO("jdbc:mysql://localhost:330/restaurant", "root", "MySQL_151*");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                new AdminLogin(restaurantDAO);
            }
        });
    }*/

}
