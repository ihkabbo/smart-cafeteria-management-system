package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.StudentSession;

import javax.swing.*;
import java.awt.*;

public class StudentProfileForm extends JFrame {

    public StudentProfileForm() {

        setTitle("Student Profile");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel("Student Profile");

        titleLabel.setBounds(170, 25, 200, 30);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        add(titleLabel);


        // =========================
        // GET CURRENT STUDENT
        // =========================

        Student student =
                StudentSession.getCurrentStudent();


        if (student == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student session not found.\nPlease login again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            dispose();

            return;
        }


        // =========================
        // STUDENT ID
        // =========================

        JLabel studentIdLabel =
                new JLabel("Student ID:");

        studentIdLabel.setBounds(
                70, 90, 120, 25
        );

        add(studentIdLabel);


        JLabel studentIdValue =
                new JLabel(student.getStudentId());

        studentIdValue.setBounds(
                200, 90, 220, 25
        );

        add(studentIdValue);


        // =========================
        // NAME
        // =========================

        JLabel nameLabel =
                new JLabel("Name:");

        nameLabel.setBounds(
                70, 130, 120, 25
        );

        add(nameLabel);


        JLabel nameValue =
                new JLabel(student.getName());

        nameValue.setBounds(
                200, 130, 220, 25
        );

        add(nameValue);


        // =========================
        // EMAIL
        // =========================

        JLabel emailLabel =
                new JLabel("Email:");

        emailLabel.setBounds(
                70, 170, 120, 25
        );

        add(emailLabel);


        JLabel emailValue =
                new JLabel(student.getEmail());

        emailValue.setBounds(
                200, 170, 250, 25
        );

        add(emailValue);


        // =========================
        // PHONE
        // =========================

        JLabel phoneLabel =
                new JLabel("Phone:");

        phoneLabel.setBounds(
                70, 210, 120, 25
        );

        add(phoneLabel);


        JLabel phoneValue =
                new JLabel(student.getPhone());

        phoneValue.setBounds(
                200, 210, 220, 25
        );

        add(phoneValue);


        // =========================
        // DEPARTMENT
        // =========================

        JLabel departmentLabel =
                new JLabel("Department:");

        departmentLabel.setBounds(
                70, 250, 120, 25
        );

        add(departmentLabel);


        JLabel departmentValue =
                new JLabel(student.getDepartment());

        departmentValue.setBounds(
                200, 250, 220, 25
        );

        add(departmentValue);


        // =========================
        // BATCH
        // =========================

        JLabel batchLabel =
                new JLabel("Batch:");

        batchLabel.setBounds(
                70, 290, 120, 25
        );

        add(batchLabel);


        JLabel batchValue =
                new JLabel(student.getBatch());

        batchValue.setBounds(
                200, 290, 220, 25
        );

        add(batchValue);


        // =========================
        // BACK BUTTON
        // =========================

        JButton backButton =
                new JButton("Back");

        backButton.setBounds(
                190, 350, 120, 35
        );

        add(backButton);


        backButton.addActionListener(e -> {

            dispose();

        });


        CafeteriaTheme.apply(this);

        setVisible(true);
    }
}