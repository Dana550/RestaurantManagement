package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import AppLayer.AuthManager;
import DataLayer.RestaurantDAO;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthManager authManager;
    private JCheckBox adminCheckBox;

    public LoginGUI(RestaurantDAO restaurantDAO) {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 180);

        // Set custom font
        Font customFont = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Button.font", customFont);
        UIManager.put("Label.font", customFont);
        UIManager.put("TextField.font", customFont);

        authManager = new AuthManager(restaurantDAO);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBackground(Color.WHITE); // Set panel background color

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLUE); // Set label text color
        usernameField = new JTextField();
        usernameField.setFont(customFont); // Set field font
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLUE); // Set label text color
        passwordField = new JPasswordField();
        passwordField.setFont(customFont); // Set field font
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLUE); // Set button background color
        loginButton.setForeground(Color.WHITE); // Set button text color
        adminCheckBox = new JCheckBox("Admin");
        adminCheckBox.setForeground(Color.BLUE); // Set checkbox text color

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean loggedIn = authManager.auth(username, password);
                boolean isAdmin = adminCheckBox.isSelected();

                if (loggedIn) {
                    if (isAdmin) {
                        JOptionPane.showMessageDialog(LoginGUI.this, "Admin login!");

                        new AdminLogin(restaurantDAO);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginGUI.this, "User login!");
                        new UserLogin(restaurantDAO);

                    }
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(adminCheckBox);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(loginButton);



        getContentPane().setBackground(Color.blue);
        add(panel);
        setLocationRelativeTo(null);
        ImageIcon image;
        image = new ImageIcon("ICON.png");
        setIconImage(image.getImage());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                RestaurantDAO restaurantDAO = null;
                try {
                    restaurantDAO = new RestaurantDAO("jdbc:mysql://localhost:330/restaurant", "root", "MySQL_151*");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                new LoginGUI(restaurantDAO);
            }
        });
    }
}
