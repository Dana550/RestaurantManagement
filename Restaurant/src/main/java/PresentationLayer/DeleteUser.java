package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUser extends JFrame {
    private JTextField usernameField;
    private AdminManager adminManager;

    public DeleteUser(RestaurantDAO restaurantDAO) {
        super("Delete User");
        AdminManager adminManager = new AdminManager(restaurantDAO);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(Color.BLUE);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.BLUE);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(DeleteUser.this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        adminManager.deleteUser(username);
                        JOptionPane.showMessageDialog(DeleteUser.this, "User deleted!");
                    }
                } else {
                    JOptionPane.showMessageDialog(DeleteUser.this, "Enter an username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(new JLabel());
        panel.add(deleteButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
