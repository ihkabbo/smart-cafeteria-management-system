package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    // =====================================================
    // ADD ORDER ITEM - NORMAL VERSION
    // =====================================================

    public boolean addOrderItem(OrderItem orderItem) {

        String sql =
                "INSERT INTO order_items " +
                "(order_id, item_id, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderItem.getOrderId());
            pst.setInt(2, orderItem.getItemId());
            pst.setInt(3, orderItem.getQuantity());
            pst.setDouble(4, orderItem.getPrice());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // ADD ORDER ITEM - TRANSACTION VERSION
    // =====================================================

    public boolean addOrderItem(
            Connection con,
            int orderId,
            int itemId,
            int quantity,
            double price) throws SQLException {

        String sql =
                "INSERT INTO order_items " +
                "(order_id, item_id, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setInt(1, orderId);
            pst.setInt(2, itemId);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);

            return pst.executeUpdate() > 0;
        }
    }


    // =====================================================
    // GET ORDER ITEM BY ID
    // =====================================================

    public OrderItem getOrderItemById(int orderItemId) {

        String sql =
                "SELECT * FROM order_items " +
                "WHERE order_item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderItemId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    OrderItem item =
                            new OrderItem();

                    item.setOrderItemId(
                            rs.getInt("order_item_id")
                    );

                    item.setOrderId(
                            rs.getInt("order_id")
                    );

                    item.setItemId(
                            rs.getInt("item_id")
                    );

                    item.setQuantity(
                            rs.getInt("quantity")
                    );

                    item.setPrice(
                            rs.getDouble("price")
                    );

                    return item;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // GET ITEMS BY ORDER ID
    // =====================================================

    public List<OrderItem> getItemsByOrderId(int orderId) {

        List<OrderItem> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM order_items " +
                "WHERE order_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderId);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    OrderItem item =
                            new OrderItem();

                    item.setOrderItemId(
                            rs.getInt("order_item_id")
                    );

                    item.setOrderId(
                            rs.getInt("order_id")
                    );

                    item.setItemId(
                            rs.getInt("item_id")
                    );

                    item.setQuantity(
                            rs.getInt("quantity")
                    );

                    item.setPrice(
                            rs.getDouble("price")
                    );

                    list.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // GET ALL ORDER ITEMS
    // =====================================================

    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM order_items " +
                "ORDER BY order_item_id DESC";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                OrderItem item =
                        new OrderItem();

                item.setOrderItemId(
                        rs.getInt("order_item_id")
                );

                item.setOrderId(
                        rs.getInt("order_id")
                );

                item.setItemId(
                        rs.getInt("item_id")
                );

                item.setQuantity(
                        rs.getInt("quantity")
                );

                item.setPrice(
                        rs.getDouble("price")
                );

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // UPDATE ORDER ITEM
    // =====================================================

    public boolean updateOrderItem(OrderItem orderItem) {

        String sql =
                "UPDATE order_items " +
                "SET quantity=?, price=? " +
                "WHERE order_item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderItem.getQuantity());
            pst.setDouble(2, orderItem.getPrice());
            pst.setInt(3, orderItem.getOrderItemId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // DELETE ORDER ITEM
    // =====================================================

    public boolean deleteOrderItem(int orderItemId) {

        String sql =
                "DELETE FROM order_items " +
                "WHERE order_item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, orderItemId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // DELETE ALL ITEMS OF AN ORDER
    // =====================================================

    public boolean deleteItemsByOrderId(
            Connection con,
            int orderId) throws SQLException {

        String sql =
                "DELETE FROM order_items " +
                "WHERE order_id=?";

        try (PreparedStatement pst =
                     con.prepareStatement(sql)) {

            pst.setInt(1, orderId);

            pst.executeUpdate();

            return true;
        }
    }
}