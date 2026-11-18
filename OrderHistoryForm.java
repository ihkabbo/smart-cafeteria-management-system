package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.Order;
import cafeteriamanagementsystem.OrderItem;
import cafeteriamanagementsystem.StudentSession;
import cafeteriamanagementsystem.service.MenuItemService;
import cafeteriamanagementsystem.service.OrderItemService;
import cafeteriamanagementsystem.service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderHistoryForm extends JFrame {

    private JTable orderTable;
    private DefaultTableModel tableModel;

    private JButton detailsButton;
    private JButton refreshButton;
    private JButton backButton;

    public OrderHistoryForm() {

        setTitle("My Order History");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel("My Order History");

        titleLabel.setBounds(
                320, 20, 220, 30
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        add(titleLabel);

        // =====================================================
        // ORDER TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("Order ID");
        tableModel.addColumn("Order Date");
        tableModel.addColumn("Total Amount");
        tableModel.addColumn("Status");

        orderTable =
                new JTable(tableModel);

        orderTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(orderTable);

        scrollPane.setBounds(
                40, 80, 750, 280
        );

        add(scrollPane);

        // =====================================================
        // VIEW DETAILS BUTTON
        // =====================================================

        detailsButton =
                new JButton("View Details");

        detailsButton.setBounds(
                280, 400, 130, 35
        );

        add(detailsButton);

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        refreshButton =
                new JButton("Refresh");

        refreshButton.setBounds(
                430, 400, 120, 35
        );

        add(refreshButton);

        // =====================================================
        // BACK BUTTON
        // =====================================================

        backButton =
                new JButton("Back");

        backButton.setBounds(
                570, 400, 120, 35
        );

        add(backButton);

        // =====================================================
        // LOAD ORDERS
        // =====================================================

        loadOrderHistory();

        // =====================================================
        // DETAILS ACTION
        // =====================================================

        detailsButton.addActionListener(e -> {

            showOrderDetails();

        });

        // =====================================================
        // REFRESH ACTION
        // =====================================================

        refreshButton.addActionListener(e -> {

            loadOrderHistory();

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
    // LOAD ORDER HISTORY
    // =====================================================

    private void loadOrderHistory() {

        tableModel.setRowCount(0);

        String studentId =
                StudentSession.getStudentId();

        if (studentId == null ||
                studentId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student information was not found.\nPlease login again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        OrderService orderService =
                new OrderService();

        List<Order> orders =
                orderService.getOrdersByStudentId(
                        studentId
                );

        if (orders == null ||
                orders.isEmpty()) {

            return;
        }

        for (Order order : orders) {

            tableModel.addRow(
                    new Object[]{

                            order.getOrderId(),

                            order.getOrderDate(),

                            String.format(
                                    "৳%.2f",
                                    order.getTotalAmount()
                            ),

                            order.getStatus()

                    }
            );
        }
    }

    // =====================================================
    // SHOW ORDER DETAILS
    // =====================================================

    private void showOrderDetails() {

        int selectedRow =
                orderTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an order first.",
                    "No Order Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int orderId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );

        // =================================================
        // GET ORDER
        // =================================================

        OrderService orderService =
                new OrderService();

        Order order =
                orderService.getOrderById(orderId);

        if (order == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order information could not be found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =================================================
        // GET ORDER ITEMS
        // =================================================

        OrderItemService orderItemService =
                new OrderItemService();

        List<OrderItem> orderItems =
                orderItemService.getItemsByOrderId(
                        orderId
                );

        if (orderItems == null ||
                orderItems.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No items found for this order.",
                    "Order Details",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        // =================================================
        // MENU ITEM SERVICE
        // =================================================

        MenuItemService menuItemService =
                new MenuItemService();

        // =================================================
        // DETAILS WINDOW
        // =================================================

        JFrame detailsFrame =
                new JFrame("Order Details");

        detailsFrame.setSize(
                750, 500
        );

        detailsFrame.setLocationRelativeTo(
                this
        );

        detailsFrame.setLayout(null);

        detailsFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // =================================================
        // ORDER INFORMATION
        // =================================================

        JLabel orderInfo =
                new JLabel(
                        "Order ID: " +
                        order.getOrderId() +
                        "    |    Date: " +
                        order.getOrderDate() +
                        "    |    Status: " +
                        order.getStatus()
                );

        orderInfo.setBounds(
                30, 20, 680, 30
        );

        orderInfo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        detailsFrame.add(
                orderInfo
        );

        // =================================================
        // DETAIL TABLE
        // =================================================

        DefaultTableModel detailModel =
                new DefaultTableModel();

        detailModel.addColumn("Item Name");
        detailModel.addColumn("Quantity");
        detailModel.addColumn("Price");
        detailModel.addColumn("Subtotal");

        JTable detailTable =
                new JTable(detailModel);

        detailTable.setRowHeight(25);

        JScrollPane detailScrollPane =
                new JScrollPane(detailTable);

        detailScrollPane.setBounds(
                30, 70, 670, 280
        );

        detailsFrame.add(
                detailScrollPane
        );

        // =================================================
        // LOAD ITEM DETAILS
        // =================================================

        for (OrderItem orderItem :
                orderItems) {

            MenuItem menuItem =
                    menuItemService.getMenuItemById(
                            orderItem.getItemId()
                    );

            String itemName;

            if (menuItem != null) {

                itemName =
                        menuItem.getItemName();

            } else {

                itemName =
                        "Unknown Item";
            }

            double subtotal =
                    orderItem.getQuantity()
                    *
                    orderItem.getPrice();

            detailModel.addRow(
                    new Object[]{

                            itemName,

                            orderItem.getQuantity(),

                            String.format(
                                    "৳%.2f",
                                    orderItem.getPrice()
                            ),

                            String.format(
                                    "৳%.2f",
                                    subtotal
                            )
                    }
            );
        }

        // =================================================
        // TOTAL
        // =================================================

        JLabel totalLabel =
                new JLabel(
                        String.format(
                                "Total Amount: ৳%.2f",
                                order.getTotalAmount()
                        )
                );

        totalLabel.setBounds(
                450, 370, 250, 30
        );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        detailsFrame.add(
                totalLabel
        );

        // =================================================
        // CLOSE BUTTON
        // =================================================

        JButton closeButton =
                new JButton("Close");

        closeButton.setBounds(
                300, 410, 120, 35
        );

        detailsFrame.add(
                closeButton
        );

        closeButton.addActionListener(e -> {

            detailsFrame.dispose();

        });

        CafeteriaTheme.apply(detailsFrame);

        detailsFrame.setVisible(true);
    }
}