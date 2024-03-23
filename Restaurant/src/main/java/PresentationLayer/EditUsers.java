package PresentationLayer;

import AppLayer.AdminManager;
import DataLayer.RestaurantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditUsers extends JFrame {
    private AdminManager adminManager;

    public EditUsers(RestaurantDAO restaurantDAO) {
        super("Edit User");
        this.adminManager = new AdminManager(restaurantDAO);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel newPasswordLabel = new JLabel("New password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField newPasswordField = new JTextField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.BLUE);
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String newPassword = newPasswordField.getText();
                adminManager.editUser(username, newPassword);
                JOptionPane.showMessageDialog(EditUsers.this, "User edited!");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(new JLabel());
        panel.add(editButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        RestaurantDAO restaurantDAO = null;
//        try {
//            restaurantDAO = new RestaurantDAO("jdbc:mysql://localhost:330/restaurant", "root", "MySQL_151*");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        new EditUsers(restaurantDAO);
//    }
}
