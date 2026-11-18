package cafeteriamanagementsystem;

import java.time.LocalDateTime;

public class Stock {

    // Attributes
    private int stockId;
    private int itemId;
    private int quantity;
    private LocalDateTime lastUpdated;

    // Default Constructor
    public Stock() {
    }

    // Parameterized Constructor
    public Stock(int stockId, int itemId, int quantity, LocalDateTime lastUpdated) {
        this.stockId = stockId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Display stock information
    public void displayStockInfo() {
        System.out.println("Stock ID: " + stockId);
        System.out.println("Item ID: " + itemId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Last Updated: " + lastUpdated);
    }
}