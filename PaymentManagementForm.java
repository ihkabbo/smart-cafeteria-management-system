package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Payment;
import cafeteriamanagementsystem.service.PaymentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentManagementForm extends JFrame {

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> statusComboBox;

    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    private int selectedPaymentId = -1;

    public PaymentManagementForm() {

        setTitle("Payment Management");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel("Payment Management");

        title.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        title.setBounds(
                360, 20, 250, 30
        );

        add(title);

        // =========================
        // PAYMENT STATUS
        // =========================

        JLabel statusLabel =
                new JLabel("Payment Status:");

        statusLabel.setBounds(
                40, 75, 110, 25
        );

        add(statusLabel);

        statusComboBox =
                new JComboBox<>();

        statusComboBox.addItem("PENDING");
        statusComboBox.addItem("PAID");
        statusComboBox.addItem("FAILED");
        statusComboBox.addItem("REFUNDED");

        statusComboBox.setBounds(
                150, 75, 150, 25
        );

        add(statusComboBox);

        // =========================
        // UPDATE BUTTON
        // =========================

        updateButton =
                new JButton("Update");

        updateButton.setBounds(
                320, 72, 100, 30
        );

        add(updateButton);

        // =========================
        // DELETE BUTTON
        // =========================

        deleteButton =
                new JButton("Delete");

        deleteButton.setBounds(
                440, 72, 100, 30
        );

        add(deleteButton);

        // =========================
        // REFRESH BUTTON
        // =========================

        refreshButton =
                new JButton("Refresh");

        refreshButton.setBounds(
                560, 72, 100, 30
        );

        add(refreshButton);

        // =========================
        // BACK BUTTON
        // =========================

        backButton =
                new JButton("Back");

        backButton.setBounds(
                680, 72, 100, 30
        );

        add(backButton);

        // =========================
        // TABLE
        // =========================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("Payment ID");
        tableModel.addColumn("Order ID");
        tableModel.addColumn("Method");
        tableModel.addColumn("Account / Number");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Status");
        tableModel.addColumn("Payment Date");

        paymentTable =
                new JTable(tableModel);

        paymentTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(paymentTable);

        scrollPane.setBounds(
                30, 140, 870, 300
        );

        add(scrollPane);

        // =========================
        // LOAD PAYMENTS
        // =========================

        loadPaymentTable();

        // =========================
        // TABLE SELECTION
        // =========================

        paymentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                paymentTable.getSelectedRow();

                        if (row != -1) {

                            selectedPaymentId =
                                    Integer.parseInt(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            0
                                                    )
                                                    .toString()
                                    );

                            String status =
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    5
                                            )
                                            .toString();

                            statusComboBox
                                    .setSelectedItem(
                                            status
                                    );
                        }
                    }
                });

        // =========================
        // UPDATE ACTION
        // =========================

        updateButton.addActionListener(e -> {

            if (selectedPaymentId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a payment first."
                );

                return;
            }

            String status =
                    statusComboBox
                            .getSelectedItem()
                            .toString();

            PaymentService service =
                    new PaymentService();

            if (service.updatePaymentStatus(
                    selectedPaymentId,
                    status)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Status Updated Successfully!"
                );

                selectedPaymentId = -1;

                loadPaymentTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Update Payment Status!"
                );
            }
        });

        // =========================
        // DELETE ACTION
        // =========================

        deleteButton.addActionListener(e -> {

            if (selectedPaymentId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a payment first."
                );

                return;
            }

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this payment?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (option ==
                    JOptionPane.YES_OPTION) {

                PaymentService service =
                        new PaymentService();

                if (service.deletePayment(
                        selectedPaymentId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Payment Deleted Successfully!"
                    );

                    selectedPaymentId = -1;

                    loadPaymentTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Delete Payment!"
                    );
                }
            }
        });

        // =========================
        // REFRESH ACTION
        // =========================

        refreshButton.addActionListener(e -> {

            selectedPaymentId = -1;

            loadPaymentTable();
        });

        // =========================
        // BACK ACTION
        // =========================

        backButton.addActionListener(e -> {

            dispose();

            new AdminDashboard();
        });

        CafeteriaTheme.apply(this);

        setVisible(true);
    }


    // =====================================================
    // LOAD PAYMENT TABLE
    // =====================================================

    private void loadPaymentTable() {

        tableModel.setRowCount(0);

        PaymentService service =
                new PaymentService();

        List<Payment> payments =
                service.getAllPayments();

        for (Payment payment : payments) {

            tableModel.addRow(
                    new Object[]{

                            payment.getPaymentId(),

                            payment.getOrderId(),

                            payment.getPaymentMethod(),

                            payment.getPaymentAccount(),

                            payment.getAmount(),

                            payment.getPaymentStatus(),

                            payment.getPaymentDate()
                    }
            );
        }
    }
}