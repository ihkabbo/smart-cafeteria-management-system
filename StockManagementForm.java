package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.Stock;
import cafeteriamanagementsystem.service.MenuItemService;
import cafeteriamanagementsystem.service.StockService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StockManagementForm extends JFrame {

    private JComboBox<MenuItem> itemComboBox;
    private JTextField quantityField;

    private JTable stockTable;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JButton backButton;

    public StockManagementForm() {

        setTitle("Stock Management");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("Stock Management");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(280, 20, 220, 30);

        add(title);

        // =========================
        // MENU ITEM
        // =========================

        JLabel itemLabel = new JLabel("Menu Item:");

        itemLabel.setBounds(40, 80, 100, 25);

        add(itemLabel);

        itemComboBox = new JComboBox<>();

        itemComboBox.setBounds(140, 80, 220, 25);

        add(itemComboBox);

        // =========================
        // QUANTITY
        // =========================

        JLabel quantityLabel = new JLabel("Quantity:");

        quantityLabel.setBounds(40, 120, 100, 25);

        add(quantityLabel);

        quantityField = new JTextField();

        quantityField.setBounds(140, 120, 220, 25);

        add(quantityField);

        // =========================
        // ADD BUTTON
        // =========================

        addButton = new JButton("Add");

        addButton.setBounds(400, 80, 100, 30);

        add(addButton);

        // =========================
        // UPDATE BUTTON
        // =========================

        updateButton = new JButton("Update");

        updateButton.setBounds(520, 80, 100, 30);

        add(updateButton);

        // =========================
        // REFRESH BUTTON
        // =========================

        refreshButton = new JButton("Refresh");

        refreshButton.setBounds(400, 120, 100, 30);

        add(refreshButton);

        // =========================
        // BACK BUTTON
        // =========================

        backButton = new JButton("Back");

        backButton.setBounds(520, 120, 100, 30);

        add(backButton);

        // =========================
        // TABLE
        // =========================

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Stock ID");
        tableModel.addColumn("Item ID");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Last Updated");

        stockTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stockTable);

        scrollPane.setBounds(40, 190, 660, 230);

        add(scrollPane);

        // =========================
        // LOAD MENU ITEMS
        // =========================

        loadMenuItems();

        // =========================
        // LOAD STOCK
        // =========================

        loadStockTable();

        // =========================
        // ADD ACTION
        // =========================

        addButton.addActionListener(e -> {

            MenuItem selectedItem =
                    (MenuItem) itemComboBox.getSelectedItem();

            if (selectedItem == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a menu item."
                );

                return;
            }

            String quantityText =
                    quantityField.getText().trim();

            if (quantityText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter quantity."
                );

                return;
            }

            try {

                int quantity =
                        Integer.parseInt(quantityText);

                if (quantity < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Quantity cannot be negative."
                    );

                    return;
                }

                Stock stock = new Stock();

                stock.setItemId(
                        selectedItem.getItemId()
                );

                stock.setQuantity(quantity);

                StockService service =
                        new StockService();

                if (service.addStock(stock)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock Added Successfully!"
                    );

                    quantityField.setText("");

                    loadStockTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Add Stock!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid number."
                );
            }
        });

        // =========================
        // UPDATE ACTION
        // =========================

        updateButton.addActionListener(e -> {

            MenuItem selectedItem =
                    (MenuItem) itemComboBox.getSelectedItem();

            if (selectedItem == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a menu item."
                );

                return;
            }

            String quantityText =
                    quantityField.getText().trim();

            if (quantityText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter quantity."
                );

                return;
            }

            try {

                int quantity =
                        Integer.parseInt(quantityText);

                if (quantity < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Quantity cannot be negative."
                    );

                    return;
                }

                StockService service =
                        new StockService();

                if (service.updateQuantity(
                        selectedItem.getItemId(),
                        quantity)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock Updated Successfully!"
                    );

                    quantityField.setText("");

                    loadStockTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock not found for this menu item."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid number."
                );
            }
        });

        // =========================
        // REFRESH ACTION
        // =========================

        refreshButton.addActionListener(e -> {

            loadMenuItems();
            loadStockTable();

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
    // LOAD MENU ITEMS
    // =====================================================

    private void loadMenuItems() {

        itemComboBox.removeAllItems();

        MenuItemService service =
                new MenuItemService();

        ArrayList<MenuItem> items =
                service.getAllMenuItems();

        for (MenuItem item : items) {

            itemComboBox.addItem(item);
        }
    }

    // =====================================================
    // LOAD STOCK TABLE
    // =====================================================

    private void loadStockTable() {

        tableModel.setRowCount(0);

        StockService service =
                new StockService();

        ArrayList<Stock> stocks =
                service.getAllStock();

        for (Stock stock : stocks) {

            tableModel.addRow(new Object[]{

                    stock.getStockId(),
                    stock.getItemId(),
                    stock.getQuantity(),
                    stock.getLastUpdated()

            });
        }
    }
}