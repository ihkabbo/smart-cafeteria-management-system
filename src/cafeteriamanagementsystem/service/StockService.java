package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Stock;
import cafeteriamanagementsystem.dao.StockDAO;

import java.util.ArrayList;

public class StockService {

    private StockDAO stockDAO;

    public StockService() {
        stockDAO = new StockDAO();
    }


    // Add Stock
    public boolean addStock(Stock stock) {

        if(stock.getItemId() <= 0) {
            return false;
        }

        if(stock.getQuantity() < 0) {
            return false;
        }

        return stockDAO.addStock(stock);
    }


    // Update Quantity
    public boolean updateQuantity(int itemId, int quantity) {

        if(itemId <= 0 || quantity < 0) {
            return false;
        }

        return stockDAO.updateQuantity(itemId, quantity);
    }


    // Get All Stock
    public ArrayList<Stock> getAllStock() {

        return stockDAO.getAllStock();
    }
}