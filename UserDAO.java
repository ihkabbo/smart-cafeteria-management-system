package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    // Add User and return generated user_id
    public int addUser(User user) {

        String sql = "INSERT INTO users(name, email, phone, password, role) VALUES (?, ?, ?, ?, ?)";


        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS)) {


            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getRole());


            int result = pst.executeUpdate();


            if(result > 0) {

                ResultSet rs = pst.getGeneratedKeys();

                if(rs.next()) {

                    return rs.getInt(1);

                }
            }


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return -1;
    }




    // Get User By Email
    public User getUserByEmail(String email) {


        String sql = "SELECT * FROM users WHERE email=?";


        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1, email);


            ResultSet rs = pst.executeQuery();


            if(rs.next()) {


                User user = new User();


                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));


                return user;

            }


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return null;

    }





    // Get User By ID

    public User getUserById(int userId) {


        String sql = "SELECT * FROM users WHERE user_id=?";


        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setInt(1, userId);


            ResultSet rs = pst.executeQuery();


            if(rs.next()) {


                User user = new User();


                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));


                return user;

            }


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return null;

    }





    // Get All Users

    public List<User> getAllUsers() {


        List<User> users = new ArrayList<>();


        String sql = "SELECT * FROM users";


        try(Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {


            while(rs.next()) {


                User user = new User();


                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));


                users.add(user);

            }


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return users;

    }





    // Delete User

    public boolean deleteUser(int userId) {


        String sql = "DELETE FROM users WHERE user_id=?";


        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setInt(1, userId);


            return pst.executeUpdate() > 0;


        } catch(SQLException e) {

            e.printStackTrace();

        }


        return false;

    }

}