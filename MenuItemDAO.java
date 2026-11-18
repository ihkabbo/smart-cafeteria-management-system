package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.MenuItem;

import java.sql.*;
import java.util.ArrayList;

public class MenuItemDAO {

    // Add Menu Item
    public boolean addMenuItem(MenuItem item) {

        String sql = "INSERT INTO menu_items " +
                     "(category_id, item_name, description, price, availability) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, item.getCategoryId());
            pst.setString(2, item.getItemName());
            pst.setString(3, item.getDescription());
            pst.setDouble(4, item.getPrice());
            pst.setBoolean(5, item.isAvailable());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Menu Items
    public ArrayList<MenuItem> getAllMenuItems() {

        ArrayList<MenuItem> list = new ArrayList<>();

        String sql = "SELECT * FROM menu_items ORDER BY item_id";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                MenuItem item = new MenuItem();

                item.setItemId(rs.getInt("item_id"));
                item.setCategoryId(rs.getInt("category_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailability(rs.getBoolean("availability"));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Menu Item By ID
    public MenuItem getMenuItemById(int itemId) {

        String sql = "SELECT * FROM menu_items WHERE item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, itemId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                MenuItem item = new MenuItem();

                item.setItemId(rs.getInt("item_id"));
                item.setCategoryId(rs.getInt("category_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailability(rs.getBoolean("availability"));

                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Menu Item
    public boolean updateMenuItem(MenuItem item) {

        String sql = "UPDATE menu_items SET " +
                     "category_id=?, item_name=?, description=?, " +
                     "price=?, availability=? " +
                     "WHERE item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, item.getCategoryId());
            pst.setString(2, item.getItemName());
            pst.setString(3, item.getDescription());
            pst.setDouble(4, item.getPrice());
            pst.setBoolean(5, item.isAvailable());
            pst.setInt(6, item.getItemId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Menu Item
    public boolean deleteMenuItem(int itemId) {

        String sql = "DELETE FROM menu_items WHERE item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, itemId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}