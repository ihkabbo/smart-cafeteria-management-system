package cafeteriamanagementsystem;

public class OrderItem {

    // Attributes
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

    // Default Constructor
    public OrderItem() {
    }

    // Parameterized Constructor
    public OrderItem(int orderItemId, int orderId,
                     int itemId, int quantity, double price) {

        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    // =========================
    // Getters and Setters
    // =========================

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // =========================
    // Calculate Total
    // =========================

    public double getTotal() {
        return quantity * price;
    }

    // =========================
    // Display Information
    // =========================

    public void displayOrderItemInfo() {

        System.out.println("Order Item ID: " + orderItemId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Item ID: " + itemId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("Total: " + getTotal());
    }
}