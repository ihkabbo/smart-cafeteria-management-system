package cafeteriamanagementsystem;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private String paymentAccount;
    private double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;

    // =========================
    // Default Constructor
    // =========================

    public Payment() {
    }

    // =========================
    // Parameterized Constructor
    // =========================

    public Payment(int paymentId,
                   int orderId,
                   String paymentMethod,
                   String paymentAccount,
                   double amount,
                   String paymentStatus,
                   LocalDateTime paymentDate) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentAccount = paymentAccount;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    // =========================
    // Getters and Setters
    // =========================

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    // =========================
    // Display Payment Info
    // =========================

    public void displayPaymentInfo() {

        System.out.println("Payment ID: " + paymentId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Account: " + paymentAccount);
        System.out.println("Amount: " + amount);
        System.out.println("Payment Status: " + paymentStatus);
        System.out.println("Payment Date: " + paymentDate);
    }
}