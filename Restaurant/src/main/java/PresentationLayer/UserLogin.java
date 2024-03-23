package PresentationLayer;

import AppLayer.OrderManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserLogin extends JFrame {
    private OrderManager orderManager;

    public UserLogin(RestaurantDAO restaurantDAO) {
        super("User");
        this.orderManager = new OrderManager(restaurantDAO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton createOrderButton = new JButton("Create order");
        createOrderButton.setBackground(Color.BLUE);
        createOrderButton.setForeground(Color.WHITE);
        createOrderButton.setFont(buttonFont);
        createOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = orderManager.createOrder();
                JOptionPane.showMessageDialog(UserLogin.this, "Order:" + id + " created!");
            }
        });
        panel.add(createOrderButton);

        JButton updateStatusButton = new JButton("Update order status");
        updateStatusButton.setBackground(Color.BLUE);
        updateStatusButton.setForeground(Color.WHITE);
        updateStatusButton.setFont(buttonFont);
        updateStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(UserLogin.this, "Enter order ID:");
                if (input != null) {
                    int orderId = Integer.parseInt(input);
                    String newStatus = JOptionPane.showInputDialog(UserLogin.this, "Enter new status:");
                    if (newStatus != null) {
                        orderManager.updateOrderStatus(orderId, newStatus);
                        JOptionPane.showMessageDialog(UserLogin.this, "Order status update!");
                    }
                }
            }
        });
        panel.add(updateStatusButton);

        JButton addItemsButton = new JButton("Add items to order");
        addItemsButton.setBackground(Color.BLUE);
        addItemsButton.setForeground(Color.WHITE);
        addItemsButton.setFont(buttonFont);
        addItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String orderIdInput = JOptionPane.showInputDialog(UserLogin.this, "Enter order ID:");
                if (orderIdInput != null) {
                    int orderId = Integer.parseInt(orderIdInput);
                    String itemName = JOptionPane.showInputDialog(UserLogin.this, "Enter item name:");
                    if (itemName != null) {
                        String quantityInput = JOptionPane.showInputDialog(UserLogin.this, "Enter quantity:");
                        if (quantityInput != null) {
                            int quantity = Integer.parseInt(quantityInput);
                            int success = orderManager.addItemsToOrder(orderId, itemName, quantity);
                            if (success == 0) JOptionPane.showMessageDialog(UserLogin.this, "Items added to order!");
                            else JOptionPane.showMessageDialog(UserLogin.this, "Not added!");
                        }
                    }
                }
            }
        });
        panel.add(addItemsButton);

        JButton deleteOrderButton = new JButton("Delete order");
        deleteOrderButton.setBackground(Color.BLUE);
        deleteOrderButton.setForeground(Color.WHITE);
        deleteOrderButton.setFont(buttonFont);
        deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(UserLogin.this, "Enter order ID:");
                if (input != null) {
                    int orderId = Integer.parseInt(input);
                    orderManager.deleteOrder(orderId);
                    JOptionPane.showMessageDialog(UserLogin.this, "Order deleted!");
                }
            }
        });
        panel.add(deleteOrderButton);

        JButton displayOrderItemsButton = new JButton("Display order items");
        displayOrderItemsButton.setBackground(Color.BLUE);
        displayOrderItemsButton.setForeground(Color.BLACK);
        displayOrderItemsButton.setFont(buttonFont);
        displayOrderItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(UserLogin.this, "Enter order ID to display:");
                if (input != null) {
                    int orderId = Integer.parseInt(input);
                    String rez = orderManager.displayOrderItems(orderId);
                    JOptionPane.showMessageDialog(UserLogin.this, rez);
                }
            }
        });
        panel.add(displayOrderItemsButton);

        JButton displayAllOrdersButton = new JButton("Display orders");
        displayAllOrdersButton.setBackground(Color.CYAN);
        displayAllOrdersButton.setForeground(Color.BLACK);
        displayAllOrdersButton.setFont(buttonFont);
        displayAllOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rezultat = orderManager.displayOrders();
                JOptionPane.showMessageDialog(UserLogin.this, rezultat);
            }
        });
        panel.add(displayAllOrdersButton);

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
