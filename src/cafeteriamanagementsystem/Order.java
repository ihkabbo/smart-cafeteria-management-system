package cafeteriamanagementsystem;

import java.time.LocalDateTime;

public class Order {

    // Attributes
    private int orderId;
    private String studentId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(int orderId, String studentId,
                 LocalDateTime orderDate,
                 double totalAmount, String status) {

        this.orderId = orderId;
        this.studentId = studentId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // =========================
    // Getters and Setters
    // =========================

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =========================
    // Display Order Information
    // =========================

    public void displayOrderInfo() {

        System.out.println("Order ID: " + orderId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Status: " + status);
    }
}