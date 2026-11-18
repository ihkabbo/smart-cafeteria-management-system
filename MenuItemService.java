package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.dao.MenuItemDAO;

import java.util.ArrayList;

public class MenuItemService {

    private MenuItemDAO menuItemDAO;

    public MenuItemService() {
        menuItemDAO = new MenuItemDAO();
    }

    // Add Menu Item
    public boolean addMenuItem(MenuItem item) {

        if (item.getItemName() == null ||
            item.getItemName().trim().isEmpty()) {

            return false;
        }

        if (item.getPrice() < 0) {

            return false;
        }

        if (item.getCategoryId() <= 0) {

            return false;
        }

        return menuItemDAO.addMenuItem(item);
    }

    // Get All Menu Items
    public ArrayList<MenuItem> getAllMenuItems() {

        return menuItemDAO.getAllMenuItems();
    }

    // Get Menu Item By ID
    public MenuItem getMenuItemById(int itemId) {

        return menuItemDAO.getMenuItemById(itemId);
    }

    // Update Menu Item
    public boolean updateMenuItem(MenuItem item) {

        if (item.getItemName() == null ||
            item.getItemName().trim().isEmpty()) {

            return false;
        }

        if (item.getPrice() < 0) {

            return false;
        }

        if (item.getCategoryId() <= 0) {

            return false;
        }

        return menuItemDAO.updateMenuItem(item);
    }

    // Delete Menu Item
    public boolean deleteMenuItem(int itemId) {

        return menuItemDAO.deleteMenuItem(itemId);
    }
}