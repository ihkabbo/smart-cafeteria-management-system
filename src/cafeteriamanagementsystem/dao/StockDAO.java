package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.Stock;

import java.sql.*;
import java.util.ArrayList;

public class StockDAO {

    // =========================
    // Add Stock
    // =========================

    public boolean addStock(Stock stock) {

        String sql = "INSERT INTO stock(item_id, quantity) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, stock.getItemId());
            pst.setInt(2, stock.getQuantity());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // Update Stock Quantity
    // =========================

    public boolean updateQuantity(int itemId, int quantity) {

        String sql = "UPDATE stock SET quantity=? WHERE item_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, quantity);
            pst.setInt(2, itemId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // Get All Stock
    // =========================

    public ArrayList<Stock> getAllStock() {

        ArrayList<Stock> list = new ArrayList<>();

        String sql = "SELECT * FROM stock";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Stock stock = new Stock();

                stock.setStockId(
                        rs.getInt("stock_id")
                );

                stock.setItemId(
                        rs.getInt("item_id")
                );

                stock.setQuantity(
                        rs.getInt("quantity")
                );

                if (rs.getTimestamp("last_updated") != null) {

                    stock.setLastUpdated(
                            rs.getTimestamp("last_updated")
                                    .toLocalDateTime()
                    );
                }

                list.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}