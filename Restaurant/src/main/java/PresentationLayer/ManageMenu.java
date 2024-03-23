package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ManageMenu extends JFrame {
    private JTextField nameField, priceField, stockField;
    private AdminManager adminManager;

    public ManageMenu(RestaurantDAO restaurantDAO) {
        super("Manage Menu");
        adminManager = new AdminManager(restaurantDAO);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceField = new JTextField();
        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setFont(new Font("Arial", Font.BOLD, 14));
        stockField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(stockLabel);
        panel.add(stockField);

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.BLUE);
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.BLUE);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton showAllButton = new JButton("Show all items");
        showAllButton.setBackground(Color.BLUE);
        showAllButton.setForeground(Color.WHITE);
        showAllButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                adminManager.addMenuItem(name, price, stock);
                JOptionPane.showMessageDialog(ManageMenu.this, "Menu item added!");
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double newPrice = Double.parseDouble(priceField.getText());
                int newStock = Integer.parseInt(stockField.getText());
                adminManager.editMenuItem(name, newPrice, newStock);
                JOptionPane.showMessageDialog(ManageMenu.this, "Menu item edited!");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                adminManager.deleteMenuItem(name);
                JOptionPane.showMessageDialog(ManageMenu.this, "Menu item deleted!");
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<String, Integer> menuItems = adminManager.getAllMenuItems();

                StringBuilder message = new StringBuilder("All menu items:\n");
                for (Map.Entry<String, Integer> entry : menuItems.entrySet()) {
                    message.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                JOptionPane.showMessageDialog(ManageMenu.this, message.toString());
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(showAllButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
