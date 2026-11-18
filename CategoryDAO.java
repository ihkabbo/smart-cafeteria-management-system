package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.Category;
import cafeteriamanagementsystem.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {

    // Add Category
    public boolean addCategory(Category category) {

        String sql = "INSERT INTO categories(category_name, description) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDescription());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Categories
    public ArrayList<Category> getAllCategories() {

        ArrayList<Category> list = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY category_id";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                list.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Category By ID
    public Category getCategoryById(int categoryId) {

        String sql = "SELECT * FROM categories WHERE category_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, categoryId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                return category;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Category
    public boolean updateCategory(Category category) {

        String sql = "UPDATE categories SET category_name=?, description=? WHERE category_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDescription());
            pst.setInt(3, category.getCategoryId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Category
    public boolean deleteCategory(int categoryId) {

        String sql = "DELETE FROM categories WHERE category_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, categoryId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}