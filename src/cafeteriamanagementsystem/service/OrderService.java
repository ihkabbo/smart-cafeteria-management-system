package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.MenuItem;
import cafeteriamanagementsystem.Order;
import cafeteriamanagementsystem.dao.OrderDAO;
import cafeteriamanagementsystem.dao.OrderItemDAO;
import cafeteriamanagementsystem.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public OrderService() {

        orderDAO = new OrderDAO();
        orderItemDAO = new OrderItemDAO();
    }


    // =====================================================
    // CREATE ORDER
    // =====================================================

    public int createOrder(
            Connection con,
            String studentId,
            double totalAmount,
            String status) {

        if (con == null) {
            return -1;
        }

        if (studentId == null ||
                studentId.trim().isEmpty()) {

            return -1;
        }

        if (totalAmount < 0) {
            return -1;
        }

        if (status == null ||
                status.trim().isEmpty()) {

            status = "PENDING";
        }

        try {

            return orderDAO.createOrder(
                    con,
                    studentId,
                    totalAmount,
                    status
            );

        } catch (SQLException e) {

            e.printStackTrace();

            return -1;
        }
    }


    // =====================================================
    // PLACE ORDER
    // =====================================================

    public boolean placeOrder(
            String studentId,
            ArrayList<MenuItem> items,
            ArrayList<Integer> quantities) {

        // -------------------------------------------------
        // Validate student
        // -------------------------------------------------

        if (studentId == null ||
                studentId.trim().isEmpty()) {

            return false;
        }


        // -------------------------------------------------
        // Validate cart
        // -------------------------------------------------

        if (items == null ||
                quantities == null) {

            return false;
        }


        if (items.isEmpty() ||
                quantities.isEmpty()) {

            return false;
        }


        if (items.size() != quantities.size()) {

            return false;
        }


        // -------------------------------------------------
        // Calculate total
        // -------------------------------------------------

        double totalAmount = 0;


        for (int i = 0; i < items.size(); i++) {

            MenuItem item =
                    items.get(i);

            int quantity =
                    quantities.get(i);


            if (item == null) {
                return false;
            }


            if (quantity <= 0) {
                return false;
            }


            if (item.getPrice() < 0) {
                return false;
            }


            totalAmount +=
                    item.getPrice() * quantity;
        }


        // -------------------------------------------------
        // Database transaction
        // -------------------------------------------------

        Connection con = null;


        try {

            con =
                    DatabaseConnection.getConnection();


            if (con == null) {
                return false;
            }


            // Start transaction

            con.setAutoCommit(false);


            // -------------------------------------------------
            // Create order
            // -------------------------------------------------

            int orderId =
                    orderDAO.createOrder(
                            con,
                            studentId,
                            totalAmount,
                            "PENDING"
                    );


            if (orderId <= 0) {

                con.rollback();

                return false;
            }


            // -------------------------------------------------
            // Add order items
            // -------------------------------------------------

            for (int i = 0; i < items.size(); i++) {

                MenuItem item =
                        items.get(i);

                int quantity =
                        quantities.get(i);


                boolean itemAdded =
                        orderItemDAO.addOrderItem(
                                con,
                                orderId,
                                item.getItemId(),
                                quantity,
                                item.getPrice()
                        );


                if (!itemAdded) {

                    con.rollback();

                    return false;
                }
            }


            // -------------------------------------------------
            // Everything successful
            // -------------------------------------------------

            con.commit();

            return true;


        } catch (SQLException e) {

            e.printStackTrace();


            // -------------------------------------------------
            // Rollback if something fails
            // -------------------------------------------------

            if (con != null) {

                try {

                    con.rollback();

                } catch (SQLException rollbackError) {

                    rollbackError.printStackTrace();
                }
            }


            return false;


        } finally {

            // -------------------------------------------------
            // Close connection
            // -------------------------------------------------

            if (con != null) {

                try {

                    con.setAutoCommit(true);
                    con.close();

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
    }


    // =====================================================
    // GET ALL ORDERS
    // =====================================================

    public List<Order> getAllOrders() {

        return orderDAO.getAllOrders();
    }


    // =====================================================
    // GET ORDER BY ID
    // =====================================================

    public Order getOrderById(int orderId) {

        if (orderId <= 0) {
            return null;
        }

        return orderDAO.getOrderById(orderId);
    }


    // =====================================================
    // GET ORDERS BY STUDENT ID
    // =====================================================

    public List<Order> getOrdersByStudentId(
            String studentId) {

        if (studentId == null ||
                studentId.trim().isEmpty()) {

            return null;
        }

        return orderDAO.getOrdersByStudentId(
                studentId
        );
    }


    // =====================================================
    // UPDATE ORDER STATUS
    // =====================================================

    public boolean updateStatus(
            int orderId,
            String status) {

        if (orderId <= 0) {
            return false;
        }

        if (status == null ||
                status.trim().isEmpty()) {

            return false;
        }

        return orderDAO.updateStatus(
                orderId,
                status
        );
    }


    // =====================================================
    // DELETE ORDER
    // =====================================================

    public boolean deleteOrder(int orderId) {

        if (orderId <= 0) {
            return false;
        }

        return orderDAO.deleteOrder(
                orderId
        );
    }
}