package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.dao.StudentDAO;
import cafeteriamanagementsystem.dao.UserDAO;

public class RegistrationService {

    private UserDAO userDAO;
    private StudentDAO studentDAO;

    public RegistrationService() {

        userDAO = new UserDAO();
        studentDAO = new StudentDAO();

    }

    public boolean registerStudent(Student student) {

        if (student.getName().isEmpty()) return false;
        if (student.getEmail().isEmpty()) return false;
        if (student.getPassword().isEmpty()) return false;

        student.setRole("STUDENT");

        int userId = userDAO.addUser(student);

        if (userId > 0) {

            student.setUserId(userId);

            return studentDAO.addStudent(student);
        }

        return false;
    }

}