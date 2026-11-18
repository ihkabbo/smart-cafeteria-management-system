package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // =====================================================
    // CREATE ORDER
    // =====================================================

    public int createOrder(
            Connection con,
            String studentId,
            double totalAmount,
            String status) throws SQLException {

        String sql =
                "INSERT INTO orders " +
                "(student_id, order_date, total_amount, status) " +
                "VALUES (?, NOW(), ?, ?)";

        try (PreparedStatement pst =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, studentId);
            pst.setDouble(2, totalAmount);
            pst.setString(3, status);

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed.");
            }

            try (ResultSet rs = pst.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("Order ID could not be generated.");
    }


    // =====================================================
    // GET ALL ORDERS
    // =====================================================

    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql =
                "SELECT * FROM orders ORDER BY order_id DESC";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(
                        rs.getInt("order_id")
                );

                order.setStudentId(
                        rs.getString("student_id")
                );

                Timestamp timestamp =
                        rs.getTimestamp("order_date");

                if (timestamp != null) {
                    order.setOrderDate(
                            timestamp.toLocalDateTime()
                    );
                }

                order.setTotalAmount(
                        rs.getDouble("total_amount")
                );

                order.setStatus(
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    // =====================================================
    // GET ORDER BY ID
    // =====================================================

    public Order getOrderById(int orderId) {

        String sql =
                "SELECT * FROM orders WHERE order_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    Order order = new Order();

                    order.setOrderId(
                            rs.getInt("order_id")
                    );

                    order.setStudentId(
                            rs.getString("student_id")
                    );

                    Timestamp timestamp =
                            rs.getTimestamp("order_date");

                    if (timestamp != null) {
                        order.setOrderDate(
                                timestamp.toLocalDateTime()
                        );
                    }

                    order.setTotalAmount(
                            rs.getDouble("total_amount")
                    );

                    order.setStatus(
                            rs.getString("status")
                    );

                    return order;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // GET ORDERS BY STUDENT ID
    // =====================================================

    public List<Order> getOrdersByStudentId(String studentId) {

        List<Order> orders = new ArrayList<>();

        String sql =
                "SELECT * FROM orders " +
                "WHERE student_id=? " +
                "ORDER BY order_id DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, studentId);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    Order order = new Order();

                    order.setOrderId(
                            rs.getInt("order_id")
                    );

                    order.setStudentId(
                            rs.getString("student_id")
                    );

                    Timestamp timestamp =
                            rs.getTimestamp("order_date");

                    if (timestamp != null) {
                        order.setOrderDate(
                                timestamp.toLocalDateTime()
                        );
                    }

                    order.setTotalAmount(
                            rs.getDouble("total_amount")
                    );

                    order.setStatus(
                            rs.getString("status")
                    );

                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    // =====================================================
    // UPDATE ORDER STATUS
    // =====================================================

    public boolean updateStatus(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status=? " +
                "WHERE order_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, status);
            pst.setInt(2, orderId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // DELETE ORDER
    // =====================================================

    public boolean deleteOrder(int orderId) {

        String sql =
                "DELETE FROM orders WHERE order_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}