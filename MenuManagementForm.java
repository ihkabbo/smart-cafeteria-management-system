package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Category;
import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.service.CategoryService;
import cafeteriamanagementsystem.service.MenuItemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuManagementForm extends JFrame {

    private JTextField itemNameField;
    private JTextArea descriptionArea;
    private JTextField priceField;

    private JComboBox<Category> categoryComboBox;
    private JCheckBox availabilityCheckBox;

    private JTable menuTable;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton backButton;

    private int selectedItemId = -1;

    public MenuManagementForm() {

        setTitle("Menu Management");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("Menu Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(330, 20, 250, 30);
        add(title);

        // =========================
        // ITEM NAME
        // =========================

        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setBounds(30, 70, 120, 25);
        add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(150, 70, 220, 25);
        add(itemNameField);

        // =========================
        // CATEGORY
        // =========================

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 110, 120, 25);
        add(categoryLabel);

        categoryComboBox = new JComboBox<>();
        categoryComboBox.setBounds(150, 110, 220, 25);
        add(categoryComboBox);

        // =========================
        // DESCRIPTION
        // =========================

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(30, 150, 120, 25);
        add(descriptionLabel);

        descriptionArea = new JTextArea();

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setBounds(150, 150, 220, 70);
        add(descriptionScroll);

        // =========================
        // PRICE
        // =========================

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(30, 235, 120, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(150, 235, 220, 25);
        add(priceField);

        // =========================
        // AVAILABILITY
        // =========================

        availabilityCheckBox = new JCheckBox("Available");
        availabilityCheckBox.setBounds(150, 275, 120, 25);
        availabilityCheckBox.setSelected(true);
        add(availabilityCheckBox);

        // =========================
        // BUTTONS
        // =========================

        addButton = new JButton("Add");
        addButton.setBounds(420, 70, 100, 30);
        add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(540, 70, 100, 30);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(660, 70, 100, 30);
        add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(420, 120, 100, 30);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(540, 120, 100, 30);
        add(backButton);

        // =========================
        // TABLE
        // =========================

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Category");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");
        tableModel.addColumn("Available");

        menuTable = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(menuTable);
        tableScroll.setBounds(30, 330, 780, 190);
        add(tableScroll);

        // =========================
        // LOAD CATEGORIES
        // =========================

        loadCategories();

        // =========================
        // LOAD MENU ITEMS
        // =========================

        loadMenuTable();

        // =========================
        // TABLE ROW SELECTION
        // =========================

        menuTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = menuTable.getSelectedRow();

                if (row != -1) {

                    selectedItemId = Integer.parseInt(
                            tableModel.getValueAt(row, 0).toString()
                    );

                    String categoryName =
                            tableModel.getValueAt(row, 1).toString();

                    itemNameField.setText(
                            tableModel.getValueAt(row, 2).toString()
                    );

                    descriptionArea.setText(
                            tableModel.getValueAt(row, 3).toString()
                    );

                    priceField.setText(
                            tableModel.getValueAt(row, 4).toString()
                    );

                    boolean available =
                            Boolean.parseBoolean(
                                    tableModel.getValueAt(row, 5).toString()
                            );

                    availabilityCheckBox.setSelected(available);

                    selectCategory(categoryName);
                }
            }
        });

        // =========================
        // ADD BUTTON
        // =========================

        addButton.addActionListener(e -> {

            try {

                Category selectedCategory =
                        (Category) categoryComboBox.getSelectedItem();

                if (selectedCategory == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please select a category."
                    );

                    return;
                }

                String itemName =
                        itemNameField.getText().trim();

                String description =
                        descriptionArea.getText().trim();

                double price =
                        Double.parseDouble(priceField.getText().trim());

                if (itemName.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter item name."
                    );

                    return;
                }

                if (price < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Price cannot be negative."
                    );

                    return;
                }

                MenuItem item = new MenuItem();

                item.setCategoryId(
                        selectedCategory.getCategoryId()
                );

                item.setItemName(itemName);
                item.setDescription(description);
                item.setPrice(price);

                item.setAvailability(
                        availabilityCheckBox.isSelected()
                );

                MenuItemService service =
                        new MenuItemService();

                if (service.addMenuItem(item)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Menu Item Added Successfully!"
                    );

                    clearFields();
                    loadMenuTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Add Menu Item!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid price."
                );
            }
        });

        // =========================
        // UPDATE BUTTON
        // =========================

        updateButton.addActionListener(e -> {

            if (selectedItemId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a menu item first."
                );

                return;
            }

            try {

                Category selectedCategory =
                        (Category) categoryComboBox.getSelectedItem();

                if (selectedCategory == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please select a category."
                    );

                    return;
                }

                String itemName =
                        itemNameField.getText().trim();

                String description =
                        descriptionArea.getText().trim();

                double price =
                        Double.parseDouble(priceField.getText().trim());

                if (itemName.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter item name."
                    );

                    return;
                }

                if (price < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Price cannot be negative."
                    );

                    return;
                }

                MenuItem item = new MenuItem();

                item.setItemId(selectedItemId);

                item.setCategoryId(
                        selectedCategory.getCategoryId()
                );

                item.setItemName(itemName);
                item.setDescription(description);
                item.setPrice(price);

                item.setAvailability(
                        availabilityCheckBox.isSelected()
                );

                MenuItemService service =
                        new MenuItemService();

                if (service.updateMenuItem(item)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Menu Item Updated Successfully!"
                    );

                    clearFields();
                    loadMenuTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Update Failed!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid price."
                );
            }
        });

        // =========================
        // DELETE BUTTON
        // =========================

        deleteButton.addActionListener(e -> {

            if (selectedItemId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a menu item first."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this menu item?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                MenuItemService service =
                        new MenuItemService();

                if (service.deleteMenuItem(selectedItemId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Menu Item Deleted Successfully!"
                    );

                    clearFields();
                    loadMenuTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Failed!"
                    );
                }
            }
        });

        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.addActionListener(e -> {

            clearFields();

        });

        // =========================
        // BACK BUTTON
        // =========================

        backButton.addActionListener(e -> {

            dispose();
            new AdminDashboard();

        });

        CafeteriaTheme.apply(this);

        setVisible(true);
    }

    // =====================================================
    // LOAD CATEGORIES
    // =====================================================

    private void loadCategories() {

        categoryComboBox.removeAllItems();

        CategoryService service =
                new CategoryService();

        ArrayList<Category> categories =
                service.getAllCategories();

        for (Category category : categories) {

            categoryComboBox.addItem(category);
        }
    }

    // =====================================================
    // LOAD MENU TABLE
    // =====================================================

    private void loadMenuTable() {

        tableModel.setRowCount(0);

        MenuItemService service =
                new MenuItemService();

        ArrayList<MenuItem> items =
                service.getAllMenuItems();

        CategoryService categoryService =
                new CategoryService();

        for (MenuItem item : items) {

            Category category =
                    categoryService.getCategoryById(
                            item.getCategoryId()
                    );

            String categoryName = "";

            if (category != null) {

                categoryName =
                        category.getCategoryName();
            }

            tableModel.addRow(new Object[]{

                    item.getItemId(),
                    categoryName,
                    item.getItemName(),
                    item.getDescription(),
                    item.getPrice(),
                    item.isAvailable()

            });
        }
    }

    // =====================================================
    // SELECT CATEGORY
    // =====================================================

    private void selectCategory(String categoryName) {

        for (int i = 0;
             i < categoryComboBox.getItemCount();
             i++) {

            Category category =
                    categoryComboBox.getItemAt(i);

            if (category.getCategoryName()
                    .equals(categoryName)) {

                categoryComboBox.setSelectedIndex(i);

                break;
            }
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        itemNameField.setText("");
        descriptionArea.setText("");
        priceField.setText("");

        availabilityCheckBox.setSelected(true);

        if (categoryComboBox.getItemCount() > 0) {

            categoryComboBox.setSelectedIndex(0);
        }

        menuTable.clearSelection();

        selectedItemId = -1;
    }
}