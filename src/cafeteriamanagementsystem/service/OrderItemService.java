package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.OrderItem;
import cafeteriamanagementsystem.dao.OrderItemDAO;

import java.util.List;

public class OrderItemService {

    private OrderItemDAO orderItemDAO;

    public OrderItemService() {
        orderItemDAO = new OrderItemDAO();
    }


    // =====================================================
    // ADD ORDER ITEM
    // =====================================================

    public boolean addOrderItem(OrderItem orderItem) {

        if (orderItem == null) {
            return false;
        }

        if (orderItem.getOrderId() <= 0) {
            return false;
        }

        if (orderItem.getItemId() <= 0) {
            return false;
        }

        if (orderItem.getQuantity() <= 0) {
            return false;
        }

        if (orderItem.getPrice() < 0) {
            return false;
        }

        return orderItemDAO.addOrderItem(orderItem);
    }


    // =====================================================
    // GET ORDER ITEM BY ID
    // =====================================================

    public OrderItem getOrderItemById(int orderItemId) {

        if (orderItemId <= 0) {
            return null;
        }

        return orderItemDAO.getOrderItemById(
                orderItemId
        );
    }


    // =====================================================
    // GET ITEMS BY ORDER ID
    // =====================================================

    public List<OrderItem> getItemsByOrderId(
            int orderId) {

        if (orderId <= 0) {
            return null;
        }

        return orderItemDAO.getItemsByOrderId(
                orderId
        );
    }


    // =====================================================
    // GET ALL ORDER ITEMS
    // =====================================================

    public List<OrderItem> getAllOrderItems() {

        return orderItemDAO.getAllOrderItems();
    }


    // =====================================================
    // UPDATE ORDER ITEM
    // =====================================================

    public boolean updateOrderItem(
            OrderItem orderItem) {

        if (orderItem == null) {
            return false;
        }

        if (orderItem.getOrderItemId() <= 0) {
            return false;
        }

        if (orderItem.getQuantity() <= 0) {
            return false;
        }

        if (orderItem.getPrice() < 0) {
            return false;
        }

        return orderItemDAO.updateOrderItem(
                orderItem
        );
    }


    // =====================================================
    // DELETE ORDER ITEM
    // =====================================================

    public boolean deleteOrderItem(
            int orderItemId) {

        if (orderItemId <= 0) {
            return false;
        }

        return orderItemDAO.deleteOrderItem(
                orderItemId
        );
    }
}