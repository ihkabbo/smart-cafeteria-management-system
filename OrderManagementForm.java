package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Order;
import cafeteriamanagementsystem.service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderManagementForm extends JFrame {

    private JTable orderTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> statusComboBox;

    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    private int selectedOrderId = -1;

    public OrderManagementForm() {

        setTitle("Order Management");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("Order Management");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(300, 20, 220, 30);

        add(title);

        // =========================
        // STATUS
        // =========================

        JLabel statusLabel = new JLabel("Status:");

        statusLabel.setBounds(40, 75, 70, 25);

        add(statusLabel);

        statusComboBox = new JComboBox<>();

        statusComboBox.addItem("PENDING");
        statusComboBox.addItem("PROCESSING");
        statusComboBox.addItem("READY");
        statusComboBox.addItem("COMPLETED");
        statusComboBox.addItem("CANCELLED");

        statusComboBox.setBounds(100, 75, 150, 25);

        add(statusComboBox);

        // =========================
        // UPDATE BUTTON
        // =========================

        updateButton = new JButton("Update");

        updateButton.setBounds(280, 75, 100, 30);

        add(updateButton);

        // =========================
        // DELETE BUTTON
        // =========================

        deleteButton = new JButton("Delete");

        deleteButton.setBounds(400, 75, 100, 30);

        add(deleteButton);

        // =========================
        // REFRESH BUTTON
        // =========================

        refreshButton = new JButton("Refresh");

        refreshButton.setBounds(520, 75, 100, 30);

        add(refreshButton);

        // =========================
        // BACK BUTTON
        // =========================

        backButton = new JButton("Back");

        backButton.setBounds(640, 75, 100, 30);

        add(backButton);

        // =========================
        // TABLE
        // =========================

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Order ID");
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Order Date");
        tableModel.addColumn("Total Amount");
        tableModel.addColumn("Status");

        orderTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(orderTable);

        scrollPane.setBounds(40, 140, 700, 300);

        add(scrollPane);

        // =========================
        // LOAD ORDERS
        // =========================

        loadOrderTable();

        // =========================
        // TABLE SELECTION
        // =========================

        orderTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = orderTable.getSelectedRow();

                if (row != -1) {

                    selectedOrderId =
                            Integer.parseInt(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                    String status =
                            tableModel
                                    .getValueAt(row, 4)
                                    .toString();

                    statusComboBox.setSelectedItem(status);
                }
            }
        });

        // =========================
        // UPDATE ACTION
        // =========================

        updateButton.addActionListener(e -> {

            if (selectedOrderId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an order first."
                );

                return;
            }

            String status =
                    statusComboBox
                            .getSelectedItem()
                            .toString();

            OrderService service =
                    new OrderService();

            if (service.updateStatus(
                    selectedOrderId,
                    status)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Order Status Updated Successfully!"
                );

                selectedOrderId = -1;

                loadOrderTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Update Order Status!"
                );
            }
        });

        // =========================
        // DELETE ACTION
        // =========================

        deleteButton.addActionListener(e -> {

            if (selectedOrderId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an order first."
                );

                return;
            }

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this order?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (option == JOptionPane.YES_OPTION) {

                OrderService service =
                        new OrderService();

                if (service.deleteOrder(selectedOrderId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Order Deleted Successfully!"
                    );

                    selectedOrderId = -1;

                    loadOrderTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Delete Order!"
                    );
                }
            }
        });

        // =========================
        // REFRESH ACTION
        // =========================

        refreshButton.addActionListener(e -> {

            selectedOrderId = -1;

            loadOrderTable();

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
    // LOAD ORDER TABLE
    // =====================================================

    private void loadOrderTable() {

        tableModel.setRowCount(0);

        OrderService service =
                new OrderService();

        List<Order> orders =
                service.getAllOrders();

        for (Order order : orders) {

            tableModel.addRow(new Object[]{

                    order.getOrderId(),
                    order.getStudentId(),
                    order.getOrderDate(),
                    order.getTotalAmount(),
                    order.getStatus()

            });
        }
    }
}