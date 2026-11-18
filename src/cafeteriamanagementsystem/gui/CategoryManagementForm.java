package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Category;
import cafeteriamanagementsystem.service.CategoryService;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoryManagementForm extends JFrame {

    private JTextField categoryNameField;
    private JTextArea descriptionArea;

    private JTable categoryTable;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton backButton;
    
    private int selectedCategoryId = -1;

    public CategoryManagementForm() {

        setTitle("Category Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Title
        JLabel title = new JLabel("Category Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(220, 20, 250, 30);
        add(title);

        // Category Name
        JLabel nameLabel = new JLabel("Category Name:");
        nameLabel.setBounds(30, 70, 120, 25);
        add(nameLabel);

        categoryNameField = new JTextField();
        categoryNameField.setBounds(150, 70, 200, 25);
        add(categoryNameField);

        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(30, 110, 120, 25);
        add(descriptionLabel);

        descriptionArea = new JTextArea();

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setBounds(150, 110, 200, 80);
        add(descriptionScroll);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(400, 70, 100, 30);
        add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 70, 100, 30);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(400, 120, 100, 30);
        add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(520, 120, 100, 30);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(460, 170, 100, 30);
        add(backButton);

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Category");
        tableModel.addColumn("Description");

        categoryTable = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(categoryTable);
        tableScroll.setBounds(30, 220, 620, 200);
        add(tableScroll);
        
        categoryTable.getSelectionModel().addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting()) {

        int row = categoryTable.getSelectedRow();

        if (row != -1) {

            selectedCategoryId = Integer.parseInt(
                    tableModel.getValueAt(row, 0).toString()
            );

            categoryNameField.setText(
                    tableModel.getValueAt(row, 1).toString()
            );

            descriptionArea.setText(
                    tableModel.getValueAt(row, 2).toString()
            );

        }

    }

});

        // Load Existing Categories
        loadCategoryTable();

        // Add Category
        addButton.addActionListener(e -> {

            String categoryName = categoryNameField.getText().trim();
            String description = descriptionArea.getText().trim();

            if(categoryName.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Category Name."
                );

                return;
            }

            Category category = new Category();

            category.setCategoryName(categoryName);
            category.setDescription(description);

            CategoryService service = new CategoryService();

            if(service.addCategory(category)){

                JOptionPane.showMessageDialog(
                        this,
                        "Category Added Successfully!"
                );

                categoryNameField.setText("");
                descriptionArea.setText("");

                loadCategoryTable();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Add Category!"
                );
            }

        });

        // Clear
        clearButton.addActionListener(e -> {

            categoryNameField.setText("");
            descriptionArea.setText("");

        });

        // Update
       updateButton.addActionListener(e -> {

    if (selectedCategoryId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a category first."
        );

        return;
    }

    Category category = new Category();

    category.setCategoryId(selectedCategoryId);
    category.setCategoryName(categoryNameField.getText().trim());
    category.setDescription(descriptionArea.getText().trim());

    CategoryService service = new CategoryService();

    if (service.updateCategory(category)) {

        JOptionPane.showMessageDialog(
                this,
                "Category Updated Successfully!"
        );

        loadCategoryTable();

        categoryNameField.setText("");
        descriptionArea.setText("");

        selectedCategoryId = -1;

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Update Failed!"
        );

    }

});
        // Delete 
        deleteButton.addActionListener(e -> {

    if (selectedCategoryId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a category first."
        );

        return;
    }

    int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this category?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (option == JOptionPane.YES_OPTION) {

        CategoryService service = new CategoryService();

        if (service.deleteCategory(selectedCategoryId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Category Deleted Successfully!"
            );

            loadCategoryTable();

            categoryNameField.setText("");
            descriptionArea.setText("");

            selectedCategoryId = -1;

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Delete Failed!"
            );
        }
    }

});

        // Back
        backButton.addActionListener(e -> {

            dispose();
            new AdminDashboard();

        });

        CafeteriaTheme.apply(this);

        setVisible(true);
    }

    // Load Categories into JTable
    private void loadCategoryTable() {

        tableModel.setRowCount(0);

        CategoryService service = new CategoryService();

        for (Category category : service.getAllCategories()) {

            tableModel.addRow(new Object[]{

                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getDescription()

            });

        }
    }
}