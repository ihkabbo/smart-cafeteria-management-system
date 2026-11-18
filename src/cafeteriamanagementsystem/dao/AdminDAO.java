package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.Admin;
import cafeteriamanagementsystem.DatabaseConnection;

import java.sql.*;

public class AdminDAO {

    // Add Admin
    public boolean addAdmin(Admin admin) {

        String sql = "INSERT INTO admins(admin_id, user_id, designation, access_level) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, admin.getAdminId());
            pst.setInt(2, admin.getUserId());
            pst.setString(3, admin.getDesignation());
            pst.setString(4, admin.getAccessLevel());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Get Admin By ID
    public Admin getAdminById(String adminId) {

        String sql = "SELECT * FROM admins WHERE admin_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, adminId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Admin admin = new Admin();

                admin.setAdminId(rs.getString("admin_id"));
                admin.setUserId(rs.getInt("user_id"));
                admin.setDesignation(rs.getString("designation"));
                admin.setAccessLevel(rs.getString("access_level"));

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update Admin
    public boolean updateAdmin(Admin admin) {

        String sql = "UPDATE admins SET designation=?, access_level=? WHERE admin_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, admin.getDesignation());
            pst.setString(2, admin.getAccessLevel());
            pst.setString(3, admin.getAdminId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Delete Admin
    public boolean deleteAdmin(String adminId) {

        String sql = "DELETE FROM admins WHERE admin_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, adminId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}