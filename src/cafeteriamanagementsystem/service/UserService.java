package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.User;
import cafeteriamanagementsystem.dao.UserDAO;

import java.util.List;

public class UserService {


    private UserDAO userDAO;


    public UserService() {

        userDAO = new UserDAO();

    }



    // Register User
    public int registerUser(User user) {

        return userDAO.addUser(user);

    }



    // Login
    public User login(String email, String password) {

        User user = userDAO.getUserByEmail(email);


        if(user != null && user.getPassword().equals(password)) {

            return user;

        }


        return null;

    }




    // Get User By ID

    public User getUserById(int userId) {

        return userDAO.getUserById(userId);

    }




    // Delete User

    public boolean deleteUser(int userId) {

        return userDAO.deleteUser(userId);

    }




    // Get All Users

    public List<User> getAllUsers() {

        return userDAO.getAllUsers();

    }


}