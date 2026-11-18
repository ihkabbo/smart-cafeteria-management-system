package cafeteriamanagementsystem.gui;

import javax.swing.*;
import java.awt.*;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.StudentSession;
import cafeteriamanagementsystem.User;
import cafeteriamanagementsystem.service.LoginService;
import cafeteriamanagementsystem.service.StudentService;

public class LoginForm extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton clearButton;
    private JButton registerButton;

    public LoginForm() {

        setTitle("Cafeteria Management System - Login");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel("Cafeteria Management System");

        titleLabel.setBounds(60, 20, 280, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(titleLabel);


        // =========================
        // EMAIL
        // =========================

        JLabel emailLabel =
                new JLabel("Email:");

        emailLabel.setBounds(50, 80, 100, 25);

        add(emailLabel);


        emailField =
                new JTextField();

        emailField.setBounds(150, 80, 180, 25);

        add(emailField);


        // =========================
        // PASSWORD
        // =========================

        JLabel passLabel =
                new JLabel("Password:");

        passLabel.setBounds(50, 120, 100, 25);

        add(passLabel);


        passwordField =
                new JPasswordField();

        passwordField.setBounds(150, 120, 180, 25);

        add(passwordField);


        // =========================
        // LOGIN
        // =========================

        loginButton =
                new JButton("Login");

        loginButton.setBounds(60, 180, 80, 30);

        add(loginButton);


        // =========================
        // CLEAR
        // =========================

        clearButton =
                new JButton("Clear");

        clearButton.setBounds(155, 180, 80, 30);

        add(clearButton);


        // =========================
        // REGISTER
        // =========================

        registerButton =
                new JButton("Register");

        registerButton.setBounds(250, 180, 90, 30);

        add(registerButton);


        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.addActionListener(e -> {

            String email =
                    emailField.getText().trim();

            String password =
                    new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Email and Password.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            LoginService loginService =
                    new LoginService();

            User user =
                    loginService.login(email, password);


            // =========================
            // LOGIN SUCCESS
            // =========================

            if (user != null) {

                // Save logged-in user
                StudentSession.setCurrentUser(user);


                // =========================
                // ADMIN
                // =========================

                if (user.getRole().equalsIgnoreCase("ADMIN")) {

                    dispose();

                    new AdminDashboard();

                    return;
                }


                // =========================
                // STUDENT
                // =========================

                if (user.getRole().equalsIgnoreCase("STUDENT")) {

                    StudentService studentService =
                            new StudentService();

                    Student student = null;

                    /*
                     * Find the student record using user_id.
                     */
                    for (Student s :
                            studentService.getAllStudents()) {

                        if (s.getUserId() == user.getUserId()) {

                            student = s;

                            break;
                        }
                    }


                    // Student record found
                    if (student != null) {

                        // Save student session
                        StudentSession.setCurrentStudent(student);

                        dispose();

                        new StudentDashboard();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Student record not found.",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        StudentSession.clearSession();
                    }

                    return;
                }


                // =========================
                // UNKNOWN ROLE
                // =========================

                JOptionPane.showMessageDialog(
                        this,
                        "Unknown user role.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );

                StudentSession.clearSession();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Email or Password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");

                passwordField.requestFocus();
            }

        });


        // =====================================================
        // CLEAR ACTION
        // =====================================================

        clearButton.addActionListener(e -> {

            emailField.setText("");

            passwordField.setText("");

            emailField.requestFocus();

        });


        // =====================================================
        // REGISTER ACTION
        // =====================================================

        registerButton.addActionListener(e -> {

            dispose();

            new RegistrationForm();

        });


        CafeteriaTheme.apply(this);

        setVisible(true);
    }
}