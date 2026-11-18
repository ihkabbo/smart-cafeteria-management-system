package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Order;
import cafeteriamanagementsystem.Payment;
import cafeteriamanagementsystem.StudentSession;
import cafeteriamanagementsystem.service.OrderService;
import cafeteriamanagementsystem.service.PaymentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentForm extends JFrame {

    private JTable orderTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> paymentMethodComboBox;

    private JTextField paymentAccountField;

    private JButton payButton;
    private JButton refreshButton;
    private JButton backButton;

    private JLabel selectedOrderLabel;
    private JLabel amountLabel;
    private JLabel accountLabel;

    private int selectedOrderId = -1;
    private double selectedAmount = 0;


    public PaymentForm() {

        setTitle("Student Payment");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);


        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel("Payment");

        title.setBounds(
                360, 20, 150, 30
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        add(title);


        // =====================================================
        // ORDER TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("Order ID");
        tableModel.addColumn("Order Date");
        tableModel.addColumn("Total Amount");
        tableModel.addColumn("Status");
        tableModel.addColumn("Payment Status");


        orderTable =
                new JTable(tableModel);

        orderTable.setRowHeight(25);


        JScrollPane scrollPane =
                new JScrollPane(orderTable);

        scrollPane.setBounds(
                40, 80, 760, 250
        );

        add(scrollPane);


        // =====================================================
        // SELECTED ORDER
        // =====================================================

        JLabel selectedLabel =
                new JLabel("Selected Order:");

        selectedLabel.setBounds(
                80, 360, 120, 25
        );

        add(selectedLabel);


        selectedOrderLabel =
                new JLabel("None");

        selectedOrderLabel.setBounds(
                200, 360, 150, 25
        );

        add(selectedOrderLabel);


        // =====================================================
        // AMOUNT
        // =====================================================

        JLabel amountTitle =
                new JLabel("Bill Amount:");

        amountTitle.setBounds(
                400, 360, 100, 25
        );

        add(amountTitle);


        amountLabel =
                new JLabel("৳0.00");

        amountLabel.setBounds(
                500, 360, 150, 25
        );

        amountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        add(amountLabel);


        // =====================================================
        // PAYMENT METHOD
        // =====================================================

        JLabel methodLabel =
                new JLabel("Payment Method:");

        methodLabel.setBounds(
                80, 410, 120, 25
        );

        add(methodLabel);


        paymentMethodComboBox =
                new JComboBox<>();

        paymentMethodComboBox.addItem("Cash");
        paymentMethodComboBox.addItem("bKash");
        paymentMethodComboBox.addItem("Nagad");
        paymentMethodComboBox.addItem("Card");

        paymentMethodComboBox.setBounds(
                200, 410, 180, 30
        );

        add(paymentMethodComboBox);


        // =====================================================
        // ACCOUNT / NUMBER
        // =====================================================

        accountLabel =
                new JLabel("Account / Number:");

        accountLabel.setBounds(
                80, 455, 120, 25
        );

        add(accountLabel);


        paymentAccountField =
                new JTextField();

        paymentAccountField.setBounds(
                200, 455, 250, 30
        );

        add(paymentAccountField);


        // =====================================================
        // PAY BUTTON
        // =====================================================

        payButton =
                new JButton("Make Payment");

        payButton.setBounds(
                480, 455, 140, 30
        );

        add(payButton);


        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        refreshButton =
                new JButton("Refresh");

        refreshButton.setBounds(
                630, 455, 100, 30
        );

        add(refreshButton);


        // =====================================================
        // BACK BUTTON
        // =====================================================

        backButton =
                new JButton("Back");

        backButton.setBounds(
                680, 510, 100, 30
        );

        add(backButton);


        // =====================================================
        // INITIAL ACCOUNT FIELD
        // =====================================================

        paymentAccountField.setEnabled(false);


        // =====================================================
        // LOAD ORDERS
        // =====================================================

        loadOrders();


        // =====================================================
        // TABLE SELECTION
        // =====================================================

        orderTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selectOrder();
                    }
                });


        // =====================================================
        // PAYMENT METHOD CHANGE
        // =====================================================

        paymentMethodComboBox
                .addActionListener(e -> {

                    updateAccountField();
                });


        // =====================================================
        // PAYMENT ACTION
        // =====================================================

        payButton.addActionListener(e -> {

            makePayment();
        });


        // =====================================================
        // REFRESH ACTION
        // =====================================================

        refreshButton.addActionListener(e -> {

            selectedOrderId = -1;
            selectedAmount = 0;

            selectedOrderLabel.setText("None");
            amountLabel.setText("৳0.00");

            paymentAccountField.setText("");

            loadOrders();
        });


        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.addActionListener(e -> {

            dispose();
        });


        CafeteriaTheme.apply(this);

        setVisible(true);
    }


    // =====================================================
    // LOAD STUDENT ORDERS
    // =====================================================

    private void loadOrders() {

        tableModel.setRowCount(0);


        String studentId =
                StudentSession.getStudentId();


        if (studentId == null ||
                studentId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student information was not found.\n" +
                    "Please login again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        OrderService orderService =
                new OrderService();


        PaymentService paymentService =
                new PaymentService();


        List<Order> orders =
                orderService.getOrdersByStudentId(
                        studentId
                );


        if (orders == null) {
            return;
        }


        List<Payment> payments =
                paymentService.getAllPayments();


        for (Order order : orders) {

            String paymentStatus =
                    "UNPAID";


            for (Payment payment : payments) {

                if (payment.getOrderId()
                        == order.getOrderId()) {

                    paymentStatus =
                            payment.getPaymentStatus();

                    break;
                }
            }


            tableModel.addRow(
                    new Object[]{

                            order.getOrderId(),

                            order.getOrderDate(),

                            order.getTotalAmount(),

                            order.getStatus(),

                            paymentStatus
                    }
            );
        }
    }


    // =====================================================
    // SELECT ORDER
    // =====================================================

    private void selectOrder() {

        int row =
                orderTable.getSelectedRow();


        if (row == -1) {
            return;
        }


        selectedOrderId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(row, 0)
                                .toString()
                );


        selectedAmount =
                Double.parseDouble(
                        tableModel
                                .getValueAt(row, 2)
                                .toString()
                );


        String paymentStatus =
                tableModel
                        .getValueAt(row, 4)
                        .toString();


        selectedOrderLabel.setText(
                String.valueOf(
                        selectedOrderId
                )
        );


        amountLabel.setText(
                String.format(
                        "৳%.2f",
                        selectedAmount
                )
        );


        if ("PAID".equalsIgnoreCase(
                paymentStatus)) {

            payButton.setEnabled(false);

        } else {

            payButton.setEnabled(true);
        }


        paymentAccountField.setText("");

        updateAccountField();
    }


    // =====================================================
    // UPDATE ACCOUNT FIELD
    // =====================================================

    private void updateAccountField() {

        String method =
                paymentMethodComboBox
                        .getSelectedItem()
                        .toString();


        if (method.equalsIgnoreCase("Cash")) {

            accountLabel.setText(
                    "Account / Number:"
            );

            paymentAccountField.setText("");

            paymentAccountField.setEnabled(false);

        } else if (
                method.equalsIgnoreCase("bKash")) {

            accountLabel.setText(
                    "bKash Number:"
            );

            paymentAccountField.setEnabled(true);

        } else if (
                method.equalsIgnoreCase("Nagad")) {

            accountLabel.setText(
                    "Nagad Number:"
            );

            paymentAccountField.setEnabled(true);

        } else {

            accountLabel.setText(
                    "Card Number:"
            );

            paymentAccountField.setEnabled(true);
        }
    }


    // =====================================================
    // MAKE PAYMENT
    // =====================================================

    private void makePayment() {

        // -------------------------------------------------
        // Check order selection
        // -------------------------------------------------

        if (selectedOrderId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an order first.",
                    "No Order Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // -------------------------------------------------
        // Check selected row
        // -------------------------------------------------

        int row =
                orderTable.getSelectedRow();


        if (row == -1) {
            return;
        }


        // -------------------------------------------------
        // Check payment status
        // -------------------------------------------------

        String paymentStatus =
                tableModel
                        .getValueAt(row, 4)
                        .toString();


        if ("PAID".equalsIgnoreCase(
                paymentStatus)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This order has already been paid.",
                    "Already Paid",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        // -------------------------------------------------
        // Get payment method
        // -------------------------------------------------

        String paymentMethod =
                paymentMethodComboBox
                        .getSelectedItem()
                        .toString();


        // -------------------------------------------------
        // Get account / number
        // -------------------------------------------------

        String paymentAccount =
                paymentAccountField
                        .getText()
                        .trim();


        // -------------------------------------------------
        // Validate account number
        // -------------------------------------------------

        if (!paymentMethod.equalsIgnoreCase("Cash")) {

            if (paymentAccount.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your " +
                        paymentMethod +
                        " number.",
                        "Required Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }


        // -------------------------------------------------
        // Confirmation
        // -------------------------------------------------

        String accountText = "";

        if (!paymentMethod.equalsIgnoreCase("Cash")) {

            accountText =
                    "\nAccount/Number: " +
                    paymentAccount;
        }


        int option =
                JOptionPane.showConfirmDialog(
                        this,

                        "Order ID: " +
                        selectedOrderId +

                        "\nBill Amount: ৳" +
                        String.format(
                                "%.2f",
                                selectedAmount
                        ) +

                        "\nPayment Method: " +
                        paymentMethod +

                        accountText +

                        "\n\nConfirm payment?",

                        "Confirm Payment",

                        JOptionPane.YES_NO_OPTION
                );


        if (option != JOptionPane.YES_OPTION) {
            return;
        }


        // -------------------------------------------------
        // Get Student ID
        // -------------------------------------------------

        String studentId =
                StudentSession.getStudentId();


        if (studentId == null ||
                studentId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student information was not found.\n" +
                    "Please login again.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // -------------------------------------------------
        // Make Payment
        // -------------------------------------------------

        PaymentService paymentService =
                new PaymentService();


        boolean success =
                paymentService.makePayment(
                        selectedOrderId,
                        studentId,
                        paymentMethod,
                        paymentAccount
                );


        // -------------------------------------------------
        // Result
        // -------------------------------------------------

        if (success) {

            JOptionPane.showMessageDialog(
                    this,

                    "Payment Successful!\n" +
                    "Order ID: " +
                    selectedOrderId +
                    "\nAmount: ৳" +
                    String.format(
                            "%.2f",
                            selectedAmount
                    ),

                    "Payment Successful",

                    JOptionPane.INFORMATION_MESSAGE
            );


            selectedOrderId = -1;
            selectedAmount = 0;

            selectedOrderLabel.setText(
                    "None"
            );

            amountLabel.setText(
                    "৳0.00"
            );

            paymentAccountField.setText("");

            loadOrders();


        } else {

            JOptionPane.showMessageDialog(
                    this,

                    "Payment failed.\n" +
                    "Please try again.",

                    "Payment Failed",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}