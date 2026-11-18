package cafeteriamanagementsystem.gui;

import javax.swing.*;
import java.awt.*;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.service.RegistrationService;

public class RegistrationForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    private JTextField studentIdField;
    private JTextField departmentField;
    private JTextField batchField;

    private JButton registerButton;
    private JButton backButton;

    public RegistrationForm() {

        setTitle("Student Registration");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Student Registration");
        title.setBounds(120, 20, 220, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 70, 200, 25);
        add(nameField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 110, 200, 25);
        add(emailField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 150, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(170, 150, 200, 25);
        add(phoneField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 190, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 190, 200, 25);
        add(passwordField);

        // Student ID
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 230, 100, 25);
        add(studentIdLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(170, 230, 200, 25);
        add(studentIdField);

        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 270, 100, 25);
        add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(170, 270, 200, 25);
        add(departmentField);

        // Batch
        JLabel batchLabel = new JLabel("Batch:");
        batchLabel.setBounds(50, 310, 100, 25);
        add(batchLabel);

        batchField = new JTextField();
        batchField.setBounds(170, 310, 200, 25);
        add(batchField);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(90, 380, 100, 35);
        add(registerButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(220, 380, 100, 35);
        add(backButton);

        // Register Action
        registerButton.addActionListener(e -> {

            Student student = new Student();

            student.setName(nameField.getText().trim());
            student.setEmail(emailField.getText().trim());
            student.setPhone(phoneField.getText().trim());
            student.setPassword(new String(passwordField.getPassword()));

            student.setStudentId(studentIdField.getText().trim());
            student.setDepartment(departmentField.getText().trim());
            student.setBatch(batchField.getText().trim());

            RegistrationService service = new RegistrationService();

            boolean success = service.registerStudent(student);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!"
                );

                dispose();
                new LoginForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Failed!"
                );

            }

        });

        // Back Action
        backButton.addActionListener(e -> {

            dispose();
            new LoginForm();

        });

        CafeteriaTheme.apply(this);

        setVisible(true);
    }
}