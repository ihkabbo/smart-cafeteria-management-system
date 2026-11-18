package cafeteriamanagementsystem;

public class MenuItem {

    // Attributes
    private int itemId;
    private int categoryId;
    private String itemName;
    private String description;
    private double price;
    private boolean availability;

    // Default Constructor
    public MenuItem() {
    }

    // Parameterized Constructor
    public MenuItem(int itemId, int categoryId, String itemName,
                    String description, double price, boolean availability) {

        this.itemId = itemId;
        this.categoryId = categoryId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }

    // =========================
    // Getters and Setters
    // =========================

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // =========================
    // Display Menu Item Info
    // =========================

    public void displayItemInfo() {

        System.out.println("Item ID: " + itemId);
        System.out.println("Category ID: " + categoryId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("Available: " + availability);
    }

    // =========================
    // Display Item Name in JComboBox
    // =========================

    @Override
    public String toString() {

        return itemName;
    }
}