package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.StudentSession;
import cafeteriamanagementsystem.service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CartForm extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;

    private JLabel totalLabel;

    private JButton removeButton;
    private JButton clearButton;
    private JButton placeOrderButton;
    private JButton backButton;


    public CartForm() {

        setTitle("Shopping Cart");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel("Shopping Cart");

        titleLabel.setBounds(320, 20, 200, 30);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        add(titleLabel);


        // =====================================================
        // TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Subtotal");


        cartTable =
                new JTable(tableModel);

        cartTable.setRowHeight(25);


        JScrollPane scrollPane =
                new JScrollPane(cartTable);

        scrollPane.setBounds(
                40, 80, 700, 280
        );

        add(scrollPane);


        // =====================================================
        // TOTAL
        // =====================================================

        totalLabel =
                new JLabel("Total: ৳0.00");

        totalLabel.setBounds(
                500, 380, 200, 30
        );

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(totalLabel);


        // =====================================================
        // REMOVE BUTTON
        // =====================================================

        removeButton =
                new JButton("Remove");

        removeButton.setBounds(
                40, 430, 110, 35
        );

        add(removeButton);


        // =====================================================
        // CLEAR CART
        // =====================================================

        clearButton =
                new JButton("Clear Cart");

        clearButton.setBounds(
                170, 430, 110, 35
        );

        add(clearButton);


        // =====================================================
        // PLACE ORDER
        // =====================================================

        placeOrderButton =
                new JButton("Place Order");

        placeOrderButton.setBounds(
                430, 430, 120, 35
        );

        add(placeOrderButton);


        // =====================================================
        // BACK
        // =====================================================

        backButton =
                new JButton("Back");

        backButton.setBounds(
                570, 430, 120, 35
        );

        add(backButton);


        // =====================================================
        // LOAD CART
        // =====================================================

        loadCart();


        // =====================================================
        // REMOVE ACTION
        // =====================================================

        removeButton.addActionListener(e -> {

            removeSelectedItem();

        });


        // =====================================================
        // CLEAR ACTION
        // =====================================================

        clearButton.addActionListener(e -> {

            if (MenuViewForm.getCartItems().isEmpty()) {
                return;
            }

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to clear the cart?",
                            "Clear Cart",
                            JOptionPane.YES_NO_OPTION
                    );


            if (option == JOptionPane.YES_OPTION) {

                MenuViewForm.clearCart();

                loadCart();
            }
        });


        // =====================================================
        // PLACE ORDER ACTION
        // =====================================================

        placeOrderButton.addActionListener(e -> {

            placeOrder();

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
    // LOAD CART
    // =====================================================

    private void loadCart() {

        tableModel.setRowCount(0);


        ArrayList<MenuItem> items =
                MenuViewForm.getCartItems();


        ArrayList<Integer> quantities =
                MenuViewForm.getCartQuantities();


        double total = 0;


        for (int i = 0; i < items.size(); i++) {

            MenuItem item =
                    items.get(i);

            int quantity =
                    quantities.get(i);


            double subtotal =
                    item.getPrice() * quantity;


            total += subtotal;


            tableModel.addRow(
                    new Object[]{

                            item.getItemId(),

                            item.getItemName(),

                            item.getPrice(),

                            quantity,

                            subtotal

                    }
            );
        }


        totalLabel.setText(
                String.format(
                        "Total: ৳%.2f",
                        total
                )
        );
    }


    // =====================================================
    // REMOVE SELECTED ITEM
    // =====================================================

    private void removeSelectedItem() {

        int selectedRow =
                cartTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item first."
            );

            return;
        }


        ArrayList<MenuItem> items =
                MenuViewForm.getCartItems();


        ArrayList<Integer> quantities =
                MenuViewForm.getCartQuantities();


        if (selectedRow < items.size()) {

            items.remove(selectedRow);

            quantities.remove(selectedRow);

            loadCart();
        }
    }


    // =====================================================
    // PLACE ORDER
    // =====================================================

    private void placeOrder() {

        // Get cart
        ArrayList<MenuItem> items =
                MenuViewForm.getCartItems();

        ArrayList<Integer> quantities =
                MenuViewForm.getCartQuantities();


        // Check cart
        if (items == null ||
                items.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // Get logged-in student
        String studentId =
                StudentSession.getStudentId();


        if (studentId == null ||
                studentId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student information was not found.\n"
                    + "Please login again.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Confirmation
        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to place this order?",
                        "Confirm Order",
                        JOptionPane.YES_NO_OPTION
                );


        if (option != JOptionPane.YES_OPTION) {
            return;
        }


        // Place order
        OrderService orderService =
                new OrderService();


        boolean success =
                orderService.placeOrder(
                        studentId,
                        items,
                        quantities
                );


        // Result
        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Order placed successfully!",
                    "Order Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );


            MenuViewForm.clearCart();

            loadCart();


        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to place order.\n"
                    + "Please try again.",
                    "Order Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}