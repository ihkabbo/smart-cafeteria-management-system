package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Order;
import cafeteriamanagementsystem.Payment;
import cafeteriamanagementsystem.dao.OrderDAO;
import cafeteriamanagementsystem.dao.PaymentDAO;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO;
    private OrderDAO orderDAO;

    public PaymentService() {

        paymentDAO = new PaymentDAO();
        orderDAO = new OrderDAO();
    }

    // =====================================================
    // MAKE PAYMENT
    // =====================================================

    public boolean makePayment(
            int orderId,
            String studentId,
            String paymentMethod,
            String paymentAccount) {

        // -------------------------------------------------
        // Validate Order ID
        // -------------------------------------------------

        if (orderId <= 0) {
            return false;
        }

        // -------------------------------------------------
        // Validate Student ID
        // -------------------------------------------------

        if (studentId == null ||
                studentId.trim().isEmpty()) {

            return false;
        }

        // -------------------------------------------------
        // Validate Payment Method
        // -------------------------------------------------

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            return false;
        }

        // -------------------------------------------------
        // Validate Mobile Payment Number
        // -------------------------------------------------

        if (paymentMethod.equalsIgnoreCase("bKash") ||
                paymentMethod.equalsIgnoreCase("Nagad")) {

            if (paymentAccount == null ||
                    paymentAccount.trim().isEmpty()) {

                return false;
            }
        }

        // -------------------------------------------------
        // Get Order
        // -------------------------------------------------

        Order order =
                orderDAO.getOrderById(orderId);

        if (order == null) {
            return false;
        }

        // -------------------------------------------------
        // Check Order Belongs To Student
        // -------------------------------------------------

        if (!studentId.equals(
                order.getStudentId())) {

            return false;
        }

        // -------------------------------------------------
        // Check Order Status
        // -------------------------------------------------

        if ("CANCELLED".equalsIgnoreCase(
                order.getStatus())) {

            return false;
        }

        // -------------------------------------------------
        // Check Existing Payment
        // -------------------------------------------------

        List<Payment> payments =
                paymentDAO.getAllPayments();

        for (Payment payment : payments) {

            if (payment.getOrderId() == orderId) {

                if ("PAID".equalsIgnoreCase(
                        payment.getPaymentStatus())) {

                    return false;
                }
            }
        }

        // -------------------------------------------------
        // Create Payment
        // -------------------------------------------------

        Payment payment =
                new Payment();

        payment.setOrderId(orderId);

        payment.setPaymentMethod(
                paymentMethod
        );

        // Cash does not need an account number
        if (paymentMethod.equalsIgnoreCase("Cash")) {

            payment.setPaymentAccount(null);

        } else {

            payment.setPaymentAccount(
                    paymentAccount.trim()
            );
        }

        // IMPORTANT:
        // Amount comes directly from the order.
        payment.setAmount(
                order.getTotalAmount()
        );

        payment.setPaymentStatus(
                "PAID"
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        // -------------------------------------------------
        // Save Payment
        // -------------------------------------------------

        boolean success =
                paymentDAO.addPayment(payment);

        // -------------------------------------------------
        // Update Order Status
        // -------------------------------------------------

        if (success) {

            orderDAO.updateStatus(
                    orderId,
                    "COMPLETED"
            );
        }

        return success;
    }


    // =====================================================
    // GET PAYMENT BY ID
    // =====================================================

    public Payment getPaymentById(int paymentId) {

        if (paymentId <= 0) {
            return null;
        }

        return paymentDAO.getPaymentById(
                paymentId
        );
    }


    // =====================================================
    // GET ALL PAYMENTS
    // =====================================================

    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();
    }


    // =====================================================
    // UPDATE PAYMENT STATUS
    // =====================================================

    public boolean updatePaymentStatus(
            int paymentId,
            String status) {

        if (paymentId <= 0) {
            return false;
        }

        if (status == null ||
                status.trim().isEmpty()) {

            return false;
        }

        return paymentDAO.updatePaymentStatus(
                paymentId,
                status
        );
    }


    // =====================================================
    // DELETE PAYMENT
    // =====================================================

    public boolean deletePayment(int paymentId) {

        if (paymentId <= 0) {
            return false;
        }

        return paymentDAO.deletePayment(
                paymentId
        );
    }
}