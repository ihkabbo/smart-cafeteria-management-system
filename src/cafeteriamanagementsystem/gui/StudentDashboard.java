package cafeteriamanagementsystem.gui;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private JButton profileButton;
    private JButton menuButton;
    private JButton orderButton;
    private JButton historyButton;
    private JButton paymentButton;
    private JButton logoutButton;

    public StudentDashboard() {

        setTitle("Student Dashboard - Cafeteria Management System");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel("Student Dashboard");

        titleLabel.setBounds(
                160, 30, 220, 30
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        add(titleLabel);


        // =====================================================
        // VIEW PROFILE
        // =====================================================

        profileButton =
                new JButton("View Profile");

        profileButton.setBounds(
                130, 90, 220, 35
        );

        add(profileButton);


        // =====================================================
        // VIEW MENU
        // =====================================================

        menuButton =
                new JButton("View Menu");

        menuButton.setBounds(
                130, 140, 220, 35
        );

        add(menuButton);


        // =====================================================
        // PLACE ORDER
        // =====================================================

        orderButton =
                new JButton("Place Order");

        orderButton.setBounds(
                130, 190, 220, 35
        );

        add(orderButton);


        // =====================================================
        // ORDER HISTORY
        // =====================================================

        historyButton =
                new JButton("Order History");

        historyButton.setBounds(
                130, 240, 220, 35
        );

        add(historyButton);


        // =====================================================
        // PAYMENT
        // =====================================================

        paymentButton =
                new JButton("Payment");

        paymentButton.setBounds(
                130, 290, 220, 35
        );

        add(paymentButton);


        // =====================================================
        // LOGOUT
        // =====================================================

        logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(
                130, 340, 220, 35
        );

        add(logoutButton);


        // =====================================================
        // PROFILE ACTION
        // =====================================================

        profileButton.addActionListener(e -> {

            new StudentProfileForm();

        });


        // =====================================================
        // MENU ACTION
        // =====================================================

        menuButton.addActionListener(e -> {

            new MenuViewForm();

        });


        // =====================================================
        // PLACE ORDER ACTION
        // =====================================================

        orderButton.addActionListener(e -> {

            new MenuViewForm();

        });


        // =====================================================
        // ORDER HISTORY ACTION
        // =====================================================

        historyButton.addActionListener(e -> {

            new OrderHistoryForm();

        });


        // =====================================================
        // PAYMENT ACTION
        // =====================================================

        paymentButton.addActionListener(e -> {

            new PaymentForm();

        });


        // =====================================================
        // LOGOUT ACTION
        // =====================================================

        logoutButton.addActionListener(e -> {

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );


            if (option ==
                    JOptionPane.YES_OPTION) {

                dispose();

                new LoginForm();
            }
        });


        // =====================================================
        // SHOW WINDOW
        // =====================================================

        CafeteriaTheme.apply(this);

        setVisible(true);
    }
}