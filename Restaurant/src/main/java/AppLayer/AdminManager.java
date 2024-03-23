package AppLayer;

import DataLayer.RestaurantDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
public class AdminManager {
    private RestaurantDAO restaurantDAO;

    public AdminManager(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    public void createUser(String name, String username, String password) {
        try {
            restaurantDAO.adaugaAngajat(name, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String username, String newPassword) {
        try {
            restaurantDAO.updateParolaAngajat(username, newPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            restaurantDAO.deleteAngajat(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMenuItem(String name, double price, int stock) {
        try {
            restaurantDAO.adaugaPreparat(name, price, stock, "meniu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editMenuItem(String name, double newPrice, int newStock) {
        try {
            restaurantDAO.updatePreparat(name, newPrice, newStock, "meniu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(String name) {
        try {
            restaurantDAO.deletePreparat(name, "meniu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public Map<String, Integer> viewMostOrderedItems() {
        return restaurantDAO.getStatisticiCeleMaiComandateProduse();
    }

    public Map<String, Integer> getAllMenuItems() {
        Map<String, Integer> menuItems = new HashMap<>();
        try {
            ResultSet resultSet = restaurantDAO.getMeniu();
            while (resultSet.next()) {
                String itemName = resultSet.getString("nume");
                int stock = resultSet.getInt("stoc");
                menuItems.put(itemName, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

}
