package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.service.MenuItemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuViewForm extends JFrame {

    private JTable menuTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;
    private JSpinner quantitySpinner;

    private JButton searchButton;
    private JButton refreshButton;
    private JButton addToCartButton;
    private JButton cartButton;
    private JButton backButton;

    // =========================
    // CART DATA
    // =========================

    private static ArrayList<MenuItem> cartItems =
            new ArrayList<>();

    private static ArrayList<Integer> cartQuantities =
            new ArrayList<>();


    public MenuViewForm() {

        setTitle("Cafeteria Menu");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel("Cafeteria Menu");

        titleLabel.setBounds(350, 20, 220, 30);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        add(titleLabel);


        // =========================
        // SEARCH
        // =========================

        JLabel searchLabel =
                new JLabel("Search Item:");

        searchLabel.setBounds(40, 75, 100, 25);

        add(searchLabel);


        searchField =
                new JTextField();

        searchField.setBounds(
                130, 75, 220, 25
        );

        add(searchField);


        searchButton =
                new JButton("Search");

        searchButton.setBounds(
                370, 72, 100, 30
        );

        add(searchButton);


        refreshButton =
                new JButton("Refresh");

        refreshButton.setBounds(
                490, 72, 100, 30
        );

        add(refreshButton);


        // =========================
        // TABLE
        // =========================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Category ID");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");
        tableModel.addColumn("Available");


        menuTable =
                new JTable(tableModel);

        menuTable.setRowHeight(25);


        JScrollPane scrollPane =
                new JScrollPane(menuTable);

        scrollPane.setBounds(
                40, 125, 810, 300
        );

        add(scrollPane);


        // =========================
        // QUANTITY
        // =========================

        JLabel quantityLabel =
                new JLabel("Quantity:");

        quantityLabel.setBounds(
                40, 450, 80, 25
        );

        add(quantityLabel);


        quantitySpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1, 1, 20, 1
                        )
                );

        quantitySpinner.setBounds(
                110, 450, 70, 25
        );

        add(quantitySpinner);


        // =========================
        // ADD TO CART
        // =========================

        addToCartButton =
                new JButton("Add To Cart");

        addToCartButton.setBounds(
                210, 445, 120, 35
        );

        add(addToCartButton);


        // =========================
        // VIEW CART
        // =========================

        cartButton =
                new JButton("View Cart");

        cartButton.setBounds(
                350, 445, 120, 35
        );

        add(cartButton);


        // =========================
        // BACK
        // =========================

        backButton =
                new JButton("Back");

        backButton.setBounds(
                700, 445, 120, 35
        );

        add(backButton);


        // =========================
        // LOAD MENU
        // =========================

        loadMenuTable();


        // =========================
        // SEARCH
        // =========================

        searchButton.addActionListener(
                e -> searchMenu()
        );

        searchField.addActionListener(
                e -> searchMenu()
        );


        // =========================
        // REFRESH
        // =========================

        refreshButton.addActionListener(e -> {

            searchField.setText("");

            loadMenuTable();

        });


        // =========================
        // ADD TO CART
        // =========================

        addToCartButton.addActionListener(
                e -> addSelectedItemToCart()
        );


        // =========================
        // VIEW CART
        // =========================

        cartButton.addActionListener(e -> {

            new CartForm();

        });


        // =========================
        // BACK
        // =========================

        backButton.addActionListener(e -> {

            dispose();

            new StudentDashboard();

        });


        CafeteriaTheme.apply(this);

        setVisible(true);
    }


    // =====================================================
    // LOAD MENU
    // =====================================================

    private void loadMenuTable() {

        tableModel.setRowCount(0);

        MenuItemService service =
                new MenuItemService();

        ArrayList<MenuItem> items =
                service.getAllMenuItems();


        for (MenuItem item : items) {

            if (!item.isAvailable()) {
                continue;
            }


            tableModel.addRow(new Object[]{

                    item.getItemId(),
                    item.getCategoryId(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getPrice(),
                    "Available"

            });
        }
    }


    // =====================================================
    // SEARCH MENU
    // =====================================================

    private void searchMenu() {

        String searchText =
                searchField.getText()
                        .trim()
                        .toLowerCase();


        tableModel.setRowCount(0);


        MenuItemService service =
                new MenuItemService();

        ArrayList<MenuItem> items =
                service.getAllMenuItems();


        for (MenuItem item : items) {

            if (!item.isAvailable()) {
                continue;
            }


            String itemName =
                    item.getItemName()
                            .toLowerCase();


            if (itemName.contains(searchText)) {

                tableModel.addRow(new Object[]{

                        item.getItemId(),
                        item.getCategoryId(),
                        item.getItemName(),
                        item.getDescription(),
                        item.getPrice(),
                        "Available"

                });
            }
        }
    }


    // =====================================================
    // ADD ITEM TO CART
    // =====================================================

    private void addSelectedItemToCart() {

        int selectedRow =
                menuTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a menu item first."
            );

            return;
        }


        int itemId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow, 0
                                )
                                .toString()
                );


        String itemName =
                tableModel
                        .getValueAt(
                                selectedRow, 2
                        )
                        .toString();


        double price =
                Double.parseDouble(
                        tableModel
                                .getValueAt(
                                        selectedRow, 4
                                )
                                .toString()
                );


        int quantity =
                (Integer) quantitySpinner.getValue();


        // Check if already in cart

        for (int i = 0;
             i < cartItems.size();
             i++) {

            if (cartItems
                    .get(i)
                    .getItemId() == itemId) {

                int oldQuantity =
                        cartQuantities.get(i);


                cartQuantities.set(
                        i,
                        oldQuantity + quantity
                );


                JOptionPane.showMessageDialog(
                        this,
                        quantity + " more "
                                + itemName
                                + " added to cart."
                );

                return;
            }
        }


        // Create cart item

        MenuItem item =
                new MenuItem();

        item.setItemId(itemId);
        item.setItemName(itemName);
        item.setPrice(price);


        cartItems.add(item);

        cartQuantities.add(quantity);


        JOptionPane.showMessageDialog(
                this,
                itemName + " added to cart."
        );
    }


    // =====================================================
    // GET CART ITEMS
    // =====================================================

    public static ArrayList<MenuItem> getCartItems() {

        return cartItems;
    }


    // =====================================================
    // GET CART QUANTITIES
    // =====================================================

    public static ArrayList<Integer> getCartQuantities() {

        return cartQuantities;
    }


    // =====================================================
    // CLEAR CART
    // =====================================================

    public static void clearCart() {

        cartItems.clear();

        cartQuantities.clear();
    }
}