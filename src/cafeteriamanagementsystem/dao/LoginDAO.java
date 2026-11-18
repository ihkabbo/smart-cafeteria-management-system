package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.User;

import java.sql.*;

public class LoginDAO {


    // Login Check
    public User login(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";


        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1, email);
            pst.setString(2, password);


            ResultSet rs = pst.executeQuery();


            if(rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));


                return user;
            }


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return null;
    }
}