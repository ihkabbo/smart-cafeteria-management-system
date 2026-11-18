package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.User;
import cafeteriamanagementsystem.dao.LoginDAO;

public class LoginService {

    private LoginDAO loginDAO;


    public LoginService() {
        loginDAO = new LoginDAO();
    }


    // Login Validation
    public User login(String username, String password) {

        if(username == null || username.isEmpty()) {
            return null;
        }

        if(password == null || password.isEmpty()) {
            return null;
        }


        return loginDAO.login(username, password);
    }
}