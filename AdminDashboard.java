package cafeteriamanagementsystem.gui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard - Cafeteria Management System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel("Admin Dashboard");

        titleLabel.setBounds(
                170, 25, 200, 30
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        add(titleLabel);


        // =========================
        // MANAGE STUDENTS
        // =========================

        JButton studentButton =
                new JButton("Manage Students");

        studentButton.setBounds(
                130, 75, 220, 35
        );

        add(studentButton);

        studentButton.addActionListener(e -> {

            dispose();

            new StudentManagementForm();

        });


        // =========================
        // MANAGE CATEGORIES
        // =========================

        JButton categoryButton =
                new JButton("Manage Categories");

        categoryButton.setBounds(
                130, 120, 220, 35
        );

        add(categoryButton);

        categoryButton.addActionListener(e -> {

            dispose();

            new CategoryManagementForm();

        });


        // =========================
        // MANAGE MENU ITEMS
        // =========================

        JButton menuButton =
                new JButton("Manage Menu Items");

        menuButton.setBounds(
                130, 165, 220, 35
        );

        add(menuButton);

        menuButton.addActionListener(e -> {

            dispose();

            new MenuManagementForm();

        });
      
        // =========================
        // MANAGE STOCK
        // =========================

        JButton stockButton =
                new JButton("Manage Stock");

        stockButton.setBounds(
                130, 210, 220, 35
        );

        add(stockButton);

        stockButton.addActionListener(e -> {

            dispose();

            new StockManagementForm();

        });


        // =========================
        // VIEW ORDERS
        // =========================

        JButton orderButton =
                new JButton("View Orders");

        orderButton.setBounds(
                130, 255, 220, 35
        );

        add(orderButton);

        orderButton.addActionListener(e -> {

            dispose();

            new OrderManagementForm();

        });


        // =========================
        // VIEW PAYMENTS
        // =========================

        JButton paymentButton =
                new JButton("View Payments");

        paymentButton.setBounds(
                130, 300, 220, 35
        );

        add(paymentButton);

        paymentButton.addActionListener(e -> {

            dispose();

            new PaymentManagementForm();

        });


        // =========================
        // LOGOUT
        // =========================

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(
                130, 350, 220, 35
        );

        add(logoutButton);

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginForm();

        });


        // =========================
        // SHOW WINDOW
        // =========================

        CafeteriaTheme.apply(this);

        setVisible(true);
    }
}